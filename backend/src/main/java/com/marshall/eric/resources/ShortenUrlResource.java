package com.marshall.eric.resources;

import com.codahale.metrics.annotation.Timed;
import com.marshall.eric.api.ShortenedUrl;
import com.marshall.eric.api.StatusResponse;
import com.marshall.eric.core.UrlShortener;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/shorten")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortenUrlResource {

    @Context
    UriInfo uri;

    public static class UrlToShorten {
        @JsonProperty("url")
        public String url;
        @JsonProperty("uid")
        public String uid;
    }

    @POST
    @Timed
    public StatusResponse shorten(UrlToShorten url) {
        String mappedUrl = UrlShortener.shorten(url.url, url.uid);
        String shortenedUrl = uri.getBaseUri() + mappedUrl;
        if (mappedUrl == null) {
            return new StatusResponse(false, null);
        }
        return new StatusResponse(true, new ShortenedUrl(shortenedUrl));
    }
}