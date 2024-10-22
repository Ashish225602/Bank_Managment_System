package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void saveEmployee(Employee employee);
    Employee getEmployee(Long employeeId);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Long employeeId);
}
