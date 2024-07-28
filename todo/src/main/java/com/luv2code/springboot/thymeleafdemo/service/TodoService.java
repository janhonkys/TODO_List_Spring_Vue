package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.entity.TodoEntry;
import java.util.List;

// Service for handling TodoEntry operations
public interface TodoService {

    // Retrieve all Todo entries
    List<TodoEntry> findAll();

    // Retrieve a Todo entry by its ID
    TodoEntry findById(int theId);

    // Save a Todo entry (create or update)
    TodoEntry save(TodoEntry theTodo);

    // Delete a Todo entry by its ID
    void deleteById(int theId);
}