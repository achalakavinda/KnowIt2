package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/7/2018.
 */

public class UserDetailModel {

    private String fname;
    private String lname;
    private String sliit_id;
    private String contact;
    private String uuid;
    private String profile_img;

    public UserDetailModel(String name, String number, String contact, String uuid){
        this.fname = name;
        this.contact = number;
        this.uuid = uuid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSliit_id() {
        return sliit_id;
    }

    public void setSliit_id(String sliit_id) {
        this.sliit_id = sliit_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

//    public String getString(){
//        return "user:{" +
//                "fname:'" +this.fname+"'"+
//                "number:'" +this.contact+"'"+
//                "}";
//    }

}
