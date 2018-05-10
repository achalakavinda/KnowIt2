package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/10/2018.
 */

public class HomeItemModel {


    private String id;
    private String author;
    private String date;
    private String title;
    private String image;
    private String description;
    private String like;
    private String comment;
    private String dislike;


    public HomeItemModel(
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

    public String getId()
    {
        return id;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getDate()
    {
        return date;
    }

    public String getTitle()
    {
        return title;
    }

    public String getImage()
    {
        return image;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLike()
    {
        return like;
    }

    public String getComment()
    {
        return comment;
    }

    public String getDeslike()
    {
        return dislike;
    }
}
