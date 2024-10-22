package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.BankTransaction;
import com.bms.bankmanagementsystem.dao.BankTransactionDAO;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BankTransactionDAOImpl implements BankTransactionDAO {

    @Override
    public void saveTransaction(BankTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void updateTransaction(BankTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            BankTransaction transaction = session.get(BankTransaction.class, transactionId);
            if (transaction != null) {
                session.delete(transaction);
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
    public BankTransaction getTransactionById(Long transactionId) {
        BankTransaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.get(BankTransaction.class, transactionId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return transaction;
    }

    @Override
    public List<BankTransaction> getAllTransactions() {
        List<BankTransaction> transactions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transactions = session.createQuery("FROM BankTransaction", BankTransaction.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return transactions;
    }
}
