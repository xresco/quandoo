package com.abed.quandoo.data.remote;

import android.util.Log;

import com.abed.quandoo.controller.EventBusHelper;
import com.abed.quandoo.data.busEvents.BusEventCustomersLoadCompleted;
import com.abed.quandoo.data.busEvents.BusEventTablesLoadCompleted;
import com.abed.quandoo.data.local.DatabaseHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscriber;
import rx.schedulers.Schedulers;


@Singleton
public class ApiHelper {
    private String TAG = getClass().getName();
    private final QuandooService mQuandooService;
    private final DatabaseHelper mDatabaseHelper;
    private final EventBusHelper mEventBusHelper;

    @Inject
    public ApiHelper(QuandooService quandooService, DatabaseHelper databaseHelper, EventBusHelper eventBusHelper) {
        mDatabaseHelper = databaseHelper;
        mEventBusHelper = eventBusHelper;
        mQuandooService = quandooService;
    }


    public void syncCustomerList() {
        mQuandooService.getCustomers()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<API_Customer>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<API_Customer> customers) {
                        for (API_Customer customer : customers) {
                            Log.d(TAG, customer.customerFirstName);
                            mDatabaseHelper.processCustomer(customer);
                            mEventBusHelper.postEventSafely(new BusEventCustomersLoadCompleted());
                        }
                    }
                });

    }


    public void syncTableList() {
        mQuandooService.getTables()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Boolean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Boolean> states) {
                        Log.d(TAG, states.toString());
                        mDatabaseHelper.processTablesStates(states);
                        mEventBusHelper.postEventSafely(new BusEventTablesLoadCompleted());

                    }
                });

    }


}
