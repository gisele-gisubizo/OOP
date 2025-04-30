package com.gisele.nurseryschool;


import java.util.ArrayList;
import java.util.List;

public class BabyClass extends NurseryClass {
    private List<String> activitiesConducted;

    public BabyClass(String classId, String className) {
        super(classId, className, 15); // Max capacity 15
        this.activitiesConducted = new ArrayList<>();
    }

    @Override
    public void enrollStudent(Student student) {
        validateStudentEnrollment(student);
        if (student.getAge() < 2 || student.getAge() > 3) {
            throw new IllegalArgumentException("BabyClass is for ages 2-3.");
        }
        students.add(student);
        student.setRegisteredClass(this);
        System.out.println("Student " + student.getStudentName() + " enrolled in " + className);
    }

    @Override
    public void setAssignedTeacher(Teacher teacher) {
        if (!teacher.getTeacherRole().equals("Early Childhood Educator")) {
            throw new IllegalArgumentException("BabyClass requires an Early Childhood Educator.");
        }
        super.setAssignedTeacher(teacher);
    }

    @Override
    public void trackProgress() {
        System.out.println("Tracking motor skills and play-based learning for " + className);
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
        report.append("Progress: Motor skills and play-based learning ongoing\n");
        return report.toString();
    }
}