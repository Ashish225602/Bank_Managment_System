package com.bms.bankmanagementsystem.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId; // Auto-generated primary key

    private String adminName; // Name of the admin
    private String adminPassword; // Password for the admin

    // Default Constructor
    public Admin() {
        super();
    }

    // Parameterized Constructor
    public Admin(String adminName, String adminPassword) {
        super();
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }

    // Getters and Setters
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    // Overriding toString() method for easy debugging and logging
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
