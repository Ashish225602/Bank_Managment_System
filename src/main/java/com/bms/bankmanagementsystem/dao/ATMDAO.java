package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Account;

import java.util.List;

public interface ATMDAO {
    void saveATM(ATM atm); // Save a new ATM
    void updateATM(ATM atm); // Update an existing ATM
    void deleteATM(Long atmId); // Delete an ATM by ID
    ATM getATMById(Long atmId); // Retrieve an ATM by ID
    List<ATM> getAllATMs(); // Retrieve all ATMs
    Account getAccountById(Long accountId); // Retrieve an Account by ID
    void updateAccount(Account account); // Update an Account's balance or details
}
