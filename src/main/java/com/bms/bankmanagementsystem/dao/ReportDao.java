package com.bms.bankmanagementsystem.dao;

import com.bms.bankmanagementsystem.Entity.Report;

import java.util.List;

public interface ReportDao {
    void saveReport(Report report);
    Report getReport(Long reportId);
    List<Report> getAllReports();
    void updateReport(Report report);
    void deleteReport(Long reportId);
}
