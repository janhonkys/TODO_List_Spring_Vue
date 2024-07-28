package com.luv2code.springboot.thymeleafdemo;

import com.luv2code.springboot.thymeleafdemo.entity.TodoEntry;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Class for Unit tests for TodoEntry class
public class TodoEntryTest {

    // Test the creation and initialization of a TodoEntry object using the constructor
    @Test
    void testTodoEntryCreation() {
        LocalDate date = LocalDate.now();
        TodoEntry todo = new TodoEntry("Title", "Description", "High", date, false);

        assertEquals("Title", todo.getTitle());
        assertEquals("Description", todo.getDescription());
        assertEquals("High", todo.getPriority());
        assertEquals(date, todo.getDate());
        assertFalse(todo.isCompleted());
    }

    // Test the setters of the TodoEntry class
    @Test
    void testTodoEntrySetters() {
        LocalDate date = LocalDate.now();
        TodoEntry todo = new TodoEntry();

        // Set values using setters
        todo.setId(1);
        todo.setTitle("New Title");
        todo.setDescription("New Description");
        todo.setPriority("Low");
        todo.setDate(date);
        todo.setCompleted(true);

        // Assert that the values are correctly set
        assertEquals(1, todo.getId());
        assertEquals("New Title", todo.getTitle());
        assertEquals("New Description", todo.getDescription());
        assertEquals("Low", todo.getPriority());
        assertEquals(date, todo.getDate());
        assertTrue(todo.isCompleted());
    }
}