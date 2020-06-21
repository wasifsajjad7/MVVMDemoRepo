package com.niit.spiritairlinepoc.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.niit.spiritairlinepoc.MvvmApp;
import com.niit.spiritairlinepoc.utils.AppConstants;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static AppPreferencesHelper instance;

    private final SharedPreferences mPrefs;


    public AppPreferencesHelper(Context context,String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    public static AppPreferencesHelper getInstance() {
        if (instance == null) {
            instance = new AppPreferencesHelper(MvvmApp.getInstance(), AppConstants.PREF_NAME);
        }
        return instance;
    }


    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

}
