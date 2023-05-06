package com.group8.project.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public class Agent implements Serializable {
    private String email;
    private String estateAgency;
    private String jobTitle;
    private String phone;
    private User user;

    public Agent() {
    }

    public Agent(String email, String estateAgency, String jobTitle, String phone) {
        this.email = email;
        this.estateAgency = estateAgency;
        this.jobTitle = jobTitle;
        this.phone = phone;
    }

    public Agent(String email, String estateAgency, String jobTitle, String phone, User user) {
        this.email = email;
        this.estateAgency = estateAgency;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.user = user;
    }

    // Getter and Setter methods
    @Column("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column("estate_agency")
    public String getEstateAgency() {
        return estateAgency;
    }

    public void setEstateAgency(String estateAgency) {
        this.estateAgency = estateAgency;
    }

    @Column("job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "email='" + email + '\'' +
                ", estateAgency='" + estateAgency + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
