package com.marflo.dw.ws.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.marflo.dw.ws.helloworld.application.HelloWorldApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        LOG.info("Received request");
        return Response.ok().entity("{\"message\" : \"hello world\"}").build();
    }
}
