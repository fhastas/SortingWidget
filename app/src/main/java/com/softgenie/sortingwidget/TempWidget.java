package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "TempWidget";

    ImageButton tempWidgetButton11, tempWidgetButton12, tempWidgetButton13, tempWidgetButton14
            , tempWidgetButton21, tempWidgetButton22, tempWidgetButton23, tempWidgetButton24
            , tempWidgetButton31, tempWidgetButton32, tempWidgetButton33, tempWidgetButton34
            , tempWidgetButton41, tempWidgetButton42, tempWidgetButton43, tempWidgetButton44
            , tempWidgetButton51, tempWidgetButton52, tempWidgetButton53, tempWidgetButton54
            , tempWidgetButton61, tempWidgetButton62, tempWidgetButton63, tempWidgetButton64;

    Button done, edit;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temp_widget);
        List<ApplicationInfo> installedApps = getInstalledApps();

        AppList appList = SharedPreferencesHelper.loadAppList(this);
        if (appList == null) {
            Log.d(TAG, "AppList is null");
            appList = new AppList(this);
            SharedPreferencesHelper.saveAppList(this, appList);
        }

        UserInfo userInfo = SharedPreferencesHelper.loadUserInfo(this);
        if (userInfo == null) {
            Log.d(TAG, "UserInfo is null");
            userInfo = new UserInfo();
            SharedPreferencesHelper.saveUserInfo(this, userInfo);
        }

        Log.d(TAG, "[TempWidget]\nAppList: " + appList + "\n" + userInfo);

        if(!userInfo.getInclude().isEmpty() || !userInfo.getExclude().isEmpty()) {
            List<AppData> toRemove = new ArrayList<>();
            for (int i = 0; i < appList.getAppList().size(); i++) {
                for (int j = 0; j < userInfo.getInclude().size(); j++) {
                    if (appList.getAppList().get(i).getAppName().equals(userInfo.getInclude().get(j))) {
                        appList.getAppList().get(i).setSelected(true);
                    }
                }
                // AppData에 제외할 값 저장
                for (int j = 0; j < userInfo.getExclude().size(); j++) {
                    if (appList.getAppList().get(i).getAppName().equals(userInfo.getExclude().get(j))) {
                        toRemove.add(appList.getAppList().get(i));
                        break;
                    }
                }
            }

            for (AppData appData : toRemove) {
                appList.getAppList().remove(appData);
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

        edit.setOnClickListener(v -> {
            Intent intent = new Intent(this, Size.class);
            finish();
        });

        final AppList finalAppList = appList;
        try{
            for(int i = 0; i < appList.getAppList().size(); i++){
                final int temp = i;
                switch(Integer.parseInt(seqPriority.get(i))){
                    case 53:
                        tempWidgetButton64.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton64.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 52:
                        tempWidgetButton63.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton63.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 51:
                        tempWidgetButton62.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton62.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 50:
                        tempWidgetButton61.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton61.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 43:
                        tempWidgetButton54.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton54.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 42:
                        tempWidgetButton53.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton53.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 41:
                        tempWidgetButton52.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton52.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 40:
                        tempWidgetButton51.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton51.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 33:
                        tempWidgetButton44.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton44.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 32:
                        tempWidgetButton43.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton43.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 31:
                        tempWidgetButton42.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton42.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 30:
                        tempWidgetButton41.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton41.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 23:
                        tempWidgetButton34.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton34.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 22:
                        tempWidgetButton33.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton33.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 21:
                        tempWidgetButton32.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton32.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 20:
                        tempWidgetButton31.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton31.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 13:
                        tempWidgetButton24.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton24.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 12:
                        tempWidgetButton23.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton23.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 11:
                        tempWidgetButton22.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton22.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 10:
                        tempWidgetButton21.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton21.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 3:
                        tempWidgetButton14.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton14.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 2:
                        tempWidgetButton13.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton13.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 1:
                        tempWidgetButton12.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton12.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 0:
                        tempWidgetButton11.setImageDrawable(appList.getAppList().get(i).getAppIcon(getApplicationContext()));
                        tempWidgetButton11.setOnClickListener(v -> {
                            Intent intent = new Intent();
                            intent.setClassName(finalAppList.getAppList().get(temp).getPackageName(), finalAppList.getAppList().get(temp).getClassName());
                            startActivity(intent);
                            finish();
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

        String stringSize = Integer.toString(userInfo.getSize());
        int maxSize = Character.getNumericValue(stringSize.charAt(0)) * Character.getNumericValue(stringSize.charAt(1));
        for(int i = 5; i >= 0; i--){
            for(int j = 3; j >= 0; j--){
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

        if(priority.size() > maxSize){
            while (priority.size() - maxSize > 0) {
                priority.remove(priority.size() - 1);
            }
        }

        return priority;
    }
    private List<ApplicationInfo> getInstalledApps() {
        PackageManager packageManager = getPackageManager();
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }
}