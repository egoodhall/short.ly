package com.marshall.eric.resources;

import com.codahale.metrics.annotation.Timed;
import com.marshall.eric.api.MappedUrlData;
import com.marshall.eric.api.StatusResponse;
import com.marshall.eric.db.DBClient;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/links")
public class MappedUrlDataResource {

    public MappedUrlDataResource(){}

    @GET
    @Timed
    @Path("{uid}")
    public StatusResponse follow(@PathParam("uid") String uid) {
        MongoCursor<Document> cursor = DBClient.mappedURLs.find(eq("uid", uid)).iterator();
        ArrayList<MappedUrlData> mappedUrlList = new ArrayList<>();
        while (cursor.hasNext()) {
            Document mappedUrlData = cursor.next();
            mappedUrlList.add(new MappedUrlData(
                    mappedUrlData.getString("destUrl"),
                    mappedUrlData.getString("mappedUrl"),
                    mappedUrlData.getLong("timestamp"),
                    mappedUrlData.getInteger("clicks")
            ));
        }
        return new StatusResponse(true, mappedUrlList);
    }
}
