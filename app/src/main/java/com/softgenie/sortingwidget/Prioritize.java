package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicInteger;


public class Prioritize extends AppCompatActivity {

    ImageView imageViewx46, imageViewx44, imageViewx42, imageViewx22;
    ImageButton prioritizeButton11, prioritizeButton12, prioritizeButton13, prioritizeButton14,
            prioritizeButton21, prioritizeButton22, prioritizeButton23, prioritizeButton24,
            prioritizeButton31, prioritizeButton32, prioritizeButton33, prioritizeButton34,
            prioritizeButton41, prioritizeButton42, prioritizeButton43, prioritizeButton44,
            prioritizeButton51, prioritizeButton52, prioritizeButton53, prioritizeButton54,
            prioritizeButton61, prioritizeButton62, prioritizeButton63, prioritizeButton64;
    Button number1, number2, number3, number4;
    Button back2, next2;

    Intent intent = getIntent();
    UserInfo userInfo = null;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prioritize);

        if (intent != null) {
            userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
        } else {
            userInfo = new UserInfo();
        }

        assert userInfo != null;
        int size = userInfo.getSize();
        AtomicInteger prioritize = new AtomicInteger(1);
        int[][] priorityList = new int[6][4];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                priorityList[i][j] = 4;
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
            priorityList[0][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton11.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton11.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton11.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton11.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton12.setOnClickListener(v -> {
            priorityList[0][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton12.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton12.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton12.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton12.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton13.setOnClickListener(v -> {
            priorityList[0][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton13.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton13.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton13.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton13.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton14.setOnClickListener(v -> {
            priorityList[0][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton14.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton14.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton14.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton14.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton21.setOnClickListener(v -> {
            priorityList[1][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton21.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton21.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton21.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton21.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton22.setOnClickListener(v -> {
            priorityList[1][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton22.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton22.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton22.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton22.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton23.setOnClickListener(v -> {
            priorityList[1][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton23.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton23.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton23.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton23.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton24.setOnClickListener(v -> {
            priorityList[1][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton24.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton24.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton24.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton24.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton31.setOnClickListener(v -> {
            priorityList[2][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton31.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton31.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton31.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton31.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton32.setOnClickListener(v -> {
            priorityList[2][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton32.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton32.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton32.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton32.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton33.setOnClickListener(v -> {
            priorityList[2][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton33.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton33.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton33.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton33.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton34.setOnClickListener(v -> {
            priorityList[2][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton34.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton34.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton34.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton34.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton41.setOnClickListener(v -> {
            priorityList[3][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton41.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton41.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton41.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton41.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton42.setOnClickListener(v -> {
            priorityList[3][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton42.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton42.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton42.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton42.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton43.setOnClickListener(v -> {
            priorityList[3][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton43.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton43.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton43.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton43.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton44.setOnClickListener(v -> {
            priorityList[3][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton44.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton44.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton44.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton44.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton51.setOnClickListener(v -> {
            priorityList[4][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton51.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton51.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton51.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton51.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton52.setOnClickListener(v -> {
            priorityList[4][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton52.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton52.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton52.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton52.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton53.setOnClickListener(v -> {
            priorityList[4][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton53.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton53.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton53.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton53.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton54.setOnClickListener(v -> {
            priorityList[4][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton54.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton54.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton54.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton54.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton61.setOnClickListener(v -> {
            priorityList[5][0] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton61.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton61.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton61.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton61.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton62.setOnClickListener(v -> {
            priorityList[5][1] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton62.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton62.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton62.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton62.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton63.setOnClickListener(v -> {
            priorityList[5][2] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton63.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton63.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton63.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton63.setImageResource(R.drawable.gray_square);
                    break;
            }
        });
        prioritizeButton64.setOnClickListener(v -> {
            priorityList[5][3] = prioritize.get();

            switch (prioritize.get()){
                case 1:
                    prioritizeButton64.setImageResource(R.drawable.red_square);
                    break;
                case 2:
                    prioritizeButton64.setImageResource(R.drawable.orange_square);
                    break;
                case 3:
                    prioritizeButton64.setImageResource(R.drawable.yellow_square);
                    break;
                default:
                    prioritizeButton64.setImageResource(R.drawable.gray_square);
                    break;
            }
        });

        userInfo.setPriority2List(priorityList);

        number1.setOnClickListener(v -> prioritize.set(1));
        number2.setOnClickListener(v -> prioritize.set(2));
        number3.setOnClickListener(v -> prioritize.set(3));
        number4.setOnClickListener(v -> prioritize.set(4));

        back2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Size.class);
            finish();
            startActivity(intent);
        });

        next2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Include.class);

            intent.putExtra("userInfo", userInfo);

            finish();
            startActivity(intent);
        });
    }
}