package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
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
import com.edu.knowit.knowit.R;

import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/10/2018.
 */

public class HomeListAdapter extends ArrayAdapter<HomeItemModel> implements View.OnClickListener{

     String TAG="";

    private ArrayList<HomeItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        HomeItemModel item;
        TextView txtId;
        TextView txtAuthor;
        TextView txtDate;
        TextView txtTitle;
        ImageView imgImage;
        TextView txtDesc;
        Button btnLike;
        Button btnComment;
        Button btnDislike;
    }

    public HomeListAdapter(ArrayList<HomeItemModel> data, Context context) {
        super(context, R.layout.row_home_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HomeItemModel dataModel=(HomeItemModel) object;
        switch (v.getId())
        {
            case R.id.like:
                System.out.println("Like button call"+dataModel.getAuthor());
                break;
            case R.id.comment:
                System.out.println("Comment button call"+dataModel.getAuthor());
                break;
            case R.id.dislike:
                System.out.println("Dislike button call"+dataModel.getAuthor());
                break;
        }
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

            viewHolder.txtId = (TextView) convertView.findViewById(R.id.id);
            viewHolder.txtAuthor = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.description);
            viewHolder.btnLike= (Button) convertView.findViewById(R.id.like);
            viewHolder.btnComment= (Button) convertView.findViewById(R.id.comment);
            viewHolder.btnDislike= (Button) convertView.findViewById(R.id.dislike);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtId.setText(dataModel.getId());
        viewHolder.txtAuthor.setText(dataModel.getAuthor());
        viewHolder.txtDate.setText(dataModel.getDate());
        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDesc.setText(dataModel.getDescription());

        viewHolder.btnDislike.setTag(position);
        viewHolder.btnLike.setTag(position);
        viewHolder.btnComment.setTag(position);

        viewHolder.btnDislike.setOnClickListener(this);
        viewHolder.btnLike.setOnClickListener(this);
        viewHolder.btnComment.setOnClickListener(this);

        // Return the completed view to render on screen
        return convertView;
    }
}
