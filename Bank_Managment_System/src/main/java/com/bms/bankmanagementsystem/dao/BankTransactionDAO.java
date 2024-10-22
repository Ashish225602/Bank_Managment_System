package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.BankTransaction;

import java.util.List;

public interface BankTransactionDAO {
    void saveTransaction(BankTransaction transaction); // Save a new transaction
    void updateTransaction(BankTransaction transaction); // Update an existing transaction
    void deleteTransaction(Long transactionId); // Delete a transaction by ID
    BankTransaction getTransactionById(Long transactionId); // Retrieve a transaction by ID
    List<BankTransaction> getAllTransactions(); // Retrieve all transactions
}
