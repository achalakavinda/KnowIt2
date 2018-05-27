package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.Models.SearchItemModel;
import com.edu.knowit.knowit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchListAdapter extends ArrayAdapter<SearchItemModel> implements View.OnClickListener{

    String TAG="";

    private ArrayList<SearchItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView authorImage;
        TextView name;
        TextView date;
        TextView txtTitle;
        TextView txtDesc;

    }
    public SearchListAdapter(ArrayList<SearchItemModel> data, Context context) {
        super(context, R.layout.row_search_item, data);
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
        SearchItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        SearchListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new SearchListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_search_item, parent, false);

            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.description);

            viewHolder.authorImage = (ImageView) convertView.findViewById(R.id.authorImage);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SearchListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDesc.setText(dataModel.getDescription());
        viewHolder.name.setText(dataModel.getAuthor());
        viewHolder.date.setText(dataModel.getDate());

        if(dataModel.getAuthor_img()!=null && !dataModel.getAuthor().isEmpty()){
            Picasso.get().load(dataModel.getAuthor_img()).into(viewHolder.authorImage);
        }

        // Return the completed view to render on screen
        return convertView;
    }


}
