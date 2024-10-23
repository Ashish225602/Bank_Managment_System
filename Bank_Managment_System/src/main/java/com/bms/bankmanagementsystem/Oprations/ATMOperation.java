package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.service.ATMService;
import com.bms.bankmanagementsystem.service.AccountService;
import com.bms.bankmanagementsystem.serviceIMP.ATMServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ATMOperation {

    private ATMService atmService;
    private AccountService accountService; // Service for account management
    private Scanner scanner;

    public ATMOperation(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
        this.atmService = new ATMServiceImpl(); // Instantiate the ATM service
        this.accountService = new AccountServiceImpl(); // Instantiate the Account service
    }

    // Method to display menu options for ATM operations for Admin
    public void displayMenuforAdmin() {
        System.out.println("------ ATM Management (Admin) ------");
        System.out.println("1. Create ATM");
        System.out.println("2. Update ATM");
        System.out.println("3. Delete ATM");
        System.out.println("4. Get ATM By ID");
        System.out.println("5. Get All ATMs");
        System.out.println("6. Withdraw from ATM");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to display menu options for ATM operations for User
    public void displayMenuforUser() {
        System.out.println("------ ATM (User) ------");
        System.out.println("1. Get All ATMs Details");
        System.out.println("2. Withdraw from ATM");
        System.out.println("3. Check Account Balance");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a new ATM
    public void createATM() {
        System.out.print("Enter ATM location: ");
        String location = scanner.nextLine();

        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        System.out.print("Enter cash available: ");
        double cashAvailable = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        ATM atm = new ATM(location, bankName, cashAvailable);
        atmService.saveATM(atm);
        System.out.println("ATM created successfully: " + atm);
    }

    // Method to update an existing ATM
    public void updateATM() {
        System.out.print("Enter ATM ID to update: ");
        Long atmId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        ATM atm = atmService.getATMById(atmId);
        if (atm != null) {
            System.out.println("Current ATM details: " + atm);
            System.out.print("Enter new location (or press Enter to keep current): ");
            String location = scanner.nextLine();
            if (!location.isEmpty()) {
                atm.setLocation(location);
            }

            System.out.print("Enter new bank name (or press Enter to keep current): ");
            String bankName = scanner.nextLine();
            if (!bankName.isEmpty()) {
                atm.setBankName(bankName);
            }

            System.out.print("Enter new cash available (or press Enter to keep current): ");
            String cashInput = scanner.nextLine();
            if (!cashInput.isEmpty()) {
                double cashAvailable = Double.parseDouble(cashInput);
                atm.setCashAvailable(cashAvailable);
            }

            atmService.updateATM(atm);
            System.out.println("ATM updated successfully: " + atm);
        } else {
            System.out.println("ATM not found!");
        }
    }

    // Method to delete an ATM
    public void deleteATM() {
        System.out.print("Enter ATM ID to delete: ");
        Long atmId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        atmService.deleteATM(atmId);
        System.out.println("ATM deleted successfully.");
    }

    // Method to get ATM by ID
    public void getATMById() {
        System.out.print("Enter ATM ID to retrieve: ");
        Long atmId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        ATM atm = atmService.getATMById(atmId);
        if (atm != null) {
            System.out.println("ATM details: " + atm);
        } else {
            System.out.println("ATM not found!");
        }
    }

    // Method to get all ATMs
    public void getAllATMs() {
        List<ATM> atms = atmService.getAllATMs();
        if (atms.isEmpty()) {
            System.out.println("No ATMs found.");
        } else {
            System.out.println("List of ATMs:");
            for (ATM atm : atms) {
                System.out.println(atm);
            }
        }
    }

    // Method to withdraw cash from an ATM
    public void withdraw() {
        System.out.print("Enter your account ID: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            BigDecimal amountToWithdraw = BigDecimal.valueOf(amount); // Convert amount to BigDecimal

            if (amountToWithdraw.compareTo(BigDecimal.ZERO) > 0 && account.getBalance().compareTo(amountToWithdraw) >= 0) {
                accountService.withdraw(account, amount);
                System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance or invalid amount!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    // Method to check account balance
    public void checkBalance() {
        System.out.print("Enter your account ID: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            System.out.println("Your account balance is: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    // Main loop for ATM operations for Admin
    public void startForAdmin() {
        while (true) {
            displayMenuforAdmin();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createATM();
                    break;
                case 2:
                    updateATM();
                    break;
                case 3:
                    deleteATM();
                    break;
                case 4:
                    getATMById();
                    break;
                case 5:
                    getAllATMs();
                    break;
                case 6:
                    withdraw();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Exiting without closing scanner here
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Main loop for ATM operations for User
    public void startForUser() {
        while (true) {
            displayMenuforUser();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    getAllATMs();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Exiting without closing scanner here
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
