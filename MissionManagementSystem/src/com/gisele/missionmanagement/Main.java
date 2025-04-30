package com.gisele.missionmanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Mission> missions = new ArrayList<>();
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
        System.out.println("\n=== Mission Management System ===");
        System.out.println("1. Create a new mission");
        System.out.println("2. Add personnel to a mission");
        System.out.println("3. Add resources to a mission");
        System.out.println("4. Perform mission operations and generate report");
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
                createMission();
                break;
            case 2:
                addPersonnelToMission();
                break;
            case 3:
                addResourceToMission();
                break;
            case 4:
                performMissionOperations();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void createMission() {
        try {
            System.out.println("\nSelect mission type:");
            System.out.println("1. Recon Mission");
            System.out.println("2. Rescue Mission");
            System.out.println("3. Combat Mission");
            System.out.println("4. Humanitarian Mission");
            System.out.print("Enter choice (1-4): ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter mission ID: ");
            String missionId = scanner.nextLine();
            System.out.print("Enter mission name: ");
            String missionName = scanner.nextLine();
            System.out.print("Enter start date (yyyy-MM-dd): ");
            Date startDate = sdf.parse(scanner.nextLine());
            System.out.print("Enter end date (yyyy-MM-dd): ");
            Date endDate = sdf.parse(scanner.nextLine());

            Mission mission;
            switch (type) {
                case 1:
                    mission = new ReconMission(missionId, missionName, startDate, endDate);
                    break;
                case 2:
                    mission = new RescueMission(missionId, missionName, startDate, endDate);
                    break;
                case 3:
                    mission = new CombatMission(missionId, missionName, startDate, endDate);
                    break;
                case 4:
                    mission = new HumanitarianMission(missionId, missionName, startDate, endDate);
                    break;
                default:
                    System.out.println("Invalid mission type.");
                    return;
            }
            missions.add(mission);
            System.out.println("Mission created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating mission: " + e.getMessage());
        }
    }

    private static void addPersonnelToMission() {
        if (missions.isEmpty()) {
            System.out.println("No missions available. Create a mission first.");
            return;
        }

        try {
            System.out.println("\nAvailable missions:");
            for (int i = 0; i < missions.size(); i++) {
                System.out.println((i + 1) + ". " + missions.get(i).getMissionName() + " (ID: " + missions.get(i).getMissionId() + ")");
            }
            System.out.print("Select mission (1-" + missions.size() + "): ");
            int missionIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (missionIndex < 0 || missionIndex >= missions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = missions.get(missionIndex);
            System.out.print("Enter personnel ID: ");
            String personnelId = scanner.nextLine();
            System.out.print("Enter personnel name: ");
            String personnelName = scanner.nextLine();
            System.out.print("Enter personnel role (e.g., Scout, Medic, Soldier, Logistics Officer): ");
            String personnelRole = scanner.nextLine();

            Personnel personnel = new Personnel(personnelId, personnelName, personnelRole);
            mission.addPersonnel(personnel);
            System.out.println("Personnel added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding personnel: " + e.getMessage());
        }
    }

    private static void addResourceToMission() {
        if (missions.isEmpty()) {
            System.out.println("No missions available. Create a mission first.");
            return;
        }

        try {
            System.out.println("\nAvailable missions:");
            for (int i = 0; i < missions.size(); i++) {
                System.out.println((i + 1) + ". " + missions.get(i).getMissionName() + " (ID: " + missions.get(i).getMissionId() + ")");
            }
            System.out.print("Select mission (1-" + missions.size() + "): ");
            int missionIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (missionIndex < 0 || missionIndex >= missions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = missions.get(missionIndex);
            System.out.print("Enter resource ID: ");
            String resourceId = scanner.nextLine();
            System.out.print("Enter resource name (e.g., Drone, Medical Kit, Weapon, Food Supplies): ");
            String resourceName = scanner.nextLine();
            System.out.print("Enter resource quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter resource type (e.g., Equipment, Medical Supplies): ");
            String resourceType = scanner.nextLine();

            Resource resource = new Resource(resourceId, resourceName, quantity, resourceType);
            if (mission instanceof ReconMission) {
                ((ReconMission) mission).addResource(resource);
            } else if (mission instanceof RescueMission) {
                ((RescueMission) mission).addResource(resource);
            } else if (mission instanceof CombatMission) {
                ((CombatMission) mission).addResource(resource);
            } else if (mission instanceof HumanitarianMission) {
                ((HumanitarianMission) mission).addResource(resource);
            }
            System.out.println("Resource added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding resource: " + e.getMessage());
        }
    }

    private static void performMissionOperations() {
        if (missions.isEmpty()) {
            System.out.println("No missions available. Create a mission first.");
            return;
        }

        try {
            System.out.println("\nAvailable missions:");
            for (int i = 0; i < missions.size(); i++) {
                System.out.println((i + 1) + ". " + missions.get(i).getMissionName() + " (ID: " + missions.get(i).getMissionId() + ")");
            }
            System.out.print("Select mission (1-" + missions.size() + "): ");
            int missionIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (missionIndex < 0 || missionIndex >= missions.size()) {
                System.out.println("Invalid mission selection.");
                return;
            }

            Mission mission = missions.get(missionIndex);
            System.out.println("\nPerforming operations for mission: " + mission.getMissionName());
            mission.assignTask();
            mission.allocateResources();
            mission.trackMissionProgress();
            System.out.println("\nMission Report:");
            System.out.println(mission.generateMissionReport());
        } catch (Exception e) {
            System.out.println("Error performing mission operations: " + e.getMessage());
        }
    }
}