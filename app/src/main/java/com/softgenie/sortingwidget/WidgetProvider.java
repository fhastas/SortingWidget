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
        List<AppInfo> appInfoList = appList.getAppList();

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

}