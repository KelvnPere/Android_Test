package com.example.savicstest.model;

public class Patient {
    private String fullName;
    private String email;
    private String age;
    private int gender;

    public Patient(String fullName, String email, String age) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
