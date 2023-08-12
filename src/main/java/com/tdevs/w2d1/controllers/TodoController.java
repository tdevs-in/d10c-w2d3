package com.tdevs.w2d1.controllers;

import com.tdevs.w2d1.TodoItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private List<TodoItem> todoList = new ArrayList<>();

    @PostMapping
    public TodoItem addTodo(@RequestBody String description) {
        TodoItem todoItem = new TodoItem(description);
        todoList.add(todoItem);
        return todoItem;
    }

    @GetMapping
    public List<TodoItem> getTodos() {
        return todoList;
    }

    @PutMapping("/{id}")
    public String updateTodoStatus(@PathVariable String id, @RequestBody boolean done) {
        for (TodoItem todoItem : todoList) {
            if (todoItem.getId().equals(id)) {
                todoItem.setDone(done);
                return "Status of todo item with ID " + id + " updated successfully.";
            }
        }
        return "Todo item with ID " + id + " not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable String id) {
        Iterator<TodoItem> iterator = todoList.iterator();
        while (iterator.hasNext()) {
            TodoItem todoItem = iterator.next();
            if (todoItem.getId().equals(id)) {
                iterator.remove();
                return "Todo item with ID " + id + " deleted successfully.";
            }
        }
        return "Todo item with ID " + id + " not found.";
    }

    @PutMapping("/{id}/status")
    public String changeTodoStatus(@PathVariable String id, @RequestBody boolean done) {
        for (TodoItem todoItem : todoList) {
            if (todoItem.getId().equals(id)) {
                todoItem.setDone(done);
                return "Status of todo item with ID " + id + " changed to " + (done ? "completed" : "pending") + ".";
            }
        }
        return "Todo item with ID " + id + " not found.";
    }

}