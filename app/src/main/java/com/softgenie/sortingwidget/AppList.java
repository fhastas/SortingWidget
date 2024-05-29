package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Calendar;
import java.util.Set;

public class AppList implements Serializable {
    private final List<AppData> appList = new ArrayList<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    public AppList(Context context) {

        PackageManager pm = context.getPackageManager();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        Set<String> appNameSet = new HashSet<>();
        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 시스템 앱이 아닌 경우만 추가
                    String appName = pm.getApplicationLabel(appInfo).toString();
                    if(!appNameSet.add(appName)) {
                        continue;
                    }
                    Drawable appIcon = pm.getApplicationIcon(appInfo);
                    Bitmap bitmap = drawableToBitmap(appIcon);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    byte[] iconBytes = outputStream.toByteArray();

                    long installationTime = pm.getPackageInfo(packageName, 0).firstInstallTime;
                    long usageTime = usageStats.getTotalTimeInForeground();

                    appList.add(new AppData(appName, iconBytes, installationTime, usageTime, packageName));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder appsInfo = new StringBuilder();
        for (AppData app : appList) {
            appsInfo.append("App name: ").append(app.getAppName()).append("\n");
        }
        return appsInfo.toString();
    }

    public List<AppData> getAppList() {
        return appList;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
            Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
            return bitmap;
        } else {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }
}
