package com.marflo.dw.ws.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

@Path("/hello-world")
@Api(value = "hello-world",
        description = "Test enpoint")
public class HelloWorldResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Variables collected"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An error has occurred"),
            @ApiResponse(code = SC_SERVICE_UNAVAILABLE, message = "Temporarily unavailable")
    })
    public Response get() {
        LOG.info("Received request");
        return Response.ok().entity("{\"message\" : \"hello world\"}").build();
    }
}
