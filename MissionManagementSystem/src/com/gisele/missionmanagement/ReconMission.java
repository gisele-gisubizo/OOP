package com.gisele.missionmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReconMission extends Mission {
    private List<Resource> resources;

    public ReconMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
        this.resources = new ArrayList<>();
    }

    @Override
    public void assignTask() {
        if (assignedPersonnel.size() < 2) {
            throw new IllegalStateException("ReconMission requires at least 2 personnel.");
        }
        System.out.println("Assigning reconnaissance tasks to personnel for mission: " + missionName);
        for (Personnel p : assignedPersonnel) {
            System.out.println(" - " + p.getPersonnelName() + ": Surveillance and intelligence gathering.");
        }
    }

    @Override
    public void allocateResources() {
        boolean hasDrone = resources.stream().anyMatch(r -> r.getResourceName().equals("Drone") && r.getQuantity() > 0);
        if (!hasDrone) {
            throw new IllegalStateException("No drones available for ReconMission.");
        }
        System.out.println("Allocating drones and communication tools for mission: " + missionName);
    }

    @Override
    public void trackMissionProgress() {
        status = "IN_PROGRESS";
        System.out.println("Tracking intelligence-gathering progress for mission: " + missionName);
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Mission Report for ReconMission: ").append(missionName).append("\n");
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