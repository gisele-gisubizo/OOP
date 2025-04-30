package com.gisele.nurseryschool;


import java.util.Objects;

public class Teacher {
    private String teacherId;
    private String teacherName;
    private String teacherRole;
    private NurseryClass assignedClass;

    public Teacher(String teacherId, String teacherName, String teacherRole) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherRole = teacherRole;
        this.assignedClass = null;
    }

    // Getters and Setters
    public String getTeacherId() { return teacherId; }
    public String getTeacherName() { return teacherName; }
    public String getTeacherRole() { return teacherRole; }
    public NurseryClass getAssignedClass() { return assignedClass; }
    public void setAssignedClass(NurseryClass assignedClass) { this.assignedClass = assignedClass; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId.equals(teacher.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId);
    }
}