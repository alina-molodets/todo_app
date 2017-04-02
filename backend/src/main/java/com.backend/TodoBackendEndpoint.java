package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.persistence.service.TodoService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by alinaaleksandrova on 3/25/17.
 */
@Component
@Path("/todo")
@ComponentScan("com.persistence.service")
public class TodoBackendEndpoint {

    @Autowired
    private TodoService todoService;

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
