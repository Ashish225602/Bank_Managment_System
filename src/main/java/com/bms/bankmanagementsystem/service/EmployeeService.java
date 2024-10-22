package com.bms.bankmanagementsystem.service;

import com.bms.bankmanagementsystem.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee); // Save a new employee
    Employee getEmployee(Long employeeId); // Retrieve an employee by ID
    List<Employee> getAllEmployees(); // Retrieve all employees
    void updateEmployee(Employee employee); // Update an existing employee
    void deleteEmployee(Long employeeId); // Delete an employee by ID
}
