package com.android.tenera.application;

import android.app.Application;

/**
 * Created by prajwalrai on 08/07/16.
 */
public class MainApplication extends Application {
    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();instance = this;
    }
}
