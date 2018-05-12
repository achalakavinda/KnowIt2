package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/13/2018.
 */

public class ProfileItemModel {

    private String id;
    private String title;
    private String Subject;
    private String Description;
    private String Views;
    private String Popular;
    private String year;
    private String date;

    public ProfileItemModel(String id, String title,  String description){
        this.id = id;
        this.title = title;
        this.Description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() { return Subject; }

    public String getDescription() {
        return Description;
    }

    public String getViews() {
        return Views;
    }

    public String getPopular() {
        return Popular;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }
}
