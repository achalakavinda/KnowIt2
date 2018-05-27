package com.edu.knowit.knowit;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.ListAdapters.ProfileViewListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.ProfileViewItemModel;
import com.edu.knowit.knowit.Models.User;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfleViewActivity extends AppCompatActivity {

    private String TAG ="ProfleViewActivity";

    ArrayList<ProfileViewItemModel> dataModels;
    private ListView listView;
    private ProfileViewListAdapter adapter;
    private Bundle bundle ;
    private Intent intent;


    private FirebaseAuth mFireBaseAuth;
    
    private FirebaseUser mFirebaseUser;
    
    private DatabaseReference mDatabaseReference;
    
    private TextView user_name, user_email, user_phone, user_sliit_id;
    
    private Button mButton;

    private Toolbar toolbar;


    private de.hdodenhof.circleimageview.CircleImageView circleImageView;



    
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

        
        user_name = (TextView) findViewById(R.id.user_name);
        user_sliit_id = (TextView) findViewById(R.id.user_sliit_id);

        circleImageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.avatar);

        listView= (ListView) findViewById(R.id.profileListView);
        listView.setAdapter(adapter);
        setListLisner();
        
        fillTextViews();
        addPostListner();
        
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

                    User user = dataSnapshot.getValue(User.class);
                    user_name.setText(user.getName());
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

    public void setListLisner(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModels.get(position);

                intent = new Intent(view.getContext(), PostViewActivity.class);

                bundle = new Bundle();//create bundle object to pass values
                bundle.putString("window",TAG);
                bundle.putString("id",  dataModels.get(position).getId());
                bundle.putString("post_id",dataModels.get(position).getPost_id());
                bundle.putString("author",dataModels.get(position).getAuthor());
                bundle.putString("author_img",dataModels.get(position).getAuthor_img());
                bundle.putString("user_id",dataModels.get(position).getUser_id());
                bundle.putString("comment",dataModels.get(position).getComment());
                bundle.putString("date",dataModels.get(position).getDate());
                bundle.putString("description",dataModels.get(position).getDescription());
                bundle.putString("dislike",dataModels.get(position).getDislike());
                bundle.putString("image",dataModels.get(position).getImage());
                bundle.putString("like",dataModels.get(position).getLike());
                bundle.putString("title",dataModels.get(position).getTitle());

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }

    public void addPostListner(){
        FirebaseMethods fm = new FirebaseMethods(this);
        DatabaseReference ref = fm.getMyRef();





        ref.child("/post").orderByChild("user_id").equalTo(fm.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataModels= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    dataModels.add(postSnapshot.getValue(ProfileViewItemModel.class));
                }

                if(dataModels.size()!=0)
                {

                    adapter = new ProfileViewListAdapter(dataModels,getApplicationContext());
                    listView.setAdapter(adapter);
                }else {
                    dataModels= new ArrayList<>();
                    adapter = new ProfileViewListAdapter(dataModels,getApplicationContext());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    
}
