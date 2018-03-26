package com.marshall.eric.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("mappedUrlData")
public class MappedUrlData {
    private String destUrl;
    private String mappedUrl;
    private Integer clicks;
    private Long timestamp;

    public MappedUrlData() {}

    public MappedUrlData(String destUrl, String mappedUrl, Long timestamp, Integer clicks) {
        this.destUrl = destUrl;
        this.mappedUrl = mappedUrl;
        this.clicks = clicks;
        this.timestamp = timestamp;
    }

    @JsonProperty("destUrl")
    public String getDestUrl() {
        return destUrl;
    }

    @JsonProperty("mappedUrl")
    public String getMappedUrl() {
        return mappedUrl;
    }

    @JsonProperty("clicks")
    public Integer getClicks() {
        return clicks;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }
}
