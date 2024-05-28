package com.softgenie.sortingwidget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
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
        installationTime = in.readLong();
        usageTime = in.readLong();
        packageName = in.readString();
        isSelected = in.readByte() != 0;
        // appIcon handling would go here, but it's more complex since Drawable is not Parcelable.
    }

    @NonNull
    @Override
    public String toString() {
        return "AppData{" +
                "appName='" + appName + '\'' +
                ", installationTime=" + installationTime +
                ", usageTime=" + usageTime +
                ", packageName='" + packageName + '\'' +
                ", isSelected=" + isSelected +
                '}';
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

    public void setUsageTime(long usageTime) {
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
        parcel.writeLong(installationTime);
        parcel.writeLong(usageTime);
        parcel.writeString(packageName);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }

    // Drawable을 Bitmap으로 변환하는 메서드, 사용해야 할때 사용할 것
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

