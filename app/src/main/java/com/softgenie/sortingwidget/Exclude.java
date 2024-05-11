package com.softgenie.sortingwidget;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Exclude extends AppCompatActivity {
    private ListView appListView;
    private AppDataAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclude);

        appListView = findViewById(R.id.appListView);

        // 앱 목록 가져오기
        List<ApplicationInfo> installedApps = getPackageManager().getInstalledApplications(0);
        List<String> appNames = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            appNames.add(getAppName(appInfo));
        }

        // 앱 데이터 리스트 생성
        List<AppData> appDataList = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            String appName = getAppName(appInfo);
            // 앱 데이터 객체 생성 후 리스트에 추가
            AppData appData = new AppData(appName, appInfo.loadIcon(getPackageManager()), 0, null);
            appDataList.add(appData);
        }

        // 리스트뷰에 앱 목록 표시
        AppDataAdapter adapter = new AppDataAdapter(this, appDataList);
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Done 버튼 클릭 시 선택된 앱 목록 가져오기
        Button doneButton = findViewById(R.id.next4);
        doneButton.setOnClickListener(v -> {
            List<AppData> selectedApps = getSelectedApps();
            Toast.makeText(Exclude.this, "선택한 앱: " + selectedApps, Toast.LENGTH_SHORT).show();
            // 여기서 선택한 앱 데이터를 다른 액티비티로 전달할 수 있습니다.
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
