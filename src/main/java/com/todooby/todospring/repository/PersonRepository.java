package com.todooby.todospring.repository;

import com.todooby.todospring.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
