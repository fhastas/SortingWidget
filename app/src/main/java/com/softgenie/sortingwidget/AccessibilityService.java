package com.softgenie.sortingwidget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    private static final String TAG = "AccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        Log.e(TAG, "Catch Event : " + event.toString());
        Log.e(TAG, "Catch Event Package Name : " + event.getPackageName());
        Log.e(TAG, "Catch Event TEXT : " + event.getText());
        Log.e(TAG, "Catch Event ContentDescription  : " + event.getContentDescription());
        Log.e(TAG, "Catch Event getSource : " + event.getSource());
        Log.e(TAG, "=========================================================================");
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