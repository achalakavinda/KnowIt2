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

import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.SearchItemModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends android.support.v4.app.Fragment {

    ArrayList<SearchItemModel> dataModels;
    ListView listView;
    private View view;
    private SearchListAdapter adapter;
    private Intent intent;
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;


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

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        dataModels= new ArrayList<>();

        co_ordinated_layout_main_payment = view.findViewById(R.id.co_ordinated_layout_main_payment);

        dataModels= new ArrayList<>();


        dataModels.add(new SearchItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-I","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-III","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CPMAD","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CDAP","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-I","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-III","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CPMAD","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CDAP","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-I","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","MIT","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","DCCN-III","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CPMAD","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new SearchItemModel("id","CDAP","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));

        adapter = new SearchListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
    }


}
