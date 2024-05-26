package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Include extends AppCompatActivity {
    private List<AppData> appDataList;
    private ListView appListView;
    private ArrayAdapter<AppData> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);

        AppList appList = new AppList(this);
        appDataList = appList.getAppList();
        appListView = findViewById(R.id.appListView);

        adapter = new ArrayAdapter<AppData>(this, R.layout.appitem, R.id.apptextView, appDataList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView appNameTextView = view.findViewById(R.id.apptextView);
                ImageView appIconImageView = view.findViewById(R.id.appimageView);
                CheckBox checkBox = view.findViewById(R.id.checkBox);

                AppData appData = appDataList.get(position);
                appNameTextView.setText(appData.getAppName());
                appIconImageView.setImageDrawable(appData.getAppIcon());
                checkBox.setChecked(appData.getSelected());
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> appData.setSelected(isChecked));

                return view;
            }
        };

        appListView.setAdapter(adapter);

        findViewById(R.id.next3).setOnClickListener(v -> {
            ArrayList<AppData> selectedApps = new ArrayList<>();
            for (AppData app : appDataList) {
                if (app.getSelected()) {
                    selectedApps.add(app);
                }
            }
            Intent intent = new Intent(Include.this, TempWidget.class);
            intent.putParcelableArrayListExtra("selectedApps", selectedApps);
            startActivity(intent);
        });
        findViewById(R.id.back3).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Prioritize.class);
            finish();
            startActivity(intent);
        });
    }
}

