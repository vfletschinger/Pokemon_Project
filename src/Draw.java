import java.util.ArrayList;
import java.util.List;

public class Draw {
    private List<Pokemon> _draw = new ArrayList<>();

    // We have to modify this function (when Pokemon is updated)
    public Draw(ArrayList<String> nameList){
        for(int i = 0; i < 21; i++){
            _draw.add(new Pokemon());
        }
    }
    public void deletePokemonOfDraw(Pokemon pokemon){
        _draw.remove(pokemon);
    }
}
