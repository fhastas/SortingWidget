package com.softgenie.sortingwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
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

        // Create RemoteViews for the widget layout
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);

        // Clear any previous views from the GridView
        views.removeAllViews(R.id.grid_view);

        // Loop through installed apps and add them to the GridView
        for (int i = 0; i < maxAppsToShow && i < installedApps.size(); i++) {
            ApplicationInfo appInfo = installedApps.get(i);
            Drawable icon = packageManager.getApplicationIcon(appInfo);
            CharSequence appName = packageManager.getApplicationLabel(appInfo);

            // Convert drawable to bitmap
            Bitmap iconBitmap = drawableToBitmap(icon);

            // Add app icon and name to the GridView
            views.setImageViewBitmap(R.id.grid_view, iconBitmap);
            views.setTextViewText(R.id.grid_view, appName.toString());
        }

        // Set an intent to launch the app when its icon is clicked
        Intent launchIntent = new Intent(context, WidgetActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.grid_view, pendingIntent);

        // Update the app widget with the modified RemoteViews
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
