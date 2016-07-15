package com.android.tenera.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.android.tenera.application.MainApplication;

public class SharePreferenceUtil {

    private Context applicationContext = MainApplication.getInstance().getApplicationContext();
    private SharedPreferences sharedPreferences = getSharedPreferences();

    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = applicationContext.getSharedPreferences("app", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public void setBoolean(String name, boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public boolean getBoolean(String name) {
        return sharedPreferences.getBoolean(name, true);
    }

    public void setString(String name, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getString(String key, String defaultVal) {
        return getSharedPreferences().getString(key, defaultVal);
    }

    public void setFloat(String name, float value) {
        Editor editor = sharedPreferences.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    public float getFloat(String key) {
        return getSharedPreferences().getFloat(key, 0.0f);
    }

    public void setInt(String name, int value) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public int getInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

}
