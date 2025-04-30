package com.gisele.landmanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Land> landRegistry = new ArrayList<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == 5) {
                System.out.println("Exiting program...");
                break;
            }
            handleMenuChoice(choice);
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Land Management System ===");
        System.out.println("1. Register a new land");
        System.out.println("2. Generate land report");
        System.out.println("3. Search lands by type");
        System.out.println("4. Search lands by owner");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                return -1;
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                registerLand();
                break;
            case 2:
                generateLandReport();
                break;
            case 3:
                searchByType();
                break;
            case 4:
                searchByOwner();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void registerLand() {
        try {
            System.out.println("\nSelect land type:");
            System.out.println("1. Agricultural Land");
            System.out.println("2. Residential Land");
            System.out.println("3. Commercial Land");
            System.out.println("4. Industrial Land");
            System.out.print("Enter choice (1-4): ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter land ID: ");
            String landId = scanner.nextLine();
            System.out.print("Enter owner name: ");
            String ownerName = scanner.nextLine();
            System.out.print("Enter location (e.g., Rural Area, Residential Zone): ");
            String location = scanner.nextLine();
            System.out.print("Enter size in acres: ");
            double sizeInAcres = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter registration date (yyyy-MM-dd): ");
            Date registrationDate = sdf.parse(scanner.nextLine());
            System.out.print("Enter land use status (Vacant, In Use, Under Development): ");
            String landUseStatus = scanner.nextLine();

            Land land;
            switch (type) {
                case 1:
                    land = new AgriculturalLand(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
                    break;
                case 2:
                    System.out.print("Enter number of residential units: ");
                    int residentialUnits = Integer.parseInt(scanner.nextLine());
                    land = new ResidentialLand(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus, residentialUnits);
                    break;
                case 3:
                    land = new CommercialLand(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
                    break;
                case 4:
                    System.out.print("Has environmental clearance? (true/false): ");
                    boolean hasEnvironmentalClearance = Boolean.parseBoolean(scanner.nextLine());
                    land = new IndustrialLand(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus, hasEnvironmentalClearance);
                    break;
                default:
                    System.out.println("Invalid land type.");
                    return;
            }
            landRegistry.add(land);
            System.out.println("Land registered successfully!");
        } catch (Exception e) {
            System.out.println("Error registering land: " + e.getMessage());
        }
    }

    private static void generateLandReport() {
        if (landRegistry.isEmpty()) {
            System.out.println("No lands registered.");
            return;
        }

        try {
            System.out.println("\nRegistered lands:");
            for (int i = 0; i < landRegistry.size(); i++) {
                System.out.println((i + 1) + ". Land ID: " + landRegistry.get(i).getLandId());
            }
            System.out.print("Select land (1-" + landRegistry.size() + "): ");
            int landIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (landIndex < 0 || landIndex >= landRegistry.size()) {
                System.out.println("Invalid land selection.");
                return;
            }

            Land land = landRegistry.get(landIndex);
            System.out.println("\nLand Report:");
            System.out.println(land.generateLandReport());
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    private static void searchByType() {
        System.out.println("\nSelect land type to search:");
        System.out.println("1. Agricultural");
        System.out.println("2. Residential");
        System.out.println("3. Commercial");
        System.out.println("4. Industrial");
        System.out.print("Enter choice (1-4): ");
        try {
            int type = Integer.parseInt(scanner.nextLine());
            String typeName;
            Class<?> typeClass;
            switch (type) {
                case 1:
                    typeName = "Agricultural";
                    typeClass = AgriculturalLand.class;
                    break;
                case 2:
                    typeName = "Residential";
                    typeClass = ResidentialLand.class;
                    break;
                case 3:
                    typeName = "Commercial";
                    typeClass = CommercialLand.class;
                    break;
                case 4:
                    typeName = "Industrial";
                    typeClass = IndustrialLand.class;
                    break;
                default:
                    System.out.println("Invalid land type.");
                    return;
            }

            System.out.println("\n" + typeName + " Lands:");
            boolean found = false;
            for (Land land : landRegistry) {
                if (typeClass.isInstance(land)) {
                    System.out.println("- Land ID: " + land.getLandId() + ", Owner: " + land.getOwnerName() + ", Location: " + land.getLocation());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No " + typeName + " lands found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void searchByOwner() {
        System.out.print("\nEnter owner name to search: ");
        String ownerName = scanner.nextLine();
        System.out.println("\nLands owned by " + ownerName + ":");
        boolean found = false;
        for (Land land : landRegistry) {
            if (land.getOwnerName().equalsIgnoreCase(ownerName)) {
                System.out.println("- Land ID: " + land.getLandId() + ", Type: " + land.getClass().getSimpleName() + ", Location: " + land.getLocation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No lands found for owner " + ownerName + ".");
        }
    }
}