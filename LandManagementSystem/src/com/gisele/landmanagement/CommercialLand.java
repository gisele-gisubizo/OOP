package com.gisele.landmanagement;

import java.util.Date;

public class CommercialLand extends Land {
    private static final double VALUE_PER_ACRE = 10000.0;
    private static final double TAX_RATE = 0.025;

    public CommercialLand(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        super(landId, ownerName, location, sizeInAcres, registrationDate, landUseStatus);
    }

    @Override
    public boolean validateOwnership() {
        return ownerName != null && !ownerName.trim().isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        // Assume zoning check verifies land is in a commercial zone
        return location.toLowerCase().contains("commercial") || location.toLowerCase().contains("business");
    }

    @Override
    public double calculateTax() {
        double landValue = sizeInAcres * VALUE_PER_ACRE;
        return landValue * TAX_RATE;
    }

    @Override
    public String generateLandReport() {
        StringBuilder report = new StringBuilder();
        report.append("Land Report for Commercial Land\n");
        report.append("Land ID: ").append(landId).append("\n");
        report.append("Owner: ").append(ownerName).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Size: ").append(sizeInAcres).append(" acres\n");
        report.append("Registration Date: ").append(registrationDate).append("\n");
        report.append("Land Use Status: ").append(landUseStatus).append("\n");
        report.append("Type: Commercial\n");
        report.append("Tax: $").append(String.format("%.2f", calculateTax())).append("\n");
        report.append("Zoning Compliance: ").append(checkZoningCompliance() ? "Compliant" : "Non-compliant").append("\n");
        report.append("Ownership Valid: ").append(validateOwnership() ? "Valid" : "Invalid").append("\n");
        return report.toString();
    }
}