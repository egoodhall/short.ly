package com.marshall.eric.api;

import org.bson.Document;

public interface Broadcastable {
    public void broadcast(String channel, String data);
}
