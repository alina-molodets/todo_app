package com.persistence.service;


import com.persistence.dao.TodoDao;
import com.persistence.entity.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public void addTodo(Todo todo) {
        logger.debug("addTodo: start");
        todoDao.addTodo(todo);
    }


}
