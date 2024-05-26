package com.softgenie.sortingwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Include extends AppCompatActivity {
    private static final String TAG = "Include";
    private ListView appListView;
    private AppDataAdapter adapter;
    Button back3, next3;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);

        back3 = findViewById(R.id.back3);
        appListView = findViewById(R.id.appListView);

        UserInfo userInfo = SharedPreferencesHelper.loadUserInfo(this);
        AppList appList = SharedPreferencesHelper.loadAppList(this);

        if (appList == null) {
            appList = new AppList(getApplicationContext());
            SharedPreferencesHelper.saveAppList(this, appList);
        }

        adapter = new AppDataAdapter(this, appList.getAppList());
        appListView.setAdapter(adapter);
        appListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        next3 = findViewById(R.id.next3);
        next3.setOnClickListener(v -> {
            List<AppData> selectedApps = getSelectedApps();
            List<String> selectedAppNames = new ArrayList<>();
            for (AppData app : selectedApps) {
                selectedAppNames.add(app.getAppName());
            }

            assert userInfo != null;
            userInfo.setInclude(selectedAppNames);
            SharedPreferencesHelper.saveUserInfo(this, userInfo);

            Log.d(TAG, "[UserInfo]\n" + userInfo);

            Intent intent = new Intent(Include.this, TempWidget.class);
            startActivity(intent);
            finish();
        });

        back3.setOnClickListener(v -> {
            Intent intent = new Intent(Include.this, Prioritize.class);
            startActivity(intent);
            finish();
        });
    }

    private List<AppData> getSelectedApps() {
        List<AppData> selectedApps = new ArrayList<>();
        for (int i = 0; i < appListView.getCount(); i++) {
            if (appListView.isItemChecked(i)) {
                selectedApps.add(adapter.getItem(i));
            }
        }
        return selectedApps;
    }
    private List<AppData> loadInstalledApps() {
        List<AppData> apps = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : installedApps) {
            try {
                String appName = appInfo.loadLabel(packageManager).toString();
                Drawable appIconDrawable = appInfo.loadIcon(packageManager);
                byte[] appIcon = drawableToByteArray(appIconDrawable);

                PackageInfo packageInfo = packageManager.getPackageInfo(appInfo.packageName, 0);
                long installationTime = packageInfo.firstInstallTime;
                long usageTime = 0; // Usage time should be calculated or retrieved as per requirement

                String packageName = appInfo.packageName;
                String className = TempWidget(packageManager, packageName);

                apps.add(new AppData(appName, appIcon, installationTime, usageTime, packageName, className));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Package not found: " + appInfo.packageName, e);
            }
        }

        return apps;
    }

    private String TempWidget(PackageManager packageManager, String packageName) {
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            ResolveInfo resolveInfo = packageManager.resolveActivity(intent, 0);
            if (resolveInfo != null) {
                return resolveInfo.activityInfo.name;
            }
        }
        return null;
    }

    private byte[] drawableToByteArray(Drawable drawable) {
        if (drawable == null) {
            return new byte[0];
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
