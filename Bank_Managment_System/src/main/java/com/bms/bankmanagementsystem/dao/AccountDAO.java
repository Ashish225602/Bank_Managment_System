package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Account;

import java.util.List;

public interface AccountDAO {
    void saveAccount(Account account); // Save a new account
    void updateAccount(Account account); // Update an existing account
    void deleteAccount(Long accountId); // Delete an account by ID
    Account getAccountById(Long accountId); // Retrieve an account by ID
    List<Account> getAllAccounts(); // Retrieve all accounts
}
