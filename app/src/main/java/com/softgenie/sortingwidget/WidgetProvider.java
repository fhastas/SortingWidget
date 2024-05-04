package com.softgenie.sortingwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // 모든 위젯 ID에 대해 업데이트 수행
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // 원격 뷰 생성
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // 여기서 버튼에 대한 추가적인 동작을 설정할 수 있습니다.

        // 위젯 업데이트
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}




