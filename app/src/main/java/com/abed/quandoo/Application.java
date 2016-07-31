package com.abed.quandoo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.abed.quandoo.injection.component.ApplicationComponent;
import com.abed.quandoo.injection.component.DaggerApplicationComponent;
import com.abed.quandoo.injection.module.ApplicationModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;


public class Application extends android.app.Application {

    ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initAlarmManager();
    }

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    private void initDB() {
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public void initAlarmManager() {
        Intent intent = new Intent("com.abed.quandoo.AlarmReceiver");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long now = System.currentTimeMillis();
        long interval = 10 * 1 * 1000; // 10 mins
        manager.setRepeating(AlarmManager.RTC_WAKEUP, now + interval, interval,
                pendingIntent); // Schedule timer for one hour from now and every hour after that
    }
}
