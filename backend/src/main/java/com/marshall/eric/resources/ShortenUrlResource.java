package com.marshall.eric.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.marshall.eric.api.ShortenedUrl;
import com.marshall.eric.core.UrlShortener;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.Jackson;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Path("/shorten")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortenUrlResource {

    @Context
    UriInfo uri;

    public static class UrlToShorten {
        @JsonProperty("url")
        public String url;
    }

    @POST
    @Timed
    public ShortenedUrl shorten(UrlToShorten url) {
        String mappedUrl = UrlShortener.shorten(url.url, "");
        String shortenedUrl = uri.getBaseUri() + mappedUrl;
        return new ShortenedUrl(shortenedUrl, mappedUrl != null);
    }
}