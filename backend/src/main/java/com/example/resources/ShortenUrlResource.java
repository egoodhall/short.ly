package com.example.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.api.ShortenedUrl;
import com.example.core.UrlShortener;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.concurrent.atomic.AtomicLong;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortenUrlResource {

    @Context
    UriInfo uri;

    @POST
    @Timed
    public ShortenedUrl shorten(String url) {
        String shortenedUrl = uri.getBaseUri() + UrlShortener.shorten(url);
        return new ShortenedUrl(shortenedUrl);
    }
}