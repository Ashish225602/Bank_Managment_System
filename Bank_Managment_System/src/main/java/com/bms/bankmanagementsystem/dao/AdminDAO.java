package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Admin;

import java.util.List;

public interface AdminDAO {
    void saveAdmin(Admin admin); // Save a new admin
    void updateAdmin(Admin admin); // Update an existing admin
    void deleteAdmin(Long adminId); // Delete an admin by ID
    Admin getAdminById(Long adminId); // Retrieve an admin by ID
    List<Admin> getAllAdmins(); // Retrieve all admins
}
