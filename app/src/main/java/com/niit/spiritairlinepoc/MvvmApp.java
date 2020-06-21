package com.niit.spiritairlinepoc;


import android.app.Application;
import com.niit.spiritairlinepoc.utils.AppLogger;

public class MvvmApp extends Application{

    private static MvvmApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AppLogger.init();
    }

    public static MvvmApp getInstance() {
        return sInstance;
    }

}