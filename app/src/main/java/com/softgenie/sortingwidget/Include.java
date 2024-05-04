package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Include extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        EdgeToEdge.enable(this);

        findViewById(R.id.back3).setOnClickListener(v -> {
            Intent intentBack = new Intent(getApplicationContext(), Prioritize.class);
            startActivity(intentBack);
        });

        findViewById(R.id.skip3).setOnClickListener(v -> {
            Intent intentSkip = new Intent(getApplicationContext(), Exclude.class);
            startActivity(intentSkip);
        });

        findViewById(R.id.next3).setOnClickListener(v -> {
            Intent intentSkip = new Intent(getApplicationContext(), Exclude.class);
            startActivity(intentSkip);
        });
    }
}
