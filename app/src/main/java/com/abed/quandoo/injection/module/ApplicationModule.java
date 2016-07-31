package com.abed.quandoo.injection.module;

import android.app.Application;
import android.content.Context;

import com.abed.quandoo.data.RxBus;
import com.abed.quandoo.data.remote.QuandooService;
import com.abed.quandoo.injection.ApplicationContext;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    RxBus provideEventBus() {
        return new RxBus();
    }


    @Provides
    @Singleton
    QuandooService provideChallengerService() {
        return QuandooService.Creator.newQuandooService();
    }

}
