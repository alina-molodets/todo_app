package com.persistence.dao;

import com.persistence.entity.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;


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
        logger.info("find TODO: {}", id);
        Todo todo = (Todo)getSession().get(Todo.class, id);
        logger.debug("findTodo: found todo: {}", todo);
        return todo;
    }

    public List<Todo> findAllTodos() {
        return getSession().createQuery("from Todo").list();
    }

    public Long addTodo(Todo todo) {
        Long savedTodo = (Long) getSession().save(todo);
        return savedTodo;
    }

    public void deleteAllTodos() {

    }

    public void updateTodo(Todo todo) {
        getSession().update(todo);
    }

    public void deleteTodo(Long id) {

    }


}
