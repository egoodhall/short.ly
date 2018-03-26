package com.marshall.eric.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusResponse {
    private Boolean success;
    private Object data;

    public StatusResponse() {}

    public StatusResponse(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    @JsonProperty("data")
    public Object getData() { return data; }

    @JsonProperty("success")
    public Boolean getSuccess() { return success; }
}
