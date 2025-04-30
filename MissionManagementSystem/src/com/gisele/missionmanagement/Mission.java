package com.gisele.missionmanagement;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Mission {
    protected String missionId;
    protected String missionName;
    protected Date missionStartDate;
    protected Date missionEndDate;
    protected String status;
    protected List<Personnel> assignedPersonnel;

    public Mission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionStartDate = missionStartDate;
        this.missionEndDate = missionEndDate;
        this.status = "PLANNED";
        this.assignedPersonnel = new ArrayList<>();
        validateMissionDates();
    }

    private void validateMissionDates() {
        if (missionStartDate.after(missionEndDate)) {
            throw new IllegalArgumentException("Mission start date must be before end date.");
        }
    }

    public String getMissionId() {
        return missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public Date getMissionStartDate() {
        return missionStartDate;
    }

    public Date getMissionEndDate() {
        return missionEndDate;
    }

    public String getStatus() {
        return status;
    }

    public List<Personnel> getAssignedPersonnel() {
        return assignedPersonnel;
    }

    public abstract void assignTask();
    public abstract void allocateResources();
    public abstract void trackMissionProgress();
    public abstract String generateMissionReport();

    protected void validatePersonnelAssignment(Personnel personnel) {
        if (assignedPersonnel.contains(personnel)) {
            throw new IllegalArgumentException("Personnel already assigned to this mission.");
        }
        if (personnel.getAssignedMission() != null) {
            throw new IllegalArgumentException("Personnel is already assigned to another mission.");
        }
    }

    public void addPersonnel(Personnel personnel) {
        validatePersonnelAssignment(personnel);
        assignedPersonnel.add(personnel);
        personnel.setAssignedMission(this);
    }
}