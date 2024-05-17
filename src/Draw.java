import java.util.ArrayList;
import java.util.List;

public class Draw {
    private List<Pokemon> _pokemonDraw = new ArrayList<>();

    // Take an ArrayList of 21 or 20 names of Pokemons
    public Draw(List<String> nameList){
        if (nameList.size() == 21) {
            for (int i = 0; i < 21; i++) {
                _pokemonDraw.add(new Pokemon(nameList.get(i)));
            }
        }
        else{
            for (int i = 0; i < 20; i++) {
                _pokemonDraw.add(new Pokemon(nameList.get(i)));
            }
        }
    }
    public void deletePokemonOfDraw(Pokemon pokemon){
        _pokemonDraw.remove(pokemon);
    }

    public List<Pokemon> getPokemonDraw() {
        return _pokemonDraw;
    }
}
