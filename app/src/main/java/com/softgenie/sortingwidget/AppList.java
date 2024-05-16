package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        int i = 0;

        for (ApplicationInfo app : apps) {
//            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                continue;
//            }

            String name = (String) pm.getApplicationLabel(app);
            Drawable icon = app.loadIcon(pm);
            Bitmap bitmapIcon = drawableToBitmap(icon);
            if(bitmapIcon == null){
                bitmapIcon = drawableToBitmap(ContextCompat.getDrawable(context, android.R.drawable.stat_notify_error));
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmapIcon.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteIcon = outputStream.toByteArray();

            long installTime = getUsageTime(app.packageName, context);

            Log.d(this.getClass().getSimpleName(), (i++) + "App name: " + name);
            appList.add(new AppData(name, byteIcon, installTime, app.packageName, app.className));
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

    private long getUsageTime(String packageName, Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            long installTime = packageInfo.firstInstallTime;

            return System.currentTimeMillis() - installTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<AppData> getAppList() {
        return appList;
    }
}