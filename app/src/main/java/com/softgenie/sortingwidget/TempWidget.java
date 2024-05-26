package com.softgenie.sortingwidget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempWidget extends AppCompatActivity {
    private static final String TAG = "TempWidget";

    ImageButton tempWidgetButton11, tempWidgetButton12, tempWidgetButton13, tempWidgetButton14
            , tempWidgetButton21, tempWidgetButton22, tempWidgetButton23, tempWidgetButton24
            , tempWidgetButton31, tempWidgetButton32, tempWidgetButton33, tempWidgetButton34
            , tempWidgetButton41, tempWidgetButton42, tempWidgetButton43, tempWidgetButton44
            , tempWidgetButton51, tempWidgetButton52, tempWidgetButton53, tempWidgetButton54
            , tempWidgetButton61, tempWidgetButton62, tempWidgetButton63, tempWidgetButton64;

    Button done, setting;

    AppList appList;
    UserInfo userInfo;

    @SuppressLint({"UseCompatLoadingForDrawables", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temp_widget);

        if(!checkAccessibilityPermissions()) {
            setAccessibilityPermissions();
        }

        if(checkUsageAccessPermission()) {
            requestUsageAccessPermission();
        }

        startService();

        ArrayList<Parcelable> Apps = getIntent().getParcelableArrayListExtra("selectedApps");

        if (Apps == null) {
            Log.d(TAG, "No selected apps received from intent");
        }

        appList = SharedPreferencesHelper.loadAppList(this);
        if (appList == null) {
            Log.d(TAG, "AppList is null");
            appList = new AppList(this);
            SharedPreferencesHelper.saveAppList(this, appList);
        }
        userInfo = SharedPreferencesHelper.loadUserInfo(this);
        if (userInfo == null) {
            Log.d(TAG, "UserInfo is null");
            userInfo = new UserInfo();
            SharedPreferencesHelper.saveUserInfo(this, userInfo);
        }

        Log.d(TAG, "[TempWidget]\nAppList: " + appList + "\n" + userInfo);

        if(userInfo.getInclude().isEmpty()) {
            for (int i = 0; i < appList.getAppList().size(); i++) {
                for (int j = 0; j < userInfo.getInclude().size(); j++) {
                    if (appList.getAppList().get(i).getAppName().equals(userInfo.getInclude().get(j))) {
                        appList.getAppList().get(i).setSelected(true);
                    }
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
        setting = findViewById(R.id.setting);
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

        setting.setOnClickListener(v -> {
            Intent intent = new Intent(this, Size.class);
            startActivity(intent);
        });

        final AppList finalAppList = appList;
        try{
            for(int i = 0; i < appList.getAppList().size(); i++){
                final int temp = i;
                switch(Integer.parseInt(seqPriority.get(i))){
                    case 53:
                        tempWidgetButton64.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton64.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 52:
                        tempWidgetButton63.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton63.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 51:
                        tempWidgetButton62.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton62.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 50:
                        tempWidgetButton61.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton61.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 43:
                        tempWidgetButton54.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton54.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 42:
                        tempWidgetButton53.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton53.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 41:
                        tempWidgetButton52.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton52.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 40:
                        tempWidgetButton51.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton51.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 33:
                        tempWidgetButton44.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton44.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 32:
                        tempWidgetButton43.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton43.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 31:
                        tempWidgetButton42.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton42.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 30:
                        tempWidgetButton41.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton41.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 23:
                        tempWidgetButton34.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton34.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 22:
                        tempWidgetButton33.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton33.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 21:
                        tempWidgetButton32.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton32.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 20:
                        tempWidgetButton31.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton31.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 13:
                        tempWidgetButton24.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton24.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 12:
                        tempWidgetButton23.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton23.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 11:
                        tempWidgetButton22.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton22.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 10:
                        tempWidgetButton21.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton21.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 3:
                        tempWidgetButton14.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton14.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 2:
                        tempWidgetButton13.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton13.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 1:
                        tempWidgetButton12.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton12.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
                            startActivity(intent);
                            finish();
                        });
                        break;
                    case 0:
                        tempWidgetButton11.setImageDrawable(appList.getAppList().get(i).getAppIcon());
                        tempWidgetButton11.setOnClickListener(v -> {
                            Intent intent = getPackageManager().getLaunchIntentForPackage(finalAppList.getAppList().get(temp).getPackageName());
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
    public void startService(){
        Intent intent = new Intent(this, AppInfoTrackerService.class);
        startService(intent);
    }

    // 접근성 권한이 있는지 없는지 확인하는 부분
    // 있으면 true, 없으면 false
    public boolean checkAccessibilityPermissions() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        // getEnabledAccessibilityServiceList는 현재 접근성 권한을 가진 리스트를 가져오게 된다
        List<AccessibilityServiceInfo> list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT);

        for (int i = 0; i < list.size(); i++) {
            AccessibilityServiceInfo info = list.get(i);

            // 접근성 권한을 가진 앱의 패키지 네임과 패키지 네임이 같으면 현재앱이 접근성 권한을 가지고 있다고 판단함
            if (info.getResolveInfo().serviceInfo.packageName.equals(getApplication().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    // 접근성 설정화면으로 넘겨주는 부분
    public void setAccessibilityPermissions() {
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
        gsDialog.setTitle("접근성 권한 설정");
        gsDialog.setMessage("접근성 권한을 필요로 합니다");
        gsDialog.setPositiveButton("확인", (dialog, which) -> {
            // 설정화면으로 보내는 부분
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }).create().show();
    }

    private boolean checkUsageAccessPermission() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode;
        mode = appOps.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private void requestUsageAccessPermission() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }
}