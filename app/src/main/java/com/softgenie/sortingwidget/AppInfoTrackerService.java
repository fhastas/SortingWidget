package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

import java.util.Collections;
import java.util.Comparator;

public class AppInfoTrackerService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "AppInfoTrackerChannel";
    private static final String CHANNEL_NAME = "AppInfoTrackerChannelName";

    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스가 시작될 때 실행될 작업
        // 알림 생성 및 foreground 서비스로 설정
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());

        // 스레드에서 AppList 객체 생성 작업 수행
        new Thread(() -> {
            AppList appList = new AppList(getApplicationContext());
            appList.getAppList().sort(new Comparator<AppInfo>() {
                public int compare(AppInfo o1, AppInfo o2) {
                    return o1.getAppName().compareTo(o2.getAppName());
                }
            });
            SharedPreferencesHelper.saveAppList(getApplicationContext(), appList);

            stopSelf();
        }).start();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("widget genie")
                .setContentText("설치된 앱들을 확인하고 있습니다")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true);

        return builder.build();
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }
}