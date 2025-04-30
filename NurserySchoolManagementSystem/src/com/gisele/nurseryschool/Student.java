package com.gisele.nurseryschool;

import java.util.Objects;

public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private String guardianName;
    private NurseryClass registeredClass;

    public Student(String studentId, String studentName, int age, String guardianName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.guardianName = guardianName;
        this.registeredClass = null;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public int getAge() { return age; }
    public String getGuardianName() { return guardianName; }
    public NurseryClass getRegisteredClass() { return registeredClass; }
    public void setRegisteredClass(NurseryClass registeredClass) { this.registeredClass = registeredClass; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}