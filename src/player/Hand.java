package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.*;
import java.util.Random;

public class Hand {

    private final List<Pokemon> _pokemons = new ArrayList<>();

    //Constructor takes an ArrayList of 5 names of Pokemons
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

    /* displayHand : function : String : returns the display of the hand
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the display of the hand
     */
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

    /* add : function : void : add a Pokemon to the hand
     * param :
     *  pokemon : Pokemon : Pokemon to add
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void add(Pokemon pokemon){
        if(_pokemons.size() < 5){
         _pokemons.add(pokemon);
        }
    }

    /* get : function : Pokemon : returns the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon to return
     * local :
     *  NONE
     * return :
     *  Pokemon : Pokemon at a certain index
     */
    public Pokemon get(Integer index){
        return this._pokemons.get(index);
    }

    /* getName : function : String : returns the name of the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  String : name of the Pokemon at a certain index
     */
    public String getName(Integer index){
        return get(index).getName();
    }

    /* getLife : function : Integer : returns the life of the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  Integer : life of the Pokemon at a certain index
     */
    public Integer getLife(Integer index){
        return get(index).getLife();
    }

    /* getAttack : function : Integer : returns the attack of the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  Integer : attack of the Pokemon at a certain index
     */
    public Integer getAttack(Integer index){
        return get(index).getAttack();
    }

    /* getType : function : PokemonType : returns the type of the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  PokemonType : type of the Pokemon at a certain index
     */
    public PokemonType getType(Integer index){
        return get(index).getAffinity();
    }

    /* size : function : Integer : returns the size of the hand
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : size of the hand
     */
    public Integer size(){
        return this._pokemons.size();
    }

    /* isEmpty : function : Boolean : returns true if the hand is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the hand is empty
     */
    public boolean isEmpty(){
        return this._pokemons.isEmpty();
    }

    /* takeNext : function : Pokemon : returns the next Pokemon ot the hand
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Pokemon : the next Pokemon of the hand
     */
    public Pokemon takeNext() {
        if(!_pokemons.isEmpty())
            return this._pokemons.removeFirst();
        else
            return null;
    }

    /* removePokemon : function : void : remove the Pokemon of the hand
     * param :
     *  pokemon : Pokemon : Pokemon to remove
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void removePokemon(Pokemon pokemon){
        _pokemons.remove(pokemon);
    }

}
