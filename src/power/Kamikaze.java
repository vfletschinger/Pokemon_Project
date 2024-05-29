package power;
import pokemon.Pokemon;
public class Kamikaze implements Power{
    private Boolean _wasAlreadyUsed;

    public Kamikaze(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.killPokemon();
            otherPokemon.killPokemon();
            _wasAlreadyUsed = true;
        }
    }
}
