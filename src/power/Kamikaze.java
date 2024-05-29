package power;
import pokemon.Pokemon;
public class Kamikaze implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Kamikaze";
    public Kamikaze(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.setLife(0);
            otherPokemon.setLife(0);
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
