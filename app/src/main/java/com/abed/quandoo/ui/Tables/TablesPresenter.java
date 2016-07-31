package com.abed.quandoo.ui.Tables;

import com.abed.quandoo.data.DataManager;
import com.abed.quandoo.data.busEvents.BusEventTablesLoadCompleted;
import com.abed.quandoo.data.model.Table;
import com.abed.quandoo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class TablesPresenter extends BasePresenter<TablesMvpView> {
    private String TAG = getClass().getName();

    @Inject
    DataManager dataManager;

    private Subscription tableLoadingSubscription;

    @Inject
    public TablesPresenter() {
    }

    @Override
    public void attachView(TablesMvpView mvpView) {
        super.attachView(mvpView);
        initRx();
    }

    @Override
    public void detachView() {
        super.detachView();
        tableLoadingSubscription.unsubscribe();
    }

    public void loadData() {
        dataManager.getApiHelper().syncTableList();
        showData();

    }

    private void showData() {
        List<Table> tables = dataManager.getDatabaseHelper().loadTables();
        getMvpView().showTables(tables);

    }

    void markTableAsReserved(Table table) {
        dataManager.getDatabaseHelper().markTableAsReserved(table);
        getMvpView().refreshData();
    }


    private void initRx() {
        tableLoadingSubscription = dataManager.getEventBusHelper().getBus()
                .register(BusEventTablesLoadCompleted.class,
                        event -> showData()
                );
    }

}
