package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.BankTransaction;
import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.service.BankTransactionService;
import com.bms.bankmanagementsystem.serviceIMP.BankTransactionServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BankTransactionOperation {

    private BankTransactionService bankTransactionService;
    private Scanner scanner;
    
    public BankTransactionOperation(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
    }

    public BankTransactionOperation() {
        this.bankTransactionService = new BankTransactionServiceImpl(); // Instantiate the bank transaction service
        this.scanner = new Scanner(System.in); // Scanner for user input
    }

    // Method to display menu options for bank transaction operations
    public void displayMenu() {
        System.out.println("------ Bank Transaction Management ------");
        System.out.println("1. Create Transaction");
        System.out.println("2. Update Transaction");
        System.out.println("3. Delete Transaction");
        System.out.println("4. Get Transaction By ID");
        System.out.println("5. Get All Transactions");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a new bank transaction
    public void createTransaction() {
        System.out.print("Enter transaction type (Withdrawal/Deposit): ");
        String transactionType = scanner.nextLine();

        System.out.print("Enter amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        Date transactionDate = new Date(); // Current date and time

        System.out.print("Enter account ID: ");
        Long accountId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter ATM ID: ");
        Long atmId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        // Here, you need to fetch Account, ATM, and Coustomer objects from your database
        // For demonstration, we'll create dummy objects. Replace this with actual fetching logic.
        Account account = new Account(); // Fetch account by accountId
        ATM atm = new ATM(); // Fetch ATM by atmId
        Coustomer coustomer = new Coustomer(); // Fetch Coustomer by customerId

        // Create and save the transaction
        BankTransaction transaction = new BankTransaction(transactionType, amount, transactionDate, account, atm, coustomer);
        bankTransactionService.saveTransaction(transaction);
        System.out.println("Transaction created successfully: " + transaction);
    }

    // Method to update an existing bank transaction
    public void updateTransaction() {
        System.out.print("Enter transaction ID to update: ");
        Long transactionId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        BankTransaction transaction = bankTransactionService.getTransactionById(transactionId);
        if (transaction != null) {
            System.out.println("Current Transaction details: " + transaction);

            System.out.print("Enter new transaction type (or press Enter to keep current): ");
            String transactionType = scanner.nextLine();
            if (!transactionType.isEmpty()) {
                transaction.setTransactionType(transactionType);
            }

            System.out.print("Enter new amount (or press Enter to keep current): ");
            String amountInput = scanner.nextLine();
            if (!amountInput.isEmpty()) {
                BigDecimal amount = new BigDecimal(amountInput);
                transaction.setAmount(amount);
            }

            bankTransactionService.updateTransaction(transaction);
            System.out.println("Transaction updated successfully: " + transaction);
        } else {
            System.out.println("Transaction not found!");
        }
    }

    // Method to delete a bank transaction
    public void deleteTransaction() {
        System.out.print("Enter transaction ID to delete: ");
        Long transactionId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        bankTransactionService.deleteTransaction(transactionId);
        System.out.println("Transaction deleted successfully.");
    }

    // Method to get transaction by ID
    public void getTransactionById() {
        System.out.print("Enter transaction ID to retrieve: ");
        Long transactionId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        BankTransaction transaction = bankTransactionService.getTransactionById(transactionId);
        if (transaction != null) {
            System.out.println("Transaction details: " + transaction);
        } else {
            System.out.println("Transaction not found!");
        }
    }

    // Method to get all transactions
    public void getAllTransactions() {
        List<BankTransaction> transactions = bankTransactionService.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("List of Transactions:");
            for (BankTransaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    // Main loop for bank transaction operations
    public void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createTransaction();
                    break;
                case 2:
                    updateTransaction();
                    break;
                case 3:
                    deleteTransaction();
                    break;
                case 4:
                    getTransactionById();
                    break;
                case 5:
                    getAllTransactions();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
