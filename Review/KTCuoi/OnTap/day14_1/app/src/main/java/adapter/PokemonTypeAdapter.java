package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day14_1.databinding.PokemonTypeItemBinding;

import java.util.List;

import view_model.PokemonTypeViewModel;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder> {
    private final Context context;
    private List<String> typeItems;

    public PokemonTypeAdapter(Context context, List<String> type_items) {
        this.context = context;
        typeItems = type_items;
    }

    @NonNull
    @Override
    public PokemonTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonTypeItemBinding pokemonTypeItemBinding = PokemonTypeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new PokemonTypeViewHolder(pokemonTypeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeViewHolder holder, int position) {
            String type = typeItems.get(position);
            PokemonTypeViewModel pokemonTypeViewModel = new PokemonTypeViewModel(type);
            holder.pokemonTypeItemBinding.setPokemonTypeVm(pokemonTypeViewModel);
    }

    @Override
    public int getItemCount() {
        return typeItems.size();
    }

    public  class PokemonTypeViewHolder extends RecyclerView.ViewHolder{
        PokemonTypeItemBinding pokemonTypeItemBinding;

        public PokemonTypeViewHolder(@NonNull PokemonTypeItemBinding binding) {
            super(binding.getRoot());
            this.pokemonTypeItemBinding = binding;
        }
    }
}
