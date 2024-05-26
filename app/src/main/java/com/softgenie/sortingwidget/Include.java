package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
                appIconImageView.setImageDrawable(appData.getAppIcon(getApplicationContext()));
                checkBox.setChecked(appData.isSelected());
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> appData.setSelected(isChecked));

                return view;
            }
        };

        appListView.setAdapter(adapter);

        findViewById(R.id.next3).setOnClickListener(v -> {
            ArrayList<AppData> selectedApps = new ArrayList<>();
            for (AppData app : appDataList) {
                if (app.isSelected()) {
                    selectedApps.add(app);
                }
            }
            Intent intent = new Intent(Include.this, TempWidget.class);
            intent.putParcelableArrayListExtra("selectedApps", (ArrayList<? extends Parcelable>) selectedApps);
            startActivity(intent);
        });
    }
}