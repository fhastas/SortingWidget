package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class TempWidget extends AppCompatActivity {

    Context context;

    ImageButton tempWidgetButton11, tempWidgetButton12, tempWidgetButton13, tempWidgetButton14
            , tempWidgetButton21, tempWidgetButton22, tempWidgetButton23, tempWidgetButton24
            , tempWidgetButton31, tempWidgetButton32, tempWidgetButton33, tempWidgetButton34
            , tempWidgetButton41, tempWidgetButton42, tempWidgetButton43, tempWidgetButton44
            , tempWidgetButton51, tempWidgetButton52, tempWidgetButton53, tempWidgetButton54
            , tempWidgetButton61, tempWidgetButton62, tempWidgetButton63, tempWidgetButton64;

    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temp_widget);

        AppList appList = SharedPreferencesHelper.loadAppList(context);
        if (appList == null) {
            appList = new AppList(context);
            SharedPreferencesHelper.saveAppList(context, appList);
        }

        UserInfo userInfo = SharedPreferencesHelper.loadUserInfo(context);
        if (userInfo == null) {
            userInfo = new UserInfo();
            SharedPreferencesHelper.saveUserInfo(context, userInfo);
        }

        for(int i = 0; i < appList.getAppList().size(); i++){
            for(int j = 0; j < userInfo.getInclude().size(); j++){
                // AppData에 포함할 값 저장
                if(appList.getAppList().get(i).getAppName().equals(userInfo.getInclude().get(j))){
                    appList.getAppList().get(i).setSelected(true);
                }
                // AppData에 제외할 값 저장
                if(appList.getAppList().get(i).getAppName().equals(userInfo.getExclude().get(j))){
                    appList.getAppList().remove(i);
                }
            }
        }


        tempWidgetButton11 = findViewById(R.id.TempWidgetButton11);
        tempWidgetButton12 = findViewById(R.id.TempWidgetButton12);
        tempWidgetButton13 = findViewById(R.id.TempWidgetButton13);
        tempWidgetButton14 = findViewById(R.id.TempWidgetButton14);
        tempWidgetButton21 = findViewById(R.id.TempWidgetButton21);
        tempWidgetButton22 = findViewById(R.id.TempWidgetButton22);
        tempWidgetButton23 = findViewById(R.id.TempWidgetButton23);
        tempWidgetButton24 = findViewById(R.id.TempWidgetButton24);
        tempWidgetButton31 = findViewById(R.id.TempWidgetButton31);
        tempWidgetButton32 = findViewById(R.id.TempWidgetButton32);
        tempWidgetButton33 = findViewById(R.id.TempWidgetButton33);
        tempWidgetButton34 = findViewById(R.id.TempWidgetButton34);
        tempWidgetButton41 = findViewById(R.id.TempWidgetButton41);
        tempWidgetButton42 = findViewById(R.id.TempWidgetButton42);
        tempWidgetButton43 = findViewById(R.id.TempWidgetButton43);
        tempWidgetButton44 = findViewById(R.id.TempWidgetButton44);
        tempWidgetButton51 = findViewById(R.id.TempWidgetButton51);
        tempWidgetButton52 = findViewById(R.id.TempWidgetButton52);
        tempWidgetButton53 = findViewById(R.id.TempWidgetButton53);
        tempWidgetButton54 = findViewById(R.id.TempWidgetButton54);
        tempWidgetButton61 = findViewById(R.id.TempWidgetButton61);
        tempWidgetButton62 = findViewById(R.id.TempWidgetButton62);
        tempWidgetButton63 = findViewById(R.id.TempWidgetButton63);
        tempWidgetButton64 = findViewById(R.id.TempWidgetButton64);
        done = findViewById(R.id.done);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> seqPriority = sortingPriority(userInfo); // 우선 순위에 따른 칸의 인덱스 값 저장

        switch (userInfo.getSize()){
            case 22:
                tempWidgetButton62.setVisibility(View.GONE);
                tempWidgetButton61.setVisibility(View.GONE);
                tempWidgetButton52.setVisibility(View.GONE);
                tempWidgetButton51.setVisibility(View.GONE);
            case 42:
                tempWidgetButton44.setVisibility(View.GONE);
                tempWidgetButton43.setVisibility(View.GONE);
                tempWidgetButton42.setVisibility(View.GONE);
                tempWidgetButton41.setVisibility(View.GONE);
                tempWidgetButton34.setVisibility(View.GONE);
                tempWidgetButton33.setVisibility(View.GONE);
                tempWidgetButton32.setVisibility(View.GONE);
                tempWidgetButton31.setVisibility(View.GONE);
            case 44:
                tempWidgetButton24.setVisibility(View.GONE);
                tempWidgetButton23.setVisibility(View.GONE);
                tempWidgetButton22.setVisibility(View.GONE);
                tempWidgetButton21.setVisibility(View.GONE);
                tempWidgetButton14.setVisibility(View.GONE);
                tempWidgetButton13.setVisibility(View.GONE);
                tempWidgetButton12.setVisibility(View.GONE);
                tempWidgetButton11.setVisibility(View.GONE);
            default:
                break;
        }

        final AppList finalAppList = appList;
        try{
            for(int i = 0; i < appList.getAppList().size(); i++){
                final int temp = i;
                switch(Integer.parseInt(seqPriority.get(i))){
                    case 64:
                        tempWidgetButton64.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton64.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 63:
                        tempWidgetButton63.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton63.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 62:
                        tempWidgetButton62.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton62.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 61:
                        tempWidgetButton61.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton61.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 54:
                        tempWidgetButton54.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton54.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 53:
                        tempWidgetButton53.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton53.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 52:
                        tempWidgetButton52.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton52.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 51:
                        tempWidgetButton51.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton51.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 44:
                        tempWidgetButton44.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton44.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 43:
                        tempWidgetButton43.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton43.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 42:
                        tempWidgetButton42.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton42.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 41:
                        tempWidgetButton41.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton41.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 34:
                        tempWidgetButton34.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton34.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 33:
                        tempWidgetButton33.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton33.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 32:
                        tempWidgetButton32.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton32.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 31:
                        tempWidgetButton31.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton31.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 24:
                        tempWidgetButton24.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton24.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 23:
                        tempWidgetButton23.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton23.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 22:
                        tempWidgetButton22.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton22.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 21:
                        tempWidgetButton21.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton21.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 14:
                        tempWidgetButton14.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton14.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 13:
                        tempWidgetButton13.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton13.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 12:
                        tempWidgetButton12.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton12.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    case 11:
                        tempWidgetButton11.setImageBitmap(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton11.setOnClickListener(v -> {
                            Intent intent = finalAppList.getAppList().get(temp).getShortcut();
                            startActivity(intent);
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        done.setOnClickListener(v -> finish());
    }

    List<String> sortingPriority(UserInfo userInfo){
        List<String> priority1 = new ArrayList<>();
        List<String> priority2 = new ArrayList<>();
        List<String> priority3 = new ArrayList<>();
        List<String> priority4 = new ArrayList<>();
        for(int i = 6; i > 0; i--){
            for(int j = 4; j > 0; j--){
                String number = i + "" + j;
                switch(userInfo.getPriority(i, j)){
                    case 1:
                        priority1.add(number);
                        break;
                    case 2:
                        priority2.add(number);
                        break;
                    case 3:
                        priority3.add(number);
                        break;
                    default:
                        priority4.add(number);
                        break;
                }
            }
        }
        List<String> priority = new ArrayList<>();
        priority.addAll(priority1);
        priority.addAll(priority2);
        priority.addAll(priority3);
        priority.addAll(priority4);

        return priority;
    }
}