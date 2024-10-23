package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Employee;
import com.bms.bankmanagementsystem.dao.EmployeeDao;
import com.bms.bankmanagementsystem.daoIMP.EmployeeDaoImpl;
import com.bms.bankmanagementsystem.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    // Constructor to initialize the DAO
    public EmployeeServiceImpl() {
        this.employeeDao = new EmployeeDaoImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeDao.getEmployee(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }
}
