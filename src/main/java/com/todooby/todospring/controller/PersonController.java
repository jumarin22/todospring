package com.todooby.todospring.controller;

// This class will control all CRUD and custom operations for todos

import com.todooby.todospring.models.Person;
import com.todooby.todospring.models.Todo;
import com.todooby.todospring.repository.PersonRepository;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/person")
@RestController
public class PersonController {
    private static final Log log = org.apache.commons.logging.LogFactory.getLog(PersonController.class);
    // Link the repository to the controller
    private final PersonRepository personRepository;
    // Constructor
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // READ - Get all people URL: api/person
    @GetMapping
    public Iterable<Person> getAllPeople() {
        log.info("Getting all people in the database...");
        return personRepository.findAll();
    }

    // READ - Get person by person id URL: api/person/id
    @GetMapping(path = "/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) {
        log.info("Getting person with ID: " + id + "...");
        log.info(personRepository.findById(id));
        return personRepository.findById(id);
    }

    // READ - Get Todos for a person URL: api/person/id/todos
    @GetMapping(path = "/{id}/todos")
    public Iterable<Todo> getTodosForPerson(@PathVariable Integer id) {
        log.info("Getting todos for person with ID: " + id + "...");
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person personChoice = personOptional.get();
            return personChoice.getTodos();
        } else {
            log.info("Person ID: " + id + " not found.");
            return null;
        }
    }

    // CREATE - POST Request URL: api/person
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        log.info("Adding new person: " + person.toString());
        return personRepository.save(person);
    }

    // UPDATE - PUT request URL: api/person/id
    @PutMapping(path = "/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        log.info("Updating person with ID: " + id);
        Optional<Person> personToUpdateOptional = personRepository.findById(id);
        if (personToUpdateOptional.isPresent()) {
            Person personUpdate = personToUpdateOptional.get();
            return personRepository.save(personUpdate);
        } else {
            log.info("Person ID: " + id + " not found.");
            return null;
        }
    }

    // DELETE - URL: api/person/id
    @DeleteMapping(path = "/{id}")
    public Person deletePerson(@PathVariable Integer id) {
        Optional<Person> personToDelete = personRepository.findById(id);
        if (personToDelete.isPresent()) {
            personRepository.delete(personToDelete.get());
            return personToDelete.get();
        } else {
            log.info("Person ID: " + id + " not found.");
            return null;
        }
    }

}
