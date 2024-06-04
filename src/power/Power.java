package power;
import pokemon.Pokemon;
public abstract class Power {
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
}
