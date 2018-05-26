package com.edu.knowit.knowit;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.Post;
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
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;

    private EditText searchText;
    private ImageButton searchBtn;

    private String  SearchString;

    private FirebaseMethods firebaseMethods;
    private DatabaseReference ref;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        listView=view.findViewById(R.id.paymentListView);
        spinner = view.findViewById(R.id.progressBarPayment);
        spinner.setVisibility(View.GONE);
        searchText = view.findViewById(R.id.searchText);
        searchBtn = view.findViewById(R.id.search_button);

        searchBtn.setOnClickListener(this);

        firebaseMethods = new FirebaseMethods(getActivity().getApplicationContext());
        ref = firebaseMethods.getPostRef();

        searchText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int s, int b, int c) {
                Log.i(TAG, cs.toString());
                setSearchString(cs.toString());
                SearchString();
            }
            public void afterTextChanged(Editable editable) {}
            public void beforeTextChanged(CharSequence cs, int i, int j, int k) {}
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        dataModels= new ArrayList<>();

        co_ordinated_layout_main_payment = view.findViewById(R.id.co_ordinated_layout_main_payment);

        dataModels= new ArrayList<>();


        dataModels.add(new SearchItemModel());

        adapter = new SearchListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
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
                        Log.d(TAG,search.getDescription()+"\n");
                        dataModels.add(search);
                    }

                    adapter = new SearchListAdapter(dataModels,getActivity().getApplicationContext());
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
