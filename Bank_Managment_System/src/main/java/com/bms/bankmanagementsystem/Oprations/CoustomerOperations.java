package com.bms.bankmanagementsystem.Oprations;

import com.bms.bankmanagementsystem.Entity.Coustomer;
import com.bms.bankmanagementsystem.service.CoustomerService;
import com.bms.bankmanagementsystem.serviceIMP.CoustomerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CoustomerOperations {
    private CoustomerService coustomerService; // Make sure this is initialized
    private Scanner scanner;

    public CoustomerOperations(Scanner scanner) {
        this.scanner = scanner; // Store the passed scanner
        this.coustomerService = new CoustomerServiceImpl(); // Initialize coustomerService
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nCoustomer Management System");
            System.out.println("1. Add Coustomer");
            System.out.println("2. Update Coustomer");
            System.out.println("3. Delete Coustomer");
            System.out.println("4. Get Coustomer By ID");
            System.out.println("5. Get All Coustomers");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCoustomer();
                    break;
                case 2:
                    updateCoustomer();
                    break;
                case 3:
                    deleteCoustomer();
                    break;
                case 4:
                    getCoustomerById();
                    break;
                case 5:
                    getAllCoustomers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addCoustomer() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email ID: ");
        String emailId = scanner.nextLine();

        Coustomer coustomer = new Coustomer(firstName, lastName, address, phoneNumber, emailId);
        coustomerService.saveCoustomer(coustomer);
        Long coustomerId = coustomer.getCoustomerId(); // Get the generated Coustomer ID
        System.out.println("Coustomer added successfully! Your Coustomer ID is: " + coustomerId);
    }

    private void updateCoustomer() {
        System.out.print("Enter Coustomer ID to update: ");
        Long coustomerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Coustomer existingCoustomer = coustomerService.getCoustomerById(coustomerId);
        if (existingCoustomer == null) {
            System.out.println("Coustomer not found!");
            return;
        }

        System.out.print("Enter New First Name (leave blank to keep current): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            existingCoustomer.setFirstName(firstName);
        }

        System.out.print("Enter New Last Name (leave blank to keep current): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            existingCoustomer.setLastName(lastName);
        }

        System.out.print("Enter New Address (leave blank to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            existingCoustomer.setAddress(address);
        }

        System.out.print("Enter New Phone Number (leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            existingCoustomer.setPhoneNumber(phoneNumber);
        }

        System.out.print("Enter New Email ID (leave blank to keep current): ");
        String emailId = scanner.nextLine();
        if (!emailId.isEmpty()) {
            existingCoustomer.setEmailId(emailId);
        }

        coustomerService.updateCoustomer(existingCoustomer);
        System.out.println("Coustomer updated successfully!");
    }

    private void deleteCoustomer() {
        System.out.print("Enter Coustomer ID to delete: ");
        Long coustomerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        coustomerService.deleteCoustomer(coustomerId);
        System.out.println("Coustomer deleted successfully!");
    }

    private void getCoustomerById() {
        System.out.print("Enter Coustomer ID: ");
        Long coustomerId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Coustomer coustomer = coustomerService.getCoustomerById(coustomerId);
        if (coustomer != null) {
            System.out.println("Coustomer Details: " + coustomer);
        } else {
            System.out.println("Coustomer not found!");
        }
    }

    private void getAllCoustomers() {
        List<Coustomer> coustomers = coustomerService.getAllCoustomers();
        if (coustomers.isEmpty()) {
            System.out.println("No Coustomers found!");
        } else {
            System.out.println("All Coustomers:");
            for (Coustomer coustomer : coustomers) {
                System.out.println(coustomer);
            }
        }
    }
}
