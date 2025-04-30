package com.gisele.nurseryschool;

import java.util.ArrayList;
import java.util.List;

public abstract class NurseryClass {
    protected String classId;
    protected String className;
    protected int maxCapacity;
    protected Teacher assignedTeacher;
    protected List<Student> students;

    public NurseryClass(String classId, String className, int maxCapacity) {
        this.classId = classId;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>();
    }

    // Getters and Setters
    public String getClassId() { return classId; }
    public String getClassName() { return className; }
    public int getMaxCapacity() { return maxCapacity; }
    public Teacher getAssignedTeacher() { return assignedTeacher; }
    public List<Student> getStudents() { return students; }

    public void setAssignedTeacher(Teacher teacher) {
        if (teacher.getAssignedClass() != null) {
            throw new IllegalArgumentException("Teacher is already assigned to another class.");
        }
        this.assignedTeacher = teacher;
        teacher.setAssignedClass(this);
    }

    // Abstract Methods
    public abstract void enrollStudent(Student student);
    public abstract void trackProgress();
    public abstract void conductActivity(String activityName);
    public abstract String generateClassReport();

    protected void validateStudentEnrollment(Student student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student is already enrolled in this class.");
        }
        if (student.getRegisteredClass() != null) {
            throw new IllegalArgumentException("Student is already enrolled in another class.");
        }
        if (students.size() >= maxCapacity) {
            throw new IllegalArgumentException("Class has reached maximum capacity.");
        }
    }
}