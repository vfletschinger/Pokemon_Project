package power;
import pokemon.Pokemon;
public abstract class Power {
    protected Boolean _wasAlreadyUsed;
    //Defines the methods that each power must have
    public abstract void use(Pokemon thisPokemon,Pokemon otherPokemon);
    public abstract Boolean getWasAlreadyUsed();
    public abstract PowerName getType();
    //Defines the methods available and identical to all powers
    public String getName(){
        return getType().get();
    }
    public boolean onAllies(){ return this.getType().onAllies(); }
    public boolean onEnemies(){ return this.getType().onEnemies(); }
    public boolean onHimself(){ return this.getType().onHimself(); }

    /* toString : function : String : returns a recap of the class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : recap of the class
     */
    public String toString(){
        if (_wasAlreadyUsed){
            return "This power has already been used.";
        }
        else{
            return "This power can be used.";
        }
    }
}
