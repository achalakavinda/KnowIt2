package com.edu.knowit.knowit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfleViewActivity extends AppCompatActivity {

    private FirebaseAuth mFireBaseAuth;
    
    private FirebaseUser mFirebaseUser;
    
    private DatabaseReference mDatabaseReference;
    
    private TextView user_name, user_email, user_phone, user_sliit_id;
    
    private Button mButton;

    private Toolbar toolbar;
    private ListView listView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profle_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        mFireBaseAuth = FirebaseAuth.getInstance();
        
        mFirebaseUser = mFireBaseAuth.getCurrentUser();
        
        mDatabaseReference = FirebaseDatabase.getInstance()
            .getReference("user_details").child(mFirebaseUser.getUid());
        
        user_name = findViewById(R.id.user_name);
        
        user_email = findViewById(R.id.user_email);
        
        user_phone = findViewById(R.id.user_contact);
        
        user_sliit_id = findViewById(R.id.user_sliit_id);
        
        fillTextViews();
        
        findViewById(R.id.edit_profile).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ProfleViewActivity.this, ProfileDetailsActivity.class));
            }
        });
        
    }
    
    
    private void fillTextViews()
    {
    
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
        
                if (dataSnapshot.exists())
                {
                    user_name.setText(dataSnapshot.child("name").getValue().toString());
                    
                    user_email.setText(dataSnapshot.child("email").getValue().toString());
                    
                    user_phone.setText(dataSnapshot.child("contact").getValue().toString());
                    
                    user_sliit_id.setText(dataSnapshot.child("sliit_id").getValue().toString());
                }
            
            }
    
            @Override
            public void onCancelled(DatabaseError databaseError) {
        
            }
        });
    
    
    }
    
}
