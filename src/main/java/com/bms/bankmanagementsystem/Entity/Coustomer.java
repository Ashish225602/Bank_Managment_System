package com.bms.bankmanagementsystem.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Coustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coustomerId; // Auto-generated primary key

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String emailId;

    // Bidirectional One-to-Many relationship with Account, with CascadeType to automatically handle changes in related accounts
    @OneToMany(mappedBy = "coustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>(); // List of accounts associated with the customer

    // Default constructor
    public Coustomer() {
        super(); // Super constructor (useful if we extend this class in the future)
    }

    // Parameterized constructor to initialize fields
    public Coustomer(String firstName, String lastName, String address, String phoneNumber, String emailId) {
        super(); // Super constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    // Copy constructor to create a new Coustomer object from an existing one
    public Coustomer(Coustomer other) {
        super(); // Super constructor
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.address = other.address;
        this.phoneNumber = other.phoneNumber;
        this.emailId = other.emailId;
        this.accounts = new ArrayList<>(other.accounts);
    }

    // Add an account to the customer and set the bidirectional relationship
    public void addAccount(Account account) {
        accounts.add(account);
        account.setCoustomer(this); // Ensure the account's reference to Coustomer is properly set
    }

    // Remove an account from the customer
    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCoustomer(null); // Unlink the customer from the account
    }

    // Getters and Setters
    public Long getCoustomerId() {
        return coustomerId;
    }

    public void setCoustomerId(Long coustomerId) {
        this.coustomerId = coustomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Coustomer [coustomerId=" + coustomerId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", address=" + address + ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + "]";
    }
}
