package com.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class ShortenedUrl {
    private String url;

    public ShortenedUrl() {

    }

    public ShortenedUrl(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }
}
