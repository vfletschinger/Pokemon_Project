import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private static final List<String> _pokemonList = new ArrayList<>();
    private final String _name;

    // We'll need to add the "PokemonType" enumeration (it will be a merge request on its own)
    private final String _affinity;
    private Integer _life;
    private final Integer _attack;

    /* Pokemon : constructor : it creates an instance of the Pokemon class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public Pokemon(){

        // We'll need to do a "Random" function/class to randomize the name and the affinity
        _name = _pokemonList.get(0);
        _affinity = "Air"; // we have to change it
        _life = 100;
        _attack = 50;

    }

    /* attackPokemon : function : void : when a Pokemon attacks another one, it will do damage so
     * in consequence the targeted pokemon's life will decrease
     * param :
     *  pokemon : Pokemon : an instance of a Pokemon class, it's the targeted pokemon
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void attackPokemon(Pokemon pokemon){
        pokemon._life -= this._attack;
    }

    /* getName : function : Integer : it gets you the name of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._name : the name of a Pokemon instance
     */
    public String getName(){
        return this._name;
    }

    /* getAffinity : function : Integer : it gets you the affinity of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._affinity : the affinity of a Pokemon instance
     */
    public String getAffinity(){
        return this._affinity;
    }

    /* getLife : function : Integer : it gets you the life of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._life : the life of a Pokemon instance
     */
    public Integer getLife(){
        return this._life;
    }

    /* getAttack : function : Integer : it gets you the attack of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._attack : the attack of a Pokemon instance
     */
    public Integer getAttack(){
        return this._attack;
    }

    // We need to change the uml -> visibility need to ne private for _pokemonList
    public static void setPokemonList(String name){
        Pokemon._pokemonList.add(name);
    }
}
