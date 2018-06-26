package com.marshall.eric;

import com.marshall.eric.health.FollowHealthCheck;
import com.marshall.eric.resources.EventFeedResource;
import com.marshall.eric.resources.FollowUrlResource;
import com.marshall.eric.resources.MappedUrlDataResource;
import com.marshall.eric.resources.ShortenUrlResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ShortenerApplication extends Application<ShortenerConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShortenerApplication().run(args);
    }

    @Override
    public String getName() {
        return "shortly";
    }

    @Override
    public void initialize(Bootstrap<ShortenerConfiguration> bootstrap) {

    }

    @Override
    public void run(ShortenerConfiguration configuration, Environment environment) {

        // Register shortener endpoint (JSON -> JSON)
        final ShortenUrlResource shorten = new ShortenUrlResource();
        environment.jersey().register(shorten);

        environment.jersey().register(EventFeedResource.class);

        // Register mapping endpoint (GET -> REDIRECT)
        final FollowUrlResource follow = new FollowUrlResource();
        environment.jersey().register(follow);

        // Get all links for a given UID (GET -> JSON)
        final MappedUrlDataResource mappedData = new MappedUrlDataResource();
        environment.jersey().register(mappedData);

        final FollowHealthCheck followHealthCheck = new FollowHealthCheck(follow);
        environment.healthChecks().register("Follow URL", followHealthCheck);

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }



}
