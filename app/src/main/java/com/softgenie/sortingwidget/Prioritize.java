package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicInteger;


public class Prioritize extends AppCompatActivity {

    ImageView imageViewx46, imageViewx44, imageViewx42, imageViewx22;
    Button prioritizeButton11, prioritizeButton12, prioritizeButton13, prioritizeButton14,
            prioritizeButton21, prioritizeButton22, prioritizeButton23, prioritizeButton24,
            prioritizeButton31, prioritizeButton32, prioritizeButton33, prioritizeButton34,
            prioritizeButton41, prioritizeButton42, prioritizeButton43, prioritizeButton44,
            prioritizeButton51, prioritizeButton52, prioritizeButton53, prioritizeButton54,
            prioritizeButton61, prioritizeButton62, prioritizeButton63, prioritizeButton64;
    Button number1, number2, number3, number4;
    Button skip2, back2, next2;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prioritize);

        int size = getIntent().getIntExtra("size", 46);
        AtomicInteger prioritize = new AtomicInteger(1);
        int[][] prioritizeList = new int[6][4];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                prioritizeList[i][j] = 4;
            }
        }

        imageViewx46 = findViewById(R.id.PrioritizeX46);
        imageViewx44 = findViewById(R.id.PrioritizeX44);
        imageViewx42 = findViewById(R.id.PrioritizeX42);
        imageViewx22 = findViewById(R.id.PrioritizeX22);
        prioritizeButton11 = findViewById(R.id.prioritizeButton11);
        prioritizeButton12 = findViewById(R.id.prioritizeButton12);
        prioritizeButton13 = findViewById(R.id.prioritizeButton13);
        prioritizeButton14 = findViewById(R.id.prioritizeButton14);
        prioritizeButton21 = findViewById(R.id.prioritizeButton21);
        prioritizeButton22 = findViewById(R.id.prioritizeButton22);
        prioritizeButton23 = findViewById(R.id.prioritizeButton23);
        prioritizeButton24 = findViewById(R.id.prioritizeButton24);
        prioritizeButton31 = findViewById(R.id.prioritizeButton31);
        prioritizeButton32 = findViewById(R.id.prioritizeButton32);
        prioritizeButton33 = findViewById(R.id.prioritizeButton33);
        prioritizeButton34 = findViewById(R.id.prioritizeButton34);
        prioritizeButton41 = findViewById(R.id.prioritizeButton41);
        prioritizeButton42 = findViewById(R.id.prioritizeButton42);
        prioritizeButton43 = findViewById(R.id.prioritizeButton43);
        prioritizeButton44 = findViewById(R.id.prioritizeButton44);
        prioritizeButton51 = findViewById(R.id.prioritizeButton51);
        prioritizeButton52 = findViewById(R.id.prioritizeButton52);
        prioritizeButton53 = findViewById(R.id.prioritizeButton53);
        prioritizeButton54 = findViewById(R.id.prioritizeButton54);
        prioritizeButton61 = findViewById(R.id.prioritizeButton61);
        prioritizeButton62 = findViewById(R.id.prioritizeButton62);
        prioritizeButton63 = findViewById(R.id.prioritizeButton63);
        prioritizeButton64 = findViewById(R.id.prioritizeButton64);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);
        skip2 = findViewById(R.id.skip2);
        back2 = findViewById(R.id.back2);
        next2 = findViewById(R.id.next2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switch (size){
            case 22:
                prioritizeButton62.setVisibility(View.GONE);
                prioritizeButton61.setVisibility(View.GONE);
                prioritizeButton52.setVisibility(View.GONE);
                prioritizeButton51.setVisibility(View.GONE);
            case 42:
                prioritizeButton44.setVisibility(View.GONE);
                prioritizeButton43.setVisibility(View.GONE);
                prioritizeButton42.setVisibility(View.GONE);
                prioritizeButton41.setVisibility(View.GONE);
                prioritizeButton34.setVisibility(View.GONE);
                prioritizeButton33.setVisibility(View.GONE);
                prioritizeButton32.setVisibility(View.GONE);
                prioritizeButton31.setVisibility(View.GONE);
            case 44:
                prioritizeButton24.setVisibility(View.GONE);
                prioritizeButton23.setVisibility(View.GONE);
                prioritizeButton22.setVisibility(View.GONE);
                prioritizeButton21.setVisibility(View.GONE);
                prioritizeButton14.setVisibility(View.GONE);
                prioritizeButton13.setVisibility(View.GONE);
                prioritizeButton12.setVisibility(View.GONE);
                prioritizeButton11.setVisibility(View.GONE);
            default:
                switch (size){
                    case 46:
                        imageViewx46.setVisibility(View.VISIBLE);
                        break;
                    case 44:
                        imageViewx44.setVisibility(View.VISIBLE);
                        break;
                    case 42:
                        imageViewx42.setVisibility(View.VISIBLE);
                        break;
                    case 22:
                        imageViewx22.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
        }

        prioritizeButton11.setOnClickListener(v -> {
            prioritizeList[0][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton11.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton11.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton11.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton11.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton12.setOnClickListener(v -> {
            prioritizeList[0][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton12.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton12.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton12.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton12.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton13.setOnClickListener(v -> {
            prioritizeList[0][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton13.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton13.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton13.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton13.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton14.setOnClickListener(v -> {
            prioritizeList[0][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton14.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton14.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton14.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton14.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton21.setOnClickListener(v -> {
            prioritizeList[1][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton21.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton21.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton21.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton21.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton22.setOnClickListener(v -> {
            prioritizeList[1][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton22.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton22.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton22.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton22.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton23.setOnClickListener(v -> {
            prioritizeList[1][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton23.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton23.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton23.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton23.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton24.setOnClickListener(v -> {
            prioritizeList[1][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton24.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton24.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton24.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton24.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton31.setOnClickListener(v -> {
            prioritizeList[2][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton31.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton31.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton31.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton31.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton32.setOnClickListener(v -> {
            prioritizeList[2][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton32.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton32.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton32.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton32.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton33.setOnClickListener(v -> {
            prioritizeList[2][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton33.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton33.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton33.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton33.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton34.setOnClickListener(v -> {
            prioritizeList[2][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton34.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton34.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton34.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton34.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton41.setOnClickListener(v -> {
            prioritizeList[3][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton41.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton41.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton41.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton41.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton42.setOnClickListener(v -> {
            prioritizeList[3][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton42.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton42.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton42.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton42.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton43.setOnClickListener(v -> {
            prioritizeList[3][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton43.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton43.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton43.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton43.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton44.setOnClickListener(v -> {
            prioritizeList[3][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton44.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton44.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton44.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton44.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton51.setOnClickListener(v -> {
            prioritizeList[4][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton51.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton51.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton51.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton51.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton52.setOnClickListener(v -> {
            prioritizeList[4][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton52.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton52.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton52.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton52.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton53.setOnClickListener(v -> {
            prioritizeList[4][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton53.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton53.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton53.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton53.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton54.setOnClickListener(v -> {
            prioritizeList[4][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton54.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton54.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton54.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton54.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton61.setOnClickListener(v -> {
            prioritizeList[5][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton61.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton61.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton61.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton61.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton62.setOnClickListener(v -> {
            prioritizeList[5][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton62.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton62.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton62.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton62.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton63.setOnClickListener(v -> {
            prioritizeList[5][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton63.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton63.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton63.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton63.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton64.setOnClickListener(v -> {
            prioritizeList[5][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton64.setBackgroundResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton64.setBackgroundResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton64.setBackgroundResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton64.setBackgroundResource(R.drawable.gray_square);
                    break;
            }
        });

        number1.setOnClickListener(v -> prioritize.set(1));
        number2.setOnClickListener(v -> prioritize.set(2));
        number3.setOnClickListener(v -> prioritize.set(3));
        number4.setOnClickListener(v -> prioritize.set(4));

        back2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Size.class);
            finish();
            startActivity(intent);
        });

        skip2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);
            finish();
            startActivity(intent);
        });

        next2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);
            intent.putExtra("prioritizeList", prioritizeList);
            intent.putExtra("size", size);
            finish();
            startActivity(intent);
        });
    }
}