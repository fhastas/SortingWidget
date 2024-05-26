package com.softgenie.sortingwidget;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AppData implements Parcelable {
    private final String appName;
    private transient Drawable appIcon; // transient 키워드를 사용하여 직렬화에서 제외
    private long installationTime;
    private long usageTime;
    private String packageName;
    private boolean isSelected;

    public AppData(String appName, Drawable appIcon, long installationTime, long usageTime, String packageName) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.installationTime = installationTime;
        this.usageTime = usageTime;
        this.packageName = packageName;
        this.isSelected = false;
    }
    protected AppData(Parcel in) {
        appName = in.readString();
        isSelected = in.readByte() != 0;
        // appIcon handling would go here, but it's more complex since Drawable is not Parcelable.
    }

    @NonNull
    @Override
    public String toString(){
        return "AppData{" +
                "appName: " + appName;
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

