package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Include extends AppCompatActivity {
    private ListView appListView;
    private List<String> selectedApps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);

        appListView = findViewById(R.id.appListView);

        // 앱 목록 가져오기
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> installedApps = getPackageManager().getInstalledApplications(0);
        List<String> appNames = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue; // 시스템 앱은 제외
            }
            appNames.add(getAppName(appInfo));
        }

        // 리스트뷰에 앱 목록 표시
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, appNames);
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Next 버튼 클릭 시 선택된 앱 목록 가져오기
        Button nextButton = findViewById(R.id.next3);
        nextButton.setOnClickListener(v -> {
            selectedApps.clear(); // 기존에 선택한 앱 목록 초기화
            selectedApps = getSelectedApps(installedApps); // 새로 선택한 앱 목록 가져오기
            // 선택한 앱 목록을 Exclude 액티비티로 전달
            Intent intent = new Intent(Include.this, Exclude.class);
            intent.putStringArrayListExtra("selectedApps", (ArrayList<String>) selectedApps);
            startActivity(intent);
            finish();
        });

    }

    // ApplicationInfo를 통해 앱 이름 가져오기
    private String getAppName(ApplicationInfo appInfo) {
        return getPackageManager().getApplicationLabel(appInfo).toString();
    }

    // ListView에서 선택된 앱 가져오기
    private List<String> getSelectedApps(List<ApplicationInfo> installedApps) {
        List<String> selectedApps = new ArrayList<>();
        for (int i = 0; i < appListView.getCount(); i++) {
            if (appListView.isItemChecked(i)) {
                selectedApps.add(installedApps.get(i).packageName); // 앱 패키지 이름 가져오기
            }
        }
        return selectedApps;
    }
}