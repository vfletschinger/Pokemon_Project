package power;
import pokemon.Pokemon;
public interface Power {
    public default void use(Pokemon thisPokemon,Pokemon otherPokemon){}
}
