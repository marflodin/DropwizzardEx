package com.marflo.dw.ws.helloworld.configuration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class HelloWorldConfiguration extends Configuration {
    public String environment;
    public String instance;
    public SwaggerBundleConfiguration swagger;

    public HelloWorldConfiguration() {
        swagger = new SwaggerBundleConfiguration();
        swagger.setResourcePackage("com.klarna.risk");
        swagger.setTitle("Risk Variable Serving");
    }
}
