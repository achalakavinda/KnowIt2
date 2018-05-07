package com.edu.knowit.knowit.FirebaseProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Achala Kavinda on 5/7/2018.
 */

public class FireDataBase {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    FireDataBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }



}
