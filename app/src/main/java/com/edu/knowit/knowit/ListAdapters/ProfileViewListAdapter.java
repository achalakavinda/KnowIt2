package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.knowit.knowit.Models.ProfileViewItemModel;
import com.edu.knowit.knowit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Achala Kavinda on 5/10/2018.
 */

public class ProfileViewListAdapter extends ArrayAdapter<ProfileViewItemModel> implements View.OnClickListener{

     private final String TAG="HomeListAdapter";

    private ArrayList<ProfileViewItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtId;
        TextView txtDate;
        TextView txtTitle;
        ImageView imgImage;
        TextView txtDesc;
        LinearLayout imgLayout;
    }

    public ProfileViewListAdapter(ArrayList<ProfileViewItemModel> data, Context context) {
        super(context, R.layout.row_profile_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ProfileViewItemModel dataModel=(ProfileViewItemModel) object;
        System.out.println("call");
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ProfileViewItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ProfileViewListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ProfileViewListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_profile_view_item, parent, false);

            viewHolder.txtId = (TextView) convertView.findViewById(R.id.id);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.description);
            viewHolder.imgLayout = (LinearLayout) convertView.findViewById(R.id.imgLayout);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProfileViewListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtId.setText(dataModel.getId());
        viewHolder.txtDate.setText(dataModel.getDate());
        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDesc.setText(dataModel.getDescription());

        if(dataModel.getImage()!=null && !dataModel.getImage().isEmpty()){
            viewHolder.imgLayout.setVisibility(View.VISIBLE);
            Picasso.get().load(dataModel.getImage()).into(viewHolder.imgImage);
        }else{
            viewHolder.imgLayout.setVisibility(View.GONE);
        }

        return convertView;
    }
}
