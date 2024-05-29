package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import player.*;
import power.*;
import java.util.Random;

public class Hand {

    private final List<Pokemon> _pokemonHand = new ArrayList<>();

    // Take an ArrayList of 5 names of Pokemons
    public Hand(List<String> nameList, List<Power> powerList){
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            if (random.nextInt(0, 4) == 1 && !(powerList.isEmpty())){

                Power power = powerList.get(random.nextInt(0,powerList.size()));
                _pokemonHand.add(new Pokemon(nameList.get(i), power));
                powerList.remove(power);
            }
            else {
                _pokemonHand.add(new Pokemon(nameList.get(i)));
            }
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
