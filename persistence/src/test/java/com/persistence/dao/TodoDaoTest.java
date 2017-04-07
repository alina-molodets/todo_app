package com.persistence.dao;

import com.persistence.config.HibernateConfig;
import com.persistence.entity.Todo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Created by alinaaleksandrova on 4/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, TodoDao.class})
@SqlGroup({
@Sql(scripts = "classpath:beforeTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
@Sql(scripts = "classpath:afterTest.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@Transactional
public class TodoDaoTest {

    private static final String EXISTING_TODO_TITLE = "firstTestTodo";

    @Autowired
    private TodoDao todoDao;



    @Test
    public void shouldAddTodo() {
        Todo todo = createTestTodo();

        Long id = todoDao.addTodo(todo);

        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void shouldFindAddedTodo() {

        Todo foundTodo = todoDao.findTodo(1l);

        Assert.assertNotNull(foundTodo);
        Assert.assertEquals(EXISTING_TODO_TITLE, foundTodo.getTitle());

    }

    @Test
    public void shouldUpdateTodo() {
        Todo existingTodo = getExistingTodo();
        existingTodo.setCompleted(true);

        todoDao.updateTodo(existingTodo);
        Todo updatedTodo = todoDao.findTodo(existingTodo.getId());

        Assert.assertEquals(updatedTodo, existingTodo);
    }

    @Test
    public void shouldFindAllTodos() {
        todoDao.addTodo(createTestTodo());
        List<Todo> foundTodos = todoDao.findAllTodos();

        Assert.assertEquals(2, foundTodos.size());
    }

    private Todo createTestTodo() {
        Todo todo = new Todo();
        todo.setTitle("testTodo");
        return todo;
    }

    private Todo getExistingTodo() {
        Todo todo = new Todo();
        todo.setTitle(EXISTING_TODO_TITLE);
        todo.setId(1L);
        return todo;
    }


}
