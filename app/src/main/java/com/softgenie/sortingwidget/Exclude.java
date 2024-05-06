package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Comparator;


public class Exclude extends AppCompatActivity {
    Button back4, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exclude);

        AppList appList = SharedPreferencesHelper.loadAppList(getApplicationContext());
        if (appList == null) {
            appList = new AppList();
            appList.getAppList().sort(Comparator.comparing(AppData::getAppName));
            SharedPreferencesHelper.saveAppList(getApplicationContext(), appList);
        }

        back4 = findViewById(R.id.back4);
        done = findViewById(R.id.done);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back4.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);
            finish();
            startActivity(intent);
        });

        done.setOnClickListener(v -> {
            finish(); // 현재 액티비티 종료
        });
    }
}