package com.example.prusp.masterdetailtest.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class FragmentUtils {
    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment,
                                   int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
}
