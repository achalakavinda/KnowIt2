package com.edu.knowit.knowit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.SearchItemModel;

import java.util.ArrayList;

public class PostViewActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG="PostViewActivity";

    private ArrayList<SearchItemModel> dataModels;

    private Toolbar toolbar;
    private ListView listView;
    private View view;
    private SearchListAdapter adapter;
    private ImageButton imageButtonCloseToggle;
    private LinearLayout linearLayoutCommentLinearLayout;
    private Button buttonLike;
    private Button buttonQandA;
    private Button buttonDislike;


    @Override
    protected void onStart() {

        super.onStart();

        dataModels= new ArrayList<>();


        dataModels= new ArrayList<>();


        dataModels.add(new SearchItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-I","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));

        adapter = new SearchListAdapter(dataModels,this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_post_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView= (ListView) findViewById(R.id.commentlistview);
        imageButtonCloseToggle = (ImageButton) findViewById(R.id.commentListToggle);
        linearLayoutCommentLinearLayout = (LinearLayout) findViewById(R.id.commentLinearLayout);

        buttonLike = (Button) findViewById(R.id.likeButton);
        buttonQandA = (Button) findViewById(R.id.qAndAButton);
        buttonDislike = (Button) findViewById(R.id.dislikeButton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageButtonCloseToggle.setOnClickListener(this);
        buttonLike.setOnClickListener(this);
        buttonDislike.setOnClickListener(this);
        buttonQandA.setOnClickListener(this);


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
                linearLayoutCommentLinearLayout.setVisibility(View.VISIBLE);
                linearLayoutCommentLinearLayout.animate();
                break;
        }
    }
}
