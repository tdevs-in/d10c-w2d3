package com.tdevs.w2d1;

import java.util.UUID;

public class TodoItem {
    private final String id;
    private final String description;
    private boolean done;

    public TodoItem(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.done = false;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}