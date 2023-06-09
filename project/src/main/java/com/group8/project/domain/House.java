package com.group8.project.domain;

import java.io.Serializable;

public class House implements Serializable {
    private String propertyId;
    private int roomsNumber;

    public House() {
    }

    public House(String propertyId, int roomsNumber) {
        this.propertyId = propertyId;
        this.roomsNumber = roomsNumber;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }
}

