package com.gisele.nurseryschool;


import java.util.ArrayList;
import java.util.List;

public class TopClass extends NurseryClass {
    private List<String> activitiesConducted;
    private List<String> assessments;

    public TopClass(String classId, String className) {
        super(classId, className, 25); // Max capacity 25
        this.activitiesConducted = new ArrayList<>();
        this.assessments = new ArrayList<>();
    }

    @Override
    public void enrollStudent(Student student) {
        validateStudentEnrollment(student);
        if (student.getAge() < 4 || student.getAge() > 5) {
            throw new IllegalArgumentException("TopClass is for ages 4-5.");
        }
        students.add(student);
        student.setRegisteredClass(this);
        System.out.println("Student " + student.getStudentName() + " enrolled in " + className);
    }

    @Override
    public void trackProgress() {
        System.out.println("Tracking reading, writing, and arithmetic for " + className);
        assessments.add("Term assessment conducted on " + System.currentTimeMillis());
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
        report.append("Assessments:\n");
        for (String assessment : assessments) {
            report.append(" - ").append(assessment).append("\n");
        }
        report.append("Progress: Preparing for primary school with reading, writing, and arithmetic\n");
        return report.toString();
    }
}