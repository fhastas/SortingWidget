package com.softgenie.sortingwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

// AppData.java

import java.io.Serializable;

public class AppData implements Parcelable {
    private String appName;
    private transient Drawable appIcon; // Drawable은 Parcelable을 구현하지 않으므로 transient 키워드를 사용합니다.
    private long installationTime;
    private long usageTime;
    private String packageName;
    private String className;
    private boolean isSelected;

    // 이 생성자를 추가합니다.
    public AppData(String appName, Drawable appIcon, long installationTime, long usageTime, String packageName, String className) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.installationTime = installationTime;
        this.usageTime = usageTime;
        this.packageName = packageName;
        this.className = className;
        this.isSelected = false; // 또는 적절한 기본값 사용
    }

    // Parcelable 인터페이스 구현을 위한 생성자
    protected AppData(Parcel in) {
        appName = in.readString();
        installationTime = in.readLong();
        usageTime = in.readLong();
        packageName = in.readString();
        className = in.readString();
        isSelected = in.readByte() != 0;
    }
    // Parcelable.Creator
    public static final Creator<AppData> CREATOR = new Creator<AppData>() {
        @Override
        public AppData createFromParcel(Parcel in) {
            return new AppData(in);
        }

        @Override
        public AppData[] newArray(int size) {
            return new AppData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    // 객체의 데이터를 Parcel 객체로 변환
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appName);
        dest.writeLong(installationTime);
        dest.writeLong(usageTime);
        dest.writeString(packageName);
        dest.writeString(className);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon(Context applicationContext) {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getSelected() {
        return false;
    }
}
