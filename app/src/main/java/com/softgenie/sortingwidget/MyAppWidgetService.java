package com.softgenie.sortingwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyAppWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyAppWidgetRemoteViewsFactory(getApplicationContext());
    }
}