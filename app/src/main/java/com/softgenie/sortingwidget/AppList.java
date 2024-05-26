package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppList implements Serializable {
    private final List<AppData> appList = new ArrayList<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public AppList(Context context) {
        PackageManager pm = context.getPackageManager();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        loadInstalledApps(context);

        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 시스템 앱이 아닌 경우만 추가
                    String appName = pm.getApplicationLabel(appInfo).toString();
                    Drawable appIcon = pm.getApplicationIcon(appInfo);

                    long installationTime = pm.getPackageInfo(packageName, 0).firstInstallTime;
                    long usageTime = usageStats.getTotalTimeInForeground();
                    String className = appInfo.className;

                    appList.add(new AppData(appName, appIcon, installationTime, usageTime, packageName, className));
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

    private void loadInstalledApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : installedApps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 시스템 앱이 아닌 경우만 추가
                String appName = appInfo.loadLabel(packageManager).toString();
                Drawable appIcon = appInfo.loadIcon(packageManager);
                long installationTime = 0;
                try {
                    installationTime = packageManager.getPackageInfo(appInfo.packageName, 0).firstInstallTime;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                long usageTime = 0; // 사용 시간은 여기서는 설정하지 않음
                appList.add(new AppData(appName, appIcon, installationTime, usageTime, appInfo.packageName, appInfo.className));
            }
        }
    }

    public List<AppData> getAppList() {
        return appList;
    }
}
