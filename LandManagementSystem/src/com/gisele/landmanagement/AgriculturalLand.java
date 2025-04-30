package com.gisele.landmanagement;

import java.util.Date;

public class AgriculturalLand extends Land {
    private static final double VALUE_PER_ACRE = 5000.0;
    private static final double TAX_RATE = 0.01;

    public AgriculturalLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        if (sizeInAcres < 1.0) {
            throw new IllegalArgumentException("Agricultural land must be at least 1 acre.");
        }
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Assume zoning check verifies land is in a farming zone
        return location.toLowerCase().contains("rural") || location.toLowerCase().contains("farming");
    }

    @Override
    public double calculateTax() {
        double landValue = sizeInAcres * VALUE_PER_ACRE;
        return landValue * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("Land Report for Agricultural Land\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Registration Date: ").append(registrationDate).append("\n");
        report.append("Land Use Status: ").append(landUseStatus).append("\n");
        report.append("Type: Agricultural\n");
        report.append("Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        report.append("Zoning Compliance: ").append(checkZoningCompliance() ? "Compliant" : "Non-compliant").append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Valid" : "Invalid").append("\n");
        return report.toString();
    }
}