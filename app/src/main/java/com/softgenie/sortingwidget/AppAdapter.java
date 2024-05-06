package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Context context;
    private List<ApplicationInfo> appList;
    private PackageManager packageManager;

    public AppAdapter(Context context, List<ApplicationInfo> appList) {
        this.context = context;
        this.appList = appList;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.icon_layout, parent, false);
            holder = new ViewHolder();
            holder.iconImageView = convertView.findViewById(R.id.icon_image_view);
            holder.nameTextView = convertView.findViewById(R.id.name_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ApplicationInfo appInfo = appList.get(position);
        holder.iconImageView.setImageDrawable(packageManager.getApplicationIcon(appInfo));
        holder.nameTextView.setText(packageManager.getApplicationLabel(appInfo));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp(appInfo.packageName);
            }
        });

        return convertView;
    }

    private void launchApp(String packageName) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        } else {
            Toast.makeText(context, "Unable to launch app", Toast.LENGTH_SHORT).show();
        }
    }

    private static class ViewHolder {
        ImageView iconImageView;
        TextView nameTextView;
    }
}
