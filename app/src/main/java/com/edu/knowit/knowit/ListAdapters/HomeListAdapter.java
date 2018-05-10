package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.R;

import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/10/2018.
 */

public class HomeListAdapter extends ArrayAdapter<HomeItemModel> implements View.OnClickListener{

    private ArrayList<HomeItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtSubmission;
        TextView txtRecievedDate;
    }

    public HomeListAdapter(ArrayList<HomeItemModel> data, Context context) {
        super(context, R.layout.row_home_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {//onclick  event

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HomeItemModel dataModel=(HomeItemModel) object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getDescription(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HomeItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        HomeListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new HomeListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_home_item, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText("Name : "+dataModel.getAmount());
        // Return the completed view to render on screen
        return convertView;
    }
}
