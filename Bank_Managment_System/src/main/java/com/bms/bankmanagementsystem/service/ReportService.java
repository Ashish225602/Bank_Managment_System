package com.bms.bankmanagementsystem.service;

import com.bms.bankmanagementsystem.Entity.Report;

import java.util.List;

public interface ReportService {
    void saveReport(Report report); // Save a new report
    Report getReport(Long reportId); // Retrieve a report by ID
    List<Report> getAllReports(); // Retrieve all reports
    void updateReport(Report report); // Update an existing report
    void deleteReport(Long reportId); // Delete a report by ID
}
