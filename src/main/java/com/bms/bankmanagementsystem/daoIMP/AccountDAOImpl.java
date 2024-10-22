package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.Account;
import com.bms.bankmanagementsystem.dao.AccountDAO;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void saveAccount(Account account) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(account);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
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

    @Override
    public void deleteAccount(Long accountId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                session.delete(account);
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
    public List<Account> getAllAccounts() {
        List<Account> accounts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            accounts = session.createQuery("FROM Account", Account.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return accounts;
    }
}
