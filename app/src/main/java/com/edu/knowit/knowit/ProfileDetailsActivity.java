package com.edu.knowit.knowit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.edu.knowit.knowit.Models.User;
import com.edu.knowit.knowit.Util.DialogBox;
import com.edu.knowit.knowit.Util.StringValidator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileDetailsActivity extends AppCompatActivity {
    
    private FirebaseAuth mFireBaseAuth;
    
    private FirebaseUser mFirebaseUser;
    
    private DatabaseReference mDatabaseReference;
    
    private EditText user_name, user_email, user_phone, user_sliit_id;

    private de.hdodenhof.circleimageview.CircleImageView circleImageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
    
        mFireBaseAuth = FirebaseAuth.getInstance();
    
        mFirebaseUser = mFireBaseAuth.getCurrentUser();
    
        mDatabaseReference = FirebaseDatabase.getInstance()
                .getReference("user_details").child(mFirebaseUser.getUid());
    
        user_name = findViewById(R.id.rgname);
    
        user_phone = findViewById(R.id.rgmobile);
    
        user_sliit_id = findViewById(R.id.rgsliitid);

        circleImageView = findViewById(R.id.avatar);
        
        fillTextViews();
        
        findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateUserDetails();
            }
        });
    }
    
    private void updateUserDetails()
    {
        
        StringValidator stringValidator = new StringValidator();
        
        DialogBox dialogBox = new DialogBox();
        
        View view = this.findViewById(android.R.id.content).getRootView();
        
        if (stringValidator.isEmptyString(user_name.getText().toString()))
        {
            dialogBox.ViewDialogBox(view, "Please Check", "Please check the name field");
            
            return;
        }
    
        if (stringValidator.isEmptyString(user_phone.getText().toString()))
        {
            dialogBox.ViewDialogBox(view, "Please Check", "Please check the phone field");
        
            return;
        }
    
        if (stringValidator.isEmptyString(user_sliit_id.getText().toString()))
        {
            dialogBox.ViewDialogBox(view, "Please Check", "Please check the SLIIT ID field");
        
            return;
        }
        mDatabaseReference.child("name").setValue(user_name.getText().toString());
        mDatabaseReference.child("contact").setValue(user_phone.getText().toString());
        mDatabaseReference.child("sliit_id").setValue(user_sliit_id.getText().toString());
    }
    
    private void fillTextViews()
    {
        
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                if (dataSnapshot.exists())
                {

                    User user = dataSnapshot.getValue(User.class);


                    user_name.setText(user.getName());
                    
                    user_phone.setText(user.getContact());
                    
                    user_sliit_id.setText(user.getSliit_id());

                    if(user.getUrl()!=null && !user.getUrl().isEmpty()){
                        Picasso.get().load(user.getUrl()).into(circleImageView);
                    }
                }
                
            }
            
            @Override
            public void onCancelled(DatabaseError databaseError) {
            
            }
        });
        
        
    }
    
}
