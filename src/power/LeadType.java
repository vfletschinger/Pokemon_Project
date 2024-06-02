package power;
import pokemon.Pokemon;
public class LeadType extends Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Lead Affinity";
    public LeadType(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            otherPokemon.becomeLeadType();
            _wasAlreadyUsed = true;
        }
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
    @Override
    public PowerName getType(){
        return PowerName.LEADTYPE;
    }
}
