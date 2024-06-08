package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.day13.Pokemon;
import com.example.day13.PokemonTypeViewModel;
import com.example.day13.PokemonViewModel;
import com.example.day13.databinding.PokemonTypeListBinding;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder>
{
    private final Context context;
    private List<String> typeItems;

    public PokemonTypeAdapter(Context context, List<String> type_items) {
        this.context = context;
        typeItems = type_items;
    }
    @NonNull
    @Override
    public PokemonTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonTypeListBinding pokemonTypeListBinding = PokemonTypeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new PokemonTypeViewHolder(pokemonTypeListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeViewHolder holder, int position) {
        String type = typeItems.get(position);
        PokemonTypeViewModel pokemonTypeViewModel = new PokemonTypeViewModel(type);
        holder.pokemonTypeListBinding.setPokemonTypeVm(pokemonTypeViewModel);
//        holder.pokemonTypeListBinding.setPokemonTypeVm(new PokemonTypeViewModel(typeItems.get(position)));
     //   Log.d("PokemonTypeAdapter", "Type: " + type);

    }

    @Override
    public int getItemCount() {
        return typeItems.size();
    }

    public  class PokemonTypeViewHolder extends RecyclerView.ViewHolder{
        PokemonTypeListBinding pokemonTypeListBinding;
        public PokemonTypeViewHolder(@NonNull PokemonTypeListBinding binding) {
            super(binding.getRoot());
            this.pokemonTypeListBinding = binding;
        }
    }
}
