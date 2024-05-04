package com.example.myappteam;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {

    // 위젯 업데이트 시 호출되는 메서드
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // 위젯 개수만큼 반복하여 각 위젯 업데이트 수행
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // 위젯 업데이트 수행
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    // 실제 위젯 업데이트를 수행하는 메서드
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // 원격 뷰 생성
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_layout);

        // AppWidgetManager를 사용하여 위젯 업데이트
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}