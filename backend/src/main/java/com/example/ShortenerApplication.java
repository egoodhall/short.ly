package com.example;

import com.example.health.FollowHealthCheck;
import com.example.resources.FollowUrlResource;
import com.example.resources.ShortenUrlResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // nothing to do yet
    }

    @Override
    public void run(ShortenerConfiguration configuration, Environment environment) {

        final ShortenUrlResource shorten = new ShortenUrlResource();
        environment.jersey().register(shorten);

        final FollowUrlResource follow = new FollowUrlResource();
        environment.jersey().register(follow);

        final FollowHealthCheck followHealthCheck = new FollowHealthCheck(follow);
        environment.healthChecks().register("Follow URL", followHealthCheck);

    }



}
