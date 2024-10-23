package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId; // Auto-generated primary key

    private String accountNumber; // Account number starting from 10001000
    private String accountType;
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL) // Cascade ensures Coustomer is automatically created or updated
    @JoinColumn(name = "coustomerId", nullable = true) // Nullable, since account creation should not depend on a pre-existing Coustomer
    private Coustomer coustomer; // Reference to the Coustomer entity

    // Static field to keep track of the last assigned account number
    private static Long accountNumberCounter = 10001000L;

    // Default constructor
    public Account() {
        this.balance = BigDecimal.ZERO; // Initialize balance to zero
    }

    // Constructor with parameters for accountType, balance, and customer
    public Account(String accountType, BigDecimal balance, Coustomer coustomer) {
        this.accountType = accountType;
        this.balance = balance;
        this.coustomer = coustomer;
    }

    // PrePersist method to generate the account number before saving to the database
    @PrePersist
    protected void onCreate() {
        this.accountNumber = generateAccountNumber();
    }

    // Method to generate a new account number
    private synchronized String generateAccountNumber() {
        return String.valueOf(accountNumberCounter++);
    }

    // Getters and Setters

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Coustomer getCoustomer() {
        return coustomer;
    }

    public void setCoustomer(Coustomer coustomer) {
        this.coustomer = coustomer;
    }

    // Business logic methods (Deposit, Withdraw)
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(this.balance) <= 0) {
            this.balance = this.balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
                + ", balance=" + balance + ", coustomer=" + coustomer + "]";
    }
}
