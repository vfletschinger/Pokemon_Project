package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.*;

import java.util.Random;


public class Draw {
    private final List<Pokemon> _pokemons = new ArrayList<>();

    // Constructor takes an ArrayList of 15 or 16 names of Pokemons
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

    /* takeNext : function : Pokemon : returns the next Pokemon of the draw
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Pokemon : the next Pokemon of the draw
     */
    public Pokemon takeNext(){
        if(!_pokemons.isEmpty())
            return this._pokemons.removeFirst();
        else
            return null;
    }

    /* isEmpty : function : Boolean : returns true if the draw is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the draw is empty
     */
    public boolean isEmpty(){
        return _pokemons.isEmpty();
    }

    /* toString : function : String : returns a recap of the class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : recap of the class
     */
    public String toString(){
        String res = "";
        for (Pokemon pokemon : _pokemons){
            res += pokemon + " ";
        }
        return res;
    }
}
