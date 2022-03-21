package com.todooby.todospring.models;

import javax.persistence.*;
import java.util.List;


// This is an Entity in the PostgreSQL database
@Entity
@Table(name="person")
public class Person {
    // Define Primary Key
    @Id
    @GeneratedValue

    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    // One to many relationship between person and todo via id and person_id
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private List<Todo> todos;

    @Column(name="password")
    private String password;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Custom method to get todos before specified date ...

}
