package power;
import pokemon.Pokemon;
public class WarriorFervor implements Power{

    private String _name = "WarriorFervor";
    private Boolean _wasAlreadyUsed;

    public WarriorFervor(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon,Pokemon otherPokemon){
        if (!_wasAlreadyUsed) {
            otherPokemon.addAttack(10);
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
