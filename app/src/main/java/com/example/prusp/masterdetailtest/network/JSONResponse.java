package com.example.prusp.masterdetailtest.network;

import com.example.prusp.masterdetailtest.model.Pokemon;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Piotr Prus on 24.08.2017.
 */

public class JSONResponse {
    @SerializedName("results")
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
