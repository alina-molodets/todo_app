package com.backend;

import com.persistence.entity.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.persistence.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by alinaaleksandrova on 3/25/17.
 */
@Component
@Path("/todo")
@ComponentScan("com.persistence.service")
public class TodoBackendEndpoint {

    Logger logger = LoggerFactory.getLogger(TodoBackendEndpoint.class);

    @Autowired
    private TodoService todoService;

    @Context
    private UriInfo uriInfo;

    @GET
    public String executeGetRequest() {
        return "test";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response executeGetByIdRequest(@PathParam("id") String id) {
          return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response executePostRequest(Todo todo) {
        logger.info("execute post request: received todo {}", todo);
        todo.setCompleted(false);
        Long id = todoService.addTodo(todo);
        URI createdURI =  uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
        todo.setId(id);
        todo.setUrl(createdURI.toString());
        return Response.ok(todo).contentLocation(createdURI).build();
    }

    @DELETE
    public String executeDeleteRequest() {
        return "delete";
    }
}
