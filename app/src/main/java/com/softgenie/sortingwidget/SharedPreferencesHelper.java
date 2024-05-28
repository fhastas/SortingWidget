package com.softgenie.sortingwidget;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
//AppList와 AppData를 저장하고 로드
public class SharedPreferencesHelper {
    private static final String appListPREF = "AppListPref";
    private static final String userInfoPREF = "UserInfoPref";

    public static void saveAppList(Context context, AppList appList) {
        SharedPreferences preferences = context.getSharedPreferences(appListPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(appList, AppList.class);

        editor.remove("appList");
        editor.putString("appList", json);

        editor.apply();
    }

    public static AppList loadAppList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(appListPREF, Context.MODE_PRIVATE);
        if (preferences != null) {
            Gson gson = new Gson();
            String json = preferences.getString("appList", null);
            if (json != null) {
                Type type = new TypeToken<AppList>(){}.getType();
                return gson.fromJson(json, type);
            }
        }
        return null;
    }

    public static void saveUserInfo(Context context, UserInfo userInfo){
        SharedPreferences preferences = context.getSharedPreferences(userInfoPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userInfo, UserInfo.class);

        editor.clear();
        editor.putString("userInfo", json);

        editor.apply();
    }

    public static UserInfo loadUserInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(userInfoPREF, Context.MODE_PRIVATE);
        if (preferences != null) {
            Gson gson = new Gson();
            String json = preferences.getString("userInfo", null);
            if (json != null) {
                Type type = new TypeToken<UserInfo>() {}.getType();
                return gson.fromJson(json, type);
            }
        }
        return null;
    }
}
