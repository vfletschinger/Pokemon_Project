import java.util.ArrayList;
import java.util.List;

public class Player {
    protected Battlefield _battlefield;
    protected Draw _draw;
    protected Hand _hand;
    protected List<Pokemon> _pokemonDiscard = new ArrayList<>();

    public Player(Draw draw, Hand hand){
        _draw = draw;
        _hand = hand;
        _battlefield = new Battlefield();
    }
    public void choicePokemonBattlefield(int index){
        _battlefield.addPokemonToBattlefield(_hand.getPokemonHand().remove(index));
		_hand.addPokemonToHand(_draw.getPokemonDraw().remove(index));
    }
    public String displayDecks(){
        String display = "";

        display += "pioche: " + _draw.getPokemonDraw().size() + "pokemons\n";
        display += "defausse: " + _pokemonDiscard.size() + "pokemons\n\n";

        return display;
    }

    public Boolean hasLost(){
        return true;
    }

    public void addPokemonToDiscard(Pokemon pokemon){
        _pokemonDiscard.add(pokemon);
    }

    // displayHand de User
    public String displayHand(){
        String display = "";

        display = "En main:\n";
        for(int i =0; i < _hand.getPokemonHand().size() ; i++){
            display += "- " + _hand.getPokemonHand().get(i).getName() + ", " + _hand.getPokemonHand().get(i).getAffinity() + ", ";
            display += "Vie : " + _hand.getPokemonHand().get(i).getLife() + ", Attaque : " + _hand.getPokemonHand().get(i).getAttack() + "\n";
        }

        display += "\n";

        return display;
    }

    public Hand getHand (){
        return this._hand;
    }
    public Draw getDraw (){
        return this._draw;
    }

}
