package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Employee Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name="employee") //This is for the actual name of the database table we are mapping to the class.
public class Employee {
    //Define fields
    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name="id")//This is mapping the primary key to the id column in the database.
    private int id;

    @Column(name = "first_name") //This will map the firstName field to the column named first_name in the database.
    private String firstName;

    @Column(name = "last_name")//This will map the lastName field to the column named last_name in the database.
    private String lastName;

    @Column(name = "email") //This will map the email field to the column named email in the database.
    private String email;

    //Define constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Define toString Method
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
