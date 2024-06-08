package com.example.bai01;

import android.graphics.Color;

public class PokemonViewModel {
    private Pokemon pokemon;

    public PokemonViewModel(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    public String getName()
    {
        return this.pokemon.getName();
    }

    public String getUrlImg ()
    {
        return this.pokemon.getImage();
    }
    public String getId()
    {
        return String.format(this.pokemon.getId());
    }
    public int getBackgroundColor() {
        // Assuming the color string is in the format "#RRGGBB" or "#AARRGGBB"
        return Color.parseColor(this.pokemon.getBackgroundColor());
    }
}
