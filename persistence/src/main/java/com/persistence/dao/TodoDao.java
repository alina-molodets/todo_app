package com.persistence.dao;

import com.persistence.entity.Todo;
import com.persistence.entity.TodoId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by alinaaleksandrova on 3/27/17.
 */
@Repository
public class TodoDao {

    private final static Logger logger = LoggerFactory.getLogger(TodoDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Todo findTodo(TodoId id) {
        logger.info("find TODO: {}", id);
        Todo todo = getSession().get(Todo.class, id);
        logger.debug("findTodo: found todo: {}", todo);
        return todo;
    }

    public List<Todo> findAllTodos() {
        return getSession().createQuery("from Todo").list();
    }

    public TodoId addTodo(Todo todo) {
        return (TodoId) getSession().save(todo);
    }

    public int deleteAllTodos() {
        return getSession().createQuery("delete from Todo").executeUpdate();
    }

    public void updateTodo(Todo todo) {
        getSession().update(todo);
    }

    public void deleteTodo(TodoId id) {
        Todo todo  = (Todo)getSession().createCriteria(Todo.class)
                .add(Restrictions.eq("id", id.getId()))
                .uniqueResult();

        getSession().delete(todo);
    }


}
