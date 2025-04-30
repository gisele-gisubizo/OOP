package com.gisele.nurseryschool;


import java.util.ArrayList;
import java.util.List;

public class MiddleClass extends NurseryClass {
    private List<String> activitiesConducted;

    public MiddleClass(String classId, String className) {
        super(classId, className, 20); // Max capacity 20
        this.activitiesConducted = new ArrayList<>();
    }

    @Override
    public void enrollStudent(Student student) {
        validateStudentEnrollment(student);
        if (student.getAge() < 3 || student.getAge() > 4) {
            throw new IllegalArgumentException("MiddleClass is for ages 3-4.");
        }
        students.add(student);
        student.setRegisteredClass(this);
        System.out.println("Student " + student.getStudentName() + " enrolled in " + className);
    }

    @Override
    public void trackProgress() {
        System.out.println("Tracking language development and basic counting for " + className);
    }

    @Override
    public void conductActivity(String activityName) {
        activitiesConducted.add(activityName);
        System.out.println("Conducted activity: " + activityName + " in " + className);
    }

    @Override
    public String generateClassReport() {
        StringBuilder report = new StringBuilder();
        report.append("Class Report for ").append(className).append("\n");
        report.append("Class ID: ").append(classId).append("\n");
        report.append("Assigned Teacher: ").append(assignedTeacher != null ? assignedTeacher.getTeacherName() : "None").append("\n");
        report.append("Enrolled Students: ").append(students.size()).append("/").append(maxCapacity).append("\n");
        report.append("Students:\n");
        for (Student s : students) {
            report.append(" - ").append(s.getStudentName()).append(" (ID: ").append(s.getStudentId()).append(", Age: ").append(s.getAge()).append(")\n");
        }
        report.append("Activities Conducted:\n");
        for (String activity : activitiesConducted) {
            report.append(" - ").append(activity).append("\n");
        }
        report.append("Progress: Language development and basic counting ongoing\n");
        return report.toString();
    }
}