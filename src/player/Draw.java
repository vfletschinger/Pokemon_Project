package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import player.*;
import power.*;

import java.util.Random;


public class Draw {
    private List<Pokemon> _pokemonDraw = new ArrayList<>();

    // Take an ArrayList of 15 or 16 names of Pokemons
    public Draw(List<String> nameList, List<Power> powerList){
        Random random = new Random();
        if (nameList.size() == 16) {
            for (int i = 0; i < 16; i++) {
                if (random.nextInt(0, 4) == 1 && !(powerList.isEmpty())){
                    Power power = powerList.get(random.nextInt(0,powerList.size()));
                    _pokemonDraw.add(new Pokemon(nameList.get(i), power));
                    powerList.remove(power);
                }
                else {
                    _pokemonDraw.add(new Pokemon(nameList.get(i)));
                }
            }
        }
        else{
            for (int i = 0; i < 15; i++) {
                _pokemonDraw.add(new Pokemon(nameList.get(i)));
            }
        }
    }
    public void deletePokemonOfDraw(Pokemon pokemon){
        _pokemonDraw.remove(pokemon);
    }

    public List<Pokemon> getPokemonDraw() {
        return _pokemonDraw;
    }
}
