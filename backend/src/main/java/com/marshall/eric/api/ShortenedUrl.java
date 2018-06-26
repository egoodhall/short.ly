package com.marshall.eric.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("shortenedUrl")
public class ShortenedUrl {
    private String url;

    public ShortenedUrl() {}

    public ShortenedUrl(String url) {
        this.url = url;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
}
