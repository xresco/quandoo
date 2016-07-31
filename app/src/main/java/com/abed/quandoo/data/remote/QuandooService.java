package com.abed.quandoo.data.remote;

import com.abed.quandoo.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface QuandooService {

    @GET("customer-list.json")
    Observable<List<API_Customer>> getCustomers();

    @GET("table-map.json")
    Observable<List<Boolean>> getTables();


    /********
     * Helper class that sets up a new services
     *******/
     class Creator {
        public static QuandooService newQuandooService() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(QuandooService.class);
        }
    }

}
