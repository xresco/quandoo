package com.abed.quandoo.ui.Tables;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.abed.quandoo.R;
import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.data.model.Table;
import com.abed.quandoo.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablesActivity extends BaseActivity implements TablesMvpView {

    @Inject
    TablesPresenter mTablesPresenter;
    @Inject
    TablesAdapter tablesAdapter;
    @BindView(R.id.progress_loading)
    ProgressBar progress_loading;
    @BindView(R.id.rv_items)
    RecyclerView recyclerItems;


    private final static String EXTRA_CUSTOMER = "EXTRA_CUSTOMER";

    public static void startActivity(Context context, Customer customer) {
        Intent intent = new Intent(context, TablesActivity.class);
        //TODO: The customer info is being passed without beign used. I believe there should be an API that link the reserved table to the customer. I will keep the customer here until the api is ready
        intent.putExtra(EXTRA_CUSTOMER, customer);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_tables);
        ButterKnife.bind(this);
        mTablesPresenter.attachView(this);
        mTablesPresenter.loadData();
        tablesAdapter.setClicksListener((view, position, table) -> {
            mTablesPresenter.markTableAsReserved(table);
        });
        recyclerItems.setAdapter(tablesAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTablesPresenter.detachView();
    }

    @Override
    public void showTables(List<Table> tables) {
        if (tables.size() > 0)
            progress_loading.setVisibility(View.GONE);
        tablesAdapter.updateList(tables);
    }

    @Override
    public void refreshData() {
        tablesAdapter.notifyDataSetChanged();
    }
}
