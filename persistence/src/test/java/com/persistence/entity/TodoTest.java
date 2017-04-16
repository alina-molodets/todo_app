package com.persistence.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alinaaleksandrova on 4/16/17.
 */
public class TodoTest {

    private static final String SOME_TODO_TITLE = "Todo title";
    private static final String NEW_TODO_TITLE = "New title";
    private static final Long SOME_TODO_ORDER = 1l;
    private static final Long NEW_TODO_ORDER = 2l;

    @Test
    public void shouldChangeTitleOfTodoTest() {
        Todo existingTodo = new TodoBuilder().setTitle(SOME_TODO_TITLE).createTodo();
        Todo newTodo = new TodoBuilder().setTitle(NEW_TODO_TITLE).createTodo();

        existingTodo.update(newTodo);

        Assert.assertEquals(NEW_TODO_TITLE, existingTodo.getTitle());
    }

    @Test
    public void shouldChangeCompletedStateOfTodoTest() {
        Todo existingTodo = new TodoBuilder().setCompleted(false).createTodo();
        Todo newTodo = new TodoBuilder().setCompleted(true).createTodo();

        existingTodo.update(newTodo);

        Assert.assertEquals(newTodo.isCompleted(), existingTodo.isCompleted());
    }

    @Test
    public void shouldChangeTodoOrderTest() {
        Todo existingTodo = new TodoBuilder().setOrder(SOME_TODO_ORDER).createTodo();
        Todo newTodo = new TodoBuilder().setOrder(NEW_TODO_ORDER).createTodo();

        existingTodo.update(newTodo);

        Assert.assertEquals(NEW_TODO_ORDER, existingTodo.getOrder());
    }
}
