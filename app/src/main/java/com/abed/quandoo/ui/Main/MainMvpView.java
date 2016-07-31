package com.abed.quandoo.ui.Main;

import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showCustomers(List<Customer> customers);

}
