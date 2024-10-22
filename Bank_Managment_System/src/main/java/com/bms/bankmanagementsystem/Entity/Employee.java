package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId; // Auto-generated primary key

    private String firstName; // First name of the employee
    private String lastName; // Last name of the employee
    private String designation; // Designation of the employee
    private BigDecimal salary; // Using BigDecimal to handle salary
    private String dateOfBirth; // Employee's date of birth
    private String address; // Employee's address
    private String phoneNum; // Employee's phone number

    // Default Constructor
    public Employee() {
        super();
    }

    // Parameterized Constructor
    public Employee(Long employeeId, String firstName, String lastName, String designation, BigDecimal salary,
                    String dateOfBirth, String address, String phoneNum) {
        super();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    // Overriding toString() method for easy debugging and logging
    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", designation=" + designation + ", salary=" + salary + ", dateOfBirth=" + dateOfBirth
                + ", address=" + address + ", phoneNum=" + phoneNum + "]";
    }
}
