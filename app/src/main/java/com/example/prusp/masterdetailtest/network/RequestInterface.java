package com.example.prusp.masterdetailtest.network;

import com.example.prusp.masterdetailtest.model.PokeTypes;
import com.example.prusp.masterdetailtest.model.Pokemon;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Piotr Prus on 24.08.2017.
 */

public interface RequestInterface {

    @GET("pokemon")
    Observable<JSONResponse> getJSON(@Query("limit") String number);

    @GET("pokemon/{id}")
    Observable<Pokemon.PokemonDetail> getPokeDetail(@Path("id") String id);

    @GET("type/{id}")
    Observable<PokeTypes> getTypes(@Path("id") String typeId);
}
