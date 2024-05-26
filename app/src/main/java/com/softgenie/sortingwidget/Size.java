package com.softgenie.sortingwidget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Size extends AppCompatActivity {
    private static final String TAG = "Size";
    private static final String PREFS_NAME = "SortingWidgetPrefs";
    private static final String KEY_SIZE = "key_size";

    Button next1, button4x6, button4x4, button4x2, button2x2;
    ImageView imageViewx46, imageViewx44, imageViewx42, imageViewx22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_size);

        AtomicInteger size = new AtomicInteger(46); // 다음 Activity에 전달할 변수 데이터 생성과 함께 초기화

        if(!checkAccessibilityPermissions()) {
            setAccessibilityPermissions();
        }

        requestUsageAccessPermission();
        startService();

        initializeViews();
        setInitialVisibility();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setButtonListeners(size);

        next1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Prioritize.class);
            intent.putExtra("size", size.get());
            Log.d(TAG, "Size: " + size.get());

            finish();
            startActivity(intent);
        });
    }

    private void initializeViews() {
        next1 = findViewById(R.id.next1);
        button4x6 = findViewById(R.id.button4x6);
        button4x4 = findViewById(R.id.button4x4);
        button4x2 = findViewById(R.id.button4x2);
        button2x2 = findViewById(R.id.button2x2);
        imageViewx46 = findViewById(R.id.PrioritizeX46);
        imageViewx44 = findViewById(R.id.PrioritizeX44);
        imageViewx42 = findViewById(R.id.imageViewx42);
        imageViewx22 = findViewById(R.id.imageViewx22);
    }

    private void setInitialVisibility() {
        imageViewx46.setVisibility(View.VISIBLE);
        imageViewx44.setVisibility(View.INVISIBLE);
        imageViewx42.setVisibility(View.INVISIBLE);
        imageViewx22.setVisibility(View.INVISIBLE);
    }

    private void setButtonListeners(AtomicInteger size) {
        button4x6.setOnClickListener(v -> {
            size.set(46);
            updateImageViewVisibility(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
        });

        button4x4.setOnClickListener(v -> {
            size.set(44);
            updateImageViewVisibility(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
        });

        button4x2.setOnClickListener(v -> {
            size.set(42);
            updateImageViewVisibility(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
        });

        button2x2.setOnClickListener(v -> {
            size.set(22);
            updateImageViewVisibility(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
        });
    }

    private void updateImageViewVisibility(int x46Visibility, int x44Visibility, int x42Visibility, int x22Visibility) {
        imageViewx46.setVisibility(x46Visibility);
        imageViewx44.setVisibility(x44Visibility);
        imageViewx42.setVisibility(x42Visibility);
        imageViewx22.setVisibility(x22Visibility);
    }

    public void startService(){
        Intent intent = new Intent(this, AppInfoTrackerService.class);
        startService(intent);
    }

    public boolean checkAccessibilityPermissions() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT);

        for (AccessibilityServiceInfo info : list) {
            if (info.getResolveInfo().serviceInfo.packageName.equals(getApplication().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public void setAccessibilityPermissions() {
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
        gsDialog.setTitle("접근성 권한 설정");
        gsDialog.setMessage("접근성 권한을 필요로 합니다");
        gsDialog.setPositiveButton("확인", (dialog, which) -> {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }).create().show();
    }

    private void requestUsageAccessPermission() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }
}
