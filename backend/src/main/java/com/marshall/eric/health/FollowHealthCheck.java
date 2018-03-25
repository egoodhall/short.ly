package com.marshall.eric.health;

import com.codahale.metrics.health.HealthCheck;
import com.marshall.eric.resources.FollowUrlResource;

import javax.ws.rs.core.Response;

public class FollowHealthCheck extends HealthCheck {
    private final FollowUrlResource resource;

    public FollowHealthCheck(FollowUrlResource res) {
        this.resource = res;
    }

    @Override
    protected Result check() throws Exception {
        Response res = resource.follow("FollowHealthCheck");
        if (res.getStatus() != 404) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}