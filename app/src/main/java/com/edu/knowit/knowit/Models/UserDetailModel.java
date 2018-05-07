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

    public String getString(){
        return "user:{" +
                "fname:'" +this.fname+"'"+
                "number:'" +this.contact+"'"+
                "}";
    }

}
