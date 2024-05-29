package power;
import pokemon.Pokemon;
public interface Power {
    default String getName(){return "";}
    default void use(Pokemon thisPokemon,Pokemon otherPokemon){}
    default Boolean getWasAlreadyUsed(){return false;}
}
