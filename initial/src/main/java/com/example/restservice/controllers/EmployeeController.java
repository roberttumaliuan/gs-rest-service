package com.example.restservice.controllers;

import java.net.URI;

import com.example.restservice.RestServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// Import the above-defined classes
// to use the properties of those
// classes
import com.example.restservice.entities.Employee;
import com.example.restservice.services.Employees;
import com.example.restservice.repo.EmployeeManager;

// Creating the REST controller
@SpringBootApplication
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    private EmployeeManager employeeManager;
    // Implementing a GET method
    // to get the list of all
    // the employees
    @GetMapping(
            path = "/",
            produces = "application/json"
    )

    public Employees getEmployees()
    {
        return employeeManager.getAllEmployees();
    }

    // Create a POST method
    // to add an employee
    // to the list

    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")

    public ResponseEntity<Object> addEmployee(
            @RequestBody Employee employee)
    {
        // Creating an ID of an employee
        // from the number of employees
        Integer id
                = employeeManager
                     .getAllEmployees()
                     .getEmployeeList()
                     .size()
                + 1;
        employee.setId(id);

        employeeManager.addEmployee(employee);

        URI location
                = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        employee.getId()
                ).toUri();
        return ResponseEntity
                .created(location)
                .build();
    }
}
