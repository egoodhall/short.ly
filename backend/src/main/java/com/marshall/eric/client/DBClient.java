package com.marshall.eric.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBClient {
    private DBClient() {}

    public static final MongoClient client = new MongoClient("db");
    public static final MongoDatabase db = DBClient.client.getDatabase("shortlyDB");
    public static final MongoCollection mappedURLs = db.getCollection("mappedUrls");
    public static final MongoCollection mapPosition = db.getCollection("mapPosition");
}
