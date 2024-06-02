package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;

public abstract class Player {
    private Battlefield _battlefield;
    private Draw _draw;
    private Hand _hand;
    private List<Pokemon> _pokemonDiscard = new ArrayList<>();
    public Player(Draw draw, Hand hand){
        _draw = draw;
        _hand = hand;
        _battlefield = new Battlefield();
    }
    public String displayDecks(){
        String display = "";

        display += "pioche: " + sizeOfDraw() + "pokemons\n";
        display += "defausse: " + sizeOfDiscard() + "pokemons\n\n";

        return display;
    }
    public Boolean hasLost(){ return (handIsEmpty() && battlefieldIsEmpty()); }
    public void addPokemonToDiscard(Pokemon pokemon){
        _pokemonDiscard.add(pokemon);
    }
    public abstract void turn(Player opponent);
    public Hand getHand (){
        return this._hand;
    }
    public Draw getDraw (){
        return this._draw;
    }
    public Pokemon getPokemonOnHand(Integer index){
        return this._hand.get(index);
    }
    public String getPokemonNameOnHand(Integer index){
        return this._hand.getName(index);
    }
    public PokemonType getPokemonTypeOnHand(Integer index){
        return this._hand.getType(index);
    }
    public Integer getPokemonLifeOnHand(Integer index){
        return this._hand.getLife(index);
    }
    public Integer getPokemonAttackOnHand(Integer index){
        return this._hand.getAttack(index);
    }
    public Pokemon takeNextPokemonOnHand(){
        return this._hand.takeNext();
    }
    public Integer sizeOfHand(){
        return this._hand.size();
    }
    public void addPokemonOnHand(Pokemon pokemon){
        this._hand.add(pokemon);
    }
    public boolean handIsEmpty(){
        return this._hand.isEmpty();
    }
    public String displayHand(){ return this._hand.displayHand(); }
    public Pokemon getPokemonOnBattlefield(Integer index){
        return this._battlefield.get(index);
    }
    public String getPokemonNameOnBattlefield(Integer index) { return this._battlefield.getName(index); }
    public void removePokemonOnBattlefield(Integer index){
        this._battlefield.remove(index);
    }
    public void removePokemonOnBattlefield(Pokemon pokemon){
        this._battlefield.remove(pokemon);
    }
    public void addPokemonOnBattlefield(Pokemon pokemon){
        this._battlefield.add(pokemon);
    }
    public Integer sizeOfBattlefield(){
        return this._battlefield.size();
    }
    public String getPokemonTypeNameOnHand(Integer index){
        return this._hand.getTypeName(index);
    }
    public void removePokemonOnHand(Pokemon pokemon){
        this._hand.removePokemon(pokemon);
    }
    public boolean battlefieldIsEmpty(){
        return this._battlefield.isEmpty();
    }
    public boolean drawIsEmpty(){ return this._draw.isEmpty(); }
    public Pokemon takeNextPokemonOnDraw(){
        return this._draw.takeNext();
    }
    public Integer sizeOfDraw(){
        return this._draw.size();
    }
    public String displayBattlefield(){
        return this._battlefield.display();
    }
    public Integer sizeOfDiscard(){
        return this._pokemonDiscard.size();
    }
    public List<Pokemon> getBattlefieldPokemons(){
        return this._battlefield.getPokemonList();
    }
    public void usePokemonPower(Pokemon pokemonAttacker, Pokemon opponentPokemon){ pokemonAttacker.usePower(opponentPokemon); }

}
