package com.persistence.entity;

/**
 * Created by alinaaleksandrova on 4/16/17.
 */
public class TodoBuilder {

    private Long id;
    private String title;
    private boolean completed;
    private String url;
    private Long order;

    public TodoBuilder setId(Long newId) {
        this.id = newId;
        return this;
    }

    public TodoBuilder setTitle(String newTitle) {
        this.title = newTitle;
        return this;
    }

    public TodoBuilder setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public TodoBuilder setUrl(String newUrl) {
        this.url = newUrl;
        return this;
    }

    public TodoBuilder setOrder(Long newOrder) {
        this.order = newOrder;
        return this;
    }

    public Todo createTodo() {
        return new Todo(id, title, completed, url, order);
    }
}
