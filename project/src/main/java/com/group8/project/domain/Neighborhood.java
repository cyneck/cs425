package com.group8.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Neighborhood implements Serializable {
    private String propertyId;
    private String neighborhoodId;
    private BigDecimal crimeRate;

    public Neighborhood() {}

    public Neighborhood(String propertyId, String neighborhoodId, BigDecimal crimeRate) {
        this.propertyId = propertyId;
        this.neighborhoodId = neighborhoodId;
        this.crimeRate = crimeRate;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(String neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public BigDecimal getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(BigDecimal crimeRate) {
        this.crimeRate = crimeRate;
    }
}
