package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Admin;
import com.bms.bankmanagementsystem.service.AdminService;
import com.bms.bankmanagementsystem.serviceIMP.AdminServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AdminOperation {
    private BankTransactionOperation bankTransactionOperation; 
    private AccountOperation accountOperations;
    private ATMOperation atmOperations;
    private ReportOperations reportOperations;
    private LoanOperations loanOperations;
    private EmployeeOperations employeeOperations;
    private CoustomerOperations coustomerOperations;
    
    private AdminService adminService;
    private Scanner scanner;

    public AdminOperation(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
        this.bankTransactionOperation = new BankTransactionOperation(scanner); // Pass scanner to BankTransactionOperation
        this.accountOperations = new AccountOperation(scanner); // Pass scanner to AccountOperation
        this.atmOperations = new ATMOperation(scanner); // Pass scanner to ATMOperation
        this.reportOperations = new ReportOperations(scanner); // Pass scanner to ReportOperations
        this.loanOperations = new LoanOperations(scanner); // Pass scanner to LoanOperations
        this.employeeOperations = new EmployeeOperations(scanner); // Pass scanner to EmployeeOperations
        this.coustomerOperations = new CoustomerOperations(scanner); // Pass scanner to CoustomerOperations
        this.adminService = new AdminServiceImpl(); // Initialize your AdminService implementation here
    }

    // Method to display admin login
    public Admin login() {
        System.out.println("------ Admin Login ------");
        System.out.print("Enter admin name: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        // Validate admin credentials
        for (Admin admin : adminService.getAllAdmins()) {
            if (admin.getAdminName().equals(adminName) && admin.getAdminPassword().equals(adminPassword)) {
                System.out.println("Login successful! Welcome, " + admin.getAdminName());
                return admin;
            }
        }
        System.out.println("Invalid admin credentials! Please try again.");
        return null;
    }

    // Method to display menu options for admin operations
    public void manageAdmin() {
        while (true) {
            System.out.println("------ Admin Management ------");
            System.out.println("1. Create Admin");
            System.out.println("2. Update Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. Get Admin By ID");
            System.out.println("5. Get All Admins");
            System.out.println("0. Back to Admin Area");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput(); // Use the utility method for input

            switch (choice) {
                case 1:
                    createAdmin();
                    break;
                case 2:
                    updateAdmin();
                    break;
                case 3:
                    deleteAdmin();
                    break;
                case 4:
                    getAdminById();
                    break;
                case 5:
                    getAllAdmins();
                    break;
                case 0:
                    return; // Go back to Admin Area
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public void displayforAdmin() {
        System.out.println("------ Admin Area ------");
        System.out.println("1. Manage Employee");
        System.out.println("2. Manage Account");
        System.out.println("3. Manage ATM");
        System.out.println("4. Manage Transaction");
        System.out.println("5. Manage Coustomer");
        System.out.println("6. Manage Loan");
        System.out.println("7. Manage Report");
        System.out.println("8. Manage Admin");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a new admin
    public void createAdmin() {
        System.out.print("Enter admin name: ");
        String adminName = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        Admin admin = new Admin(adminName, adminPassword);
        adminService.saveAdmin(admin);
        System.out.println("Admin created successfully: " + admin);
    }

    // Method to update an existing admin
    public void updateAdmin() {
        System.out.print("Enter admin ID to update: ");
        Long adminId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Admin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            System.out.println("Current admin details: " + admin);
            System.out.print("Enter new admin name (or press Enter to keep current): ");
            String adminName = scanner.nextLine();
            if (!adminName.isEmpty()) {
                admin.setAdminName(adminName);
            }

            System.out.print("Enter new admin password (or press Enter to keep current): ");
            String adminPassword = scanner.nextLine();
            if (!adminPassword.isEmpty()) {
                admin.setAdminPassword(adminPassword);
            }

            adminService.updateAdmin(admin);
            System.out.println("Admin updated successfully: " + admin);
        } else {
            System.out.println("Admin not found!");
        }
    }

    // Method to delete an admin
    public void deleteAdmin() {
        System.out.print("Enter admin ID to delete: ");
        Long adminId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        adminService.deleteAdmin(adminId);
        System.out.println("Admin deleted successfully.");
    }

    // Method to get admin by ID
    public void getAdminById() {
        System.out.print("Enter admin ID to retrieve: ");
        Long adminId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Admin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            System.out.println("Admin details: " + admin);
        } else {
            System.out.println("Admin not found!");
        }
    }

    // Method to get all admins
    public void getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) {
            System.out.println("No admins found.");
        } else {
            System.out.println("List of admins:");
            for (Admin admin : admins) {
                System.out.println(admin);
            }
        }
    }

    // Method to get valid integer input from user
    private int getValidIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    // Main loop for admin operations
    public void startForAdmin() {
        Admin admin = login(); // Admin must log in first
        if (admin == null) {
            return; // Exit if login failed
        }

        while (true) {
            displayforAdmin();
            int choice = getValidIntegerInput(); // Use the utility method for input

            switch (choice) {
                case 1:
                    employeeOperations.displayMenu(); // Assuming you have a start method for employee operations
                    break;
                case 2:
                    accountOperations.startForAdmin(); // Assuming you have a start method for account operations
                    break;
                case 3:
                    atmOperations.startForAdmin(); // Assuming you have a start method for ATM operations
                    break;
                case 4:
                    bankTransactionOperation.startAdminOperations(); // Assuming you have a start method for transaction operations
                    break;
                case 5:
                    coustomerOperations.displayMenu(); // Assuming you have a start method for customer operations
                    break;
                case 6:
                    loanOperations.startForAdmin(); // Assuming you have a start method for loan operations
                    break;
                case 7:
                    reportOperations.displayMenu(); // Assuming you have a start method for report operations
                    break;
                case 8:
                    manageAdmin(); // Call to manage admin operations
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return; // Do not close the scanner here
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
