package com.edu.knowit.knowit.Models;

/**
 * Created by Achala Kavinda on 5/10/2018.
 */

public class HomeItemModel {


    String id;
    String amount;
    String pahase_id;
    String user_id;
    String created_at;


    public HomeItemModel(String id,String amount,String pahase_id,String user_id,String Created_at) {

        this.id=id;
        this.amount=amount;
        this.pahase_id=pahase_id;
        this.user_id=user_id;
        this.created_at = Created_at;

    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getPahase_id() {
        return pahase_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUser_id() {
        return user_id;
    }

}
