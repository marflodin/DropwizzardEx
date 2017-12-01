package com.marflo.dw.ws.helloworld.application;

import com.bendb.dropwizard.redis.JedisBundle;
import com.bendb.dropwizard.redis.JedisFactory;
import com.marflo.dw.ws.helloworld.configuration.HelloWorldConfiguration;
import com.marflo.dw.ws.helloworld.healthcheck.TemplateHealthCheck;
import com.marflo.dw.ws.helloworld.resources.HelloWorldResource;
import com.marflo.dw.ws.helloworld.statestore.StateStore;
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
        bootstrap.addBundle(new JedisBundle<HelloWorldConfiguration>() {
            @Override
            public JedisFactory getJedisFactory(HelloWorldConfiguration configuration) {
                return configuration.redis;
            }
        });
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        StateStore stateStore = new StateStore(configuration.redis, environment);
        environment.healthChecks().register("template", new TemplateHealthCheck());
        environment.jersey().register(new HelloWorldResource(stateStore));
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
