import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game
{
  public static void main(String args[])
  {
    //Creation of the Pokemon name list
    List<String> nameList = Arrays.asList("ALI AYADI", "RMOUQUE ILIAS", "KRAHENBUHL ADRIEN", "SCHNEIDER CATHIE-ANNE", "MARTENS LOUISE", "WESSLER-LAUX ERIC", "FONNE JEAN-PIERRE", "RICHARD VERONIQUE", "L'HELGUEN HERVE", "CORMAC CHESTER", "WEMMERT CEDRIC", "LUTTRINGER JEAN-ROMAIN", "LEQUENTREC ETIENNE", "MEYER CYRIL", "BREANT JULIAN", "ROY ISABELLE", "MOSSER RENE", "PERRIN ROMAIN", "ZIMMERMANN MATHIEU", "LAM FRANCIS", "TRESTINI MARC", "BOUSSETA IDRISSI-SELMA", "BRAUD AGNES", "LACHICHE NICOLAS", "LEBORGNE AURELIE", "GANCARSKI PIERRE", "BALDI GUILLAUME", "MAINGUY CLAIRE", "ETTAHRI BOUCHRA", "GRAZIANI CELINE", "GOSSA_JULIEN");

    System.out.println("Lancement du jeu ...");

    //Create draws and hands for both players
    Random random = new Random();

    //First player
    List<String> firstNameListForDraw = new ArrayList<String>();
    List<String> firstNameListForHand = new ArrayList<String>();
    List<String> nameListClone = new ArrayList<String>(nameList);
    int nbPokemon = nameListClone.size();
    for (int i = 0; i < 21; i++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForDraw.add(nameListClone.get(index));
      nameListClone.remove(index);
      nbPokemon--;
    }
    Draw FirstDraw = new Draw(firstNameListForDraw);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForHand.add(nameListClone.get(index));
      nameListClone.remove(index);
      nbPokemon--;
    }
    Hand FirstHand = new Hand(firstNameListForHand);

    //Second player
    List<String> secondNameListForDraw = new ArrayList<String>();
    List<String> secondNameListForHand = new ArrayList<String>();
    List<String> nameListClone2 = new ArrayList<String>(nameList);
    nbPokemon = nameListClone2.size();
    for (int i = 0; i < 20; i++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForDraw.add(nameListClone2.get(index));
      nameListClone2.remove(index);
      nbPokemon--;
    }
    Draw SecondDraw = new Draw(secondNameListForDraw);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForHand.add(nameListClone2.get(index));
      nameListClone2.remove(index);
      nbPokemon--;
    }
    Hand SecondHand = new Hand(secondNameListForHand);


    // Create two Pokemon instances: pokemon1, pokemon2
    Pokemon pokemon1 = new Pokemon("JOHNNY");
    Pokemon pokemon2 = new Pokemon("JEAN");

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
