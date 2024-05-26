package com.softgenie.sortingwidget;

import static com.softgenie.sortingwidget.AppList.drawableToBitmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Exclude extends AppCompatActivity {
    private static final String TAG = "Exclude";
    private ListView appListView;
    private AppDataAdapter adapter;
    Button back4, next4;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclude);
        back4 = findViewById(R.id.back4);
        appListView = findViewById(R.id.appListView);

        Intent intent = new Intent(Exclude.this, TempWidget.class);
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
        Button doneButton = findViewById(R.id.next4);
        doneButton.setOnClickListener(v -> {
            List<AppData> selectedApps = getSelectedApps();
            List<String> selectedAppNames = new ArrayList<>();
            for (AppData app : selectedApps) {
                selectedAppNames.add(app.getAppName());
            }

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

        next4.setOnClickListener(v -> {
            intent.setClass(Exclude.this, TempWidget.class); // 이미 정의된 intent 변수를 재사용
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
