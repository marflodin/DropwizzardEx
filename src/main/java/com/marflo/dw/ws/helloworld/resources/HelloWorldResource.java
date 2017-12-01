package com.marflo.dw.ws.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.marflo.dw.ws.helloworld.statestore.StateStore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;

import static javax.servlet.http.HttpServletResponse.*;

@Path("/hello-world")
@Api(value = "hello-world",
        description = "Test enpoint")
public class HelloWorldResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);
    private final StateStore stateStore;

    public HelloWorldResource(StateStore stateStore) {
        this.stateStore = stateStore;
    }

    @GET
    @Path("/{key}")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Key found"),
            @ApiResponse(code = SC_NOT_FOUND, message = "Key not found"),
            @ApiResponse(code = SC_SERVICE_UNAVAILABLE, message = "Temporarily unavailable")
    })
    public Response get(@PathParam("key") String key) {
        LOG.info("Received request for key {}", key);
        String value = stateStore.get(key);
        if (value == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(new ValueResponse(value)).build();
    }

    @PUT
    @Path("/{key}")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = SC_ACCEPTED, message = "Variables stored"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An error has occurred"),
            @ApiResponse(code = SC_SERVICE_UNAVAILABLE, message = "Temporarily unavailable")
    })
    public Response get(@PathParam("key") String key, @QueryParam("value") String value) {
        LOG.info("Store value {} for key {}", value, key);
        stateStore.put(key, value);
        return Response.accepted().build();
    }
}
