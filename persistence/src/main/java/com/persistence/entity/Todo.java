package com.persistence.entity;

import javax.persistence.*;

/**
 * Created by alinaaleksandrova on 3/26/17.
 */
@Entity(name = "Todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private boolean completed;
    @Column
    private String url;
    @Column(name = "placement")
    private Long order;

    public Todo() {
    }

    public Todo(Long id, String title, boolean completed, String url, Long order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.url = url;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (completed != todo.completed) return false;
        if (!id.equals(todo.id)) return false;
        if (!title.equals(todo.title)) return false;
        if (url != null ? !url.equals(todo.url) : todo.url != null) return false;
        return order != null ? order.equals(todo.order) : todo.order == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", url='" + url + '\'' +
                ", order=" + order +
                '}';
    }

    public void update(Todo newTodo) {
        if (newTodo.getTitle() != null) {
            this.title = newTodo.getTitle();
        }
        if (this.isCompleted() != newTodo.isCompleted()) {
            this.completed = newTodo.isCompleted();
        }
        if (newTodo.getOrder() != null && this.getOrder() != newTodo.getOrder()) {
            this.order = newTodo.getOrder();
        }

    }
}
