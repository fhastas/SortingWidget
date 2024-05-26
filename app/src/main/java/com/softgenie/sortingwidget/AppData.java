package com.softgenie.sortingwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

// AppData.java
import android.graphics.drawable.Drawable;
import java.io.Serializable;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AppData implements Parcelable {
    private String appName;
    private transient Drawable appIcon; // transient 키워드를 사용하여 직렬화에서 제외
    private long installationTime;
    private long usageTime;
    private String packageName;
    private String className;
    private boolean isSelected;

    public AppData(String appName, Drawable appIcon, long installationTime, long usageTime, String packageName, String className) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.installationTime = installationTime;
        this.usageTime = usageTime;
        this.packageName = packageName;
        this.className = className;
        this.isSelected = false;
    }
    protected AppData(Parcel in) {
        appName = in.readString();
        isSelected = in.readByte() != 0;
        // appIcon handling would go here, but it's more complex since Drawable is not Parcelable.
    }
    public static final Parcelable.Creator<AppData> CREATOR = new Parcelable.Creator<AppData>() {
        @Override
        public AppData createFromParcel(Parcel in) {
            return new AppData(in);
        }

        @Override
        public AppData[] newArray(int size) {
            return new AppData[size];
        }
    };
    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public long getInstallationTime() {
        return installationTime;
    }

    public long getUsageTime() {
        return usageTime;
    }


    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }
}

