package com.bms.bankmanagementsystem.daoIMP;

import com.bms.bankmanagementsystem.Entity.Report;
import com.bms.bankmanagementsystem.dao.ReportDao;
import com.bms.bankmanagementsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReportDaoImpl implements ReportDao {

    @Override
    public void saveReport(Report report) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(report);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Report getReport(Long reportId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Report.class, reportId);
        }
    }

    @Override
    public List<Report> getAllReports() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Report", Report.class).list();
        }
    }

    @Override
    public void updateReport(Report report) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(report);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReport(Long reportId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Report report = session.get(Report.class, reportId);
            if (report != null) {
                session.delete(report);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
