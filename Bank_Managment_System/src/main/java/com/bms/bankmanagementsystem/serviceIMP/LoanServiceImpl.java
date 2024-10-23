package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Loan;
import com.bms.bankmanagementsystem.dao.LoanDao;
import com.bms.bankmanagementsystem.daoIMP.LoanDaoImpl;
import com.bms.bankmanagementsystem.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    private LoanDao loanDao;

    // Constructor to initialize the DAO
    public LoanServiceImpl() {
        this.loanDao = new LoanDaoImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveLoan(Loan loan) {
        loanDao.saveLoan(loan);
    }

    @Override
    public Loan getLoan(Long loanId) {
        return loanDao.getLoan(loanId);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanDao.getAllLoans();
    }

    @Override
    public void updateLoan(Loan loan) {
        loanDao.updateLoan(loan);
    }

    @Override
    public void deleteLoan(Long loanId) {
        loanDao.deleteLoan(loanId);
    }
}
