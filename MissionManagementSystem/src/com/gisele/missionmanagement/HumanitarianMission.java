package com.gisele.missionmanagement;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HumanitarianMission extends Mission {
    private List<Resource> resources;

    public HumanitarianMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
        this.resources = new ArrayList<>();
    }

    @Override
    public void assignTask() {
        boolean hasLogistics = assignedPersonnel.stream().anyMatch(p -> p.getPersonnelRole().equals("Logistics Officer"));
        if (!hasLogistics) {
            throw new IllegalStateException("HumanitarianMission requires at least one Logistics Officer.");
        }
        System.out.println("Assigning logistics and aid distribution tasks for mission: " + missionName);
        for (Personnel p : assignedPersonnel) {
            System.out.println(" - " + p.getPersonnelName() + ": Aid distribution");
        }
    }

    @Override
    public void allocateResources() {
        boolean hasFood = resources.stream().anyMatch(r -> r.getResourceName().equals("Food Supplies") && r.getQuantity() > 0);
        if (!hasFood) {
            throw new IllegalStateException("No food supplies available for HumanitarianMission.");
        }
        System.out.println("Allocating food supplies and transportation for mission: " + missionName);
    }

    @Override
    public void trackMissionProgress() {
        status = "IN_PROGRESS";
        System.out.println("Tracking aid distribution progress for mission: " + missionName);
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Mission Report for HumanitarianMission: ").append(missionName).append("\n");
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