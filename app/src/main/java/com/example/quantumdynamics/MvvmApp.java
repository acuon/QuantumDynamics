package com.example.quantumdynamics;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

public class MvvmApp extends Application {

    private MvvmApp appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public MvvmApp getAppContext() {
        return appContext;
    }
}
