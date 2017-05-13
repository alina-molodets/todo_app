package com.backend;

import com.persistence.entity.Todo;
import com.persistence.entity.TodoBuilder;
import com.persistence.entity.TodoId;
import com.persistence.service.TodoService;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alinaaleksandrova on 4/17/17.
 */
@RunWith(MockitoJUnitRunner.class)      //this annotation doesn` work
public class TodoBackendEndpointTest extends JerseyTest {

    public static final TodoId SOME_TODO_ID = new TodoId(1L);
    public static final String SOME_TODO_TITLE = "some title";
    private TodoService service;

    @Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        service = Mockito.mock(TodoService.class);
        return new JerseyConfig(service)
                .property("contextConfig", context);
    }

    @Test
    public void shouldRespondToDeleteRequestAndDeleteAllTodos() {
        Response output = target("/todo").request().delete();

        assertEquals(200, output.getStatus());
        verify(service).deleteAllTodos();
        assertCorsHeaders(output.getHeaders());
    }

    @Test
    public void shouldRespondToGetRequestAndReturnTodo() {
        Todo todo = new TodoBuilder().setId(SOME_TODO_ID).setTitle(SOME_TODO_TITLE).createTodo();
        when(service.findTodo(SOME_TODO_ID)).thenReturn(todo);

        Response output = target("/todo/" + SOME_TODO_ID).request().get();

        assertEquals(200, output.getStatus());
        assertEquals(todo, output.readEntity(Todo.class));
        assertCorsHeaders(output.getHeaders());

    }

    @Test
    public void shouldRespondToGetRequestAndReturnAllTodos() {
        Todo todo = new TodoBuilder().setId(SOME_TODO_ID).setTitle(SOME_TODO_TITLE).createTodo();
        when(service.findAllTodos()).thenReturn(Arrays.asList(todo));

        Response output = target("/todo").request().get();

        assertEquals(200, output.getStatus());
        assertEquals(Arrays.asList(todo), output.readEntity(new GenericType<List<Todo>>() {}));
        assertCorsHeaders(output.getHeaders());
    }

    @Test
    public void shouldRespondToPostRequest() {
        Todo todo = new TodoBuilder().setId(SOME_TODO_ID).setTitle(SOME_TODO_TITLE).createTodo();
        when(service.addTodo(todo)).thenReturn(SOME_TODO_ID);
        todo.setUrl(getBaseUri() + "todo/" + SOME_TODO_ID);

        Response output = target("/todo").request().post(Entity.json(todo));

        assertEquals(200, output.getStatus());
        assertEquals(todo, output.readEntity(Todo.class));
        assertCorsHeaders(output.getHeaders());

        //TODO: how to assert contentLocation ???
    }

    @Test
    public void shouldRespondToDeleteRequestAndDeleteTodo() {

        Response output = target("/todo/" + SOME_TODO_ID).request().delete();

        assertEquals(200, output.getStatus());
        verify(service).deleteTodoById(SOME_TODO_ID);
        assertCorsHeaders(output.getHeaders());
    }

    @Ignore
    @Test
    public void shouldNotFindTodoByPatching() {

        Todo todo = new TodoBuilder().setId(SOME_TODO_ID).setTitle(SOME_TODO_TITLE).createTodo();

        Response output = target("/todo/" + SOME_TODO_ID).request().method("patch");

        assertEquals(204, output.getStatus());
        assertCorsHeaders(output.getHeaders());
    }

    public void assertCorsHeaders(MultivaluedMap<String, Object> headers) {

        assertNotNull(headers.getFirst("Access-Control-Allow-Origin"));
        assertNotNull(headers.getFirst("Access-Control-Allow-Headers"));
        assertNotNull(headers.getFirst("Access-Control-Allow-Credentials"));


    }

}
