package com.group8.project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Preference implements Serializable {
    private String email;
    private Date desiredDate;
    private String preferredLocation;
    private double budget;

    public Preference() {}

    public Preference(String email, Date desiredDate, String preferredLocation, double budget) {
        this.email = email;
        this.desiredDate = desiredDate;
        this.preferredLocation = preferredLocation;
        this.budget = budget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "email='" + email + '\'' +
                ", desiredDate=" + desiredDate +
                ", preferredLocation='" + preferredLocation + '\'' +
                ", budget=" + budget +
                '}';
    }
}
