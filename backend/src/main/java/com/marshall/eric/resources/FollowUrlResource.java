package com.marshall.eric.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marshall.eric.core.UrlShortener;
import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class FollowUrlResource {

    @Context
    private javax.ws.rs.container.ResourceContext rc;

    @GET
    @Timed
    @Path("{channel}")
    public Response follow(@HeaderParam("User-Agent") String userAgent, @PathParam("channel") String channel) {
        String redirect = UrlShortener.lookup(channel);

        // If the URL isn't valid, send a 404
        if (redirect == null) {
            return Response.status(404).build();
        }

        // Build click event
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode data = factory.objectNode();
        data.put("ts", new DateTime().getMillis());
        data.put("userAgent", userAgent);

        // Broadcast event to any listeners
        rc.getResource(EventFeedResource.class).broadcast(channel, data.toString());

        // Send redirect
        return Response.temporaryRedirect(URI.create(redirect)).build();
    }
}