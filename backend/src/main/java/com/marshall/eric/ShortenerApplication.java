package com.marshall.eric;

import com.marshall.eric.health.FollowHealthCheck;
import com.marshall.eric.resources.FollowUrlResource;
import com.marshall.eric.resources.MappedUrlDataResource;
import com.marshall.eric.resources.ShortenUrlResource;
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

    }

    @Override
    public void run(ShortenerConfiguration configuration, Environment environment) {

        final ShortenUrlResource shorten = new ShortenUrlResource();
        environment.jersey().register(shorten);

        final FollowUrlResource follow = new FollowUrlResource();
        environment.jersey().register(follow);

        final MappedUrlDataResource mappedData = new MappedUrlDataResource();
        environment.jersey().register(mappedData);

        final FollowHealthCheck followHealthCheck = new FollowHealthCheck(follow);
        environment.healthChecks().register("Follow URL", followHealthCheck);

    }



}
