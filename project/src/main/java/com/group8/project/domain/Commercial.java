package com.group8.project.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public class Commercial implements Serializable {
    @Column("property_id")
    private String propertyId;
    @Column("business_type")
    private String businessType;

    public Commercial() {
    }

    public Commercial(String propertyId, String businessType) {
        this.propertyId = propertyId;
        this.businessType = businessType;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public String toString() {
        return "Commercial{" +
                "propertyId='" + propertyId + '\'' +
                ", businessType='" + businessType + '\'' +
                '}';
    }
}
