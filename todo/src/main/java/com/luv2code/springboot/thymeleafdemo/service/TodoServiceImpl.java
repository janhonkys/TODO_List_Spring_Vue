package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.TodoRepository;
import com.luv2code.springboot.thymeleafdemo.entity.TodoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service implementation for handling TodoEntry operations
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    // Constructor-based dependency injection
    @Autowired
    public TodoServiceImpl(TodoRepository theTodoRepository) {
        todoRepository = theTodoRepository;
    }

    // Retrieve all Todo entries
    @Override
    public List<TodoEntry> findAll() {
        return todoRepository.findAll();
    }

    // Retrieve a Todo entry by its ID
    @Override
    public TodoEntry findById(int theId) {
        Optional<TodoEntry> result = todoRepository.findById(theId);

        TodoEntry theTodo = null;

        if (result.isPresent()) {
            theTodo = result.get();
        }
        else {
            // Handle the case where the Todo entry is not found
            throw new RuntimeException("Did not find todo id - " + theId);
        }
        return theTodo;
    }

    // Save or update a Todo entry
    @Override
    public TodoEntry save(TodoEntry theTodo) {
        return todoRepository.save(theTodo);
    }

    // Delete a Todo entry by its ID
    @Override
    public void deleteById(int theId) {
        todoRepository.deleteById(theId);
    }
}