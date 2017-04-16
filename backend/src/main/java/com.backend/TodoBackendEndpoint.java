package com.backend;

import com.persistence.entity.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.persistence.service.TodoService;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.List;

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

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeGetByIdRequest(@PathParam("id") String id) {
        logger.info("executeGetById received for id {}", id);

        Todo todo = todoService.findTodo(Long.valueOf(id));
        return Response.ok(todo).build();
    }


    @GET
    public Response executeGetAllRequest() {
        List<Todo> todos = todoService.findAllTodos();
        logger.info("found Todos: {}", todos);

        return Response.ok(todos, MediaType.APPLICATION_JSON_TYPE).build();
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
        todoService.updateTodo(todo);
        logger.info("execute post request: updates todo {}", todo);
        return Response.ok(todo).contentLocation(createdURI).build();
    }

    @DELETE
    public Response executeDeleteRequest() {
        todoService.deleteAllTodos();
        return Response.ok().build();
    }

    @Path("/{id}")
    @DELETE
    public Response executeDeleteByIdRequest(@PathParam("id")Long id) {
        todoService.deleteTodoById(id);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id")String id, Todo todo) {
        Todo existingTodo = todoService.findTodo(Long.valueOf(id));
        if (existingTodo == null) {
            return Response.noContent().build();
        }

        Todo updatedTodo = todoService.updateTodo(existingTodo, todo);
        return Response.ok(updatedTodo).build();
    }
}
