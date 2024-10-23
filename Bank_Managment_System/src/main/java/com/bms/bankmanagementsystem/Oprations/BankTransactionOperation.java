package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.BankTransaction;
import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.service.BankTransactionService;
import com.bms.bankmanagementsystem.service.AccountService;
import com.bms.bankmanagementsystem.service.ATMService;
import com.bms.bankmanagementsystem.service.CoustomerService;
import com.bms.bankmanagementsystem.serviceIMP.BankTransactionServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.AccountServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.ATMServiceImpl;
import com.bms.bankmanagementsystem.serviceIMP.CoustomerServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BankTransactionOperation {

	private BankTransactionService bankTransactionService;
	private AccountService accountService;
	private ATMService atmService;
	private CoustomerService coustomerService;
	private Scanner scanner;

	public BankTransactionOperation(Scanner scanner) {
		this.scanner = scanner; // Store the passed scanner
		this.bankTransactionService = new BankTransactionServiceImpl(); // Initialize the service
		this.accountService = new AccountServiceImpl(); // Initialize the Account service
		this.atmService = new ATMServiceImpl(); // Initialize the ATM service
		this.coustomerService = new CoustomerServiceImpl(); // Initialize the Coustomer service
	}

	// Default constructor
	public BankTransactionOperation() {
		this.bankTransactionService = new BankTransactionServiceImpl();
		this.scanner = new Scanner(System.in); // Initialize with a new Scanner if not provided
	}

	// Method to create a transaction when a withdrawal is made
	public void createWithdrawalTransaction(Long accountId, Long atmId, BigDecimal amount) {
		Account account = accountService.getAccountById(accountId);
		ATM atm = atmService.getATMById(atmId);
		Coustomer coustomer = account.getCoustomer(); // Assuming Coustomer is associated with Account

		if (account != null && atm != null && coustomer != null) {
			String transactionType = "Withdrawal";
			Date transactionDate = new Date(); // Current date and time

			// Create and save the transaction
			BankTransaction transaction = new BankTransaction(transactionType, amount, transactionDate, account, atm, coustomer);
			bankTransactionService.saveTransaction(transaction);

			System.out.println("Transaction created successfully: " + transaction);
		} else {
			System.out.println("Account, ATM, or Coustomer details not found!");
		}
	}

	// Method to display menu options for bank transaction operations for users
	public void displayMenuForUser() {
		System.out.println("------ Bank Transaction ------");
		System.out.println("1. Get Transaction By ID");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
	}

	// Method to display menu options for bank transaction operations for admins
	public void displayMenuForAdmin() {
		System.out.println("------ Bank Transaction Management ------");
		System.out.println("1. Create Transaction");
		System.out.println("2. Update Transaction");
		System.out.println("3. Delete Transaction");
		System.out.println("4. Get Transaction By ID");
		System.out.println("5. Get All Transactions");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
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

	// Main loop for bank transaction operations for users
	public void startUserOperations() {
		while (true) {
			displayMenuForUser();
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				getTransactionById();
				break;
			case 0:
				System.out.println("Exiting...");
				return; // Exit without closing the scanner here
			default:
				System.out.println("Invalid option! Please try again.");
			}
		}
	}

	// Main loop for bank transaction operations for admins
	// Main loop for bank transaction operations for admins
	public void startAdminOperations() {
		while (true) {
			displayMenuForAdmin();
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				System.out.print("Enter Account ID: ");
				Long accountId = scanner.nextLong();
				scanner.nextLine(); // Consume newline

				System.out.print("Enter ATM ID: ");
				Long atmId = scanner.nextLong();
				scanner.nextLine(); // Consume newline

				System.out.print("Enter withdrawal amount: ");
				BigDecimal amount = scanner.nextBigDecimal();
				scanner.nextLine(); // Consume newline

				createWithdrawalTransaction(accountId, atmId, amount); // Updated to use createWithdrawalTransaction()
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
				return; // Exit without closing the scanner here
			default:
				System.out.println("Invalid option! Please try again.");
			}
		}
	}


}
