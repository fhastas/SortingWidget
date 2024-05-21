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

        next1 = findViewById(R.id.next1);
        button4x6 = findViewById(R.id.button4x6); // Initialize button4x6
        button4x4 = findViewById(R.id.button4x4); // Initialize button4x4
        button4x2 = findViewById(R.id.button4x2); // Initialize button4x2
        button2x2 = findViewById(R.id.button2x2); // Initialize button2x2
        imageViewx46 = findViewById(R.id.PrioritizeX46);
        imageViewx44 = findViewById(R.id.PrioritizeX44);
        imageViewx42 = findViewById(R.id.imageViewx42);
        imageViewx22 = findViewById(R.id.imageViewx22);

        // Initially set x46 image visible and others invisible
        imageViewx46.setVisibility(View.VISIBLE);
        imageViewx44.setVisibility(View.INVISIBLE);
        imageViewx42.setVisibility(View.INVISIBLE);
        imageViewx22.setVisibility(View.INVISIBLE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button4x6.setOnClickListener(v -> {
            size.set(46);

            imageViewx46.setVisibility(View.VISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // 알고리즘이 있는 클래스와 메소드 명으로 바꾼다
            // AlgorithmClass.performAlgorithm(x46);

        });

        button4x4.setOnClickListener(v -> {
            size.set(44);

            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.VISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // AlgorithmClass.performAlgorithm(x44);
        });

        button4x2.setOnClickListener(v -> {
            size.set(42);

            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.VISIBLE);
            imageViewx22.setVisibility(View.INVISIBLE);
            // AlgorithmClass.performAlgorithm(x42);
        });

        button2x2.setOnClickListener(v -> {
            size.set(22);

            imageViewx46.setVisibility(View.INVISIBLE);
            imageViewx44.setVisibility(View.INVISIBLE);
            imageViewx42.setVisibility(View.INVISIBLE);
            imageViewx22.setVisibility(View.VISIBLE);
            // AlgorithmClass.performAlgorithm(x22);
        });

        next1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Prioritize.class);
            intent.putExtra("size", size.get());
            Log.d(TAG, "Size: " + size.get());

            finish();
            startActivity(intent);
        });
    }

    public void startService(){
        Intent intent = new Intent(this, AppInfoTrackerService.class);
        startService(intent);
    }

    // 접근성 권한이 있는지 없는지 확인하는 부분
    // 있으면 true, 없으면 false
    public boolean checkAccessibilityPermissions() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        // getEnabledAccessibilityServiceList는 현재 접근성 권한을 가진 리스트를 가져오게 된다
        List<AccessibilityServiceInfo> list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT);

        for (int i = 0; i < list.size(); i++) {
            AccessibilityServiceInfo info = list.get(i);

            // 접근성 권한을 가진 앱의 패키지 네임과 패키지 네임이 같으면 현재앱이 접근성 권한을 가지고 있다고 판단함
            if (info.getResolveInfo().serviceInfo.packageName.equals(getApplication().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    // 접근성 설정화면으로 넘겨주는 부분
    public void setAccessibilityPermissions() {
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
        gsDialog.setTitle("접근성 권한 설정");
        gsDialog.setMessage("접근성 권한을 필요로 합니다");
        gsDialog.setPositiveButton("확인", (dialog, which) -> {
            // 설정화면으로 보내는 부분
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }).create().show();
    }
    private void requestUsageAccessPermission() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }
}