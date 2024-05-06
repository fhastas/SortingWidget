package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import java.util.Comparator;
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
        AppList appList = SharedPreferencesHelper.loadAppList(context);
        if (appList == null) {
            appList = new AppList();
            appList.appList.sort(Comparator.comparing(AppData::getAppName));
        }

        List<AppData> appDataList = appList.getAppList();

        // 예시로 4x6 그리드에 앱 정보를 표시하는 코드
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (index < appDataList.size()) {
                    AppData appData = appDataList.get(index);
                    @SuppressLint("DiscouragedApi") int buttonId = context.getResources().getIdentifier("button" + (i + 1) + "_" + (j + 1), "id", context.getPackageName());
                    @SuppressLint("DiscouragedApi") int labelId = context.getResources().getIdentifier("label" + (i + 1) + "_" + (j + 1), "id", context.getPackageName());

                    // 앱 아이콘 설정
                    views.setImageViewBitmap(buttonId, drawableToBitmap(appData.getAppIcon()));
                    // 앱 이름 설정
                    views.setTextViewText(labelId, appData.getAppName());
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