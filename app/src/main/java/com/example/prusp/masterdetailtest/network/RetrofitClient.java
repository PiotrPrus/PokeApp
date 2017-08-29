package com.example.prusp.masterdetailtest.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class RetrofitClient {
    private static final String MAIN_URL = "http://pokeapi.co/api/v2/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
