package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class gridViewAdapter extends BaseAdapter {

    Context context;
    String[] nameApp;
    int[] iconApp;

    LayoutInflater inflater;
    public gridViewAdapter(Context context, String[] nameApp, int[] iconApp) {
        this.context = context;
        this.nameApp = nameApp;
        this.iconApp = iconApp;
    }

    @Override
    public int getCount() {
        return nameApp.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_include, null);
        }

        ImageView imageView = convertView.findViewById(R.id.include);
//        TextView textView = convertView.findViewById(R.id.);

        imageView.setImageResource(iconApp[position]);
//        textView.setText(nameApp[position]);

        return convertView;
    }
}