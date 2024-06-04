package power;
import pokemon.Pokemon;
public class TotalHeal extends Power{
    private Boolean _wasAlreadyUsed;
    public TotalHeal(){
        _wasAlreadyUsed = false;
    }
    //Uses this power
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            otherPokemon.resetLife();
            _wasAlreadyUsed = true;
        }
    }
    //Get which power it is and if it's already used or not
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
    @Override
    public PowerName getType(){
        return PowerName.TOTALHEAL;
    }
}
