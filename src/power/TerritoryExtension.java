package power;
import pokemon.Pokemon;
public class TerritoryExtension implements Power{
    private Boolean _wasAlreadyUsed;

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
}
