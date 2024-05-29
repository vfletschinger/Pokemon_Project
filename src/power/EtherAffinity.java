package power;
import pokemon.Pokemon;
public class EtherAffinity implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Ether Affinity";
    public EtherAffinity(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.becomeEtherAffinity();
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
