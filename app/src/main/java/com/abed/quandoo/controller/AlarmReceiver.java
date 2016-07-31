package com.abed.quandoo.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.abed.quandoo.data.local.DatabaseHelper;


public class AlarmReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getName();


    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.clearReservation();
    }

}