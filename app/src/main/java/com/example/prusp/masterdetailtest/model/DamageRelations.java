package com.example.prusp.masterdetailtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Piotr Prus on 29.08.2017.
 */

public class DamageRelations {
    @SerializedName("half_damage_from")
    private List<PokeType> halfDamageFrom;
    @SerializedName("no_damage_from")
    private List<PokeType> noDamageFrom;
    @SerializedName("half_damage_to")
    private List<PokeType> halfDamageTo;
    @SerializedName("double_damage_from")
    private List<PokeType> doubleDamageFrom;
    @SerializedName("no_damage_to")
    private List<PokeType> noDamageTo;
    @SerializedName("double_damage_to")
    private List<PokeType> doubleDamageTo;

    public DamageRelations(List<PokeType> halfDamageFrom, List<PokeType> noDamageFrom,
                           List<PokeType> halfDamageTo, List<PokeType> doubleDamageFrom,
                           List<PokeType> noDamageTo, List<PokeType> doubleDamageTo) {
        this.halfDamageFrom = halfDamageFrom;
        this.noDamageFrom = noDamageFrom;
        this.halfDamageTo = halfDamageTo;
        this.doubleDamageFrom = doubleDamageFrom;
        this.noDamageTo = noDamageTo;
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<PokeType> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public List<PokeType> getNoDamageFrom() {
        return noDamageFrom;
    }

    public List<PokeType> getHalfDamageTo() {
        return halfDamageTo;
    }

    public List<PokeType> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public List<PokeType> getNoDamageTo() {
        return noDamageTo;
    }

    public List<PokeType> getDoubleDamageTo() {
        return doubleDamageTo;
    }
}
