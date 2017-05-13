package com.persistence.service;

import com.persistence.dao.TodoDao;
import com.persistence.entity.Todo;
import com.persistence.entity.TodoBuilder;
import com.persistence.entity.TodoId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aal
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    private static final TodoId ANY_ID = new TodoId(1L);
    private static final String ANY_TODO_TITLE = "todoTitle";
    private static final String NEW_TODO_TITLE = "newTodoTitle";

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoDao todoDao;

    @Test
    public void shouldFindTodoById() {

        Todo existingTodo = new TodoBuilder().setId(ANY_ID).setTitle(ANY_TODO_TITLE).createTodo();
        when(todoDao.findTodo(eq(ANY_ID))).thenReturn(existingTodo);

        Todo foundTodo = todoService.findTodo(ANY_ID);

        Assert.assertNotNull(foundTodo);
        Assert.assertEquals(existingTodo, foundTodo);
        verify(todoDao).findTodo(eq(ANY_ID));
    }

    @Test
    public void shouldFindAllTodos() {

        Todo existingTodo = new TodoBuilder().setId(ANY_ID).setTitle(ANY_TODO_TITLE).createTodo();
        when(todoDao.findAllTodos()).thenReturn(Arrays.asList(existingTodo));

        List<Todo> foundTodos = todoService.findAllTodos();

        Assert.assertNotNull(foundTodos);
        Assert.assertEquals(Arrays.asList(existingTodo), foundTodos);
        verify(todoDao).findAllTodos();
    }

    @Test
    public void shouldMergeTodos() {
        Todo existingTodo = new TodoBuilder().setId(ANY_ID).setTitle(ANY_TODO_TITLE).createTodo();
        Todo newTodo = new TodoBuilder().setTitle(NEW_TODO_TITLE).createTodo();
        Todo expectedMergedTodo = new TodoBuilder().setId(ANY_ID).setTitle(NEW_TODO_TITLE).createTodo();

        Todo mergedTodo = todoService.mergeTodos(existingTodo, newTodo);

        verify(todoDao).updateTodo(expectedMergedTodo);
        Assert.assertEquals(expectedMergedTodo, mergedTodo);
    }

    @Test
    public void shouldDeleteAllTodos() {
        todoService.deleteAllTodos();

        verify(todoDao).deleteAllTodos();
    }

    @Test
    public void shouldDeleteTodoById() {
        todoService.deleteTodoById(ANY_ID);

        verify(todoDao).deleteTodo(ANY_ID);
    }

    @Test
    public void shouldAddTodo() {
        Todo todo = new TodoBuilder().setTitle(ANY_TODO_TITLE).createTodo();
        when(todoDao.addTodo(eq(todo))).thenReturn(ANY_ID);

        TodoId addedId = todoService.addTodo(todo);

        verify(todoDao).addTodo(eq(todo));
        Assert.assertEquals(ANY_ID, addedId);
    }

    @Test
    public void shouldUpdateTodo() {

        Todo todo = new TodoBuilder().setId(ANY_ID).setTitle(ANY_TODO_TITLE).createTodo();

        todoService.updateTodo(todo);

        verify(todoDao).updateTodo(eq(todo));
    }

}
