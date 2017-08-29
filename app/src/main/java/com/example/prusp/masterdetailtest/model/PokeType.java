package com.example.prusp.masterdetailtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr Prus on 29.08.2017.
 */

public class PokeType {
    @SerializedName("name")
    private String typeName;
    @SerializedName("url")
    private String url;

    public String getTypeName() {
        return typeName;
    }

    public String getUrl() {
        return url;
    }
}
