package com.marflo.dw.ws.helloworld;

import com.marflo.dw.ws.helloworld.helpers.TestClusterRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.Assert.assertThat;
import static org.valid4j.matchers.http.HttpResponseMatchers.hasStatus;

public class HealthChecksTest {

    @ClassRule
    public static final TestClusterRule APP = new TestClusterRule();

    private final Client client = ClientBuilder.newClient();

    @Test
    public void shouldBeHealthy() {
        final Response response = client
                .target(APP.healthCheck())
                .request().get();
        assertThat(response, hasStatus(Status.OK));
    }
}
