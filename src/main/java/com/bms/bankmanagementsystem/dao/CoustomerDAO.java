package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Coustomer;

import java.util.List;

public interface CoustomerDAO {
    void saveCoustomer(Coustomer coustomer); // Save a new customer
    void updateCoustomer(Coustomer coustomer); // Update an existing customer
    void deleteCoustomer(Long coustomerId); // Delete a customer by ID
    Coustomer getCoustomerById(Long coustomerId); // Retrieve a customer by ID
    List<Coustomer> getAllCoustomers(); // Retrieve all customers
}
