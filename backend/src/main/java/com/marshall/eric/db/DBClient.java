package com.marshall.eric.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBClient {
    private DBClient() {}

    public static final MongoClient client = new MongoClient("db");
    public static final MongoDatabase db = DBClient.client.getDatabase("crisco");
    public static final MongoCollection mappedURLs = db.getCollection("mappedUrls");
    public static final MongoCollection mapPosition = db.getCollection("mapPosition");
}
