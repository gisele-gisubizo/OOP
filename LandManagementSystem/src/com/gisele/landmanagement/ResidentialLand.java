package com.gisele.landmanagement;

import java.util.Date;

public class ResidentialLand extends Land {
    private static final double VALUE_PER_ACRE = 8000.0;
    private static final double TAX_RATE = 0.015;
    private int residentialUnits;

    public ResidentialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus, int residentialUnits) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
        this.residentialUnits = residentialUnits;
        if (residentialUnits > 2 * sizeInAcres) {
            throw new IllegalArgumentException("Residential land cannot exceed 2 units per acre.");
        }
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Assume zoning check verifies land is in a residential zone
        return location.toLowerCase().contains("residential") || location.toLowerCase().contains("housing");
    }

    @Override
    public double calculateTax() {
        double landValue = sizeInAcres * VALUE_PER_ACRE;
        return landValue * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("Land Report for Residential Land\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Registration Date: ").append(registrationDate).append("\n");
        report.append("Land Use Status: ").append(landUseStatus).append("\n");
        report.append("Type: Residential\n");
        report.append("Residential Units: ").append(residentialUnits).append("\n");
        report.append("Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        report.append("Zoning Compliance: ").append(checkZoningCompliance() ? "Compliant" : "Non-compliant").append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Valid" : "Invalid").append("\n");
        return report.toString();
    }
}