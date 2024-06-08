package view_model;

import model.Pokemon;

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
    public String getBackground(){return this.pokemon.getBackgroundColor();}
}
