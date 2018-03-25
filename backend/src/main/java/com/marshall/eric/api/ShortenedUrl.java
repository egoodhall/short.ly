package com.marshall.eric.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.Length;

public class ShortenedUrl {
    private String url;
    private Boolean success;

    public ShortenedUrl() {

    }

    public ShortenedUrl(String url, Boolean success) {
        this.url = url;
        this.success = true;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public Boolean getSuccess() { return success; }
}
