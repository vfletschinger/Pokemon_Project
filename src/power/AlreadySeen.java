package power;
import pokemon.*;
public class AlreadySeen implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Already Seen";
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
    public String getName(){
        return _name;
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
}
