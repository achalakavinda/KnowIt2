package com.edu.knowit.knowit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.Post;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    private String TAG ="HomeFragment";
    ArrayList<HomeItemModel> dataModels;
    ListView listView;
    private View view;
    private HomeListAdapter adapter;
    private Intent intent;
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        listView=view.findViewById(R.id.paymentListView);
        spinner = view.findViewById(R.id.progressBarPayment);
        spinner.setVisibility(View.GONE);



        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        co_ordinated_layout_main_payment = view.findViewById(R.id.co_ordinated_layout_main_payment);
        addPostListner();
        spinner.setVisibility(View.GONE);
    }

    public void addPostListner(){
        FirebaseMethods fm = new FirebaseMethods(getActivity().getApplicationContext());
        DatabaseReference ref = fm.getMyRef();
        ref.child("/post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataModels= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    dataModels.add(postSnapshot.getValue(HomeItemModel.class));
                    adapter = new HomeListAdapter(dataModels,getActivity().getApplicationContext());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });



    }

}
