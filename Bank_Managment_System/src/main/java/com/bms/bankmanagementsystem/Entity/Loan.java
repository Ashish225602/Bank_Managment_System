package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId; // Auto-generated primary key

    private String loanType; // Type of loan (e.g., Home Loan, Personal Loan)
    
    @Column(precision = 15, scale = 2) // Precision for monetary values
    private BigDecimal loanAmount; // Amount of the loan, using BigDecimal for precision
    
    private String duration; // Duration of the loan (e.g., "10 years" or "120 months")

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false) // Foreign key reference to Customer entity
    private Coustomer customer;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false) // Foreign key reference to Account entity
    private Account account; // Reference to the account associated with the loan

    // Default Constructor
    public Loan() {
        super();
    }

    // Parameterized Constructor
    public Loan(String loanType, BigDecimal loanAmount, String duration, Coustomer customer, Account account) {
        super();
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.duration = duration;
        this.customer = customer; // Initialize the customer associated with the loan
        this.account = account; // Initialize the account associated with the loan
    }

    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Coustomer getCustomer() {
        return customer;
    }

    public void setCustomer(Coustomer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    // Overriding toString() method for easy debugging and logging
    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanType='" + loanType + '\'' +
                ", loanAmount=" + loanAmount +
                ", duration='" + duration + '\'' +
                ", customer=" + customer +
                ", account=" + account +
                '}';
    }
}
