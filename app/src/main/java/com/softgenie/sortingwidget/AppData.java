package com.softgenie.sortingwidget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AppData{
    private final String appName;
    private byte[] appIcon; // transient 키워드를 사용하여 직렬화에서 제외
    private long installationTime;
    private long usageTime;
    private String packageName;
    private boolean isSelected;

    public AppData(String appName, byte[] appIcon, long installationTime, long usageTime, String packageName) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.installationTime = installationTime;
        this.usageTime = usageTime;
        this.packageName = packageName;
        this.isSelected = false;
    }

    @NonNull
    @Override
    public String toString(){
        return "AppData{" +
                "appName: " + appName + "}";
    }
    public String getAppName() {
        return appName;
    }

    public Bitmap getAppIcon() {
        return byteArrayToBitmap(appIcon);
    }

    public long getInstallationTime() {
        return installationTime;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(long usageTime){
        this.usageTime = usageTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

}