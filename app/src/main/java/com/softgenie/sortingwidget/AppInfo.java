package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class AppInfo {
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
}

class AppList {
    private List<AppInfo> appInfoList;

    public AppList(Context context) {
        this.appInfoList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : packages) {
            String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            Drawable appIcon = packageInfo.applicationInfo.loadIcon(pm);
            long usageTime = 0; // Replace this with actual code to fetch usage time.

            this.appInfoList.add(new AppInfo(appName, appIcon, usageTime));
        }
    }

    public void printAppList() {
        for (AppInfo appInfo : this.appInfoList) {
            System.out.println("App Icon: " + appInfo.getAppIcon());
            System.out.println("App Name: " + appInfo.getAppName());
            System.out.println("Usage Time: " + appInfo.getUsageTime());
        }
    }
}