package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.dao.AccountDAO;
import com.bms.bankmanagementsystem.daoIMP.AccountDAOImpl;
import com.bms.bankmanagementsystem.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    // Constructor to initialize the DAO
    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveAccount(Account account) {
        accountDAO.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountDAO.deleteAccount(accountId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountDAO.getAccountById(accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    
    @Override
    public void withdraw(Account account, double amount) {
        // Check if the account has enough balance
        BigDecimal amountToWithdraw = BigDecimal.valueOf(amount);
        if (account.getBalance().compareTo(amountToWithdraw) >= 0) {
            BigDecimal newBalance = account.getBalance().subtract(amountToWithdraw);
            account.setBalance(newBalance);
            accountDAO.updateAccount(account); // Update the account with the new balance
            System.out.println("Withdrawal successful. New balance: " + newBalance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
