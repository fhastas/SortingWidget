package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Comparator;


public class Include extends AppCompatActivity {
    Button back3, next3, skip3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_include);

        AppList appList = SharedPreferencesHelper.loadAppList(getApplicationContext());
        if (appList == null) {
            appList = new AppList();
            appList.getAppList().sort(Comparator.comparing(AppData::getAppName));
            SharedPreferencesHelper.saveAppList(getApplicationContext(), appList);
        }

        back3 = findViewById(R.id.back3);
        skip3 = findViewById(R.id.skip3);
        next3 = findViewById(R.id.next3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Prioritize.class);
            finish();
            startActivity(intent);
        });

        skip3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Exclude.class);
            finish();
            startActivity(intent);
        });

        next3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Exclude.class);
            finish();
            startActivity(intent);
        });
    }
}