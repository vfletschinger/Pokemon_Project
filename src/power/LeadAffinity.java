package power;
import pokemon.Pokemon;
public class LeadAffinity implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Lead Affinity";
    public LeadAffinity(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.becomeLeadAffinity();
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
