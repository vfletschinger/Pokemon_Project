public class Fear implements Power{
    private Boolean _wasAlreadyUsed;

    public Fear(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if (!_wasAlreadyUsed) {
            otherPokemon.addAttack(-10);
            _wasAlreadyUsed = true;
        }
    }
}
