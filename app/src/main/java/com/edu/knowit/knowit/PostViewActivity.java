package com.edu.knowit.knowit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.SearchItemModel;

import java.util.ArrayList;

public class PostViewActivity extends AppCompatActivity {

    ArrayList<SearchItemModel> dataModels;

    private Toolbar toolbar;
    private ListView listView;
    private View view;
    private SearchListAdapter adapter;

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
        setContentView(R.layout.activity_post_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView= (ListView) findViewById(R.id.commentlistview);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
