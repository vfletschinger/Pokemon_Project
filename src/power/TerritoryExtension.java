package power;
import pokemon.Pokemon;
public class TerritoryExtension implements Power{
    private Boolean _wasAlreadyUsed;
    private String _name = "Territory Extension";
    public TerritoryExtension(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.extendsTerritory();
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
