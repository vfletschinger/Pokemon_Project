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
    List<String> tempNameList= Arrays.asList("ALI AYADI", "RMOUQUE ILIAS", "KRAHENBUHL ADRIEN", "SCHNEIDER CATHIE-ANNE", "MARTENS LOUISE", "WESSLER-LAUX ERIC", "FONNE JEAN-PIERRE", "RICHARD VERONIQUE", "L'HELGUEN HERVE", "CORMAC CHESTER", "WEMMERT CEDRIC", "LUTTRINGER JEAN-ROMAIN", "LEQUENTREC ETIENNE", "MEYER CYRIL", "BREANT JULIAN", "ROY ISABELLE", "MOSSER RENE", "PERRIN ROMAIN", "ZIMMERMANN MATHIEU", "LAM FRANCIS", "TRESTINI MARC", "BOUSSETA IDRISSI-SELMA", "BRAUD AGNES", "LACHICHE NICOLAS", "LEBORGNE AURELIE", "GANCARSKI PIERRE", "BALDI GUILLAUME", "MAINGUY CLAIRE", "ETTAHRI BOUCHRA", "GRAZIANI CELINE", "GOSSA JULIEN", "KAEUFFER LAURENCE", "GANGLOFF CLEMENT", "WIESER LAURENT", "RYDZEK MARIANNE", "NAEGEL BENOIT", "GRAD DOMINIQUE", "HARISTOY JULIEN", "HECKEL ERIC", "LABBE TRISTAN", "SCHEIDT GUILLAUME");
    ArrayList<String> nameList = new ArrayList<>(tempNameList);
    //Creation of the Power list1

    List<Power> powerList = Arrays.asList(new AlreadySeen(),new WarriorFervor(), new Fear(), new TerritoryExtension(), new TotalHeal(), new EtherType(), new LeadType(), new Kamikaze());

    System.out.println("Starting game ...");

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
    int nbPokemon = nameList.size();
    for (int i = 0; i < numberPokemonInDraw; i++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForDraw.add(nameList.remove(index));
      nbPokemon--;
    }
    List<Power> powerListClone = new ArrayList<>(powerList);
    Draw FirstDraw = new Draw(firstNameListForDraw, powerListClone);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      firstNameListForHand.add(nameList.remove(index));
      nbPokemon--;
    }
    Hand FirstHand = new Hand(firstNameListForHand, powerListClone);

    //Second player
    List<String> secondNameListForDraw = new ArrayList<String>();
    List<String> secondNameListForHand = new ArrayList<String>();
    nbPokemon = nameList.size();
    if(numberPokemonInDraw == 15){
        numberPokemonInDraw = 16;
    }
    else{
        numberPokemonInDraw = 15;
    }
    for (int i = 0; i < numberPokemonInDraw; i++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForDraw.add(nameList.remove(index));
      nbPokemon--;
    }
    List<Power> powerListClone2 = new ArrayList<Power>(powerList);
    Draw SecondDraw = new Draw(secondNameListForDraw, powerListClone2);
    for (int j = 0; j < 5; j++){
      int index = random.nextInt(0,nbPokemon);
      secondNameListForHand.add(nameList.remove(index));
      nbPokemon--;
    }
    Hand SecondHand = new Hand(secondNameListForHand, powerListClone2);


    // Create 2 players
    Player player = new User(FirstDraw,FirstHand);
    Player bot = new CPU(SecondDraw, SecondHand);

    // Create Display turn
    DisplayTurn displayEachTurn = new DisplayTurn(player, bot);
    Integer turn = 0;



    System.out.println(displayEachTurn.displayTurn(turn));

    if(whoStart == 0){
      System.out.println("#######################");
      System.out.println("The CPU starts !");
      System.out.println("#######################");
    }
    else{
      System.out.println("##################");
      System.out.println("You start!");
      System.out.println("##################");
    }
  
    System.out.println("\nChoose your Pokemons !");

    //Choose the 3 first Pokemons of the bot hand to put on its battlefield

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    bot.addPokemonOnBattlefield(bot.takeNextPokemonOnHand());
    bot.addPokemonOnHand(bot.takeNextPokemonOnDraw());

    //Asks the player which Pokemons he wants to put on his battlefield

    while(player.sizeOfBattlefield() < 3){
       Scanner input = new Scanner(System.in);
       String script= "";
       script += "Which Pokemon do you want to put into battle ? Enter a number (";
       for(int i = 0; i < player.sizeOfHand(); i++){
         script += i + " " + player.getPokemonNameOnHand(i);
         if(i == player.sizeOfHand() - 1){
           script += ")";
         }
         else{
           script += "/";
         }
       }
       script += ": ";
       System.out.print(script);
       Integer pokemonUserHandIndice = input.nextInt();

       Pokemon newPokemonOnBattlefield = player.getPokemonOnHand(pokemonUserHandIndice);
       player.addPokemonOnBattlefield(newPokemonOnBattlefield);
       player.removePokemonOnHand(newPokemonOnBattlefield);

       //If the draw isn't empty, the player draws instantly a Pokemon

       if(!player.drawIsEmpty()){
         System.out.println("You draw a card.");
         player.addPokemonOnHand(player.takeNextPokemonOnDraw());
       }
       System.out.println(player.displayBattlefield());
       System.out.println("Here's your new battlefield !\n");
       System.out.println(player.displayHand());
    }

    // POWERS
    Integer maxSlots = 3;

    // Display the game - Start of the game
    while(!player.hasLost() || bot.hasLost()){
      turn += 1;
      if(whoStart == 0){
        // if there's territory Extension of the user
        for(Pokemon pokemonUser : player.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerOnHimself()){
              maxSlots = 4;
            }
          }
        }

        System.out.println("The CPU attacks you \n");
        bot.turn(player);
        System.out.println(displayEachTurn.displayTurn(turn));

        while(player.sizeOfBattlefield() < maxSlots){
          String script= "";
          script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
          for(int i = 0; i < player.sizeOfHand(); i++){
            script += i + " " + player.getPokemonNameOnHand(i);
            if(i == player.sizeOfHand() - 1){
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

          Pokemon newPokemonOnBattlefield = player.getPokemonOnHand(pokemonUserHandIndice);
          player.addPokemonOnBattlefield(newPokemonOnBattlefield);
          player.removePokemonOnHand(newPokemonOnBattlefield);
          if(player.drawIsEmpty()){
            System.out.println("You draw a card.");
            player.addPokemonOnHand(player.takeNextPokemonOnDraw());
          }
          System.out.println(player.displayBattlefield());
          System.out.println("Here's your new battlefield !");
        }

        player.turn(bot);
        //
      }
      else{
        // if there's territory extension of the user
        for(Pokemon pokemonUser : player.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerOnHimself()){
              maxSlots = 4;
            }
          }
        }

        System.out.println(displayEachTurn.displayTurn(turn));

        while(player.sizeOfBattlefield() < maxSlots){
          Scanner input = new Scanner(System.in);

          String script= "";
          script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
          for(int i = 0; i < player.sizeOfHand(); i++){
            script += i + " " + player.getPokemonNameOnHand(i);
            if(i == player.sizeOfHand() - 1){
              script += ")";
            }
            else{
              script += "/";
            }
          }
          script += ": ";
          System.out.print(script);

          Integer pokemonUserHandIndice = input.nextInt();

          Pokemon newPokemonOnBattlefield = player.getPokemonOnHand(pokemonUserHandIndice);
          player.addPokemonOnHand(newPokemonOnBattlefield);
          player.removePokemonOnHand(newPokemonOnBattlefield);
          if(!player.drawIsEmpty()){
            System.out.println("You draw a card.");
            player.addPokemonOnHand(player.takeNextPokemonOnDraw());
          }
          System.out.println(player.displayBattlefield());
          System.out.println("Here's your new battlefield !");
        }

        player.turn(bot);
        System.out.println("The CPU attacks you \n");
        bot.turn(player);
      }
    }

    if(player.hasLost()){
      System.out.println("You have lost");
    }
    else{
      System.out.println("You have win");
    }

  }
}
