package com.softgenie.sortingwidget;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;


public class AppData implements Comparable<AppData> {
    private String appName;
    private Drawable appIcon;
    private long usageTime;
    private Intent shortcut;

    public AppData(String appName, Drawable appIcon, long usageTime, Intent shortcut) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.usageTime = usageTime;
        this.shortcut = shortcut;
    }

    @Override
    public int compareTo(AppData appData) {
        return Long.compare(this.usageTime, appData.usageTime);
    }

    @NonNull
    @Override
    public String toString() {
        return appName;
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
