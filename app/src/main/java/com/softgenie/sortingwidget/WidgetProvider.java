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
import android.os.AsyncTask;
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

        // 그리드에 앱 정보 표시
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (index < appInfoList.size()) {
                    AppInfo appInfo = appInfoList.get(index);
                    int imageViewId = context.getResources().getIdentifier("imageView" + (i + 1) + (j + 1), "id", context.getPackageName());
                    int textViewId = context.getResources().getIdentifier("textView" + (i + 1) + (j + 1), "id", context.getPackageName());

                    // 앱 아이콘 비동기적으로 설정
                    new LoadImageTask(context, views, imageViewId, textViewId).execute(appInfo);
                }
            }
        }
    }

    private static class LoadImageTask extends AsyncTask<AppInfo, Void, Bitmap> {
        private Context context;
        private RemoteViews views;
        private int imageViewId;
        private int textViewId;

        public LoadImageTask(Context context, RemoteViews views, int imageViewId, int textViewId) {
            this.context = context;
            this.views = views;
            this.imageViewId = imageViewId;
            this.textViewId = textViewId;
        }

        @Override
        protected Bitmap doInBackground(AppInfo... appInfos) {
            AppInfo appInfo = appInfos[0];
            return drawableToBitmap(appInfo.getAppIcon());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            views.setImageViewBitmap(imageViewId, bitmap);
            // 텍스트뷰 설정 등 다른 작업 수행
            // ...
        }
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
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
