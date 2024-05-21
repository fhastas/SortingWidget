package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Include extends AppCompatActivity {
    private static final String TAG = "Include";
    private ListView appListView;
    private AppDataAdapter adapter;
    Button back3, next3;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        back3 = findViewById(R.id.back3);
        appListView = findViewById(R.id.appListView);

        Intent intent = new Intent(Include.this, Exclude.class);

        UserInfo userInfo = SharedPreferencesHelper.loadUserInfo(this);

        AppList appList = SharedPreferencesHelper.loadAppList(this);
        if (appList == null) {
            appList = new AppList(getApplicationContext());
            SharedPreferencesHelper.saveAppList(this, appList);
        }

        // 리스트뷰에 앱 목록 표시
        adapter = new AppDataAdapter(this, appList.getAppList());
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Next 버튼 클릭 시 선택된 앱 목록 가져오기
        next3 = findViewById(R.id.next3);
        next3.setOnClickListener(v -> {
            List<AppData> selectedApps = getSelectedApps();
            List<String> selectedAppNames = new ArrayList<>();
            for (AppData app : selectedApps) {
                selectedAppNames.add(app.getAppName());
            }

            assert userInfo != null;
            userInfo.setInclude(selectedAppNames);
            SharedPreferencesHelper.saveUserInfo(this, userInfo);

            Log.d(TAG, "[UserInfo]\n" + userInfo);

            intent.setClass(Include.this, Exclude.class); // 이미 정의된 intent 변수를 재사용
            startActivity(intent);
            finish();
        });

        back3.setOnClickListener(v -> {
            intent.setClass(Include.this, Prioritize.class);
            startActivity(intent);
            finish();
        });
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