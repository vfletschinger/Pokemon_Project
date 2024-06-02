import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import player.*;
import power.*;
import pokemon.*;
public class Game
{
  public static void main(String args[])
  {
    //Creation of the Pokemon name list
    List<String> nameList = Arrays.asList("ALI AYADI", "RMOUQUE ILIAS", "KRAHENBUHL ADRIEN", "SCHNEIDER CATHIE-ANNE", "MARTENS LOUISE", "WESSLER-LAUX ERIC", "FONNE JEAN-PIERRE", "RICHARD VERONIQUE", "L'HELGUEN HERVE", "CORMAC CHESTER", "WEMMERT CEDRIC", "LUTTRINGER JEAN-ROMAIN", "LEQUENTREC ETIENNE", "MEYER CYRIL", "BREANT JULIAN", "ROY ISABELLE", "MOSSER RENE", "PERRIN ROMAIN", "ZIMMERMANN MATHIEU", "LAM FRANCIS", "TRESTINI MARC", "BOUSSETA IDRISSI-SELMA", "BRAUD AGNES", "LACHICHE NICOLAS", "LEBORGNE AURELIE", "GANCARSKI PIERRE", "BALDI GUILLAUME", "MAINGUY CLAIRE", "ETTAHRI BOUCHRA", "GRAZIANI CELINE", "GOSSA_JULIEN");
    //Creation of the Power list
    List<Power> powerList = Arrays.asList(new AlreadySeen(),new WarriorFervor(), new Fear(), new TerritoryExtension(), new TotalHeal(), new EtherType(), new LeadType(), new Kamikaze());

    System.out.println("Lancement du jeu ...");

    //Create draws and hands for both players
    Random random = new Random();

    // Create a random int between 1 and 0 to know which person start the game
    // 0 == User - 1 == CPU
    int whoStart = random.nextInt(0,2);
    System.out.println(whoStart);
    int numberPokemonInDraw;
    if (whoStart == 0) {
        numberPokemonInDraw = 15;
    }
    else{
        numberPokemonInDraw = 16;
    }

    //First player
    List<String> firstNameListForDraw = new ArrayList<String>();
    List<String> firstNameListForHand = new ArrayList<String>();
    List<String> nameListClone = new ArrayList<String>(nameList);
    int nbPokemon = nameListClone.size();
    for (int i = 0; i < numberPokemonInDraw; i++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForDraw.add(nameListClone.get(index));
      nameListClone.remove(index);
      nbPokemon--;
    }
    List<Power> powerListClone = new ArrayList<>(powerList);
    Draw FirstDraw = new Draw(firstNameListForDraw, powerListClone);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForHand.add(nameListClone.get(index));
      nameListClone.remove(index);
      nbPokemon--;
    }
    Hand FirstHand = new Hand(firstNameListForHand, powerListClone);

