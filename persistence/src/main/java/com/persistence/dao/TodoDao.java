package com.persistence.dao;

import com.persistence.entity.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


/**
 * Created by alinaaleksandrova on 3/27/17.
 */
@Repository
public class TodoDao {

    final static Logger logger = LoggerFactory.getLogger(TodoDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Todo findTodo(Long id) {
        logger.debug("findTodo");
        return null;
    }

    public Long addTodo(Todo todo) {
        Long savedTodo = (Long) getSession().save(todo);
        return savedTodo;
    }

    public void deleteTodo(Long id) {

    }


}
