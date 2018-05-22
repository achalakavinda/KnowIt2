package com.edu.knowit.knowit.Models;

/**
 * Created by User on 8/22/2017.
 */

public class CommentItemModel {
    private String id;
    private String post_id;
    private String author;
    private String author_image;
    private String date;
    private String description;

    public CommentItemModel(){}

    public CommentItemModel(String id, String post_id , String author, String author_image, String date, String description){
        this.id = id;
        this.post_id = post_id;
        this.author = author;
        this.author_image = author_image;
        this.date = date;
        this.description = description;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_image() {
        return author_image;
    }

    public void setAuthor_image(String author_image) {
        this.author_image = author_image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
