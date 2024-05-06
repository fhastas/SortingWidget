package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

class AppInfo implements Comparable<AppInfo> {
    private String appName;
    private Drawable appIcon;
    private long usageTime;
    private Intent shortcut;

    public AppInfo(String appName, Drawable appIcon, long usageTime, Intent shortcut) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.usageTime = usageTime;
        this.shortcut = shortcut;
    }

    @Override
    public int compareTo(AppInfo appInfo) {
        return Long.compare(this.usageTime, appInfo.usageTime);
    }

    @NonNull
    @Override
    public String toString() {
        return "AppInfo{" +
                "appName=" + appName +
                ", appIcon=" + appIcon +
                ", usageTime=" + usageTime + '}';
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(long usageTime) {
        this.usageTime = usageTime;
    }

    public Intent getShortcut() {
        return shortcut;
    }

    public void setShortcut(Intent shortcut) {
        this.shortcut = shortcut;
    }
}

public class AppList {
    List<AppInfo> appList = new ArrayList<>();

    AppList(Context context) {
        PackageManager pm = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        for (ApplicationInfo app : apps) {
            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = pm.getApplicationIcon(app);
            long usageTime = getUsageTime(app.packageName, context);
            Intent shortcut = pm.getLaunchIntentForPackage(app.packageName);

            appList.add(new AppInfo(name, icon, usageTime, shortcut));
        }
    }

    private long getUsageTime(String packageName, Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            long installTime = packageInfo.firstInstallTime;

            return System.currentTimeMillis() - installTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<AppInfo> getAppList() {
        return appList;
    }
}