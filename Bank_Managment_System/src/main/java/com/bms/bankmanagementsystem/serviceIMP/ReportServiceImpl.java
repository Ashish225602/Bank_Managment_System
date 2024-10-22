package com.bms.bankmanagementsystem.serviceIMP;

import com.bms.bankmanagementsystem.Entity.Report;
import com.bms.bankmanagementsystem.dao.ReportDao;
import com.bms.bankmanagementsystem.daoIMP.ReportDaoImpl;
import com.bms.bankmanagementsystem.service.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao;

    // Constructor to initialize the DAO
    public ReportServiceImpl() {
        this.reportDao = new ReportDaoImpl(); // Instantiate the DAO implementation
    }

    @Override
    public void saveReport(Report report) {
        reportDao.saveReport(report);
    }

    @Override
    public Report getReport(Long reportId) {
        return reportDao.getReport(reportId);
    }

    @Override
    public List<Report> getAllReports() {
        return reportDao.getAllReports();
    }

    @Override
    public void updateReport(Report report) {
        reportDao.updateReport(report);
    }

    @Override
    public void deleteReport(Long reportId) {
        reportDao.deleteReport(reportId);
    }
}
