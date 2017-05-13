package com.persistence.entity;

import java.io.Serializable;

/**
 * Created by aal.
 */

public class TodoId implements Serializable {

    private Long id;

    public TodoId() {
    }

    public TodoId(Long id) {
        this.id = id;
    }

    public TodoId(String id) {
        this.id = Long.valueOf(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoId todoId = (TodoId) o;

        return id.equals(todoId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "TodoId{" +
                "id=" + id +
                '}';
    }


}
