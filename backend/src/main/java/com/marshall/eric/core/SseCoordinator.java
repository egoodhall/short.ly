package com.marshall.eric.core;

import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coordinate multiple channels of SSE streams that allow for
 * connections to multiple clients
 */
public class SseCoordinator {

    private ConcurrentHashMap<String, SseBroadcaster> broadcasters;

    public SseCoordinator() {
        broadcasters = new ConcurrentHashMap<>();
    }

    /**
     * Create an EventOutput for the given route, then save it into the
     * map, and return it
     *
     * @param route The route (shortened URL path) to subscribe to
     * @return The EventOutput used for sending generated messages
     */
    public EventOutput subscribeTo(String route) {
        // Create new ConcurrentSkipListSet if none is found already
        broadcasters.putIfAbsent(route, new SseBroadcaster());

        return broadcasters.get(route).subscribe();
    }

    /**
     * Broadcast a server-sent event on a given channel,
     * if there are any active listeners on it.
     *
     * @param channel The channel (shortened URL path) to publish on
     * @param event   The event to be published
     */
    public void broadcast(String channel, OutboundEvent event) {

        // Send to all connections
        if (broadcasters.containsKey(channel)) {
            // Get the channel's list of connections
            broadcasters.get(channel).broadcast(event);
            this.prune(channel);
        }
    }

    /**
     * Prunes the specified channel, removing it if there are 0 active listeners on it
     *
     * @param channel The channel to check/prune
     */
    private void prune(String channel) {
        if (broadcasters.containsKey(channel) && broadcasters.get(channel).size() == 0) {
            broadcasters.remove(channel);
        }
    }

    /**
     * Track multiple connections (EventOutput) for a given channel,
     * allowing for broadcasting to multiple clients
     */
    private class SseBroadcaster {
        Set<EventOutput> connections = Sets.newSetFromMap(new ConcurrentHashMap<EventOutput, Boolean>());

        /**
         * Create an event output, add it to the collection, and return it
         *
         * @return The event output for the given connection
         */
        EventOutput subscribe() {
            EventOutput eventOutput = new EventOutput();
            connections.add(eventOutput);
            return eventOutput;
        }

        /**
         * Broadcast to all active listeners tracked by the SseBroadcaster
         *
         * @param event The event to broadcast
         */
        void broadcast(OutboundEvent event) {
            connections.forEach((EventOutput connection) -> {
                // If connection is closed, remove it
                try {
                    connection.write(event);
                } catch (IOException ex) {
                    // If error, close and remove the connection
                    try {
                        connection.close();
                        connections.remove(connection);
                    } catch (IOException ex2) {
                        System.out.println(ex2.getMessage());
                    }
                    System.out.println(ex.getMessage());
                }
            });
        }

        /**
         * Get the number of clients being managed by the
         * SseBroadcaster
         *
         * @return The nubmer of active listeners
         */
        int size() {
            return connections.size();
        }
    }
}
