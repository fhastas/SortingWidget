package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Include extends AppCompatActivity {
    private static final String TAG = "Include";

    AppList appList;
    private List<AppData> appDataList;
    private ListView appListView;
    private ArrayAdapter<AppData> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);

        appList = new AppList(this);

        appDataList = appList.getAppList();
        appListView = findViewById(R.id.appListView);

        adapter = new ArrayAdapter<>(this, R.layout.appitem, R.id.appTextView, appDataList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView appNameTextView = view.findViewById(R.id.appTextView);
                ImageView appIconImageView = view.findViewById(R.id.appimageView);
                CheckBox checkBox = view.findViewById(R.id.checkBox);

                AppData appData = appDataList.get(position);
                appNameTextView.setText(appData.getAppName());
                appIconImageView.setImageBitmap(appData.getAppIcon());
                checkBox.setChecked(appData.getSelected());
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> appData.setSelected(isChecked));

                return view;
            }
        };

        appListView.setAdapter(adapter);

        findViewById(R.id.next3).setOnClickListener(v -> {
            ArrayList<String> selectedApps = new ArrayList<>();
            for (AppData app : appDataList) {
                if (app.getSelected()) {
                    selectedApps.add(app.getAppName());
                }
            }
            SharedPreferencesHelper.saveIncludeInfo(this, selectedApps);

            Intent intent = new Intent(Include.this, TempWidget.class);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.back3).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Prioritize.class);
            startActivity(intent);
            finish();
        });
    }
}

