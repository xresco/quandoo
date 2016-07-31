package com.abed.quandoo.injection.component;

import com.abed.quandoo.ui.Main.MainActivity;
import com.abed.quandoo.injection.PerActivity;
import com.abed.quandoo.injection.module.ActivityModule;
import com.abed.quandoo.ui.Tables.TablesActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(TablesActivity tablesActivity);

}
