package com.abed.quandoo;


import com.abed.quandoo.controller.EventBusHelper;
import com.abed.quandoo.data.DataManager;
import com.abed.quandoo.data.RxBus;
import com.abed.quandoo.data.local.DatabaseHelper;
import com.abed.quandoo.data.model.Customer;
import com.abed.quandoo.data.remote.ApiHelper;
import com.abed.quandoo.data.remote.QuandooService;
import com.abed.quandoo.ui.Main.MainMvpView;
import com.abed.quandoo.ui.Main.MainPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainMvpView mMockMainMvpView;
    @Mock
    DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Before
    public void setUp() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        EventBusHelper eventBusHelper = new EventBusHelper(new RxBus());

        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        QuandooService quandooService = retrofit.create(QuandooService.class);
        ApiHelper apiHelper = new ApiHelper(quandooService, databaseHelper, eventBusHelper);

        mMainPresenter = new MainPresenter(new DataManager(databaseHelper, eventBusHelper, apiHelper));
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Customer> customers = makeListCustomer(10);
        mMockMainMvpView.showCustomers(customers);
    }


    private List<Customer> makeListCustomer(int number) {
        List<Customer> ribots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribots.add(makeCustomer());
        }
        return ribots;
    }

    private Customer makeCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerFirstName("First");
        customer.setCustomerLastName("Last");
        return customer;

    }


}
