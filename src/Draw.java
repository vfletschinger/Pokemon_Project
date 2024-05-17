import java.util.ArrayList;
import java.util.List;

public class Draw {
    private List<Pokemon> _pokemonDraw = new ArrayList<>();

    // We have to modify this function (when Pokemon is updated)
    public Draw(ArrayList<String> nameList){
        for(int i = 0; i < 21; i++){
            _pokemonDraw.add(new Pokemon());
        }
    }
    public void deletePokemonOfDraw(Pokemon pokemon){
        _pokemonDraw.remove(pokemon);
    }

    public List<Pokemon> getPokemonDraw() {
        return _pokemonDraw;
    }
}
