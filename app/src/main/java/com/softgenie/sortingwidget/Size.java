package com.softgenie.sortingwidget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;


public class Size extends AppCompatActivity {

    Button skip1, next1, button4x6, button4x4, button4x2, button2x2;
    ImageView imageViewx46, imageViewx44, imageViewx42, imageViewx22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_size);

        skip1 = findViewById(R.id.skip1);
        next1 = findViewById(R.id.next1);

        button4x6 = findViewById(R.id.button4x6); // Initialize button4x6
        button4x4 = findViewById(R.id.button4x4); // Initialize button4x4
        button4x2 = findViewById(R.id.button4x2); // Initialize button4x2
        button2x2 = findViewById(R.id.button2x2); // Initialize button2x2

        imageViewx46 = findViewById(R.id.imageViewx46);
        imageViewx44 = findViewById(R.id.imageViewx44);
        imageViewx42 = findViewById(R.id.imageViewx42);
        imageViewx22 = findViewById(R.id.imageViewx22);

        // Initially set x46 image visible and others invisible
        imageViewx46.setVisibility(View.VISIBLE);
        imageViewx44.setVisibility(View.INVISIBLE);
        imageViewx42.setVisibility(View.INVISIBLE);
        imageViewx22.setVisibility(View.INVISIBLE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        skip1.setOnClickListener(v -> {
            Intent intent = new Intent(Size.this, Prioritize.class);
            finish();
            startActivity(intent);
        });

        next1.setOnClickListener(v -> {
            Intent intent = new Intent(Size.this, Prioritize.class);
            finish();
            startActivity(intent);
        });

        button4x6.setOnClickListener(v -> {
            imageViewx46.setVisibility(View.VISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // 알고리즘이 있는 클래스와 메소드 명으로 바꾼다
            // AlgorithmClass.performAlgorithm(x46);

        });

        button4x4.setOnClickListener(v -> {
            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.VISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // AlgorithmClass.performAlgorithm(x44);
        });

        button4x2.setOnClickListener(v -> {
            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.VISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // AlgorithmClass.performAlgorithm(x42);
        });

        button2x2.setOnClickListener(v -> {
            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.VISIBLE);
            // AlgorithmClass.performAlgorithm(x22);
        });
    }

}