package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.*;
import java.util.Random;

public class Hand {

    private final List<Pokemon> _pokemons = new ArrayList<>();

    // Take an ArrayList of 5 names of Pokemons
    public Hand(List<String> nameList, List<Power> powerList){
        Random random = new Random();
        for(int i = 0; i < 5; i++){
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

    public String displayHand(){
        String display = "";

        display = "En main:\n";
        for(int i =0; i < size() ; i++){
            display += "- " + getName(i) + ", " + getType(i) + ", ";
            display += "Vie : " + getLife(i) + ", Attaque : " + getAttack(i) + "\n";
        }

        display += "\n";

        return display;
    }

    public void add(Pokemon pokemon){
        if(_pokemons.size() < 5){
         _pokemons.add(pokemon);
        }
    }
    public Pokemon get(Integer index){
        return this._pokemons.get(index);
    }
    public String getName(Integer index){
        return get(index).getName();
    }
    public Integer getLife(Integer index){
        return get(index).getLife();
    }
    public Integer getAttack(Integer index){
        return get(index).getAttack();
    }

    public PokemonType getType(Integer index){
        return get(index).getAffinity();
    }
    public Integer size(){
        return this._pokemons.size();
    }
    public boolean isEmpty(){
        return this._pokemons.isEmpty();
    }
    public Pokemon takeNext() {
        if(!_pokemons.isEmpty())
            return this._pokemons.removeFirst();
        else
            return null;
    }
    public void removePokemon(Pokemon pokemon){
        _pokemons.remove(pokemon);
    }

}
