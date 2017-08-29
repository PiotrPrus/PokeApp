package com.example.prusp.masterdetailtest.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.prusp.masterdetailtest.R;
import com.example.prusp.masterdetailtest.utils.FragmentUtils;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class DetailActivity extends AppCompatActivity {


    private static final int DEFAULT_POKE_ID = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_detail_activity);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(DetailFragment.ARG_ITEM_ID, getIntent().
                    getIntExtra("id", DEFAULT_POKE_ID));
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            FragmentUtils.addFragment(getSupportFragmentManager(),
                    fragment, R.id.pokemon_detail_container);
        }
    }
}
