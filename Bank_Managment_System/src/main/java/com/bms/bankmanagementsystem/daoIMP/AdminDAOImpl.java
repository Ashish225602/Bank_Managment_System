package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.Admin;
import com.bms.bankmanagementsystem.dao.AdminDAO;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public void saveAdmin(Admin admin) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(admin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(admin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void deleteAdmin(Long adminId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Admin admin = session.get(Admin.class, adminId);
            if (admin != null) {
                session.delete(admin);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public Admin getAdminById(Long adminId) {
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            admin = session.get(Admin.class, adminId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            admins = session.createQuery("FROM Admin", Admin.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return admins;
    }
}
