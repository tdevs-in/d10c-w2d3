package com.tdevs.w2d1.controllers;

import com.tdevs.w2d1.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoController todoController;

    private List<TodoItem> todoList;

    @BeforeEach
    void setUp() {
        todoList = new ArrayList<>();
    }

    @Test
    public void testAddTodo() throws Exception {
        when(todoController.addTodo(any(String.class))).thenAnswer(invocation -> {
            String description = invocation.getArgument(0);
            TodoItem todoItem = new TodoItem(description);
            todoList.add(todoItem);
            return todoItem;
        });

        String description = "Test Todo";
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                        .content(description))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.done").value(false));
    }

    @Test
    public void testGetTodos() throws Exception {
        when(todoController.getTodos()).thenReturn(todoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(todoList.size()));
    }

    @Test
    public void testUpdateTodoStatus() throws Exception {
        String id = UUID.randomUUID().toString();
        boolean done = true;

        when(todoController.updateTodoStatus(eq(id), anyBoolean())).thenReturn("Status of todo item with ID " + id + " updated successfully.");

        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(done)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Status of todo item with ID " + id + " updated successfully."));

        verify(todoController, times(1)).updateTodoStatus(eq(id), eq(done));
    }

    @Test
    public void testDeleteTodo() throws Exception {
        String id = UUID.randomUUID().toString();

        when(todoController.deleteTodo(eq(id))).thenReturn("Todo item with ID " + id + " deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Todo item with ID " + id + " deleted successfully."));

        verify(todoController, times(1)).deleteTodo(eq(id));
    }

    @Test
    public void testChangeTodoStatus() throws Exception {
        String id = UUID.randomUUID().toString();
        boolean done = true;

        when(todoController.changeTodoStatus(eq(id), anyBoolean())).thenReturn("Status of todo item with ID " + id + " changed to " + (done ? "completed" : "pending") + ".");

        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}/status", id).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(done)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Status of todo item with ID " + id + " changed to " + (done ? "completed" : "pending") + "."));

        verify(todoController, times(1)).changeTodoStatus(eq(id), eq(done));
    }
}
