package com.example.prusp.masterdetailtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Piotr Prus on 24.08.2017.
 */

public class Pokemon {

    @SerializedName("name")
    String name;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public class PokemonDetail {
        @SerializedName("height")
        private int height;
        @SerializedName("weight")
        private int weight;
        @SerializedName("name")
        private String name;
        @SerializedName("base_experience")
        private int baseExp;
        @SerializedName("sprites")
        Sprites sprites;
        @SerializedName("types")
        ArrayList<Types> types;

        public PokemonDetail(int height, int weight, String name, int baseExp, Sprites sprites, ArrayList<Types> types) {
            this.height = height;
            this.weight = weight;
            this.name = name;
            this.baseExp = baseExp;
            this.sprites = sprites;
            this.types = types;
        }

        public int getHeight() {
            return height;
        }

        public int getWeight() {
            return weight;
        }

        public String getName() {
            return name;
        }

        public int getBaseExp() {
            return baseExp;
        }

        public Sprites getSprites() {
            return sprites;
        }

        public ArrayList<Types> getTypes() {
            return types;
        }
    }

    public class Types {
        @SerializedName("type")
        PokeType pokeType;

        public PokeType getPokeType() {
            return pokeType;
        }
    }

}
