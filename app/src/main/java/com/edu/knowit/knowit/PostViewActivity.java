package com.edu.knowit.knowit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.knowit.knowit.ListAdapters.CommentListAdapter;
import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.CommentItemModel;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.User;
import com.edu.knowit.knowit.Util.DialogBox;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.edu.knowit.knowit.Util.StringValidator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostViewActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG="PostViewActivity";

    private View view;
    private Intent intent;
    private Bundle extraBundle;
    private User user;

    private ArrayList<CommentItemModel> dataModels;

    private Toolbar toolbar;
    private ListView listView;

    private TextView txtId;
    private ImageView authorImage;
    private TextView txtName;
    private TextView txtDate;
    private TextView txtSubject;
    private LinearLayout imgLayout;
    private ImageView image;
    private TextView description;
    private Button buttonReply;
    private EditText commentText;




    private CommentListAdapter adapter;
    private ImageButton imageButtonCloseToggle;
    private RelativeLayout linearLayoutCommentLinearLayout;
    private Button buttonLike;
    private Button buttonQandA;
    private Button buttonDislike;

    public static class info{
        private static String my_id;
        private static String  my_name;
        private static String my_url;
        public static  String id;
        public static  String post_id;
        public static  String author;
        public static  String author_img;
        public static  String date;
        public static  String title;
        public static  String image;
        public static  String category;
        public static  String description;
        public static  String like;
        public static  String comment;
        public static  String dislike;
    }


    @Override
    protected void onStart() {

        super.onStart();

        dataModels= new ArrayList<>();



        adapter = new CommentListAdapter(dataModels,this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        getUserInfo(new FirebaseMethods(this).getUserID());

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_post_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView= (ListView) findViewById(R.id.commentlistview);
        imageButtonCloseToggle = (ImageButton) findViewById(R.id.commentListToggle);
        linearLayoutCommentLinearLayout = (RelativeLayout) findViewById(R.id.commentLinearLayout);

        txtId = (TextView) findViewById(R.id.id);
        txtName = (TextView) findViewById(R.id.name);
        authorImage = (ImageView) findViewById(R.id.authorImage);
        txtDate = (TextView) findViewById(R.id.date);
        txtSubject = (TextView) findViewById(R.id.subject);
        description = (TextView) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.image);



        buttonQandA = (Button) findViewById(R.id.qAndAButton);
        buttonReply = (Button) findViewById(R.id.reply);

        commentText = (EditText) findViewById(R.id.commentText);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageButtonCloseToggle.setOnClickListener(this);
        buttonQandA.setOnClickListener(this);
        buttonReply.setOnClickListener(this);



        if(extractExtraFromBundle()){
            setValue();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.commentListToggle:
                linearLayoutCommentLinearLayout.setVisibility(View.GONE);
                break;
            case R.id.qAndAButton:
                addCommentListner();
                linearLayoutCommentLinearLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.reply:
                pushComment();
                break;
        }
    }

    private boolean extractExtraFromBundle(){

        extraBundle = intent.getExtras();
        if(extraBundle!= null){
            info.id = extraBundle.getString("id");
            info.post_id = extraBundle.getString("post_id");
            info.author = extraBundle.getString("author");
            info.author_img = extraBundle.getString("author_img");
            info.comment = extraBundle.getString("comment");
            info.date = extraBundle.getString("date");
            info.description = extraBundle.getString("description");
            info.dislike = extraBundle.getString("dislike");
            info.image = extraBundle.getString("image");
            info.like = extraBundle.getString("like");
            info.title = extraBundle.getString("title");

            return true;

        }else {
//                new DialogBox().ViewDialogBox(,"Error","No Values are pass");
            Log.d(TAG,"Bundle values are not pass");
                return false;
        }
    }

    private void setValue(){
        txtId.setText(info.id);
        txtName.setText(info.author);
        txtDate.setText(info.date);
        txtSubject.setText(info.title);
        description.setText(info.description);

        if(info.author_img!=null && !info.author_img.isEmpty()){
            Picasso.get().load(info.author_img).into(authorImage);
        }
        if(info.image!=null && !info.image.isEmpty()){
            Picasso.get().load(info.image).into(image);
        }
    }

    public void getUserInfo(String userID){
        System.out.println(userID);
        FirebaseMethods fm  = new FirebaseMethods(this);
        DatabaseReference ref = fm.getUserInfoRef(userID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                System.out.println(user.getName());
                info.my_name = user.getName();
                info.my_url = user.getUrl();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void pushComment(){
        FirebaseMethods fm = new FirebaseMethods(this);
        DatabaseReference ref = fm.createComment().child(info.post_id);
        CommentItemModel comment = new CommentItemModel(fm.getUserID(),info.id,info.my_name,info.my_url,fm.getTimestamp(),commentText.getText().toString());

        ref.push().setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG,"Success");
                commentText.setText("");
                Toast.makeText(getApplication().getApplicationContext(),"Succesfully Posted",Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,e.getMessage());
                        Toast.makeText(getApplication().getApplicationContext(),"Error ...",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void addCommentListner(){
        FirebaseMethods fm = new FirebaseMethods(this);
        DatabaseReference ref = fm.getMyRef();



        ref.child("/comment").child(info.post_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataModels= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    dataModels.add(postSnapshot.getValue(CommentItemModel.class));
                }

                if(dataModels.size()!=0){
                    adapter = new CommentListAdapter(dataModels,getApplication().getApplicationContext());
                    listView.setAdapter(adapter);
                }else {
                    System.out.println("no post found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }
}
