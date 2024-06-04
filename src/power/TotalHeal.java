package power;
import pokemon.Pokemon;
public class TotalHeal extends Power{
    private Boolean _wasAlreadyUsed;

    //Constructor
    public TotalHeal(){
        _wasAlreadyUsed = false;
    }

    /* use : function : void : use the power of a Pokemon to an enemy Pokemon
     * param :
     *  thisPokemon : Pokemon : Pokemon who use the power
     *  otherPokemon : Pokemon : Pokemon on which the power is used
     * local :
     *  NONE
     * return :
     *  NONE
     */
    @Override
    public void use(Pokemon thisPokemon, Pokemon otherPokemon){
        if(!_wasAlreadyUsed){
            otherPokemon.resetLife();
            _wasAlreadyUsed = true;
        }
    }

    /* getWasAlreadyUsed : function : Boolean : returns if the power was already used
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the power was already used
     */
    @Override
    public Boolean getWasAlreadyUsed(){
        return _wasAlreadyUsed;
    }

    /* getType : function : PowerName : returns the type of the power
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  PowerName : the type of the power
     */
    @Override
    public PowerName getType(){
        return PowerName.TOTALHEAL;
    }
}
