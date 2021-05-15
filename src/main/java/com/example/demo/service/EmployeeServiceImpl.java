package com.example.demo.service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    //Constructor Injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    //The service method findAll() will call the EmployeeDAOHibernateIMPL findAll() method
    //which will run a query to find all the employees.
    @Override
    @Transactional //Defines the scope of a single database transaction. This is used with the entity manager
                   //which when you move to the EmployeeDAOHibernateIMPL class you will see this working.
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    //The service method findById() will call the EmployeeDAOHibernateIMPL findById() method
    //which will run a query to find the employee by id.
    @Override
    @Transactional
    public Employee findById(int theId) {
        return employeeDAO.finById(theId);
    }

    //The service method save() will call the EmployeeDAOHibernateIMPL save() method
    //which will run a query to save or update the employee.
    @Override
    @Transactional
    public void save(Employee theEmployee) {
        employeeDAO.save(theEmployee);
    }

    //The service method deleteById() will call the EmployeeDAOHibernateIMPL deleteById() method
    //which will run a query to delete the employee by Id.
    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}
