package com.abed.quandoo.ui.Main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.abed.quandoo.R;
import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.ui.Tables.TablesActivity;
import com.abed.quandoo.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MainAdapter mainAdapter;

    @BindView(R.id.recylcer_items)
    RecyclerView recyclerItems;
    @BindView(R.id.progress_loading)
    ProgressBar progress_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(this);
        mMainPresenter.loadData();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerItems.setLayoutManager(mLayoutManager);
        recyclerItems.setAdapter(mainAdapter);
        mainAdapter.setClicksListener((view, position, customer) -> TablesActivity.startActivity(view.getContext(), customer));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showCustomers(List<Customer> customers) {
        if (customers.size() > 0)
            progress_loading.setVisibility(View.GONE);
        mainAdapter.updateList(customers);
    }

}
