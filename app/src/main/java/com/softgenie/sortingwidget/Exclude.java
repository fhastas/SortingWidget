package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exclude extends AppCompatActivity {
    private ListView appListView;
    private AppDataAdapter adapter;
    Button back4;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclude);
        back4 = findViewById(R.id.back4);
        appListView = findViewById(R.id.appListView);
        Intent intent = new Intent(this, Show.class);


        // 앱 목록 가져오기
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> installedApps = getPackageManager().getInstalledApplications(0);
        List<String> appNames = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            appNames.add(getAppName(appInfo));
        }

        // 앱 데이터 리스트 생성
        List<AppData> appDataList = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue; // 시스템 앱은 제외
            }
            String appName = getAppName(appInfo);
            Drawable appIconDrawable = appInfo.loadIcon(getPackageManager());
            Bitmap appIconBitmap = null;
            if(appIconDrawable instanceof BitmapDrawable bitmapDrawable){
                appIconBitmap = bitmapDrawable.getBitmap();
            }
            // 앱 데이터 객체 생성 후 리스트에 추가
            AppData appData = new AppData(appName, appIconBitmap, 0, null);
            appDataList.add(appData);
        }

        // 리스트뷰에 앱 목록 표시
        AppDataAdapter adapter = new AppDataAdapter(this, appDataList);
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Next 버튼 클릭 시 선택된 앱 목록 가져오기
        Button doneButton = findViewById(R.id.next4);
        doneButton.setOnClickListener(v -> {
            List<AppData> selectedApps = getSelectedApps();
            List<String> selectedAppNames = selectedApps.stream().map(AppData::getAppName).toList();
            intent.putExtra("excluded", selectedAppNames.toArray());// 선택된 앱 목록을 Intent에 추가
            intent.setClass(Exclude.this, Show.class);
            startActivity(intent);
            finish();
        });

        back4.setOnClickListener(v -> {
            intent.setClass(Exclude.this, Include.class); // 이미 정의된 intent 변수를 재사용
            startActivity(intent);
            finish();
        });

    }

    // ApplicationInfo를 통해 앱 이름 가져오기
    private String getAppName(ApplicationInfo appInfo) {
        return getPackageManager().getApplicationLabel(appInfo).toString();
    }

    // ListView에서 선택된 앱 가져오기
    private List<AppData> getSelectedApps() {
        List<AppData> selectedApps = new ArrayList<>();
        for (int i = 0; i < appListView.getCount(); i++) {
            if (appListView.isItemChecked(i)) {
                selectedApps.add(adapter.getItem(i)); // adapter.getItem(i)로 선택된 앱 데이터 가져옴
            }
        }
        return selectedApps;
    }
}
