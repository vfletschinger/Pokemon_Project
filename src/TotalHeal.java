public class TotalHeal implements Power{
    private Boolean _wasAlreadyUsed;

    public TotalHeal(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            otherPokemon.resetLife();
            _wasAlreadyUsed = true;
        }
    }
}
