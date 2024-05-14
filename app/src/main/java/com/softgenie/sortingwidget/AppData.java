package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class AppData implements Comparable<AppData> {
    private String appName;
    private Bitmap appIcon;
    private long usageTime;
    private Intent shortcut;
    private boolean selected;

    public AppData(String appName, Bitmap appIcon, long usageTime, Intent shortcut) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.usageTime = usageTime;
        this.shortcut = shortcut;
        this.selected = false;
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

    public Bitmap getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Bitmap appIcon) {
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

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
