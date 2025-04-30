package com.gisele.nurseryschool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<NurseryClass> classes = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == 7) {
                System.out.println("Exiting program...");
                break;
            }
            handleMenuChoice(choice);
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Nursery School Management System ===");
        System.out.println("1. Create a new class");
        System.out.println("2. Add a teacher");
        System.out.println("3. Assign teacher to class");
        System.out.println("4. Enroll a student");
        System.out.println("5. Conduct an activity");
        System.out.println("6. Generate class report");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }

    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
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
                createClass();
                break;
            case 2:
                addTeacher();
                break;
            case 3:
                assignTeacherToClass();
                break;
            case 4:
                enrollStudent();
                break;
            case 5:
                conductActivity();
                break;
            case 6:
                generateClassReport();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void createClass() {
        try {
            System.out.println("\nSelect class type:");
            System.out.println("1. Baby Class");
            System.out.println("2. Middle Class");
            System.out.println("3. Top Class");
            System.out.print("Enter choice (1-3): ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter class ID: ");
            String classId = scanner.nextLine();
            System.out.print("Enter class name: ");
            String className = scanner.nextLine();

            NurseryClass nurseryClass;
            switch (type) {
                case 1:
                    nurseryClass = new BabyClass(classId, className);
                    break;
                case 2:
                    nurseryClass = new MiddleClass(classId, className);
                    break;
                case 3:
                    nurseryClass = new TopClass(classId, className);
                    break;
                default:
                    System.out.println("Invalid class type.");
                    return;
            }
            classes.add(nurseryClass);
            System.out.println("Class created successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void addTeacher() {
        try {
            System.out.print("\nEnter teacher ID: ");
            String teacherId = scanner.nextLine();
            System.out.print("Enter teacher name: ");
            String teacherName = scanner.nextLine();
            System.out.print("Enter teacher role (e.g., Early Childhood Educator, Assistant): ");
            String teacherRole = scanner.nextLine();

            Teacher teacher = new Teacher(teacherId, teacherName, teacherRole);
            teachers.add(teacher);
            System.out.println("Teacher added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding teacher: " + e.getMessage());
        }
    }

    private static void assignTeacherToClass() {
        if (classes.isEmpty() || teachers.isEmpty()) {
            System.out.println("No classes or teachers available.");
            return;
        }

        try {
            System.out.println("\nAvailable classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getClassName() + " (ID: " + classes.get(i).getClassId() + ")");
            }
            System.out.print("Select class (1-" + classes.size() + "): ");
            int classIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (classIndex < 0 || classIndex >= classes.size()) {
                System.out.println("Invalid class selection.");
                return;
            }

            System.out.println("\nAvailable teachers:");
            for (int i = 0; i < teachers.size(); i++) {
                System.out.println((i + 1) + ". " + teachers.get(i).getTeacherName() + " (ID: " + teachers.get(i).getTeacherId() + ")");
            }
            System.out.print("Select teacher (1-" + teachers.size() + "): ");
            int teacherIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
                System.out.println("Invalid teacher selection.");
                return;
            }

            NurseryClass nurseryClass = classes.get(classIndex);
            Teacher teacher = teachers.get(teacherIndex);
            nurseryClass.setAssignedTeacher(teacher);
            System.out.println("Teacher assigned successfully!");
        } catch (Exception e) {
            System.out.println("Error assigning teacher: " + e.getMessage());
        }
    }

    private static void enrollStudent() {
        if (classes.isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        try {
            System.out.print("\nEnter student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter student age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter guardian name: ");
            String guardianName = scanner.nextLine();

            Student student = new Student(studentId, studentName, age, guardianName);
            students.add(student);

            System.out.println("\nAvailable classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getClassName() + " (ID: " + classes.get(i).getClassId() + ")");
            }
            System.out.print("Select class (1-" + classes.size() + "): ");
            int classIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (classIndex < 0 || classIndex >= classes.size()) {
                System.out.println("Invalid class selection.");
                return;
            }

            NurseryClass nurseryClass = classes.get(classIndex);
            nurseryClass.enrollStudent(student);
            System.out.println("Student enrolled successfully!");
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    private static void conductActivity() {
        if (classes.isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        try {
            System.out.println("\nAvailable classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getClassName() + " (ID: " + classes.get(i).getClassId() + ")");
            }
            System.out.print("Select class (1-" + classes.size() + "): ");
            int classIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (classIndex < 0 || classIndex >= classes.size()) {
                System.out.println("Invalid class selection.");
                return;
            }

            System.out.print("Enter activity name (e.g., Singing, Painting): ");
            String activityName = scanner.nextLine();

            NurseryClass nurseryClass = classes.get(classIndex);
            nurseryClass.conductActivity(activityName);
            nurseryClass.trackProgress();
            System.out.println("Activity conducted successfully!");
        } catch (Exception e) {
            System.out.println("Error conducting activity: " + e.getMessage());
        }
    }

    private static void generateClassReport() {
        if (classes.isEmpty()) {
            System.out.println("No classes available.");
            return;
        }

        try {
            System.out.println("\nAvailable classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getClassName() + " (ID: " + classes.get(i).getClassId() + ")");
            }
            System.out.print("Select class (1-" + classes.size() + "): ");
            int classIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (classIndex < 0 || classIndex >= classes.size()) {
                System.out.println("Invalid class selection.");
                return;
            }

            NurseryClass nurseryClass = classes.get(classIndex);
            System.out.println("\nClass Report:");
            System.out.println(nurseryClass.generateClassReport());
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}