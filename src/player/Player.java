package player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pokemon.*;

public abstract class Player {
    private Battlefield _battlefield;
    private Draw _draw;
    private Hand _hand;
    private List<Pokemon> _pokemonDiscard = new ArrayList<>();

    //Constructor
    public Player(Draw draw, Hand hand){
        _draw = draw;
        _hand = hand;
        _battlefield = new Battlefield();
    }

    /* hasLost : function : Boolean : returns true if the player has lost
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the player has lost
     */
    public Boolean hasLost(){ return (handIsEmpty() && battlefieldIsEmpty()); }

    /* addPokemonToDiscard : function : void : add a Pokemon to the discard
     * param :
     *  pokemon : Pokemon : Pokemon to add to the discard
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addPokemonToDiscard(Pokemon pokemon){
        _pokemonDiscard.add(pokemon);
    }

    /* turn : function : void : Play the turn of the player
     * param :
     *  opponent : Player : Player who plays the turn
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public abstract void turn(Player opponent);

    /* getPokemonOnHand : function : Pokemon : returns the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  Pokemon : Pokemon at a certain index
     */
    public Pokemon getPokemonOnHand(Integer index){
        return this._hand.get(index);
    }

    /* getPokemonNameOnHand : function : String : returns the name of the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  String : name of the Pokemon at a certain index
     */
    public String getPokemonNameOnHand(Integer index){
        return this._hand.getName(index);
    }

    /* takeNextPokemonOnHand : function : Pokemon : returns the next Pokemon of the hand
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Pokemon : the next Pokemon of the hand
     */
    public Pokemon takeNextPokemonOnHand(){
        return this._hand.takeNext();
    }

    /* sizeOfHand : function : Integer : returns the size of the hand
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : size of the hand
     */
    public Integer sizeOfHand(){
        return this._hand.size();
    }

    /* addPokemonOnHand : function : void : add a Pokemon to the hand
     * param :
     *  pokemon : Pokemon : Pokemon to add to the hand
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addPokemonOnHand(Pokemon pokemon){
        this._hand.add(pokemon);
    }

    /* handIsEmpty : function : Boolean : returns true if the hand is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the hand is empty
     */
    public boolean handIsEmpty(){
        return this._hand.isEmpty();
    }

    /* displayHand : function : String : returns the hand of the player
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the hand of the player
     */
    public String displayHand(){ return this._hand.displayHand(); }

    /* getPokemonOnBattlefield : function : Pokemon : returns the Pokemon from the battlefield at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  Pokemon : Pokemon from the battlefield at a certain index
     */
    public Pokemon getPokemonOnBattlefield(Integer index){
        return this._battlefield.get(index);
    }

    /* getPokemonNameOfBattlefield : function : String : returns the name of the Pokemon from the battlefield at a certain index
     * param :
     *  index : Integer : index of the Pokemon
     * local :
     *  NONE
     * return :
     *  String : name of the Pokemon from the battlefield at a certain index
     */
    public String getPokemonNameOnBattlefield(Integer index) { return this._battlefield.getName(index); }

    /* removePokemonOnBattlefield : function : void : remove the Pokemon from the battlefield
     * param :
     *  pokemon : Pokemon : Pokemon to remove from the battlefield
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void removePokemonOnBattlefield(Pokemon pokemon){
        this._battlefield.remove(pokemon);
    }

    /* addPokemonOnBattlefield : function : void : add the Pokemon to the battlefield
     * param :
     *  pokemon : Pokemon : Pokemon to add to the battlefield
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addPokemonOnBattlefield(Pokemon pokemon){
        this._battlefield.add(pokemon);
    }

    /* sizeOfBattlefield : function : Integer : returns the size of the battlefield
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : the size of the battlefield
     */
    public Integer sizeOfBattlefield(){
        return this._battlefield.size();
    }

    /* removePokemonOnHand : function : void : remove the Pokemon from the hand
     * param :
     *  pokemon : Pokemon : Pokemon to remove from the hand
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void removePokemonOnHand(Pokemon pokemon){
        this._hand.removePokemon(pokemon);
    }

    /* battlefieldIsEmpty : function : Boolean : returns true if the battlefield is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the battlefield is empty
     */
    public boolean battlefieldIsEmpty(){
        return this._battlefield.isEmpty();
    }

    /* drawIsEmpty : function : Boolean : returns true if the draw is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the draw is empty
     */
    public boolean drawIsEmpty(){ return this._draw.isEmpty(); }

    /* takeNextPokemonOnDraw : function : Pokemon : returns the next Pokemon of the draw
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Pokemon : the next Pokemon of the draws
     */
    public Pokemon takeNextPokemonOnDraw(){
        return this._draw.takeNext();
    }

    /* displayBattlefield : function : String : returns the display of the battlefield
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the display of the battlefield
     */
    public String displayBattlefield(){
        return this._battlefield.display();
    }

    /* getBattlefieldPokemons : function : List<Pokemon> : returns the list of the Pokemons on the battlefield
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  List<Pokemon> : the list of the Pokemons on the battlefield
     */
    public List<Pokemon> getBattlefieldPokemons(){
        return this._battlefield.getPokemonList();
    }

    /* usePokemonPower : function : void : uses the power of the Pokemon
     * param :
     *  pokemonAttacker : Pokemon : Pokemon that uses the its power
     *  opponentPokemon : Pokemon : Pokemon on which the power is used
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void usePokemonPower(Pokemon pokemonAttacker, Pokemon opponentPokemon){ pokemonAttacker.usePower(opponentPokemon); }

    /* inputContinue : function : void : when the player input is
     * param :
     *  opponent : Player : opponent Player
     *  pokemonAttacker : Pokemon : Pokemon who uses its power
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void inputContinue(){
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public String toString (){
        String res = "BATTLEFIELD : ";
        res += _battlefield.toString() + "\nHAND : ";
        res += _hand.toString() + "\nDRAW : ";
        res += _draw.toString() + "\nDiscard : ";
        for (Pokemon pokemon : _pokemonDiscard){
            res += pokemon + " ";
        }
        return res;
    }
}
