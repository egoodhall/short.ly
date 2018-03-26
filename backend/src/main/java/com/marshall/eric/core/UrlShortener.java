package com.marshall.eric.core;

import com.google.common.collect.HashBiMap;
import com.marshall.eric.client.DBClient;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.jndi.MongoClientFactory;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class UrlShortener {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static String shorten(String url, String uid) {
        if (url != null) {
            String key = randomString(6);
            while (DBClient.mappedURLs.find(eq("mappedUrl", key)).first() != null) {
                key = randomString(6);
            }
            DBClient.mappedURLs.insertOne(
                new Document("mappedUrl", key)
                    .append("destUrl", url)
                    .append("timestamp", System.currentTimeMillis())
                    .append("uid", uid)
                    .append("clicks", 0)
            );
            return key;
        }
        return null;
    }

    public static String lookup(String path) {
        Document lookup = (Document) DBClient.mappedURLs.findOneAndUpdate(eq("mappedUrl", path), inc("count", 1));
        return lookup == null ? null : lookup.getString("destUrl");
    }
}
