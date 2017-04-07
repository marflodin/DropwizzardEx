package com.marflo.dw.ws.helloworld.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok().entity("{\"message\" : \"hello world\"}").build();
    }
}
