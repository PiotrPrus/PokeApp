package com.example.prusp.masterdetailtest.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prusp.masterdetailtest.PokemonAdapter;
import com.example.prusp.masterdetailtest.R;
import com.example.prusp.masterdetailtest.detail.DetailFragment;
import com.example.prusp.masterdetailtest.model.Pokemon;
import com.example.prusp.masterdetailtest.mvp.MvpActivity;
import com.example.prusp.masterdetailtest.network.JSONResponse;
import com.example.prusp.masterdetailtest.utils.FragmentUtils;
import com.example.prusp.masterdetailtest.utils.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    private static final String SIZE_OF_POKEMON_LIST = "151";
    private boolean twoPane;

    @BindView(R.id.pokemon_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.error_loading_data_text_view)
    TextView errorTextView;


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (findViewById(R.id.pokemon_detail_container) != null) {
            twoPane = true;
        }
        presenter.loadData(SIZE_OF_POKEMON_LIST);
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getPoke(activity, position);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getPoke(activity, position);
            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDataSuccess(JSONResponse list) {
        List<Pokemon> pokemonList = list.getResults();
        recyclerView.setAdapter(new PokemonAdapter(pokemonList, R.layout.pokemon_list_content));
    }

    @Override
    public void getDataFail(String message) {
        errorTextView.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToDetail(Intent intent, int id) {
        if (twoPane) {
            setupDetailFragment(id);
        } else {
            startActivity(intent);
        }
    }

    private void setupDetailFragment(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(DetailFragment.ARG_ITEM_ID, id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        FragmentUtils.addFragment(getSupportFragmentManager(),
                fragment, R.id.pokemon_detail_container);
    }
}
