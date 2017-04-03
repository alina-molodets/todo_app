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
        logger.debug("findTodo: start");
        return todoDao.findTodo(id);
    }

    public List<Todo> findAllTodos() {
        return todoDao.findAllTodos();
    }

    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    @Transactional(readOnly = false)
    public Long addTodo(Todo todo) {
        logger.debug("addTodo: start");
        Long addedTodo = todoDao.addTodo(todo);
        logger.info("addTodo: todo {} was saved", addedTodo);
        return addedTodo;
    }


}
