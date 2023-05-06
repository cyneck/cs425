package com.group8.project.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public class Apartment implements Serializable {
    @Column("property_id")
    private String propertyId;
    @Column("rooms_number")
    private Integer roomsNumber;
    @Column("building_type")
    private String buildingType;

    public Apartment() {
    }

    public Apartment(String propertyId, Integer roomsNumber, String buildingType) {
        this.propertyId = propertyId;
        this.roomsNumber = roomsNumber;
        this.buildingType = buildingType;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(Integer roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "propertyId='" + propertyId + '\'' +
                ", roomsNumber=" + roomsNumber +
                ", buildingType='" + buildingType + '\'' +
                '}';
    }
}
