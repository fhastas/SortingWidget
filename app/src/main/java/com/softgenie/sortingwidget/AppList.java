package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

public class AppList {
    private List<AppData> appList = new ArrayList<>();

    public AppList() {
        this.appList = null;
    }

    public AppList(Context context) {
        PackageManager pm = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        for (ApplicationInfo app : apps) {
            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = pm.getApplicationIcon(app);
            long usageTime = getUsageTime(app.packageName, context);
            Intent shortcut = pm.getLaunchIntentForPackage(app.packageName);

            appList.add(new AppData(name, icon, usageTime, shortcut));
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

    public List<AppData> getAppList() {
        return appList;
    }

    public void setAppList(List<AppData> appList) {
        this.appList = appList;
    }
}