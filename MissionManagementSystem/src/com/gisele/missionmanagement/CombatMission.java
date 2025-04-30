package com.gisele.missionmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CombatMission extends Mission {
    private List<Resource> resources;

    public CombatMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
        this.resources = new ArrayList<>();
    }

    @Override
    public void assignTask() {
        if (assignedPersonnel.size() < 3) {
            throw new IllegalStateException("CombatMission requires at least 3 personnel.");
        }
        System.out.println("Assigning combat tasks for mission: " + missionName);
        for (Personnel p : assignedPersonnel) {
            System.out.println(" - " + p.getPersonnelName() + ": Combat operations");
        }
    }

    @Override
    public void allocateResources() {
        boolean hasWeapon = resources.stream().anyMatch(r -> r.getResourceName().equals("Weapon") && r.getQuantity() > 0);
        if (!hasWeapon) {
            throw new IllegalStateException("No weapons available for CombatMission.");
        }
        System.out.println("Allocating weapons and vehicles for mission: " + missionName);
    }

    @Override
    public void trackMissionProgress() {
        status = "IN_PROGRESS";
        System.out.println("Tracking combat progress for mission: " + missionName);
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Mission Report for CombatMission: ").append(missionName).append("\n");
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