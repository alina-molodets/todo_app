package com.backend;

import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by alinaaleksandrova on 3/25/17.
 */
@Component
@Path("/todo")
public class TodoBackendEndpoint {

    @GET
    public String executeGetRequest() {
        return "test";
    }

    @POST
    public String executePostRequest() {
        return "post";
    }

    @DELETE
    public String executeDeleteRequest() {
        return "delete";
    }
}
