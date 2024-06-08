package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day13.Pokemon;
import com.example.day13.PokemonDatabaseManager;
import com.example.day13.PokemonViewModel;
import com.example.day13.R;
import com.example.day13.databinding.ListItemPokemonBinding;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private final Context context;
    private List<Pokemon> items;
    private  PokemonDatabaseManager dbManager;
    private boolean isFirstLoad = true;

    public PokemonAdapter(Context context, List<Pokemon> items) {
        this.context = context;
        this.items = items;
        this.dbManager = new PokemonDatabaseManager(context);
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
        if (!pokemon.getTypes().isEmpty()) {
            String firstType = pokemon.getTypes().get(0);
            int drawableResource = getDrawableResourceForType(firstType);
            if (drawableResource != 0) {
                Drawable backgroundDrawable = ContextCompat.getDrawable(holder.itemView.getContext(), drawableResource);
                pokemon.setBackground(backgroundDrawable);
            }
        }
        PokemonViewModel pokemonViewModel = new PokemonViewModel(pokemon);
        holder.listItemPokemonBinding.setPokemonVm(pokemonViewModel);
        holder.bind(pokemon);

        PokemonDatabaseManager db = new PokemonDatabaseManager(holder.listItemPokemonBinding.getRoot().getContext());
        holder.listItemPokemonBinding.executePendingBindings();
        if(db.getIDPokemon(pokemon.getId())==0)
        {
            holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.VISIBLE);
        }
        else {
            holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
        }
        holder.listItemPokemonBinding.downloadIcon.setOnClickListener(e -> {
            PokemonDatabaseManager manager = new PokemonDatabaseManager(context);
            manager.InsertPokemon(pokemon);
            holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
        });

//   //     Log.d("PokemonAdapter", "Pokemon: " + pokemon);
//
////        holder.listItemPokemonBinding.downloadIcon.setOnClickListener(e -> {
////            PokemonDatabaseManager manager = new PokemonDatabaseManager(context);
////            manager.InsertPokemon(pokemon);
////            holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
////        });
//
//        if (isFirstLoad) { // On first load, make the download icon clickable
//            holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.VISIBLE);
//            holder.listItemPokemonBinding.downloadIcon.setOnClickListener(e -> {
//                dbManager.InsertPokemon(pokemon);
//                holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
//            });
//        } else {
//            if (dbManager.isPokemonInDatabase(pokemon.getId())) {
//                holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
//            } else {
//                holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.VISIBLE);
//                holder.listItemPokemonBinding.downloadIcon.setOnClickListener(e -> {
//                    dbManager.InsertPokemon(pokemon);
//                    holder.listItemPokemonBinding.downloadIcon.setVisibility(ViewGroup.GONE);
//                });
//            }
//        }


    }
    private int getDrawableResourceForType(String type) {
        switch (type) {
            case "Grass":
                return R.color.Grass; // Replace with your actual drawable resource
            case "Fire":
                return R.color.Fire; // Replace with your actual drawable resource
            // Add more cases for other types as needed
            case "Water":
                return R.color.Water;
            default:
                return 0;
        }
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
