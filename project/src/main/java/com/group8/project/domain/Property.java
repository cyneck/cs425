package com.group8.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Property implements Serializable {
    private String propertyId;
    private String email;
    private Agent agent;
    private String propertyType;
    private String description;
    private String city;
    private String state;
    private String address;
    private Integer availability;
    private BigDecimal rentalPrice;
    private BigDecimal squareFootage;

    public Property() {
    }

    public Property(String propertyId, Agent agent, String propertyType, String description, String city, String state, String address, Integer availability, BigDecimal rentalPrice, BigDecimal squareFootage) {
        this.propertyId = propertyId;
        this.agent = agent;
        this.propertyType = propertyType;
        this.description = description;
        this.city = city;
        this.state = state;
        this.address = address;
        this.availability = availability;
        this.rentalPrice = rentalPrice;
        this.squareFootage = squareFootage;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(BigDecimal squareFootage) {
        this.squareFootage = squareFootage;
    }
}
