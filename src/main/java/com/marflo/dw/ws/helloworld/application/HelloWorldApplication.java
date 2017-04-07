package com.marflo.dw.ws.helloworld.application;

import com.marflo.dw.ws.helloworld.configuration.HelloWorldConfiguration;
import com.marflo.dw.ws.helloworld.healthcheck.TemplateHealthCheck;
import com.marflo.dw.ws.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {

        environment.healthChecks().register("template", new TemplateHealthCheck());
        environment.jersey().register(new HelloWorldResource());
    }

    public static void main(String... args) throws Exception {
        new HelloWorldApplication().run(args);
    }
}
