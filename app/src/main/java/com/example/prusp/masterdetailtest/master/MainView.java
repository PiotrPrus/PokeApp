package com.example.prusp.masterdetailtest.master;

import android.content.Intent;

import com.example.prusp.masterdetailtest.network.JSONResponse;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public interface MainView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(JSONResponse list);

    void getDataFail(String message);

    void moveToDetail(Intent intent, int id);
}
