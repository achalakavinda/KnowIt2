package com.edu.knowit.knowit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.SearchItemModel;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.edu.knowit.knowit.Util.StringValidator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass
 */
public class SearchFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private String TAG = "SearchFragment";

    ArrayList<SearchItemModel> dataModels;
    ListView listView;
    private View view;
    private SearchListAdapter adapter;

    private EditText searchText;
    private ImageButton searchBtn;

    private String  SearchString;

    private FirebaseMethods firebaseMethods;
    private DatabaseReference ref;

    private static final String[] paths = {"DCCN", "PS", "MIT"};
    private Spinner spinner;

    private Bundle bundle ;
    private Intent intent;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        listView=view.findViewById(R.id.paymentListView);
        searchText = view.findViewById(R.id.searchText);
        searchBtn = view.findViewById(R.id.search_button);

        searchBtn.setOnClickListener(this);

        firebaseMethods = new FirebaseMethods(getActivity().getApplicationContext());
        ref = firebaseMethods.getPostRef();


        spinner = (Spinner) view.findViewById(R.id.searchDropdown);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        searchText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int s, int b, int c) {
                Log.i(TAG, cs.toString());
                setSearchString(cs.toString());
                SearchString();
            }
            public void afterTextChanged(Editable editable) {}
            public void beforeTextChanged(CharSequence cs, int i, int j, int k) {}
        });

        setSearchString(spinner.getSelectedItem().toString());
        SearchString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSearchString(spinner.getSelectedItem().toString());
                SearchString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        dataModels= new ArrayList<>();

//
//        dataModels.add(new SearchItemModel());

        adapter = new SearchListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.search_button:

                Log.d(TAG,searchText.getText().toString());
                break;

                default:

                    return;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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


    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String searchString){
        this.SearchString = searchString;
    }

    public void SearchString(){
        StringValidator stringValidator = new StringValidator();

        if(!stringValidator.isEmptyString(getSearchString())){
            ref.orderByChild("category").equalTo(getSearchString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    dataModels= new ArrayList<>();
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                       SearchItemModel search = postSnapshot.getValue(SearchItemModel.class);
                        dataModels.add(search);
                    }

                    if(dataModels.size()!=0)
                    {
                        if(getActivity().getApplicationContext() == null)
                        {
                            return;
                        }
                        adapter = new SearchListAdapter(dataModels, getActivity().getApplicationContext());
                        listView.setAdapter(adapter);
                    }else{
                        if(getActivity().getApplicationContext() == null)
                        {
                            return;
                        }
                        adapter = new SearchListAdapter(dataModels, getActivity().getApplicationContext());
                        listView.setAdapter(adapter);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
