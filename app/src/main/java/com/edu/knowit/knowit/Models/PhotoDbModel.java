package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/12/2018.
 */

public class PhotoDbModel {

    private String caption;
    private String date_created;
    private String image_path;
    private String user_id;
    private String tags;

    public PhotoDbModel(String caption,String date_created,String image_path,String user_id,String tags){
     this.caption =  caption;
     this.date_created = date_created;
     this.image_path = image_path;
     this.user_id = user_id;
     this.tags = tags;
    }

    public String getCaption() {
        return caption;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getTags() {
        return tags;
    }

    public String getUser_id() {
        return user_id;
    }
}
