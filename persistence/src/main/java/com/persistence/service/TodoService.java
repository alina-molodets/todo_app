package com.persistence.service;


import com.persistence.dao.TodoDao;
import com.persistence.entity.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by alinaaleksandrova on 4/1/17.
 */
@Service
@Transactional(readOnly = true)
public class TodoService {

    final static Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    TodoDao todoDao;

    public Todo findTodo(Long id) {
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
    public void deleteTodoById(Long id) {
        todoDao.deleteTodo(id);
    }

    @Transactional(readOnly = false)
    public Long addTodo(Todo todo) {
        Long addedTodo = todoDao.addTodo(todo);
        logger.info("addTodo: todo {} was saved", addedTodo);
        return addedTodo;
    }


    @Transactional(readOnly = false)
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }
}
