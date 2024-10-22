package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.dao.CoustomerDAO;
import com.bms.bankmanagementsystem.daoIMP.CoustomerDAOImpl;
import com.bms.bankmanagementsystem.service.CoustomerService;

import java.util.List;

public class CoustomerServiceImpl implements CoustomerService {

    private CoustomerDAO coustomerDAO;

    // Constructor to initialize the DAO
    public CoustomerServiceImpl() {
        this.coustomerDAO = new CoustomerDAOImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveCoustomer(Coustomer coustomer) {
        coustomerDAO.saveCoustomer(coustomer);
    }

    @Override
    public void updateCoustomer(Coustomer coustomer) {
        coustomerDAO.updateCoustomer(coustomer);
    }

    @Override
    public void deleteCoustomer(Long coustomerId) {
        coustomerDAO.deleteCoustomer(coustomerId);
    }

    @Override
    public Coustomer getCoustomerById(Long coustomerId) {
        return coustomerDAO.getCoustomerById(coustomerId);
    }

    @Override
    public List<Coustomer> getAllCoustomers() {
        return coustomerDAO.getAllCoustomers();
    }
}
