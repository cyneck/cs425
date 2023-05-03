package com.group8.project.domain;

import java.io.Serializable;

public class Nearby implements Serializable {
    private String propertyId;
    private int nearbyId;
    private String type;
    private String name;
    private String description;
    private String images;

    public Nearby() {}

    public Nearby(String propertyId, int nearbyId, String type, String name, String description, String images) {
        this.propertyId = propertyId;
        this.nearbyId = nearbyId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.images = images;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public int getNearbyId() {
        return nearbyId;
    }

    public void setNearbyId(int nearbyId) {
        this.nearbyId = nearbyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
