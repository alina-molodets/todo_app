package com.persistence.service;


import com.persistence.dao.TodoDao;
import com.persistence.entity.Todo;
import com.persistence.entity.TodoId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;


/**
 * Created by alinaaleksandrova on 4/1/17.
 */
@Service
@Transactional(readOnly = true)
public class TodoService {

    private final static Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    TodoDao todoDao;

    public Todo findTodo(TodoId id) {
        return todoDao.findTodo(id);
    }

    public List<Todo> findAllTodos() {
        return todoDao.findAllTodos();
    }

    @Transactional(readOnly = false)
    public Todo mergeTodos(Todo existingTodo, Todo newTodo) {
        existingTodo.update(newTodo);
        updateTodo(existingTodo);
        return existingTodo;
    }


    @Transactional(readOnly = false)
    public void deleteAllTodos() {
        todoDao.deleteAllTodos();
    }

    @Transactional(readOnly = false)
    public void deleteTodoById(TodoId id) {
        todoDao.deleteTodo(id);
    }

    @Transactional(readOnly = false)
    public TodoId addTodo(Todo todo) {
        TodoId addedTodoId = todoDao.addTodo(todo);
        logger.info("addTodo: todo with id {} was saved", addedTodoId.getId());
        return addedTodoId;
    }


    @Transactional(readOnly = false)
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

}
