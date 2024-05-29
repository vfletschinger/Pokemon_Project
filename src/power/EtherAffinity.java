package power;
import pokemon.Pokemon;
public class EtherAffinity implements Power{
    private Boolean _wasAlreadyUsed;

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
}
