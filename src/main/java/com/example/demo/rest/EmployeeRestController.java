package com.example.demo.rest;

import java.util.List;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Employee;

@RestController //The @RestController is a convenience annotation for creating Restful controllers.
@RequestMapping("/api") //The RequestMapping annotation is used to map HTTP request to handle method for REST Controllers
public class EmployeeRestController {

    //This is calling the EmployeeService interface which with will be implemented by the EmployeeServiceIMPL.
    //Then the EmployeeServiceIMPL will call the EmployeeDAO interface which will be implemented
    //by the EmployeeDAOHibernateIMPL.
    private EmployeeService employeeService;

    //Constructor Injection: this is telling the spring framework to wire up your dependencies for the employee service.
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    //This is a GET request that will read a list of all the employees.
    //localhost:8080/api/employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
        //The employeeService.findAll() method will call the EmployeeServiceIMPL findAll() method
        //implementation which will then send you to the EmployeeDAOHibernateIMPL which is where the
        //hibernate query to retrieve all the employees gets executed.
    }

    //This is a GET request to read a single employee by ID.
    //localhost:8080/api/employees/2
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        //The employeeService.findById() method will call the EmployeeServiceIMPL findById() method
        //implementation which will then send you to the EmployeeDAOHibernateIMPL which is where the
        //hibernate query to find the employee by Id gets executed.
        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    //This is a POST request to add a new employee.
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        //also just in case they pass an id in JSON .... set id to o
        //this is to force a save of new item .... instead of update
        theEmployee.setId(0);
        employeeService.save(theEmployee);
        //The employeeService.save() method will call the EmployeeServiceIMPL save() method
        //implementation which will then send you to the EmployeeDAOHibernateIMPL which is where the
        //hibernate query to save the new employee gets executed.
        return theEmployee;
    }

    //This is a PUT request to update an existing employee.
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        //The employeeService.save() method will call the EmployeeServiceIMPL save() method
        //implementation which will then send you to the EmployeeDAOHibernateIMPL which is where the
        //hibernate query to update an existing employee gets executed.
        return theEmployee;
    }

    //This is a DELETE request to delete an existing employee.
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        //throw exception if null
        if(tempEmployee == null) {
            throw new RuntimeException("Employee is not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        //The employeeService.deleteById() method will call the EmployeeServiceIMPL deleteById() method
        //implementation which will then send you to the EmployeeDAOHibernateIMPL which is where the
        //hibernate query for deleting an existing employee gets executed.
        return "Deleted employee id - " + employeeId;
    }

}