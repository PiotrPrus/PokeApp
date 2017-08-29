package com.example.prusp.masterdetailtest.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.prusp.masterdetailtest.base.BaseActivity;
import com.example.prusp.masterdetailtest.base.BasePresenter;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
