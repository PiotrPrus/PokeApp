package com.example.prusp.masterdetailtest.base;

import android.util.Log;

import com.example.prusp.masterdetailtest.network.RequestInterface;
import com.example.prusp.masterdetailtest.network.RetrofitClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class BasePresenter<V> {
    public V view;
    protected RequestInterface request;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void attachView(V view) {
        this.view = view;
        request = RetrofitClient.getRetrofit().create(RequestInterface.class);
    }

    public void dettachView() {
        this.view = null;
        onUnsubscribe();
    }

    private void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
