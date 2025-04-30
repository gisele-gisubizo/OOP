package com.gisele.missionmanagement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RescueMission extends Mission {
    private List<Resource> resources;

    public RescueMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
        this.resources = new ArrayList<>();
    }

    @Override
    public void assignTask() {
        boolean hasMedic = assignedPersonnel.stream().anyMatch(p -> p.getPersonnelRole().equals("Medic"));
        if (!hasMedic) {
            throw new IllegalStateException("RescueMission requires at least one medic.");
        }
        System.out.println("Assigning rescue and medical tasks for mission: " + missionName);
        for (Personnel p : assignedPersonnel) {
            System.out.println(" - " + p.getPersonnelName() + ": " + (p.getPersonnelRole().equals("Medic") ? "Medical support" : "Rescue operations"));
        }
    }

    @Override
    public void allocateResources() {
        boolean hasMedicalKit = resources.stream().anyMatch(r -> r.getResourceName().equals("Medical Kit") && r.getQuantity() > 0);
        if (!hasMedicalKit) {
            throw new IllegalStateException("No medical kits available for RescueMission.");
        }
        System.out.println("Allocating medical kits and ambulances for mission: " + missionName);
    }

    @Override
    public void trackMissionProgress() {
        status = "IN_PROGRESS";
        System.out.println("Tracking rescue progress for mission: " + missionName);
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Mission Report for RescueMission: ").append(missionName).append("\n");
        report.append("Mission ID: ").append(missionId).append("\n");
        report.append("Start Date: ").append(missionStartDate).append("\n");
        report.append("End Date: ").append(missionEndDate).append("\n");
        report.append("Status: ").append(status).append("\n");
        report.append("Assigned Personnel:\n");
        for (Personnel p : assignedPersonnel) {
            report.append(" - ").append(p.getPersonnelName()).append(" (").append(p.getPersonnelRole()).append(")\n");
        }
        report.append("Resources Used:\n");
        for (Resource r : resources) {
            report.append(" - ").append(r.getResourceName()).append(": ").append(r.getQuantity()).append("\n");
        }
        return report.toString();
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }
}