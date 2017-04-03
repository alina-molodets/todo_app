package com.persistence.entity;

import javax.persistence.*;

/**
 * Created by alinaaleksandrova on 3/26/17.
 */
@Entity(name = "Todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private boolean completed;
    @Column
    private String url;


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

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (completed != todo.completed) return false;
        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;
        return url != null ? url.equals(todo.url) : todo.url == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
