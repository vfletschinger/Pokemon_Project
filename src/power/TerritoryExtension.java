package power;
import pokemon.Pokemon;
public class TerritoryExtension extends Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Territory Extension";
    public TerritoryExtension(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            _wasAlreadyUsed = true;
        }
    }
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }
    @Override
    public PowerName getType(){
        return PowerName.TERRITORYEXTENSION;
    }
}
