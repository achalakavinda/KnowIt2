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

import com.edu.knowit.knowit.ListAdapters.ProfileListAdapter;
import com.edu.knowit.knowit.ListAdapters.SearchListAdapter;
import com.edu.knowit.knowit.Models.ProfileItemModel;
import com.edu.knowit.knowit.Models.SearchItemModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {

    ArrayList<ProfileItemModel> dataModels;
    ListView listView;
    private View view;
    private ProfileListAdapter adapter;
    private Intent intent;
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        listView=view.findViewById(R.id.profileListView);
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


        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));
        dataModels.add(new ProfileItemModel("id","PS","dhaus dhdj sdh dhasd hadjh adhad hhda dua da dhasdhashdhasudhahdahd"));

        adapter = new ProfileListAdapter(dataModels,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        spinner.setVisibility(View.GONE);
    }

}
