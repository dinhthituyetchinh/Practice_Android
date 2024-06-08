package com.example.bai1_day14;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1_day14.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private final Context context;
    private List<Pokemon> items;

    public PokemonAdapter(Context context, List<Pokemon> items) {
        this.context = context;
        this.items = items;
    }

    public void updateList(List<Pokemon> items)
    {
        this.items = items;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding listItemPokemonBinding = PokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokemonViewHolder(listItemPokemonBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = items.get(position);
        PokemonViewModel pokemonViewModel = new PokemonViewModel(pokemon);
        holder.listItemPokemonBinding.setPokemonVm(pokemonViewModel);
        holder.bind(pokemon);
        Log.d("PokemonAdapter", "Pokemon: " + pokemon);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder
    {
        PokemonItemBinding listItemPokemonBinding;
        public PokemonViewHolder(@NonNull PokemonItemBinding binding) {
            super(binding.getRoot());
            this.listItemPokemonBinding = binding;

        }
        public void bind(Pokemon pokemon)
        {
            listItemPokemonBinding.setPokemonVm(new PokemonViewModel(pokemon));
            listItemPokemonBinding.executePendingBindings();

        }
    }

}

