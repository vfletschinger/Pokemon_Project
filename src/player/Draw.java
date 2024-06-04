package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.*;

import java.util.Random;


public class Draw {
    private final List<Pokemon> _pokemons = new ArrayList<>();

    // Take an ArrayList of 15 or 16 names of Pokemons
    public Draw(List<String> nameList, List<Power> powerList){
        Random random = new Random();
        if (nameList.size() == 16) {
            for (int i = 0; i < 16; i++) {
                if (random.nextInt(0, 4) == 1 && !(powerList.isEmpty())){
                    Power power = powerList.get(random.nextInt(0,powerList.size()));
                    _pokemons.add(new Pokemon(nameList.get(i), power));
                    powerList.remove(power);
                }
                else {
                    _pokemons.add(new Pokemon(nameList.get(i)));
                }
            }
        }
        else{
            for (int i = 0; i < 15; i++) {
                _pokemons.add(new Pokemon(nameList.get(i)));
            }
        }
    }
    public Pokemon takeNext(){
        if(!_pokemons.isEmpty())
            return this._pokemons.removeFirst();
        else
            return null;
    }

    public boolean isEmpty(){
        return _pokemons.isEmpty();
    }
}
