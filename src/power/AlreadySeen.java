package power;
import pokemon.*;
public class AlreadySeen extends Power{
    private Boolean _wasAlreadyUsed;
    public AlreadySeen(){
        _wasAlreadyUsed = false;
    }
    //Uses this power
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.increaseNumberOfAttacksLeft();
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
        return PowerName.ALREADYSEEN;
    }
}
