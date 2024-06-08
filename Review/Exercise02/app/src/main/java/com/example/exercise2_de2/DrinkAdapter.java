package com.example.exercise2_de2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    private List<Drink> drinks;
    private Context context;

    public DrinkAdapter(Context context, List<Drink> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drink, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.drinkName.setText(drink.getStrDrink());
        Picasso.get().load(drink.getStrDrinkThumb()).into(holder.drinkImage);

        holder.itemView.setOnClickListener(v -> {
            // Handle click event to open drink detail activity
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView drinkImage;
        TextView drinkName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkImage = itemView.findViewById(R.id.drink_image);
            drinkName = itemView.findViewById(R.id.drink_name);
        }
    }
}
