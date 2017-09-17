package com.example.android.listviewwithradiobutton;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by anuragkondeya on 17/9/17.
 */

public class CustomListViewAdapter extends ArrayAdapter {

    String[] mOptions;
    Context mContext;
    @LayoutRes int mLayoutResource;
    int selectedPosition = 0;


    static class ViewHolder {
        TextView options;
        RadioButton select;
    }



    public CustomListViewAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.mContext = context;
        this.mLayoutResource = resource;
    }

    public void setOptions(String[] options){
        this.mOptions = options;
    }

    private void updateRadioSelection(View view){
        selectedPosition = (Integer)view.getTag();
        notifyDataSetChanged();
    }

    private RadioButton.OnClickListener radioButtonClickListener(){
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateRadioSelection(view);
            }
        };
    }

    @Override
    public int getCount() {
        return mOptions.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(null == convertView){
            convertView = ((Activity)mContext).getLayoutInflater().inflate(mLayoutResource,null);
            viewHolder = new ViewHolder();
            //viewHolder.options = (TextView) convertView.findViewById(R.id.tv_options);
            viewHolder.select = (RadioButton)convertView.findViewById(R.id.radio_selection);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
       // viewHolder.options.setText(mOptions[position]);
        viewHolder.select.setChecked(position == selectedPosition);
        viewHolder.select.setText(mOptions[position]);
        viewHolder.select.setTag(position);
        viewHolder.select.setOnClickListener(radioButtonClickListener());
        notifyDataSetChanged();
        return convertView;
    }
}
