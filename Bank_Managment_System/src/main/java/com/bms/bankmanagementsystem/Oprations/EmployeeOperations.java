package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Employee;
import com.bms.bankmanagementsystem.service.EmployeeService;
import com.bms.bankmanagementsystem.serviceIMP.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class EmployeeOperations {
    private EmployeeService employeeService;
    private Scanner scanner;
    
    public EmployeeOperations(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
    }

    // Constructor to initialize the service and scanner
    public EmployeeOperations() {
        this.employeeService = new EmployeeServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    // Display the menu and handle user input
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Get Employee By ID");
            System.out.println("5. Get All Employees");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    getEmployeeById();
                    break;
                case 5:
                    getAllEmployees();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void addEmployee() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Salary: ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNum = scanner.nextLine();

        Employee employee = new Employee(null, firstName, lastName, designation, salary, dateOfBirth, address, phoneNum);
        employeeService.saveEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    private void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.getEmployee(employeeId);
        if (employee != null) {
            System.out.print("Enter First Name (" + employee.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) {
                employee.setFirstName(firstName);
            }

            System.out.print("Enter Last Name (" + employee.getLastName() + "): ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) {
                employee.setLastName(lastName);
            }

            System.out.print("Enter Designation (" + employee.getDesignation() + "): ");
            String designation = scanner.nextLine();
            if (!designation.isEmpty()) {
                employee.setDesignation(designation);
            }

            System.out.print("Enter Salary (" + employee.getSalary() + "): ");
            String salaryInput = scanner.nextLine();
            if (!salaryInput.isEmpty()) {
                employee.setSalary(new BigDecimal(salaryInput));
            }

            System.out.print("Enter Date of Birth (" + employee.getDateOfBirth() + "): ");
            String dateOfBirth = scanner.nextLine();
            if (!dateOfBirth.isEmpty()) {
                employee.setDateOfBirth(dateOfBirth);
            }

            System.out.print("Enter Address (" + employee.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                employee.setAddress(address);
            }

            System.out.print("Enter Phone Number (" + employee.getPhoneNum() + "): ");
            String phoneNum = scanner.nextLine();
            if (!phoneNum.isEmpty()) {
                employee.setPhoneNum(phoneNum);
            }

            employeeService.updateEmployee(employee);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee deleted successfully!");
    }

    private void getEmployeeById() {
        System.out.print("Enter Employee ID: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee != null) {
            System.out.println("Employee Details: " + employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("All Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}
