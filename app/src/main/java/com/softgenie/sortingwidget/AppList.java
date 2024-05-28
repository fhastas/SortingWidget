package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Calendar;
import java.util.Set;
//사용자의 설치된 앱 목록, 사용 시간 관리
public class AppList implements Serializable {
    private final List<AppData> appList = new ArrayList<>();
    private final Set<String> addedPackages = new HashSet<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public AppList(Context context) {
        PackageManager pm = context.getPackageManager();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        // 권한 확인
        if (usageStatsManager == null) {
            throw new IllegalStateException("UsageStatsManager not available");
        }

        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            try {
                if (addedPackages.contains(packageName)) {
                    continue;
                }

                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    String appName = pm.getApplicationLabel(appInfo).toString();
                    Drawable appIcon = pm.getApplicationIcon(appInfo);

                    long installationTime = pm.getPackageInfo(packageName, 0).firstInstallTime;
                    long usageTime = usageStats.getTotalTimeInForeground();

                    appList.add(new AppData(appName, appIcon, installationTime, usageTime, packageName));
                    addedPackages.add(packageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                // 사용자에게 피드백을 제공하거나 로그를 기록하는 것이 좋습니다.
                Log.e("AppList", "Application not found for package: " + packageName, e);
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
