package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/20/2018.
 */

public class Post {

    private String id;
    private String author;
    private String date;
    private String title;
    private String image;
    private String description;
    private String like;
    private String comment;
    private String dislike;


    public Post(
            String id,
            String author,
            String date,
            String title,
            String image,
            String description,
            String like,
            String comment,
            String dislike
    )
    {

        this.id = id;
        this.author = author;
        this.date = date;
        this.image = image;
        this.description = description;
        this.like = like;
        this.comment = comment;
        this.dislike = dislike;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }


}