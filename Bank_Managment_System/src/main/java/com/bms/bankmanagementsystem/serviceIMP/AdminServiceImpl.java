package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Admin;
import com.bms.bankmanagementsystem.dao.AdminDAO;
import com.bms.bankmanagementsystem.daoIMP.AdminDAOImpl;
import com.bms.bankmanagementsystem.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;

    // Constructor to initialize the DAO
    public AdminServiceImpl() {
        this.adminDAO = new AdminDAOImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminDAO.saveAdmin(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminDAO.deleteAdmin(adminId);
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminDAO.getAdminById(adminId);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }
}
