package power;
import pokemon.Pokemon;
public class Fear implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Fear";
    public Fear(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if (!_wasAlreadyUsed) {
            otherPokemon.addAttack(-10);
            otherPokemon.setPenalty(true);
            _wasAlreadyUsed = true;
        }
    }

    @Override
    public String getName(){
        return _name;
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
}
