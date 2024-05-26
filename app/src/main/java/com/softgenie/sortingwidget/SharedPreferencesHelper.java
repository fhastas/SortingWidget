package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesHelper {

    private static final String PREFS_NAME = "user_prefs";
    private static final String USER_INFO_KEY = "user_info";
    private static final String APP_LIST_KEY = "app_list";

    public static void saveUserInfo(Context context, UserInfo userInfo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userInfo);
        editor.putString(USER_INFO_KEY, json);
        editor.apply();
    }

    public static UserInfo loadUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(USER_INFO_KEY, null);
        return gson.fromJson(json, UserInfo.class);
    }

    public static void saveAppList(Context context, AppList appList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(appList);
        editor.putString(APP_LIST_KEY, json);
        editor.apply();
    }

    public static AppList loadAppList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(APP_LIST_KEY, null);
        return gson.fromJson(json, AppList.class);
    }
}

