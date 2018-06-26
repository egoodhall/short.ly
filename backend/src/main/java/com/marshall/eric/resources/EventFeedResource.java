package com.marshall.eric.resources;

import com.marshall.eric.api.Broadcastable;
import com.marshall.eric.core.SseCoordinator;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("events")
public class EventFeedResource implements Broadcastable {

    SseCoordinator broadcasters;

    public EventFeedResource() {
        broadcasters = new SseCoordinator();
    }

    @GET
    @Path("/{channel}")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput subscribe(@PathParam("channel") String channel, @Context HttpServletRequest request) {
        System.out.println(String.format("Adding subscription from %s", request.getRemoteAddr()));
        return broadcasters.subscribeTo(channel);
    }

    @Override
    public void broadcast(String channel, String data) {
        System.out.println(data);
        OutboundEvent event = new OutboundEvent.Builder()
                .name("click")
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .data(String.class, data)
                .build();
        broadcasters.broadcast(channel, event);
    }
}
