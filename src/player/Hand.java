package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import player.*;
public class Hand {

    private final List<Pokemon> _pokemonHand = new ArrayList<>();

    // Take an ArrayList of 5 names of Pokemons
    public Hand(List<String> nameList){
        for(int i = 0; i < 5; i++){
            _pokemonHand.add(new Pokemon(nameList.get(i)));
        }
    }

    public void addPokemonToHand(Pokemon pokemon){
        if(_pokemonHand.size() < 5){
         _pokemonHand.add(pokemon);
        }
    }
    public void deletePokemonOfHand(Pokemon pokemon){
        _pokemonHand.remove(pokemon);
    }

    public List<Pokemon> getPokemonHand() {
        return _pokemonHand;
    }

}
