public class Game
{
  public static void main(String args[])
  {
    System.out.println("Lancement du jeu ...");

    // Initialization of some names in _pokemonList of the Pokemon class
    Pokemon.setPokemonList("Ali Ayadi");

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
