package com.marshall.eric.core;

import com.marshall.eric.db.DBClient;
import org.bson.Document;

import java.security.SecureRandom;

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

    /**
     * Shorten a URL for the user with the given UID
     * @param url The URL to shorten
     * @param uid The UID of the user requesting the shortened URL
     */
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

    /**
     * Look up a URL in the database, and return its destination URL
     * @param path The shortened URL extension to follow
     */
    public static String lookup(String path) {
        Document lookup = (Document) DBClient.mappedURLs.findOneAndUpdate(eq("mappedUrl", path), inc("clicks", 1));
        return lookup == null ? null : lookup.getString("destUrl");
    }
}
