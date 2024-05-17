import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Pokemon> _pokemonHand = new ArrayList<>();

    // We have to modify this function (when Pokemon is updated)
    public Hand(ArrayList<String> nameList){
        for(int i = 0; i < 5; i++){
            _pokemonHand.add(new Pokemon());
        }
    }

    public void addPokemonToHand(Pokemon pokemon){
        if(_pokemonHand.size() < 5){
         _pokemonHand.add(pokemon);
        }
    }
    public void deletePokemonOfDraw(Pokemon pokemon){
        _pokemonHand.remove(pokemon);
    }

    public List<Pokemon> getPokemonHand() {
        return _pokemonHand;
    }

}
