package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Report;
import com.bms.bankmanagementsystem.Entity.BankTransaction; // Make sure this is correct
import com.bms.bankmanagementsystem.Entity.Employee; // Make sure this is correct
import com.bms.bankmanagementsystem.service.ReportService;
import com.bms.bankmanagementsystem.serviceIMP.ReportServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReportOperations {
    private ReportService reportService;
    private Scanner scanner;
    
    public ReportOperations(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
    }

    public ReportOperations() {
        this.reportService = new ReportServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Report Management Menu:");
            System.out.println("1. Add Report");
            System.out.println("2. View Report");
            System.out.println("3. View All Reports");
            System.out.println("4. Update Report");
            System.out.println("5. Delete Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addReport();
                    break;
                case 2:
                    viewReport();
                    break;
                case 3:
                    viewAllReports();
                    break;
                case 4:
                    updateReport();
                    break;
                case 5:
                    deleteReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addReport() {
        System.out.print("Enter report type: ");
        String reportType = scanner.nextLine();
        System.out.print("Enter report details: ");
        String reportDetails = scanner.nextLine();
        Date reportDate = new Date(); // Current date

        // Assuming you have methods to get BankTransaction and Employee by ID
        System.out.print("Enter transaction ID: ");
        Long transactionId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        BankTransaction transaction = new BankTransaction(); // Retrieve the actual BankTransaction
        transaction.setTransactionId(transactionId); // Assuming you have a method to fetch transaction

        System.out.print("Enter employee ID: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Employee employee = new Employee(); // Retrieve the actual Employee
        employee.setEmployeeId(employeeId); // Assuming you have a method to fetch employee

        Report report = new Report(null, reportType, reportDate, reportDetails, transaction, employee);
        reportService.saveReport(report);
        System.out.println("Report added successfully.");
    }

    private void viewReport() {
        System.out.print("Enter report ID to view: ");
        Long reportId = scanner.nextLong();
        Report report = reportService.getReport(reportId);
        if (report != null) {
            System.out.println("Report Details: " + report);
        } else {
            System.out.println("Report not found.");
        }
    }

    private void viewAllReports() {
        List<Report> reports = reportService.getAllReports();
        if (reports.isEmpty()) {
            System.out.println("No reports found.");
        } else {
            System.out.println("All Reports:");
            for (Report report : reports) {
                System.out.println(report);
            }
        }
    }

    private void updateReport() {
        System.out.print("Enter report ID to update: ");
        Long reportId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Report report = reportService.getReport(reportId);
        if (report != null) {
            System.out.print("Enter new report type (leave blank to keep current): ");
            String reportType = scanner.nextLine();
            if (!reportType.isEmpty()) report.setReportType(reportType);

            System.out.print("Enter new report details (leave blank to keep current): ");
            String reportDetails = scanner.nextLine();
            if (!reportDetails.isEmpty()) report.setReportDetails(reportDetails);

            // Optional: Update transaction and employee if needed
            System.out.print("Enter new transaction ID (leave blank to keep current): ");
            String transactionIdInput = scanner.nextLine();
            if (!transactionIdInput.isEmpty()) {
                BankTransaction transaction = new BankTransaction();
                transaction.setTransactionId(Long.valueOf(transactionIdInput)); // Fetch transaction
                report.setTransaction(transaction);
            }

            System.out.print("Enter new employee ID (leave blank to keep current): ");
            String employeeIdInput = scanner.nextLine();
            if (!employeeIdInput.isEmpty()) {
                Employee employee = new Employee();
                employee.setEmployeeId(Long.valueOf(employeeIdInput)); // Fetch employee
                report.setEmployee(employee);
            }

            reportService.updateReport(report);
            System.out.println("Report updated successfully.");
        } else {
            System.out.println("Report not found.");
        }
    }

    private void deleteReport() {
        System.out.print("Enter report ID to delete: ");
        Long reportId = scanner.nextLong();
        reportService.deleteReport(reportId);
        System.out.println("Report deleted successfully.");
    }
}
