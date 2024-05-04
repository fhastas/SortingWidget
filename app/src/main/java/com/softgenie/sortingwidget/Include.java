package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Include extends AppCompatActivity {
    Button back3;
    Button skip3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_include);
        back3 = findViewById(R.id.back3);
        skip3 = findViewById(R.id.skip3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Prioritize.class);
            startActivity(intent);
        });

        skip3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Exclude.class);
            startActivity(intent);
        });

    }
}