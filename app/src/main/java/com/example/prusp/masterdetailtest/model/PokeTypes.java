package com.example.prusp.masterdetailtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr Prus on 29.08.2017.
 */

public class PokeTypes {
    @SerializedName("damage_relations")
    DamageRelations damageRelations;

    public DamageRelations getDamageRelations() {
        return damageRelations;
    }
}
