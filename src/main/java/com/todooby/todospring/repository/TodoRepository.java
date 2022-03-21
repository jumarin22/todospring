package com.todooby.todospring.repository;

import com.todooby.todospring.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
