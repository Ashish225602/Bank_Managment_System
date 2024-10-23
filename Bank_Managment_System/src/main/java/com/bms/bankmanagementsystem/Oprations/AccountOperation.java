package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.service.AccountService;
import com.bms.bankmanagementsystem.service.CoustomerService;
import com.bms.bankmanagementsystem.serviceIMP.AccountServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.CoustomerServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AccountOperation {

    private AccountService accountService;
    private CoustomerService coustomerService;
    private Scanner scanner;

    public AccountOperation(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
        this.accountService = new AccountServiceImpl(); // Instantiate the service
        this.coustomerService = new CoustomerServiceImpl(); // Instantiate customer service
    }

    // Method to display menu options for normal Admin
    public void displayMenuforAdmin() {
        System.out.println("------ Account Management (Admin) ------");
        System.out.println("1. Create Account");
        System.out.println("2. Update Account");
        System.out.println("3. Delete Account");
        System.out.println("4. Get Account By ID");
        System.out.println("5. Get All Accounts");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to display menu options for normal user
    public void displayMenuforUser() {
        System.out.println("------ Account Management (User) ------");
        System.out.println("1. Create Account");
        System.out.println("2. Update Account");
        System.out.println("3. Delete Account");
        System.out.println("4. Get Account By ID");
        System.out.println("5. Get All Accounts");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a new account
    public void createAccount() {
        System.out.print("Enter account type (Savings/Current): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        BigDecimal balance = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        // Get customer by ID using CoustomerService
        Coustomer customer = coustomerService.getCoustomerById(customerId);

        if (customer != null) {
            Account account = new Account(accountType, balance, customer); // Use new constructor
            accountService.saveAccount(account);
            System.out.println("Account created successfully: " + account);
        } else {
            System.out.println("Customer not found!");
        }
    }

    // Method to update an existing account
    public void updateAccount() {
        System.out.print("Enter account ID to update: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            System.out.println("Current account details: " + account);
            System.out.print("Enter new account type (or press Enter to keep current): ");
            String accountType = scanner.nextLine();
            if (!accountType.isEmpty()) {
                account.setAccountType(accountType);
            }

            System.out.print("Enter new balance (or press Enter to keep current): ");
            String balanceInput = scanner.nextLine();
            if (!balanceInput.isEmpty()) {
                account.setBalance(new BigDecimal(balanceInput));
            }

            accountService.updateAccount(account);
            System.out.println("Account updated successfully: " + account);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Method to delete an account
    public void deleteAccount() {
        System.out.print("Enter account ID to delete: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        accountService.deleteAccount(accountId);
        System.out.println("Account deleted successfully.");
    }

    // Method to get account by ID
    public void getAccountById() {
        System.out.print("Enter account ID to retrieve: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            System.out.println("Account details: " + account);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Method to get all accounts
    public void getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("List of accounts:");
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }

    // Main loop for user interaction for Admin
    public void startForAdmin() {
        while (true) {
            displayMenuforAdmin();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    updateAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    getAccountById();
                    break;
                case 5:
                    getAllAccounts();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Do not close the scanner here
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Main loop for user interaction for User
    public void startForUser() {
        while (true) {
            displayMenuforUser();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    updateAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    getAccountById();
                    break;
                case 5:
                    getAllAccounts();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Do not close the scanner here
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
