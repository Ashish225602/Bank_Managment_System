package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId; // Auto-generated primary key

    private String transactionType; // Type of transaction (e.g., Withdrawal, Deposit)

    @Column(precision = 15, scale = 2) // Precision for monetary values
    private BigDecimal amount; // Amount involved in the transaction

    @Temporal(TemporalType.TIMESTAMP) // Timestamp for when the transaction occurred
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id") // Foreign key reference to Account entity
    private Account account;

    // Reference to ATM
    @ManyToOne
    @JoinColumn(name = "atm_id") // Foreign key reference to ATM entity
    private ATM atm;

    // Reference to Customer (optional but recommended for tracking)
    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign key reference to Coustomer entity
    private Coustomer coustomer;

    // Default Constructor
    public BankTransaction() {
        super();
    }

    // Parameterized Constructor
    public BankTransaction(String transactionType, BigDecimal amount, Date transactionDate, Account account, ATM atm, Coustomer coustomer) {
        super();
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
        this.atm = atm;
        this.coustomer = coustomer; // Initialize the customer associated with the transaction
    }

    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public Coustomer getCoustomer() {
        return coustomer;
    }

    public void setCoustomer(Coustomer coustomer) {
        this.coustomer = coustomer;
    }

    // Overriding toString() method for easy debugging and logging
    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", account=" + account +
                ", atm=" + atm +
                ", coustomer=" + coustomer +
                '}';
    }
}
