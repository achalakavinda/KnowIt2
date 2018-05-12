package com.edu.knowit.knowit.ListAdapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.edu.knowit.knowit.Models.ProfileItemModel;
import com.edu.knowit.knowit.Models.SearchItemModel;
import com.edu.knowit.knowit.R;

import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/13/2018.
 */

public class ProfileListAdapter extends ArrayAdapter<ProfileItemModel> implements View.OnClickListener {

    String TAG="";

    private ArrayList<ProfileItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ProfileItemModel item;
        TextView txtDate;
        TextView txtTitle;
        TextView txtDesc;
    }

    public ProfileListAdapter(ArrayList<ProfileItemModel> data, Context context) {
        super(context, R.layout.row_profile_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        SearchItemModel dataModel=(SearchItemModel) object;
        switch (v.getId())
        {

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ProfileItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ProfileListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ProfileListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_profile_item, parent, false);

            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.description);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProfileListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDesc.setText(dataModel.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }



}
