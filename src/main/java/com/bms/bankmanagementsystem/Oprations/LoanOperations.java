package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.Entity.Loan;
import com.bms.bankmanagementsystem.service.CoustomerService;
import com.bms.bankmanagementsystem.service.LoanService;
import com.bms.bankmanagementsystem.serviceIMP.CoustomerServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.LoanServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class LoanOperations {
    private LoanService loanService;
    private CoustomerService coustomerService; // Added CoustomerService to fetch customers
    private Scanner scanner;
    
    public LoanOperations(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
    }

    public LoanOperations() {
        this.loanService = new LoanServiceImpl();
        this.coustomerService = new CoustomerServiceImpl(); // Initialize CoustomerService
        this.scanner = new Scanner(System.in);
    }

    public void startForAdmin() {
        while (true) {
            DidplayMenueForAdmin();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addLoan();
                    break;
                case 2:
                    viewLoan();
                    break;
                case 3:
                    viewAllLoans();
                    break;
                case 4:
                    updateLoan();
                    break;
                case 5:
                    deleteLoan();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void startForUser() {
        while (true) {
            DidplayMenueForUser();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addLoan();
                    break;
                case 2:
                    viewLoan();
                    break;
                case 4:
                    updateLoan();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void DidplayMenueForAdmin() {
        System.out.println("Loan Management Menu:");
        System.out.println("1. Get Loan");
        System.out.println("2. View Loan");
        System.out.println("3. View All Loans");
        System.out.println("4. Update Loan");
        System.out.println("5. Delete Loan");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void DidplayMenueForUser() {
        System.out.println("Loan Management Menu:");
        System.out.println("1. Get Loan");
        System.out.println("2. View Loan");
        System.out.println("4. Update Loan");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void addLoan() {
        System.out.print("Enter loan type: ");
        String loanType = scanner.nextLine();
        System.out.print("Enter loan amount: ");
        BigDecimal loanAmount = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter loan duration: ");
        String duration = scanner.nextLine();

        // Get customer by ID
        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Coustomer customer = coustomerService.getCoustomerById(customerId); // Fetch the actual customer
        if (customer == null) {
            System.out.println("Customer not found. Loan cannot be added.");
            return; // Exit the method if customer not found
        }

        // Get account by ID
        System.out.print("Enter account ID: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Account account = new Account(); // You'll need to implement the method to fetch the actual account
        account.setAccountId(accountId); // Assuming you have a method to fetch account

        Loan loan = new Loan(loanType, loanAmount, duration, customer, account);
        loanService.saveLoan(loan);
        System.out.println("Loan added successfully.");
    }

    private void viewLoan() {
        System.out.print("Enter loan ID to view: ");
        Long loanId = scanner.nextLong();
        Loan loan = loanService.getLoan(loanId);
        if (loan != null) {
            System.out.println("Loan Details: " + loan);
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void viewAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            System.out.println("All Loans:");
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        }
    }

    private void updateLoan() {
        System.out.print("Enter loan ID to update: ");
        Long loanId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Loan loan = loanService.getLoan(loanId);
        if (loan != null) {
            System.out.print("Enter new loan type (leave blank to keep current): ");
            String loanType = scanner.nextLine();
            if (!loanType.isEmpty()) loan.setLoanType(loanType);

            System.out.print("Enter new loan amount (leave blank to keep current): ");
            String amountInput = scanner.nextLine();
            if (!amountInput.isEmpty()) loan.setLoanAmount(new BigDecimal(amountInput));

            System.out.print("Enter new loan duration (leave blank to keep current): ");
            String duration = scanner.nextLine();
            if (!duration.isEmpty()) loan.setDuration(duration);

            // Get new customer by ID
            System.out.print("Enter new customer ID (leave blank to keep current): ");
            String customerIdInput = scanner.nextLine();
            if (!customerIdInput.isEmpty()) {
                Coustomer customer = coustomerService.getCoustomerById(Long.valueOf(customerIdInput)); // Fetch customer
                if (customer != null) {
                    loan.setCustomer(customer);
                } else {
                    System.out.println("Customer not found. Keeping current customer.");
                }
            }

            // Get new account by ID
            System.out.print("Enter new account ID (leave blank to keep current): ");
            String accountIdInput = scanner.nextLine();
            if (!accountIdInput.isEmpty()) {
                Account account = new Account(); // You'll need to implement the method to fetch the actual account
                account.setAccountId(Long.valueOf(accountIdInput)); // Assuming you have a method to fetch account
                loan.setAccount(account);
            }

            loanService.updateLoan(loan);
            System.out.println("Loan updated successfully.");
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void deleteLoan() {
        System.out.print("Enter loan ID to delete: ");
        Long loanId = scanner.nextLong();
        loanService.deleteLoan(loanId);
        System.out.println("Loan deleted successfully.");
    }
}
