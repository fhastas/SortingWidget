package com.softgenie.sortingwidget;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import java.util.ArrayList;
import java.util.List;

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            setGridItems(context, views);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private void setGridItems(Context context, RemoteViews views) {
        AppList appList = new AppList(context);
        List<AppInfo> appInfoList = appList.getAppInfoList();

        for (int i = 0; i < 4; i++) { // 4x6 그리드에 대한 루프
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (index < appInfoList.size()) {
                    AppInfo appInfo = appInfoList.get(index);
                    int buttonId = context.getResources().getIdentifier("button" + (i + 1) + "_" + (j + 1), "id", context.getPackageName());
                    int labelId = context.getResources().getIdentifier("label" + (i + 1) + "_" + (j + 1), "id", context.getPackageName());
                    views.setImageViewBitmap(buttonId, drawableToBitmap(appInfo.getAppIcon()));
                    views.setTextViewText(labelId, appInfo.getAppName());

                    // 사용 시간 데이터를 가져와서 표시합니다.
                    views.setTextViewText(labelId, String.valueOf(appInfo.getUsageTime()));
                }
            }
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private static class AppInfo {
        private String appName;
        private Drawable appIcon;
        private long usageTime;

        public AppInfo(String appName, Drawable appIcon, long usageTime) {
            this.appName = appName;
            this.appIcon = appIcon;
            this.usageTime = usageTime;
        }

        public String getAppName() {
            return appName;
        }

        public Drawable getAppIcon() {
            return appIcon;
        }

        public long getUsageTime() {
            return usageTime;
        }
    }

    private static class AppList {
        private final List<AppInfo> appInfoList;

        public AppList(Context context) {
            this.appInfoList = new ArrayList<>();
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> packages = pm.getInstalledPackages(0);

            for (PackageInfo packageInfo : packages) {
                String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                Drawable appIcon = packageInfo.applicationInfo.loadIcon(pm);
                long usageTime = getUsageTimeForPackage(context, packageInfo.packageName);
                this.appInfoList.add(new AppInfo(appName, appIcon, usageTime));
            }
        }

        public List<AppInfo> getAppInfoList() {
            return appInfoList;
        }

        private long getUsageTimeForPackage(Context context, String packageName) {
            UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            if (usageStatsManager == null) {
                return 0;
            }

            long currentTime = System.currentTimeMillis();
            long oneDayAgo = currentTime - (24 * 60 * 60 * 1000); // 24시간 전

            List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, oneDayAgo, currentTime);

            if (usageStatsList != null) {
                for (UsageStats usageStats : usageStatsList) {
                    if (usageStats.getPackageName().equals(packageName)) {
                        return usageStats.getTotalTimeInForeground();
                    }
                }
            }

            return 0; // 해당 패키지의 사용 시간 데이터를 찾을 수 없을 경우 0 반환
        }
    }
}
