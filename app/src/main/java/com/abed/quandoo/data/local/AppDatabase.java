package com.abed.quandoo.data.local;

import com.raizlabs.android.dbflow.annotation.Database;


@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;

}





