package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Loan;

import java.util.List;

public interface LoanDao {
    void saveLoan(Loan loan);
    Loan getLoan(Long loanId);
    List<Loan> getAllLoans();
    void updateLoan(Loan loan);
    void deleteLoan(Long loanId);
}
