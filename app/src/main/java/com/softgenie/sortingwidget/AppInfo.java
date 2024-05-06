package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

private static class AppInfo {
    private String appName;
    private Drawable appIcon;
    private long usageTime;

    public AppInfo(String appName, Drawable appIcon, long usageTime) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.usageTime = usageTime;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public long getUsageTime() {
        return usageTime;
    }
}

private static class AppList {
    private final List<AppInfo> appInfoList;

    public AppList(Context context) {
        this.appInfoList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : packages) {
            String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            Drawable appIcon = packageInfo.applicationInfo.loadIcon(pm);
            long usageTime = getUsageTimeForPackage(context, packageInfo.packageName);
            this.appInfoList.add(new AppInfo(appName, appIcon, usageTime));
        }
    }

    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    private long getUsageTimeForPackage(Context context, String packageName) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            return 0;
        }

        long currentTime = System.currentTimeMillis();
        long oneDayAgo = currentTime - (24 * 60 * 60 * 1000); // 24시간 전

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, oneDayAgo, currentTime);

        if (usageStatsList != null) {
            for (UsageStats usageStats : usageStatsList) {
                if (usageStats.getPackageName().equals(packageName)) {
                    return usageStats.getTotalTimeInForeground();
                }
            }
        }

        return 0; // 해당 패키지의 사용 시간 데이터를 찾을 수 없을 경우 0 반환
    }
}
}