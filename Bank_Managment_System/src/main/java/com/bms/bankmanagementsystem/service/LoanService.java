package com.bms.bankmanagementsystem.service;

import com.bms.bankmanagementsystem.Entity.Loan;

import java.util.List;

public interface LoanService {
    void saveLoan(Loan loan); // Save a new loan
    Loan getLoan(Long loanId); // Retrieve a loan by ID
    List<Loan> getAllLoans(); // Retrieve all loans
    void updateLoan(Loan loan); // Update an existing loan
    void deleteLoan(Long loanId); // Delete a loan by ID
}
