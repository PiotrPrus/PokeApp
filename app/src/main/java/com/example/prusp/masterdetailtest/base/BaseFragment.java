package com.example.prusp.masterdetailtest.base;

import android.support.v4.app.Fragment;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class BaseFragment extends Fragment {
    private CompositeSubscription compositeSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    private void onUnsubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }
}
