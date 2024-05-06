package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
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

import com.softgenie.sortingwidget.R;

import java.util.List;

public class WidgetHelper {

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        PackageManager packageManager = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

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

            // Create RemoteViews for each app item
            RemoteViews appItemView = new RemoteViews(context.getPackageName(), R.layout.app_widget_item);
            appItemView.setImageViewBitmap(R.id.icon_image_view, iconBitmap);
            appItemView.setTextViewText(R.id.name_text_view, appName.toString());

            // Set click intent for launching the app
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appInfo.packageName);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            appItemView.setOnClickPendingIntent(R.id.app_item_layout, pendingIntent);

            // Add the app item view to the GridView
            views.addView(R.id.grid_view, appItemView);
        }

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
