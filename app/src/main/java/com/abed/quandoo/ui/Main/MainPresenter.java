package com.abed.quandoo.ui.Main;

import com.abed.quandoo.data.DataManager;
import com.abed.quandoo.data.busEvents.BusEventCustomersLoadCompleted;
import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class MainPresenter extends BasePresenter<MainMvpView> {
    private String TAG = getClass().getName();

    @Inject
    DataManager dataManager;

    private Subscription customersLoadingSubscription;

    @Inject
    public MainPresenter() {
    }

    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
        initRx();
    }

    @Override
    public void detachView() {
        super.detachView();
        customersLoadingSubscription.unsubscribe();
    }

    public void loadData() {
        dataManager.getApiHelper().syncCustomerList();
        showData();
    }

    private void showData() {
        List<Customer> customers = dataManager.getDatabaseHelper().loadAllCustomers();
        getMvpView().showCustomers(customers);
    }

    private void initRx() {
        customersLoadingSubscription = dataManager.getEventBusHelper().getBus()
                .register(BusEventCustomersLoadCompleted.class,
                        event -> showData()
                );
    }
}
