package com.example.bai1_day14;

public class PokemonViewModel {
    private Pokemon pokemon;

    public PokemonViewModel(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getName()
    {
        return this.pokemon.getName();
    }

    public String getId()
    {
        return String.format(this.pokemon.getId());
    }

    public String getColor ()
    {
        return  this.pokemon.getColor();
    }
}
