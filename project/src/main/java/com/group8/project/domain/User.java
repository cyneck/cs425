package com.group8.project.domain;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;


public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String firstName;
    private String lastName;
    private String passwd;

    private String role;

    public User() {
    }

    public User(String email, String firstName, String lastName, String passwd) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwd = passwd;
    }

    public User(String email, String firstName, String lastName, String passwd, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwd = passwd;
        this.role = role;
    }

    public User(String email, String firstName, String lastName, String passwd, String role, List<Address> addresses) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwd = passwd;
        this.role = role;
        this.addresses = addresses;
    }

    private List<Address> addresses;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return passwd;
    }

    public void setPassword(String passwd) {
        this.passwd = passwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
