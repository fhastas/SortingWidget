package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class AppList implements Serializable {
    private final List<AppData> appList = new ArrayList<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public AppList(Context context) {
        PackageManager pm = context.getPackageManager();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            try {
                // 중복 확인 로직 추가
                boolean isAlreadyAdded = false;
                for (AppData appData : appList) {
                    if (appData.getPackageName().equals(packageName)) {
                        isAlreadyAdded = true;
                        break;
                    }
                }
                if (isAlreadyAdded) {
                    continue; // 이미 리스트에 추가된 앱이면 다음으로 넘어감
                }
                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 시스템 앱이 아닌 경우만 추가
                    String appName = pm.getApplicationLabel(appInfo).toString();
                    Drawable appIcon = pm.getApplicationIcon(appInfo);

                    long installationTime = pm.getPackageInfo(packageName, 0).firstInstallTime;
                    long usageTime = usageStats.getTotalTimeInForeground();

                    appList.add(new AppData(appName, appIcon, installationTime, usageTime, packageName));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder appsInfo = new StringBuilder();
        for (AppData app : appList) {
            appsInfo.append("App name: ").append(app.getAppName()).append("\n");
        }
        return appsInfo.toString();
    }

    public List<AppData> getAppList() {
        return appList;
    }
}
