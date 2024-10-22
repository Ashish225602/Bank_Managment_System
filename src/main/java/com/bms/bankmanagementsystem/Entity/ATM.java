package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmId; // Auto-generated primary key

    private String location; // Location of the ATM
    private String bankName; // Name of the bank that owns the ATM
    private double cashAvailable; // Cash available in the ATM

    // One ATM can have many transactions
    @OneToMany(mappedBy = "atm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BankTransaction> transactions = new HashSet<>(); // Transactions related to this ATM

    // Default constructor
    public ATM() {
        super(); // Super constructor
    }

    // Parameterized constructor
    public ATM(String location, String bankName, double cashAvailable) {
        super(); // Super constructor
        this.location = location;
        this.bankName = bankName;
        this.cashAvailable = cashAvailable;
    }

    // Getters and Setters
    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getCashAvailable() {
        return cashAvailable;
    }

    // Set the available cash in the ATM
    public void setCashAvailable(double cashAvailable) {
        this.cashAvailable = cashAvailable;
    }

    public Set<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    // Method to withdraw cash from the ATM
    public boolean withdrawCash(double amount) {
        if (amount > 0 && cashAvailable >= amount) {
            this.cashAvailable -= amount;
            return true; // Successful withdrawal
        }
        return false; // Insufficient funds or invalid amount
    }

    // Method to check the balance in the ATM
    public double checkBalance() {
        return this.cashAvailable;
    }

    @Override
    public String toString() {
        return "ATM [atmId=" + atmId + ", location=" + location + ", bankName=" + bankName + ", cashAvailable=" + cashAvailable + "]";
    }
}
