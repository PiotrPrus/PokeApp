package com.example.prusp.masterdetailtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prusp.masterdetailtest.model.Pokemon;

import java.util.List;

/**
 * Created by Piotr Prus on 27.08.2017.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private List<Pokemon> pokemonList;
    private int simpleRow;

    public PokemonAdapter(List<Pokemon> pokemonList, int simpleRow) {
        this.pokemonList = pokemonList;
        this.simpleRow = simpleRow;
    }

    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(simpleRow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.pokemonIdTextView.setText(String.valueOf(holder.getAdapterPosition() + 1));
        holder.pokemonNameTextView.setText(pokemonList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null && !pokemonList.isEmpty()) {
            return pokemonList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        private TextView pokemonNameTextView;
        private TextView pokemonIdTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            pokemonNameTextView = itemView.findViewById(R.id.pokemon_name_in_list_text_view);
            pokemonIdTextView = itemView.findViewById(R.id.id_list_text_view);

        }
    }
}
