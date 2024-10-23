package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.ATM;
import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.dao.ATMDAO;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ATMDAOImpl implements ATMDAO {

    @Override
    public void saveATM(ATM atm) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(atm);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void updateATM(ATM atm) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(atm);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void deleteATM(Long atmId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ATM atm = session.get(ATM.class, atmId);
            if (atm != null) {
                session.delete(atm);
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
    public ATM getATMById(Long atmId) {
        ATM atm = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            atm = session.get(ATM.class, atmId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return atm;
    }

    @Override
    public List<ATM> getAllATMs() {
        List<ATM> atms = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            atms = session.createQuery("FROM ATM", ATM.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return atms;
    }
    @Override
    public Account getAccountById(Long accountId) {
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            account = session.get(Account.class, accountId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(account);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }
}
