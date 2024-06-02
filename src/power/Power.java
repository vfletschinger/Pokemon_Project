package power;
import pokemon.Pokemon;
public abstract class Power {
    public abstract void use(Pokemon thisPokemon,Pokemon otherPokemon);
    public abstract Boolean getWasAlreadyUsed();
    public abstract PowerName getType();
    public String getName(){
        return getType().get();
    }
    public boolean onAllies(){ return this.getType().onAllies(); }
    public boolean onEnnemies(){ return this.getType().onEnnemies(); }
    public boolean onHimself(){ return this.getType().onHimself(); }
}
