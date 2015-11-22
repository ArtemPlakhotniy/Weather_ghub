package com.example.minorius.weather_ghub.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minorius.weather_ghub.R;

import java.util.ArrayList;

/**
 * Created by Minorius on 22.11.2015.
 */
public class WorkingAdapter extends ArrayAdapter<UpData> {

    private final Activity activity;
    private final ArrayList<UpData> weather_list;

    public WorkingAdapter(final Activity a, final int textViewResourceId, final ArrayList<UpData>weather_list){
        super(a, textViewResourceId, weather_list);
        this.weather_list = weather_list;
        activity = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_prototype, parent, false);
            holder = new ViewHolder();

            holder.txtDate = (TextView) v.findViewById(R.id.txtDate);
            holder.txtTemp = (TextView) v.findViewById(R.id.txtTemp);
            holder.txtWeather = (TextView) v.findViewById(R.id.txtWeather);
//            holder.imageView = (ImageView) v.findViewById(R.id.imageView);
//            holder.textView8 = (TextView) v.findViewById(R.id.textView8);
//            holder.textView10 = (TextView) v.findViewById(R.id.textView10);
            v.setTag(holder);
        } else {
            holder = (ViewHolder)v.getTag();
        }

        UpData upData = weather_list.get(position);
        if(upData != null){
            holder.txtDate.setText(upData.getDate());
            holder.txtTemp.setText(upData.getTemp());
            holder.txtWeather.setText(upData.getP());

//            holder.textView8.setText(upData.getSpeed());
//            holder.textView10.setText(upData.getHumidity());
        }
        return v;
    }
    private static class ViewHolder{

        public TextView txtDate;
        public TextView txtTemp;
        public TextView txtWeather;
//        public ImageView imageView;
//        public TextView textView8;
//        public TextView textView10;

    }
}