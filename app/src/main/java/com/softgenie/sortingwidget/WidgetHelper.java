package com.softgenie.sortingwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

import java.util.List;

public class WidgetHelper {

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        int maxAppsToShow = 16;
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);

        for (int i = 0; i < maxAppsToShow && i < installedApps.size(); i++) {
            ApplicationInfo appInfo = installedApps.get(i);
            Drawable icon = packageManager.getApplicationIcon(appInfo);
            CharSequence appName = packageManager.getApplicationLabel(appInfo);

            // Update ImageView using RemoteViews.setImageViewBitmap() method
            views.setImageViewBitmap(R.id.imageView1 + i, drawableToBitmap(icon));

// Update TextView using RemoteViews.setTextViewText() method
            views.setTextViewText(R.id.textView1 + i, appName.toString());



        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
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
