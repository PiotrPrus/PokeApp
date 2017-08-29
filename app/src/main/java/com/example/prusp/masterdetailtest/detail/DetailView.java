package com.example.prusp.masterdetailtest.detail;

import com.example.prusp.masterdetailtest.model.PokeTypes;
import com.example.prusp.masterdetailtest.model.Pokemon;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public interface DetailView {
    void showLoading();

    void hideLoading();

    void getDataSuccess(Pokemon.PokemonDetail detail);

    void getDataFail(String message);

    void getDataSuccess(PokeTypes pokeTypes);
}
