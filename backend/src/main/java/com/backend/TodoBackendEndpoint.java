package com.backend;

import com.persistence.entity.Todo;
import com.persistence.entity.TodoId;
import com.persistence.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by alinaaleksandrova on 3/25/17.
 */
@Component
@Path("/todo")
@ComponentScan("com.persistence.service")
public class TodoBackendEndpoint {

    private final static Logger logger = LoggerFactory.getLogger(TodoBackendEndpoint.class);

    @Inject
    private TodoService todoService;

    @Context
    private UriInfo uriInfo;



    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeGetByIdRequest(@PathParam("id") Long id) {
        logger.info("executeGetById received for id {}", id);

        Todo todo = todoService.findTodo(new TodoId(id));
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
        TodoId id = todoService.addTodo(todo);
        URI createdURI =  uriInfo.getAbsolutePathBuilder().path(String.valueOf(id.getId())).build();
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
        todoService.deleteTodoById(new TodoId(id));
        return Response.ok().build();
    }

    @Path("/{id}")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id")Long id, Todo todo) {
        Todo existingTodo = todoService.findTodo(new TodoId(id));
        if (existingTodo == null) {
            return Response.noContent().build();
        }

        Todo updatedTodo = todoService.mergeTodos(existingTodo, todo);
        return Response.ok(updatedTodo).build();
    }
}
