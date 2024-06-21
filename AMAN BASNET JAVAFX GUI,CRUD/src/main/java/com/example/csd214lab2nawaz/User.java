package com.example.csd214lab2nawaz;

public class User {

    private int id;
    private String name;

    private String email;

    private String zipcode;

    public User(int id, String name, String email, String zipcode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }}