package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.BankTransaction;
import com.bms.bankmanagementsystem.dao.BankTransactionDAO;
import com.bms.bankmanagementsystem.daoIMP.BankTransactionDAOImpl;
import com.bms.bankmanagementsystem.service.BankTransactionService;

import java.util.List;

public class BankTransactionServiceImpl implements BankTransactionService {

    private BankTransactionDAO bankTransactionDAO;

    // Constructor to initialize the DAO
    public BankTransactionServiceImpl() {
        this.bankTransactionDAO = new BankTransactionDAOImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveTransaction(BankTransaction transaction) {
        bankTransactionDAO.saveTransaction(transaction);
    }

    @Override
    public void updateTransaction(BankTransaction transaction) {
        bankTransactionDAO.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        bankTransactionDAO.deleteTransaction(transactionId);
    }

    @Override
    public BankTransaction getTransactionById(Long transactionId) {
        return bankTransactionDAO.getTransactionById(transactionId);
    }

    @Override
    public List<BankTransaction> getAllTransactions() {
        return bankTransactionDAO.getAllTransactions();
    }
}
