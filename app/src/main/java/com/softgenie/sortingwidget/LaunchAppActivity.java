package com.softgenie.sortingwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class LaunchAppActivity extends Activity {

    private GridView gridView;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        // Initialize GridView
        gridView = findViewById(R.id.grid_view);

        // Get installed apps
        List<ApplicationInfo> installedApps = getInstalledApps();

        // Set up the adapter for GridView
        appAdapter = new AppAdapter(this, installedApps);
        gridView.setAdapter(appAdapter);

        // Set item click listener for launching the selected app
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo appInfo = (ApplicationInfo) parent.getItemAtPosition(position);
                launchApp(appInfo.packageName);
            }
        });
    }

    private List<ApplicationInfo> getInstalledApps() {
        PackageManager packageManager = getPackageManager();
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }

    private void launchApp(String packageName) {
        PackageManager packageManager = getPackageManager();
        Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, "Unable to launch app", Toast.LENGTH_SHORT).show();
        }
    }
}

