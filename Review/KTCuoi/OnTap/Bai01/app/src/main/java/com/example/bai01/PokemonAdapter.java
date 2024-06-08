package com.example.bai01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bai01.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private final Context context;
    private List<Pokemon> items;

    public PokemonAdapter(Context context, List<Pokemon> items) {
        this.context = context;
        this.items = items;
    }

//    public void updateList(List<Pokemon> items)
//    {
//        this.items = items;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding pokemonItemBinding = PokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokemonViewHolder(pokemonItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = items.get(position);
        PokemonViewModel pokemonViewModel = new PokemonViewModel(pokemon);
        holder.pokemonItemBinding.setPokemonVm(pokemonViewModel);
        holder.bind(pokemon);
        Log.d("PokemonAdapter", "Pokemon: " + pokemon);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder
    {
        PokemonItemBinding pokemonItemBinding;
        public PokemonViewHolder(@NonNull PokemonItemBinding binding) {
            super(binding.getRoot());
            this.pokemonItemBinding = binding;

        }
        public void bind(Pokemon pokemon)
        {
            pokemonItemBinding.setPokemonVm(new PokemonViewModel(pokemon));
            pokemonItemBinding.executePendingBindings();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    pokemonItemBinding.pokemonTypes.getContext(), LinearLayoutManager.HORIZONTAL, false);

            pokemonItemBinding.pokemonTypes.setLayoutManager(linearLayoutManager);

            PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(context, pokemon.getTypes());
            pokemonItemBinding.pokemonTypes.setAdapter(pokemonTypeAdapter);
        }
    }

    @BindingAdapter("ImageUrl")
    public static void loadImg(ImageView imageView, String url)
    {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
