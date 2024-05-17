import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game
{
  public static void main(String args[])
  {
    //Creation de la liste de noms des pokemons
    List<String> nameList = Arrays.asList("ALI AYADI", "RMOUQUE ILIAS", "KRAHENBUHL ADRIEN", "SCHNEIDER CATHIE-ANNE", "MARTENS LOUISE", "WESSLER-LAUX ERIC", "FONNE JEAN-PIERRE", "RICHARD VERONIQUE", "L'HELGUEN HERVE", "CORMAC CHESTER", "WEMMERT CEDRIC", "LUTTRINGER JEAN-ROMAIN", "LEQUENTREC ETIENNE", "MEYER CYRIL", "BREANT JULIAN", "ROY ISABELLE", "MOSSER RENE", "PERRIN ROMAIN", "ZIMMERMANN MATHIEU", "LAM FRANCIS", "TRESTINI MARC", "BOUSSETA IDRISSI-SELMA", "BRAUD AGNES", "LACHICHE NICOLAS", "LEBORGNE AURELIE", "GANCARSKI PIERRE", "BALDI GUILLAUME", "MAINGUY CLAIRE", "ETTAHRI BOUCHRA", "GRAZIANI CELINE", "GOSSA_JULIEN");

    System.out.println("Lancement du jeu ...");

    // Initialization of some names in _pokemonList of the Pokemon class
    Pokemon.setPokemonList("Ali Ayadi");
    Pokemon.setPokemonList("Zimmermann Mathieu");

    // Create two Pokemon instances: pokemon1, pokemon2
    Pokemon pokemon1 = new Pokemon();
    Pokemon pokemon2 = new Pokemon();

    // Display the attributes of pokemon1
    System.out.println(pokemon1.getName());
    System.out.println(pokemon1.getAffinity());
    System.out.println(pokemon1.getLife());
    System.out.println(pokemon1.getAttack());

    // Display an fight between pokemon1 and pokemon2

    System.out.println("\n" + pokemon1.getName() + " attacks " + pokemon2.getName());
    pokemon1.attackPokemon(pokemon2);
    System.out.println("Life of " + pokemon2.getName() + " = " + pokemon2.getLife());

    System.out.println("\n" + pokemon2.getName() + " attacks " + pokemon1.getName());
    pokemon2.attackPokemon(pokemon1);
    System.out.println("Life of " + pokemon1.getName() + " = " + pokemon1.getLife());

  }
}
