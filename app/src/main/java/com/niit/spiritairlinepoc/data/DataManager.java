package com.niit.spiritairlinepoc.data;

import com.niit.spiritairlinepoc.data.local.db.AppDbHelper;
import com.niit.spiritairlinepoc.data.local.prefs.AppPreferencesHelper;
import com.niit.spiritairlinepoc.data.network.services.POCService;

public class DataManager {

    private static DataManager sInstance;

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public POCService getUserService() {
        return POCService.getInstance();
    }

    public AppPreferencesHelper getSharedPreference(){
        return  AppPreferencesHelper.getInstance();
    }

    public AppDbHelper getAppDbHelper(){
        return  AppDbHelper.getInstance();
    }

}
