package com.edu.knowit.knowit;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.edu.knowit.knowit.ListAdapters.FeedsListAdapter;
import com.edu.knowit.knowit.Models.FeedsItemModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends android.support.v4.app.Fragment {
    ArrayList<FeedsItemModel> dataModels;
    ListView listView;
    private View view;
    private FeedsListAdapter adapter;
    private Intent intent;
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;



    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feeds, container, false);

        listView=view.findViewById(R.id.paymentListView);
        spinner = view.findViewById(R.id.progressBarPayment);
        spinner.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dataModels= new ArrayList<>();

        co_ordinated_layout_main_payment = view.findViewById(R.id.co_ordinated_layout_main_payment);

        dataModels= new ArrayList<>();

        dataModels.add(new FeedsItemModel("dsdada1231","Achala Kavinda","2017-01-23","MIT","","Ok that made a bit of a difference. The image and the child LinearLayout are now similar widths but they aren't 50% of the parent. Here is a screenshot i.stack.imgur.com/UuFHp.png","","",""));

        adapter = new FeedsListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
    }
}
