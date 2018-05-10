package com.edu.knowit.knowit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment {

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
    public void onStart() {
        super.onStart();
        dataModels= new ArrayList<>();

        co_ordinated_layout_main_payment = view.findViewById(R.id.co_ordinated_layout_main_payment);

        dataModels= new ArrayList<>();


        dataModels.add(new HomeItemModel("dsdada1231","Achala Kavinda","2017-01-23","MIT","","Ok that made a bit of a difference. The image and the child LinearLayout are now similar widths but they aren't 50% of the parent. Here is a screenshot i.stack.imgur.com/UuFHp.png","","",""));
        dataModels.add(new HomeItemModel("dsdada1231","Kavini","2017-01-12","MIT","","If I want to add third child that must be under the first one, but it is not?","","",""));
        dataModels.add(new HomeItemModel("dsdada1231","Sithira","2017-01-12","MIT","","To create a linear layout in which each child uses the same amount of space on the screen, set the android:layout_height of each view to","","",""));

        adapter = new HomeListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
    }
}
