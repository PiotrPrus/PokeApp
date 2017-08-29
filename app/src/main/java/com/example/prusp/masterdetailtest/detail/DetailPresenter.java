package com.example.prusp.masterdetailtest.detail;

import com.example.prusp.masterdetailtest.base.BasePresenter;
import com.example.prusp.masterdetailtest.model.PokeTypes;
import com.example.prusp.masterdetailtest.model.Pokemon;
import com.example.prusp.masterdetailtest.network.NetworkCallback;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    public DetailPresenter(DetailView view) {
        super.attachView(view);
    }

    void loadData(String id) {
        view.showLoading();
        addSubscribe(request.getPokeDetail(id), new NetworkCallback<Pokemon.PokemonDetail>() {
            @Override
            public void onSuccess(Pokemon.PokemonDetail detail) {
                view.getDataSuccess(detail);
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

    void loadTypes(String typeId) {
        addSubscribe(request.getTypes(typeId), new NetworkCallback<PokeTypes>() {
            @Override
            public void onSuccess(PokeTypes pokeTypes) {
                view.getDataSuccess(pokeTypes);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
