package com.abed.quandoo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abed.quandoo.Application;
import com.abed.quandoo.injection.component.ActivityComponent;
import com.abed.quandoo.injection.component.DaggerActivityComponent;
import com.abed.quandoo.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(Application.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
