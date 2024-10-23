package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.dao.CoustomerDAO;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CoustomerDAOImpl implements CoustomerDAO {

    @Override
    public void saveCoustomer(Coustomer coustomer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(coustomer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void updateCoustomer(Coustomer coustomer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(coustomer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public void deleteCoustomer(Long coustomerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Coustomer coustomer = session.get(Coustomer.class, coustomerId);
            if (coustomer != null) {
                session.delete(coustomer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception
        }
    }

    @Override
    public Coustomer getCoustomerById(Long coustomerId) {
        Coustomer coustomer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            coustomer = session.get(Coustomer.class, coustomerId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return coustomer;
    }

    @Override
    public List<Coustomer> getAllCoustomers() {
        List<Coustomer> coustomers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            coustomers = session.createQuery("FROM Coustomer", Coustomer.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return coustomers;
    }
}
