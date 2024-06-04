package power;
import pokemon.Pokemon;
public class Fear extends Power{
    private Boolean _wasAlreadyUsed;
    public Fear(){
        _wasAlreadyUsed = false;
    }
    //Uses this power
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if (!_wasAlreadyUsed) {
            otherPokemon.addAttack(-10);
            otherPokemon.setPenalty(true);
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
        return PowerName.FEAR;
    }
}
