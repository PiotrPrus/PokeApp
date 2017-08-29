package com.example.prusp.masterdetailtest.master;

import android.app.Activity;
import android.content.Intent;

import com.example.prusp.masterdetailtest.base.BasePresenter;
import com.example.prusp.masterdetailtest.detail.DetailActivity;
import com.example.prusp.masterdetailtest.network.JSONResponse;
import com.example.prusp.masterdetailtest.network.NetworkCallback;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        super.attachView(view);
    }

    void loadData(String number) {
        view.showLoading();
        addSubscribe(request.getJSON(number), new NetworkCallback<JSONResponse>() {

            @Override
            public void onSuccess(JSONResponse model) {
                view.getDataSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    void getPoke(Activity activity, int pokemonId) {
        Intent intent = new Intent(activity, DetailActivity.class);
        pokemonId = pokemonId + 1;
        intent.putExtra("id", pokemonId);
        view.moveToDetail(intent, pokemonId);
    }
}
