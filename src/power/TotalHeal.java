package power;
import pokemon.Pokemon;
public class TotalHeal implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Total Heal";
    public TotalHeal(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            otherPokemon.resetLife();
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
