// Test class resubmission for Task 4: Unit testing
package com.example.restservice;

import com.example.restservice.entities.Employee;
import com.example.restservice.repo.EmployeeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RestServiceApplicationTests {
    @Mock
	private EmployeeManager employeeManager;

	// Helper function to get the number of current employees
	int getEmployeeCount(EmployeeManager manager){
		return manager.getAllEmployees().getEmployeeList().size();
	}

	@Test
	void createEmployeeManager(){
		EmployeeManager newEmployeeManager = new EmployeeManager();
		assert(getEmployeeCount(newEmployeeManager) != 0);
	}

	@Test
	// Ensure that adding an employee increases the employee count by 1
	void addEmployee(){
		EmployeeManager employeeManager = new EmployeeManager();
		int employeeCount = getEmployeeCount(employeeManager);
		Employee employee = new Employee(3, "Daria", "Jones",
				"dariajones@gmail.com", "Software developer");
		employeeManager.addEmployee(employee);
		assert(employeeCount + 1 == getEmployeeCount(employeeManager));
	}

	@ExtendWith(MockitoExtension.class)
	@BeforeEach void setUp(){
		this.employeeManager = new EmployeeManager();
		Employee newEmployee = new Employee(3,"Daria","Jones",
				"dariajones@gmail.com","Software developer");
		this.employeeManager.addEmployee(newEmployee);
	}

	@Test
	// Check whether added employee ID is found in ID field
	void employeeIdInList(){
		List<Employee> employees = this.employeeManager.getAllEmployees().getEmployeeList();

		for(int i = 0; i < employees.size(); i++){
			Employee employee = employees.get(i);
			if(employee.getId() == 3){
				return;
			}
		} assert(false);
	}

	@Test
	// Check whether added employee first name is found in first name field
	void employeeFirstNameInList(){
		List<Employee> employees = this.employeeManager.getAllEmployees().getEmployeeList();

		for(int i = 0; i < employees.size(); i++){
			Employee employee = employees.get(i);

			if(employee.getFirstName() == "Daria"){
				return;
			}
		} assert(false);
	}

	@Test
		// Check whether added employee last name is found in last name field
	void employeeLastNameInList(){
		List<Employee> employees = this.employeeManager.getAllEmployees().getEmployeeList();

		for(int i = 0; i < employees.size(); i++){
			Employee employee = employees.get(i);

			if(employee.getLastName() == "Jones"){
				return;
			}
		} assert(false);
	}

	@Test
		// Check whether added employee email is found in email field
	void employeeEmailInList(){
		List<Employee> employees = this.employeeManager.getAllEmployees().getEmployeeList();

		for(int i = 0; i < employees.size(); i++){
			Employee employee = employees.get(i);

			if(employee.getEmail() == "dariajones@gmail.com"){
				return;
			}
		} assert(false);
	}

	@Test
		// Check whether added employee title is found in title field
	void employeeTitleInList(){
		List<Employee> employees = this.employeeManager.getAllEmployees().getEmployeeList();

		for(int i = 0; i < employees.size(); i++){
			Employee employee = employees.get(i);

			if(employee.getTitle() == "Software developer"){
				return;
			}
		} assert(false);
	}
	@Test
	public void contextLoads() {
	}
}
