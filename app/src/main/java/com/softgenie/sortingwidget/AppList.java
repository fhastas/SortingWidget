package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

        int i = 0;

        for (UsageStats usageStats : usageStatsList) {
            String packageName = usageStats.getPackageName();
            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    continue;
                }
                String appName = pm.getApplicationLabel(appInfo).toString();

                Drawable icon = pm.getApplicationIcon(appInfo);
                Bitmap bitmapIcon = drawableToBitmap(icon);
                if(bitmapIcon == null){
                    bitmapIcon = drawableToBitmap(ContextCompat.getDrawable(context, android.R.drawable.stat_notify_error));
                }
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmapIcon.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] byteIcon = outputStream.toByteArray();

                long installationTime = pm.getPackageInfo(packageName, 0).firstInstallTime;
                long usageTime = usageStats.getTotalTimeInForeground();

                Log.d(this.getClass().getSimpleName(), (i++) + "App name: " + appName);
                appList.add(new AppData(appName, byteIcon, installationTime, usageTime, packageName));

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        for (AppData app : appList) {
            Log.d(this.getClass().getSimpleName(), "App name: " + app.getAppName());
        }
        return appList.toString();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        return bitmap;
    }

    public List<AppData> getAppList() {
        return appList;
    }
}