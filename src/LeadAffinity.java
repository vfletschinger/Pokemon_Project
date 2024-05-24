public class LeadAffinity implements Power{
    private Boolean _wasAlreadyUsed;

    public LeadAffinity(){
        _wasAlreadyUsed = false;
    }
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            thisPokemon.becomeLeadAffinity();
            _wasAlreadyUsed = true;
        }
    }
}
