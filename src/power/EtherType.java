package power;
import pokemon.Pokemon;
public class EtherType extends Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Ether Affinity";
    public EtherType(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.becomeEtherType();
            _wasAlreadyUsed = true;
        }
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
    @Override
    public PowerName getType(){
        return PowerName.ETHERTYPE;
    }
}
