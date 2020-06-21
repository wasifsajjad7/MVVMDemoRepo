package com.niit.spiritairlinepoc.data.local.db.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.niit.spiritairlinepoc.MvvmApp;
import com.niit.spiritairlinepoc.data.local.db.dao.UserDAO;
import com.niit.spiritairlinepoc.data.network.model.UserDetails;

@androidx.room.Database(entities = {UserDetails.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database sInstance;

    public abstract UserDAO logDao();

    private static Database initialize(Context context) {
        sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "log-database").fallbackToDestructiveMigration().build();
        return sInstance;
    }

    public static Database getInstance() {
        if (sInstance == null) {
            return initialize(MvvmApp.getInstance());
        } else {
            return sInstance;
        }
    }

}
