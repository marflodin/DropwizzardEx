package com.marflo.dw.ws.helloworld.application;

import com.marflo.dw.ws.helloworld.configuration.HelloWorldConfiguration;
import com.marflo.dw.ws.helloworld.healthcheck.TemplateHealthCheck;
import com.marflo.dw.ws.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldApplication.class);

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        initializeSwagger(bootstrap);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        environment.healthChecks().register("template", new TemplateHealthCheck());
        environment.jersey().register(new HelloWorldResource());
    }

    private void initializeSwagger(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<HelloWorldConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorldConfiguration configuration) {
                return configuration.swagger;
            }
        });
    }

    public static void main(String... args) throws Exception {
        LOG.info("Application starting");
        new HelloWorldApplication().run(args);
    }
}
