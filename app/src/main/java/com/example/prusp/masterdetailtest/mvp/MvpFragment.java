package com.example.prusp.masterdetailtest.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.prusp.masterdetailtest.base.BaseFragment;
import com.example.prusp.masterdetailtest.base.BasePresenter;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
