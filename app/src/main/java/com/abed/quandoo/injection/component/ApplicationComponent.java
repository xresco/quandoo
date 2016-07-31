package com.abed.quandoo.injection.component;

import android.app.Application;
import android.content.Context;

import com.abed.quandoo.data.DataManager;
import com.abed.quandoo.data.RxBus;
import com.abed.quandoo.data.local.DatabaseHelper;
import com.abed.quandoo.data.remote.QuandooService;
import com.abed.quandoo.injection.ApplicationContext;
import com.abed.quandoo.injection.module.ApplicationModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    QuandooService quandooService();

    DatabaseHelper databaseHelper();

    RxBus eventBus();
}
