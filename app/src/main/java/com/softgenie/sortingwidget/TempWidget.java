package com.softgenie.sortingwidget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TempWidget extends AppCompatActivity {
    private static final String TAG = "TempWidget";

    public static final int DEFAULT_SIZE_WIDTH = 4; // 기본 최대 너비
    public static final int DEFAULT_SIZE_HEIGHT = 6; // 기본 최대 높이
    public static final int DEFAULT_SIZE_MAX = DEFAULT_SIZE_WIDTH * DEFAULT_SIZE_HEIGHT;

    ImageView[] tempWidgetButtons;
    int sizeMaxW, sizeMaxH; //size의 논리적 크기

    Button done, setting;

    AppList appList;
    UserInfo userInfo;

    @SuppressLint({"UseCompatLoadingForDrawables", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temp_widget);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 접근 권한 확인 및 요청
        if(!checkAccessibilityPermissions()) {
            requestAccessibilityPermissions();
        }
        if(!checkUsageAccessPermission()) {
            requestUsageAccessPermission();
        }

        // AppList 생성 서비스 실행
        AppInfoTrackerService();

        appList = SharedPreferencesHelper.loadAppList(this);

        if (appList == null) {
            Log.e(TAG, "AppList is null");
            appList = new AppList(this);
            SharedPreferencesHelper.saveAppList(this, appList);
        }
        for(int i = 0; i < appList.getAppList().size(); i++){
            if(appList.getAppList().get(i).getAppIcon() == null){
                Log.e(TAG, "AppList Icon is null");
                appList = new AppList(this);
                SharedPreferencesHelper.saveAppList(this, appList);
                break;
            }
        }

        userInfo = SharedPreferencesHelper.loadUserInfo(this);
        if (userInfo == null) {
            Log.e(TAG, "UserInfo is null");
            userInfo = new UserInfo();
            SharedPreferencesHelper.saveUserInfo(this, userInfo);
        }

        Log.d(TAG, "AppList{\n " + appList.toString() + "}\n" + userInfo.toString());

        tempWidgetButtons = new ImageView[DEFAULT_SIZE_MAX];

        setupImageViews(tempWidgetButtons);
        done = findViewById(R.id.done);
        setting = findViewById(R.id.setting);

        // size 갱신
        if(updateGridSize(userInfo.getSize() / 10, userInfo.getSize() % 10)) {
            Log.d(TAG, "Successful size update");
        } else{
            Log.e(TAG, "Not Change Size ");
        }

        List<AppData> sortedAppList = toSortAppList();
        List<Integer> sortedPriority = toSortPriority();
        Log.d(TAG, sortedAppList + "\n" + sortedPriority.toString());

        // ImageView[]의 이미지와 클릭 반응 설정
        settingImageViews(sortedAppList, sortedPriority);

        setting.setOnClickListener(v -> {
            Intent intent = new Intent(this, Size.class);
            startActivity(intent);
            finish();
        });

        done.setOnClickListener(v -> finish());


    }

    // 0~23 REAL INDEX 값
    public ImageView getButtonFromIndex(int realIndex) {
        if (realIndex < 0 || realIndex >= tempWidgetButtons.length) {
            Log.e("getButtonFromIndex ERROR","index outbound error");
            return null;
        }

        return tempWidgetButtons[realIndex];
    }

    // 11, 12 etc... 값
    public ImageView getButtonFromPos(int MNPos_1based) {
        return getButtonFromIndex(PosToRealIndex((MNPos_1based)));
    }

    // 0-based
    public ImageView getButtonFromIndex(int y, int x) {
        return getButtonFromIndex(PosToRealIndex(y, x));
    }

    // 1-based, 11, 12, etc...
    public static int PosToRealIndex(int MNPos_1based) {
        final int temp = MNPos_1based - 11;
        final int y = temp / 10;
        final int x = temp % 10;

        return PosToRealIndex(y, x);
    }

    // 0-based
    public static int PosToRealIndex(int y, int x) {
        if ((x | y) < 0 || y >= DEFAULT_SIZE_HEIGHT || x >= DEFAULT_SIZE_WIDTH)
            return -1;

        return y * DEFAULT_SIZE_WIDTH + x;
    }

    // 반환: 변경이 됐을 시 true, 안됐을 시 false
    public boolean updateGridSize(int newSize_height, int newSize_width) {
        if (newSize_height == sizeMaxH && newSize_width == sizeMaxW)
            return false;

        if (((newSize_height - 1) | (newSize_width - 1)) < 0 ||
              newSize_height > DEFAULT_SIZE_HEIGHT ||
              newSize_width > DEFAULT_SIZE_WIDTH)
        {
            Log.e("updateGridSize ERROR", "size outbound error");
            return false;
        }

        sizeMaxH = newSize_height;
        sizeMaxW = newSize_width;

        // all off
        ImageView[] wButtons = tempWidgetButtons;
        for (ImageView button : wButtons)
            button.setVisibility(View.INVISIBLE);

        for (int dy = DEFAULT_SIZE_HEIGHT; dy > DEFAULT_SIZE_HEIGHT - newSize_height; dy--)
            for (int dx = DEFAULT_SIZE_WIDTH; dx > DEFAULT_SIZE_WIDTH - newSize_width; dx--)
                wButtons[PosToRealIndex(dy - 1, dx - 1)].setVisibility(View.VISIBLE);
        return true;
    }

    // 초기 버튼 정보를 가져와서 배열로 담기 위한 도우미
    // 최초 초기화 이후에 다시 호출하지 말 것.
    private void setupImageViews(ImageView[] buttons) {
        final String pattern = "TempWidgetButton";

        Field[] fields = R.id.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                String name = field.getName();
                if (name.startsWith(pattern)) {
                    int resId = field.getInt(null);
                    ImageView button = findViewById(resId);

                    if (button == null) continue;

                    int pos = Integer.parseInt(name.substring(pattern.length()));

                    buttons[PosToRealIndex(pos)] = button;
                    button.setVisibility(View.INVISIBLE);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void AppInfoTrackerService(){
        Intent intent = new Intent(this, AppInfoTrackerService.class);
        startService(intent);
    }

    public boolean checkAccessibilityPermissions(){ // 접근성 권한 확인
        AccessibilityManager am = (AccessibilityManager)getSystemService(Context.ACCESSIBILITY_SERVICE);

        List<AccessibilityServiceInfo> list = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT);

        for(AccessibilityServiceInfo info : list){
            if(info.getResolveInfo().serviceInfo.packageName.equals(getApplication().getPackageName()))
                return true;
        }
        return false;
    }
    public boolean checkUsageAccessPermission(){ // 앱 정보 접근 권한 확인
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode;
        mode = appOps.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private void requestAccessibilityPermissions(){ // 접근성 설정 화면으로 넘기기
        AlertDialog.Builder accessDialog = new AlertDialog.Builder(this);
        accessDialog.setTitle("권한 설정");
        accessDialog.setMessage("접근성 권한을 필요로 합니다");
        accessDialog.setPositiveButton("확인", ((dialog, which) -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)))).create().show();
    }

    private void requestUsageAccessPermission(){ // 앱 정보 접근 설정 화면으로 넘기기
        AlertDialog.Builder accessDialog = new AlertDialog.Builder(this);
        accessDialog.setTitle("권한 설정");
        accessDialog.setMessage("앱 정보 접근 권한을 필요로 합니다");
        accessDialog.setPositiveButton("확인", ((dialog, which) -> startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)))).create().show();
    }
    List<Integer> toSortPriority(){
        List<Integer> priority1 = new ArrayList<>();
        List<Integer> priority2 = new ArrayList<>();
        List<Integer> priority3 = new ArrayList<>();
        List<Integer> priority4 = new ArrayList<>();

        for(int i = 5; i >= 0; i--){
            for(int j = 3; j >= 0; j--){
                int number = PosToRealIndex(i, j);
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
        List<Integer> priority = new ArrayList<>();
        priority.addAll(priority1);
        priority.addAll(priority2);
        priority.addAll(priority3);
        priority.addAll(priority4);

        int maxSize = sizeMaxH * sizeMaxW;

        if (priority.size() > maxSize) {
            if(maxSize == 4){
                priority.removeIf(index -> index == 20 || index == 21);
            }
            priority = priority.subList(0, maxSize);
        }

        return priority;
    }
    private List<AppData> toSortAppList() {
        List<AppData> selectedApps = new ArrayList<>();
        List<AppData> notSelectedApps = new ArrayList<>();

        int maxSize = sizeMaxH * sizeMaxW;

        // Include에서 선택된 앱들 appList에 반영
        ArrayList<String> includeApps = SharedPreferencesHelper.loadIncludeInfo(this);
        if(includeApps == null){
            includeApps = new ArrayList<>();
        }

        if (!includeApps.isEmpty()) {
            for (int i = 0; i < appList.getAppList().size(); i++) {
                for (int j = 0; j < includeApps.size(); j++) {
                    if (appList.getAppList().get(i).getAppName().equals(includeApps.get(j))) {
                        appList.getAppList().get(i).setSelected(true);
                    }
                }
            }

            // 포함되어야 하는 앱과 아닌 앱 구분
            for (AppData app : appList.getAppList()) {
                if (selectedApps.size() >= maxSize) break;

                if (app.getSelected()) {
                    selectedApps.add(app);
                } else {
                    notSelectedApps.add(app);
                }
            }
            if (selectedApps.size() > maxSize) {
                // 선택된 앱들이 24를 넘을 경우 선택된 앱들을 정렬 후 반환
                selectedApps.sort(Comparator.comparingLong(AppData::getUsageTime));
                if(selectedApps.size() > maxSize)
                    selectedApps.subList(0, maxSize);

                return selectedApps;
            } else if (notSelectedApps.size() > maxSize - selectedApps.size()) {
                // 선택된 앱들이 24개를 넘지 않을 경우 선택되지 않은 앱들도 포함하여 정렬 후 반환
                notSelectedApps.sort(Comparator.comparingLong(AppData::getUsageTime));
                if(notSelectedApps.size() > maxSize - selectedApps.size())
                    notSelectedApps.subList(0, maxSize - selectedApps.size());

                List<AppData> sortedAppList = new ArrayList<>();
                sortedAppList.addAll(selectedApps);
                sortedAppList.addAll(notSelectedApps);

                sortedAppList.sort(Comparator.comparingLong(AppData::getUsageTime));
                return sortedAppList;
            }
        }
        // 선택된 앱들이 아무 것도 없을 경우 선택되지 않은 앱들을 정렬 후 반환
        Log.d("toSortedAppList", "Include is null");
        notSelectedApps.addAll(appList.getAppList());
        notSelectedApps.sort(Comparator.comparingLong(AppData::getUsageTime));
        if(notSelectedApps.size() > maxSize)
            notSelectedApps.subList(0, maxSize);
        return notSelectedApps;
    }

    private void settingImageViews(List<AppData> sortedAppList, List<Integer> sortedPriority){
        try{
            for(int i = 0; i < sortedPriority.size(); i++){
                int index = sortedPriority.get(i);
                Log.d("TEST", String.valueOf(index));
                if(sortedAppList.get(i).getAppIcon() != null) {
                    tempWidgetButtons[index].setImageBitmap(sortedAppList.get(i).getAppIcon());
                } else{
                    Log.d("settingImageButtons_debug", "appIcon is null");
                    tempWidgetButtons[index].setImageResource(R.drawable.ic_launcher_foreground);
                }

                int finalI = i;
                tempWidgetButtons[index].setOnClickListener(v -> {
                    Intent intent = getPackageManager().getLaunchIntentForPackage(sortedAppList.get(finalI).getPackageName());
                    startActivity(intent);
                    finish();
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}