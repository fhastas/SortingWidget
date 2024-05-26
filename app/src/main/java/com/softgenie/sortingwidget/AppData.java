package com.softgenie.sortingwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class AppData implements Comparable<AppData> {
    private final String appName;
    private final byte[] appIcon;
    private long installationTime;
    private long usageTime;
    private final String packageName;
    private final String className;
    private boolean selected;

    public AppData(String appName, byte[] appIcon, long installationTime, long usageTime, String packageName, String className) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.installationTime = installationTime;
        this.usageTime = usageTime;
        this.packageName = packageName;
        this.className = className;
        this.selected = false;
    }


    @Override
    public int compareTo(AppData appData) {
        return Long.compare(this.installationTime, appData.installationTime);
    }

    @NonNull
    @Override
    public String toString() {
        return appName;
    }

    public String getAppName() {
        return appName;
    }


    public Drawable getAppIcon(Context context) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(appIcon, 0, appIcon.length);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public long getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(long installationTime) {
        this.installationTime = installationTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
