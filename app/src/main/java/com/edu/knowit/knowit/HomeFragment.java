package com.edu.knowit.knowit;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.knowit.knowit.ListAdapters.HomeListAdapter;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Util.DialogBox;
import com.edu.knowit.knowit.Util.FirebaseMethods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment{
    private String TAG ="HomeFragment";
    ArrayList<HomeItemModel> dataModels;
    ListView listView;
    private View view;
    private HomeListAdapter adapter;
    private Intent intent;
    private CoordinatorLayout co_ordinated_layout_main_payment;
    private ProgressBar spinner;
    private Bundle bundle ;
    private DialogBox dBox;
    private TextView noPost;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        listView=view.findViewById(R.id.paymentListView);
        spinner = view.findViewById(R.id.progressBarHome);
        listView.setAdapter(adapter);
        noPost = view.findViewById(R.id.noPost);

        dBox = new DialogBox();


        // Inflate the layout for this fragment
        return view;
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
                bundle.putString("user_id",dataModels.get(position).getUser_id());
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

        spinner.setVisibility(View.VISIBLE);


        ref.child("/post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spinner.setVisibility(View.VISIBLE);
                dataModels= new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    dataModels.add(postSnapshot.getValue(HomeItemModel.class));
                }

                if(dataModels.size()!=0)
                {
                    if(null == getActivity().getApplicationContext())
                    {
                        spinner.setVisibility(View.GONE);
                        return;
                    }
                    noPost.setVisibility(View.GONE);
                    adapter = new HomeListAdapter(dataModels,getActivity().getApplicationContext());
                    listView.setAdapter(adapter);
                    spinner.setVisibility(View.GONE);
                }else {
                    spinner.setVisibility(View.GONE);
                    dataModels= new ArrayList<>();
                    if(null == getActivity().getApplicationContext())
                    {
                        spinner.setVisibility(View.GONE);
                        return;
                    }
                    noPost.setVisibility(View.VISIBLE);
                    adapter = new HomeListAdapter(dataModels,getActivity().getApplicationContext());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

}
