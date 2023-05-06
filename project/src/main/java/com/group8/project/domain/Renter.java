package com.group8.project.domain;


import java.io.Serial;
import java.io.Serializable;

public class Renter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private int age;
    private String sex;
    private String job;
    private String phone;
    private User user;

    public Renter() {
    }

    public Renter(String email, int age, String sex, String job, String phone) {
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.job = job;
        this.phone = phone;
    }

    public Renter(String email, int age, String sex, String job, String phone, User user) {
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.job = job;
        this.phone = phone;
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

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
        return "Renter{" +
                "email='" + email + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }
}
