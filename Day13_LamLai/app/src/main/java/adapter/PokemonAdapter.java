package adapter;

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
import com.example.day13.Pokemon;
import com.example.day13.PokemonViewModel;
import com.example.day13.databinding.ListItemPokemonBinding;

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
        ListItemPokemonBinding listItemPokemonBinding = ListItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        ListItemPokemonBinding listItemPokemonBinding;
        public PokemonViewHolder(@NonNull ListItemPokemonBinding binding) {
            super(binding.getRoot());
            this.listItemPokemonBinding = binding;

        }
        public void bind(Pokemon pokemon)
        {
            listItemPokemonBinding.setPokemonVm(new PokemonViewModel(pokemon));
            listItemPokemonBinding.executePendingBindings();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    listItemPokemonBinding.pokemonTypes.getContext(), LinearLayoutManager.HORIZONTAL, false);

            listItemPokemonBinding.pokemonTypes.setLayoutManager(linearLayoutManager);

            PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(context, pokemon.getTypes());
            listItemPokemonBinding.pokemonTypes.setAdapter(pokemonTypeAdapter);
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
