package com.todooby.todospring.controller;

import com.todooby.todospring.models.Todo;
import com.todooby.todospring.repository.TodoRepository;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



// This class will control all CRUD operations for todos
@RequestMapping("api/todo")
@RestController
public class TodoController {
    private static final Log log = org.apache.commons.logging.LogFactory.getLog(TodoController.class);

    // Connect repo to controller
    private final TodoRepository todoRepository;
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // READ - Get all todos URL: api/todo
    @GetMapping
    public Iterable<Todo> getAllTodos() {
        return this.todoRepository.findAll();
    }

    // READ - Get todos by todo id URL: api/todo/id
    @GetMapping(path = "/{id}")
    public Optional<Todo> getTodoById(@PathVariable Integer id) {
        log.info("Getting todo with ID: " + id + "...");
        return this.todoRepository.findById(id);
    }

    // CREATE POST Request URL: api/todo
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        log.info("Adding new todo: " + todo.toString() + "...");
        return this.todoRepository.save(todo);
    }

    // UPDATE - PUT request URL: api/todo/id
    @PutMapping(path = "/{id}")
    public Todo todoUpdate(@RequestBody Todo todo, @PathVariable Integer id) {
        log.info("Updating todo with ID: " + id + "...");
        Optional<Todo> todoToUpdateOptional = this.todoRepository.findById(id);
        if (todoToUpdateOptional.isPresent()) {
            Todo todoUpdate = todoToUpdateOptional.get();
            return this.todoRepository.save(todoUpdate);
        } else {
            log.info("Todo ID: " + id + " not found.");
            return null;
        }
    }

    // DELETE - URL: api/todo/id
    @DeleteMapping(path = "/{id}")
    public Todo deleteTodo(@PathVariable Integer id) {
        Optional<Todo> todoDelete = this.todoRepository.findById(id);
        if (todoDelete.isPresent()) {
            this.todoRepository.delete(todoDelete.get());
            return todoDelete.get();
        } else {
            log.info("Todo ID: " + id + " not found.");
            return null;
        }
    }

}
