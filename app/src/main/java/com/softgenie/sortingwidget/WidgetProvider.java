package com.softgenie.sortingwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // 여기서 데이터 생성 또는 받아오기
        String widgetText = "위젯에 나타낼 텍스트";

        // 각 위젯 ID에 대해 업데이트 수행
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, widgetText);
        }
    }

    // 위젯 업데이트를 처리하는 메서드
    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String widgetText) {
        // 위젯 업데이트에 사용할 뷰 레이아웃 설정
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // 데이터를 위젯에 적용
        //    views.setTextViewText(R.id.widget_data_text, widgetText);

        // 위젯 업데이트 적용
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}



