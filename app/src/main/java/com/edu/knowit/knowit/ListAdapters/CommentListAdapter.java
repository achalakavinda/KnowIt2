package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.knowit.knowit.Models.CommentItemModel;
import com.edu.knowit.knowit.R;



import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/22/2018.
 */

public class CommentListAdapter  extends ArrayAdapter<CommentItemModel> implements View.OnClickListener{
    String TAG="";

    private ArrayList<CommentItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView id;
        TextView author;
        ImageView author_image;
        TextView data;
        TextView description;
    }

    public CommentListAdapter(ArrayList<CommentItemModel> data, Context context) {
        super(context, R.layout.row_comment_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CommentItemModel dataModel=(CommentItemModel) object;
        switch (v.getId())
        {

        }
    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CommentItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        CommentListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new CommentListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_comment_item, parent, false);


            viewHolder.id = (TextView) convertView.findViewById(R.id.id);
            viewHolder.author = (TextView) convertView.findViewById(R.id.name);
            viewHolder.data = (TextView) convertView.findViewById(R.id.date);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CommentListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.id.setText(dataModel.getId());
        viewHolder.author.setText(dataModel.getAuthor());
        viewHolder.data.setText(dataModel.getDate());
        viewHolder.description.setText(dataModel.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }


}
