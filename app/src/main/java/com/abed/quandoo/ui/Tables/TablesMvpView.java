package com.abed.quandoo.ui.Tables;

import com.abed.quandoo.data.model.Table;
import com.abed.quandoo.ui.base.MvpView;

import java.util.List;

public interface TablesMvpView extends MvpView {

    void showTables(List<Table> tables);
    void refreshData();

}
