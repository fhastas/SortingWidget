package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Exclude extends AppCompatActivity {

    Button back4, next4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exclude);

        back4 = findViewById(R.id.back4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back4.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);
            startActivity(intent);
        });

        next4.setOnClickListener(v -> {
            // 마지막 단게를 마치고서 알고리즘에 정보를 넘겨준다
        });

    }
}