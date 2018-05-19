package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/7/2018.
 */

public class User {
    private String name;
    private String contact;
    private String email;
    private String sliit_id;
    private String url;
    private String timestap;

    public User(String name, String contact, String email, String sliit_id, String url, String timestap){
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.sliit_id = sliit_id;
        this.url = url;
        this.timestap = timestap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSliit_id() {
        return sliit_id;
    }

    public void setSliit_id(String sliit_id) {
        this.sliit_id = sliit_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimestap() {
        return timestap;
    }

    public void setTimestap(String timestap) {
        this.timestap = timestap;
    }
}
