package power;
import pokemon.*;
public class AlreadySeen extends Power{
    private Boolean _wasAlreadyUsed;
    public AlreadySeen(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.increaseNumberOfAttacksLeft();
            _wasAlreadyUsed = true;
        }
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
    @Override
    public PowerName getType(){
        return PowerName.ALREADYSEEN;
    }
}
