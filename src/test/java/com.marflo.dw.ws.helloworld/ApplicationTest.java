package com.marflo.dw.ws.helloworld;

import com.marflo.dw.ws.helloworld.helpers.TestClusterRule;
import com.marflo.dw.ws.helloworld.resources.ValueResponse;
import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
    public void shouldRespondNotFoundForUnknownKeys() {
        final Response response = client
                .target(APP.hello())
                .path("test")
                .request().get();
        assertThat(response, hasStatus(Response.Status.NOT_FOUND));
    }

    @Test
    public void shouldRespondWithKnownKeys() {
        String key = "key";
        String value = "value";
        final Response responsePut = client
                .target(APP.hello())
                .path(key)
                .request().put(Entity.json(value));
        assertThat(responsePut, hasStatus(Response.Status.ACCEPTED));

        final Response response = client
                .target(APP.hello())
                .path(key)
                .request().get();
        assertThat(response, hasStatus(Response.Status.OK));
        assertThat(response, hasEntity(String.class, hasJsonPath("$.value", Matchers.equalTo(value))));
    }
}
