package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for CRUD operations on TodoEntry entities
public interface TodoRepository extends JpaRepository<TodoEntry, Integer> {

}