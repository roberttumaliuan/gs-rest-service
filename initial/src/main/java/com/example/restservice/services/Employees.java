package com.example.restservice.services;
import com.example.restservice.entities.Employee;

import java.util.ArrayList;
import java.util.List;
public class Employees {

    private List<Employee> employeeList;

    // Method to return the list
    // of employees
    public List<Employee> getEmployeeList()
    {
        if(employeeList == null){
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList)
    {
        this.employeeList = employeeList;
    }
}

