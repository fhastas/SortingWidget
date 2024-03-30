package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Prioritize extends AppCompatActivity {

    Button skip2;
    Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prioritize);
        skip2 = findViewById(R.id.skip2);
        back2 = findViewById(R.id.back2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Size.class);
            startActivity(intent);
        });

        skip2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);
            startActivity(intent);
        });

    }
}