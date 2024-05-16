import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private List<Pokemon> _pokemonDiscard = new ArrayList<>();

    public Player(){
    }
    public abstract void choicePokemonBattlefield();
    public  String displayDecks(){
        return "hey";
    }

    public Boolean hasLost(){
        return true;
    }

    public void addPokemonToDiscard(Pokemon pokemon){
        _pokemonDiscard.add(pokemon);
    }
    public String displayHand(){
        return "truc";
    }
}
