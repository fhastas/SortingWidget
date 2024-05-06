package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "AppListPref";

    public static void saveAppList(Context context, AppList appList) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(appList);
        editor.putString("appList", json);
        editor.apply();
    }

    public static AppList loadAppList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("appList", null);
        Type type = new TypeToken<AppList>(){}.getType();
        return gson.fromJson(json, type);
    }
}
