package com.marflo.dw.ws.helloworld;

import com.marflo.dw.ws.helloworld.helpers.TestClusterRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.valid4j.matchers.http.HttpResponseMatchers.hasEntity;
import static org.valid4j.matchers.http.HttpResponseMatchers.hasStatus;

public class ApplicationTest {
    @ClassRule
    public static final TestClusterRule APP = new TestClusterRule();

    private final Client client = ClientBuilder.newClient();

    @Test
    public void shouldRespondHelloOnRequest() {
        final Response response = client
                .target(APP.hello())
                .request().get();
        assertThat(response, hasStatus(Response.Status.OK));
        assertThat(response, hasEntity(String.class, hasJsonPath("$.message", equalTo("hello world"))));
    }
}
