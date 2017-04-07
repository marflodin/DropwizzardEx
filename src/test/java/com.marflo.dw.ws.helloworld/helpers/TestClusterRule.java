package com.marflo.dw.ws.helloworld.helpers;

import com.marflo.dw.ws.helloworld.application.HelloWorldApplication;
import com.marflo.dw.ws.helloworld.configuration.HelloWorldConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.marflo.dw.ws.helloworld.helpers.FreePorts.findRandomFreePort;
import static io.dropwizard.testing.ConfigOverride.config;
import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

public class TestClusterRule implements TestRule {

    private final DropwizardAppRule<HelloWorldConfiguration> app;

    public TestClusterRule() {
        app = createDropwizardAppRule(findRandomFreePort());
    }

    private DropwizardAppRule<HelloWorldConfiguration> createDropwizardAppRule(int appPort) {
        return new DropwizardAppRule<>(HelloWorldApplication.class,
                resourceFilePath("config.yml"),
                config("server.applicationConnectors[0].port", Integer.toString(appPort))
        );
     }

    public String healthCheck() {
        return "http://localhost:" + app.getAdminPort() + "/healthcheck";
    }

    public String hello() {
        return "http://localhost:" + app.getLocalPort() + "/hello-world";
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return RuleChain.outerRule(app).apply(base, description);
    }
}
