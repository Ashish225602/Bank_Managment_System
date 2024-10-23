package com.bms.bankmanagementsystem;

import com.bms.bankmanagementsystem.Oprations.*;
import com.bms.bankmanagementsystem.serviceIMP.*;

import java.util.Scanner;

public class App {
	private static final Scanner scanner = new Scanner(System.in); // Use a single static scanner

	public static void main(String[] args) {
		// Initialize operations
		AccountOperation accountOperations = new AccountOperation(scanner);
		ATMOperation atmOperations = new ATMOperation(scanner);
		ReportOperations reportOperations = new ReportOperations(scanner);
		LoanOperations loanOperations = new LoanOperations(scanner);
		EmployeeOperations employeeOperations = new EmployeeOperations(scanner);
		CoustomerOperations coustomerOperations = new CoustomerOperations(scanner);
		AdminOperation adminOperation = new AdminOperation(scanner);
		BankTransactionOperation transactionOperation = new BankTransactionOperation(scanner); // Pass scanner to constructor

		// Main menu
		while (true) {
			displayMainMenu();
			int choice = getValidIntegerInput();

			switch (choice) {
			case 1:
				createCustomerAndAccount(coustomerOperations, accountOperations);
				break;
			case 2:
				accountOperations.startForUser();                   
				break;
			case 3:
				atmOperations.startForUser(); // Start ATM operations
				break;
			case 4:
				loanOperations.startForUser(); // Start loan operations
				break;
			case 5:
				transactionOperation.startUserOperations(); // Use the transaction operations
				break;
			case 6:
				adminOperation.startForAdmin(); // Start admin operations
				break;
			case 7:
				System.out.println("Exiting the system. Thank you!");
				scanner.close(); // Close the scanner
				return; // Exit the program
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayMainMenu() {
		System.out.println("\n===============================");
		System.out.println("===   Banking Management System   ===");
		System.out.println("===============================\n");
		System.out.println("         Welcome to Bank         ");
		System.out.println("=================================");
		System.out.printf("1. Create New Account%n");
		System.out.printf("2. Manage Account%n");
		System.out.printf("3. Use ATM%n");
		System.out.printf("4. Loan Operations%n");
		System.out.printf("5. Get Transaction Details%n");
		System.out.printf("6. Admin (Administrative person only)%n");
		System.out.printf("7. Exit%n");
		System.out.print("\nEnter your choice: ");
	}


	private static void createCustomerAndAccount(CoustomerOperations coustomerOperations, AccountOperation accountOperations) {
		// Create Customer
		coustomerOperations.addCoustomer(); // This method should be public in CoustomerOperations

		// After creating a customer, create the account
		accountOperations.createAccount(); // This should prompt for account details
	}

	// Method to get a valid integer input from the user
	public static int getValidIntegerInput() {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid integer: ");
			}
		}
	}
}
