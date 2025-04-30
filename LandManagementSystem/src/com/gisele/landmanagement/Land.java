package com.gisele.landmanagement;

import java.util.Date;

public abstract class Land {
    protected String landId;
    protected String ownerName;
    protected String location;
    protected double sizeInAcres;
    protected Date registrationDate;
    protected String landUseStatus;

    public Land(String landId, String ownerName, String location, double sizeInAcres, Date registrationDate, String landUseStatus) {
        this.landId = landId;
        this.ownerName = ownerName;
        this.location = location;
        this.sizeInAcres = sizeInAcres;
        this.registrationDate = registrationDate;
        this.landUseStatus = landUseStatus;
        validate();
    }

    private void validate() {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        if (sizeInAcres <= 0) {
            throw new IllegalArgumentException("Size in acres must be positive.");
        }
        if (!landUseStatus.equals("Vacant") && !landUseStatus.equals("In Use") && !landUseStatus.equals("Under Development")) {
            throw new IllegalArgumentException("Invalid land use status. Must be Vacant, In Use, or Under Development.");
        }
    }

    // Getters
    public String getLandId() { return landId; }
    public String getOwnerName() { return ownerName; }
    public String getLocation() { return location; }
    public double getSizeInAcres() { return sizeInAcres; }
    public Date getRegistrationDate() { return registrationDate; }
    public String getLandUseStatus() { return landUseStatus; }

    // Abstract Methods
    public abstract boolean validateOwnership();
    public abstract boolean checkZoningCompliance();
    public abstract double calculateTax();
    public abstract String generateLandReport();
}