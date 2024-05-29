public class AlreadySeen implements Power{
    private Boolean _wasAlreadyUsed;

    public AlreadySeen(){
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
