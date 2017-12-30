package com.example.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.core.UrlShortener;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@Path("/")
public class FollowUrlResource {
    private final AtomicLong counter;

    public FollowUrlResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Path("{hash}")
    public Response follow(@PathParam("hash") String hash) {
        String redirect = UrlShortener.lookup(hash);
        if (redirect == null) {
            return Response.status(404).build();
        }
        return Response.temporaryRedirect(URI.create(redirect)).build();
    }
}