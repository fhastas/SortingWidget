package com.softgenie.sortingwidget;

import static com.softgenie.sortingwidget.AppList.drawableToBitmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Exclude extends AppCompatActivity {
    private static final String TAG = "Exclude";
    private ListView appListView;
    private AppDataAdapter adapter;
    Button back4;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclude);
        back4 = findViewById(R.id.back4);
        appListView = findViewById(R.id.appListView);

        Intent intent = new Intent(Exclude.this, TempWidget.class);
        UserInfo userInfo = SharedPreferencesHelper.loadUserInfo(this);

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
            Bitmap bitmapIcon = drawableToBitmap(appIconDrawable);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmapIcon.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteIcon = outputStream.toByteArray();
            // 앱 데이터 객체 생성 후 리스트에 추가
            AppData appData = new AppData(appName, byteIcon, 0, appInfo.packageName, appInfo.className);
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

            assert userInfo != null;
            userInfo.setExclude(selectedAppNames);
            SharedPreferencesHelper.saveUserInfo(this, userInfo);

            Log.d(TAG, "[UserInfo]\n" + userInfo);

            intent.setClass(Exclude.this, TempWidget.class);
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
