package com.abed.quandoo.data;

import com.abed.quandoo.controller.EventBusHelper;
import com.abed.quandoo.data.local.DatabaseHelper;
import com.abed.quandoo.data.remote.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.functions.Action0;

/**
 * Responsible of managing anything related to data like: local files, sharedPref, DB ....
 * For now it only supports local files since it's the only thing i needed
 */
@Singleton
public class DataManager {


    private final DatabaseHelper mDatabaseHelper;
    private final ApiHelper mApiHelper;
    private final EventBusHelper mEventBusHelper;

    @Inject
    public DataManager(DatabaseHelper databaseHelper, EventBusHelper eventBusHelper, ApiHelper apiHelper) {
        mDatabaseHelper = databaseHelper;
        mEventBusHelper = eventBusHelper;
        mApiHelper = apiHelper;
    }



    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventBusHelper.postEventSafely(event);
            }
        };
    }

    public EventBusHelper getEventBusHelper() {
        return mEventBusHelper;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public ApiHelper getApiHelper() {
        return mApiHelper;
    }
}
