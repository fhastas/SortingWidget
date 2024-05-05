package com.softgenie.sortingwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import android.content.Intent;
import android.app.PendingIntent;

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
                    views.setImageViewBitmap(getButtonId(context, i, j), drawableToBitmap(appInfo.getAppIcon()));
                    views.setTextViewText(getLabelId(context, i, j), appInfo.getAppName());
                }
            }
        }
    }

    private int getButtonId(Context context, int rowIndex, int colIndex) {
        return context.getResources().getIdentifier("button" + (rowIndex + 1) + "_" + (colIndex + 1), "id", context.getPackageName());
    }

    private int getLabelId(Context context, int rowIndex, int colIndex) {
        return context.getResources().getIdentifier("label" + (rowIndex + 1) + "_" + (colIndex + 1), "id", context.getPackageName());
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