package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.dao.ATMDAO;
import com.bms.bankmanagementsystem.dao.AccountDAO;
import com.bms.bankmanagementsystem.daoIMP.ATMDAOImpl;
import com.bms.bankmanagementsystem.daoIMP.AccountDAOImpl;
import com.bms.bankmanagementsystem.service.ATMService;

import java.math.BigDecimal;
import java.util.List;

public class ATMServiceImpl implements ATMService {

    private ATMDAO atmDAO;
    private AccountDAO accountDAO;

    // Constructor to initialize the DAOs
    public ATMServiceImpl() {
        this.atmDAO = new ATMDAOImpl(); // Instantiate the ATM DAO
        this.accountDAO = new AccountDAOImpl(); // Instantiate the Account DAO
    }

    @Override
    public void saveATM(ATM atm) {
        atmDAO.saveATM(atm);
    }

    @Override
    public void updateATM(ATM atm) {
        atmDAO.updateATM(atm);
    }

    @Override
    public void deleteATM(Long atmId) {
        atmDAO.deleteATM(atmId);
    }

    @Override
    public ATM getATMById(Long atmId) {
        return atmDAO.getATMById(atmId);
    }

    @Override
    public List<ATM> getAllATMs() {
        return atmDAO.getAllATMs();
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountDAO.getAccountById(accountId);
    }

    // Withdraw method refactored to align with AccountService interface
    @Override
    public void withdraw(Account account, double amount) {
        // Check if the account has enough balance
        BigDecimal amountToWithdraw = BigDecimal.valueOf(amount);
        if (account.getBalance().compareTo(amountToWithdraw) >= 0) {
            BigDecimal newBalance = account.getBalance().subtract(amountToWithdraw);
            account.setBalance(newBalance);
            accountDAO.updateAccount(account); // Update the account with the new balance
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
