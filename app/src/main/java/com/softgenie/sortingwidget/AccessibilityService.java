package com.softgenie.sortingwidget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    private static final String TAG = "AccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        Log.d(TAG, "Catch Event : " + event.toString());
//        Log.d(TAG, "Catch Event Package Name : " + event.getPackageName());
//        Log.d(TAG, "Catch Event TEXT : " + event.getText());
//        Log.d(TAG, "Catch Event ContentDescription  : " + event.getContentDescription());
//        Log.d(TAG, "Catch Event getSource : " + event.getSource());
//        Log.d(TAG, "=========================================================================");
    }

    @Override
    public void onServiceConnected(){
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; // 전체 이벤트 가져오기
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN | AccessibilityServiceInfo.FEEDBACK_HAPTIC;
        info.notificationTimeout = 100; // millisecond

        setServiceInfo(info);
    }

    @Override
    public void onInterrupt() {
        Log.e("TEST", "OnInterrupt");
    }

}