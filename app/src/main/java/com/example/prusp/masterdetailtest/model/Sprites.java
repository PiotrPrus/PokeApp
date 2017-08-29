package com.example.prusp.masterdetailtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr Prus on 28.08.2017.
 */

public class Sprites {
    @SerializedName("back_default")
    String backDefaultImage;
    @SerializedName("front_default")
    String frontDefaultImage;

    public Sprites(String backDefaultImage, String frontDefaultImage) {
        this.backDefaultImage = backDefaultImage;
        this.frontDefaultImage = frontDefaultImage;
    }

    public String getBackDefaultImage() {
        return backDefaultImage;
    }

    public String getFrontDefaultImage() {
        return frontDefaultImage;
    }
}