    //Second player
    List<String> secondNameListForDraw = new ArrayList<String>();
    List<String> secondNameListForHand = new ArrayList<String>();
    List<String> nameListClone2 = new ArrayList<String>(nameList);
    nbPokemon = nameListClone2.size();
    if(numberPokemonInDraw == 15){
        numberPokemonInDraw = 16;
    }
    else{
        numberPokemonInDraw = 15;
    }
    for (int i = 0; i < numberPokemonInDraw; i++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForDraw.add(nameListClone2.get(index));
      nameListClone2.remove(index);
      nbPokemon--;
    }
    List<Power> powerListClone2 = new ArrayList<Power>(powerList);
    Draw SecondDraw = new Draw(secondNameListForDraw, powerListClone2);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForHand.add(nameListClone2.get(index));
      nameListClone2.remove(index);
      nbPokemon--;
    }
    Hand SecondHand = new Hand(secondNameListForHand, powerListClone2);


    // Create 2 players
    Player joueur = new User(FirstDraw,FirstHand);
    Player bot = new CPU(SecondDraw, SecondHand);

    // Create Display turn
    DisplayTurn displayEachTurn = new DisplayTurn(joueur, bot);
    Integer turn = 0;

    // METTRE 3 POKE POUR CPU

    System.out.println(displayEachTurn.displayTurn(turn));

    if(whoStart == 0){
      System.out.println("#######################");
      System.out.println("L'ordinateur commence !");
      System.out.println("#######################");
    }
    else{
      System.out.println("##################");
      System.out.println("Vous commencencez!");
      System.out.println("##################");
    }
  
    System.out.println("\nChoissisez vos pokémons !");

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    while(joueur.sizeOfBattlefield() < 3){
       Scanner input = new Scanner(System.in);
       String script= "";
       script += "Quel Pokemon voulez-vous mettre en combat ? Entrez un chiffre (";
       for(int i = 0; i < joueur.sizeOfHand(); i++){
         script += i + " " + joueur.getPokemonNameOnHand(i);
         if(i == joueur.sizeOfHand() - 1){
           script += ")";
         }
         else{
           script += "/";
         }
       }
       script += ": ";
       System.out.print(script);
       Integer pokemonUserHandIndice = input.nextInt();

       Pokemon newPokemonOnBattlefield = joueur.getPokemonOnHand(pokemonUserHandIndice);
       joueur.addPokemonOnBattlefield(newPokemonOnBattlefield);
       joueur.removePokemonOnHand(newPokemonOnBattlefield);
       if(!joueur.drawIsEmpty()){
         System.out.println("Vous piochez une carte.");
         joueur.addPokemonOnHand(joueur.takeNextPokemonOnDraw());
       }
       System.out.println(joueur.displayBattlefield());
       System.out.println("Voici votre nouveau terrain !\n");
       System.out.println(joueur.displayHand());
    }

    // POWERS
    Integer maxSlots = 3;

    // Display the game - Start of the game
    while(!joueur.hasLost() || bot.hasLost()){
      turn += 1;
      if(whoStart == 0){
        // if there's territory Extension of the user
        for(Pokemon pokemonUser : joueur.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerOnHimself()){
              maxSlots = 4;
            }
          }
        }

        System.out.println("L'ordinateur vous attaque \n");
        bot.turn(joueur);
        System.out.println(displayEachTurn.displayTurn(turn));

        while(joueur.sizeOfBattlefield() < maxSlots){
          String script= "";
          script += "\nQuel Pokemon voulez-vous mettre en combat ? Entrez un chiffre (";
          for(int i = 0; i < joueur.sizeOfHand(); i++){
            script += i + " " + joueur.getPokemonNameOnHand(i);
            if(i == joueur.sizeOfHand() - 1){
              script += ")";
            }
            else{
              script += "/";
            }
          }
          script += ": ";
          System.out.print(script);

          Scanner input = new Scanner(System.in);
          Integer pokemonUserHandIndice = input.nextInt();

          Pokemon newPokemonOnBattlefield = joueur.getPokemonOnHand(pokemonUserHandIndice);
          joueur.addPokemonOnBattlefield(newPokemonOnBattlefield);
          joueur.removePokemonOnHand(newPokemonOnBattlefield);
          if(joueur.drawIsEmpty()){
            System.out.println("Vous piochez une carte.");
            joueur.addPokemonOnHand(joueur.takeNextPokemonOnDraw());
          }
          System.out.println(joueur.displayBattlefield());
          System.out.println("Voici votre nouveau terrain !");
        }

        joueur.turn(bot);
        //
      }
      else{
        // if there's territory extension of the user
        for(Pokemon pokemonUser : joueur.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerOnHimself()){
              maxSlots = 4;
            }
          }
        }

        System.out.println(displayEachTurn.displayTurn(turn));

        while(joueur.sizeOfBattlefield() < maxSlots){
          Scanner input = new Scanner(System.in);

          String script= "";
          script += "\nQuel Pokemon voulez-vous mettre en combat ? Entrez un chiffre (";
          for(int i = 0; i < joueur.sizeOfHand(); i++){
            script += i + " " + joueur.getPokemonNameOnHand(i);
            if(i == joueur.sizeOfHand() - 1){
              script += ")";
            }
            else{
              script += "/";
            }
          }
          script += ": ";
          System.out.print(script);

          Integer pokemonUserHandIndice = input.nextInt();

          Pokemon newPokemonOnBattlefield = joueur.getPokemonOnHand(pokemonUserHandIndice);
          joueur.addPokemonOnHand(newPokemonOnBattlefield);
          joueur.removePokemonOnHand(newPokemonOnBattlefield);
          if(!joueur.drawIsEmpty()){
            System.out.println("Vous piochez une carte.");
            joueur.addPokemonOnHand(joueur.takeNextPokemonOnDraw());
          }
          System.out.println(joueur.displayBattlefield());
          System.out.println("Voici votre nouveau terrain !");
        }

        joueur.turn(bot);
        System.out.println("L'ordinateur vous attaque \n");
        bot.turn(joueur);
      }
    }

    if(joueur.hasLost()){
      System.out.println("Vous avez perdu");
    }
    else{
      System.out.println("Vous avez gagné");
    }

  }
}
