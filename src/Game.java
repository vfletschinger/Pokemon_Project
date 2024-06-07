import java.io.File;
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

    //Initialisation of the game of the first player
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

    //Initialisation of the game of the second player
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
    Player user = new User(FirstDraw,FirstHand);
    Player bot = new CPU(SecondDraw, SecondHand);

    // Create Display turn
    DisplayTurn displayEachTurn = new DisplayTurn();
    Integer turn = 0;
    
    String ascii = """
                                              ,'\\
    _.----.        ____         ,'  _\\   ___    ___     ____
_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.
\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |
 \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |
   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |
     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |
      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |
       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |
        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                `'                            '-._|

                           _._       _,._
                        _.'   `. ' .'   _`.
                ,""\"/`""-.-.,/. ` V'\\-,`.,--/""."-..
              ,'    `...,' . ,\\-----._|     `.   /   \\
             `.            .`  -'`"" .._   :> `-'   `.
            ,'  ,-.  _,.-'| `..___ ,'   |'-..__   .._ L
           .    \\_ -'   `-'     ..      `.-' `.`-.'_ .|
           |   ,',-,--..  ,--../  `.  .-.    , `-.  ``.
           `.,' ,  |   |  `.  /'/,,.\\/  |    \\|   |
                `  `---'    `j   .   \\  .     '   j
              ,__`"        ,'|`'\\_/`.'\\'        |\\-'-, _,.
       .--...`-. `-`. /    '- ..      _,    /\\ ,' .--"'  ,'".
     _'-""-    --  _`'-.../ __ '.'`-^,_`-""\""---....__  ' _,-`
   _.----`  _..--.'        |  "`-..-" __|'"'         .""-. ""'--.._
  /        '    /     ,  _.+-.'  ||._'   ""\"". .          `     .__\\
 `---    /        /  / j'       _/|..`  -. `-`\\ \\   \\  \\   `.  \\ `-..
," _.-' /    /` ./  /`_|_,-"   ','|       `. | -'`._,   L  \\ .  `.   |
`"' /  /  / ,__...-----| _.,  ,'            `|----.._`-.|' |. .` ..  .
   /  '| /.,/   \\--.._ `-,' ,          .  '`.'  __,., '  ''``._ \\ \\`,'
  /_,'---  ,     \\`._,-` \\ //  / . \\    `._,  -`,  / / _   |   `-L -
   /       `.     ,  ..._ ' `_/ '| |\\ `._'       '-.'   `.,'     |
  '         /    /  ..   `.  `./ | ; `.'    ,"" ,.  `.    \\      |
   `.     ,'   ,'   | |\\  |       "        |  ,'\\ |   \\    `    ,L
   /|`.  /    '     | `-| '                  /`-' |    L    `._/  \\
  / | .`|    |  .   `._.'                   `.__,'   .  |     |  (`
 '-""-'_|    `. `.__,._____     .    _,        ____ ,-  j     ".-'"'
        \\      `-.  \\/.    `"--.._    _,.---'""\\/  "_,.'     /-'
         )        `-._ '-.        `--"      _.-'.-""        `.
        ./            `,. `".._________...""_.-"`.          _j
       /_\\.__,"".   ,.'  "`-...________.---"     .".   ,.  / \\
              \\_/"\""-'                           `-'--(_,`"`-` 
            """;

    System.out.println(ascii);
    System.out.println("Welcome to the game POOkemon !!!");
    System.out.println("Choose your pokemons to figth against others pokemons !");
    user.inputContinue();
    System.out.println("\nHere's the turn 0 ! so you can have an idea of what it is : \n");
    displayEachTurn.displayTurn(turn, user, bot);
    System.out.println("Here's the battlefield ! It's where you're going to fight against a CPU !\n");


    if(whoStart == 0){
      System.out.println("#######################");
      System.out.println("The CPU starts !");
      System.out.println("#######################");
    }
    else{
      System.out.println("#######################");
      System.out.println("You start!");
      System.out.println("#######################");
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

    while(user.sizeOfBattlefield() < 3){
      System.out.println(user.displayHand());
      Scanner input = new Scanner(System.in);
      String script= "";
      script += "Which Pokemon do you want to put into battle ? Enter a number (";
      for(int i = 0; i < user.sizeOfHand(); i++){
        script += i + " " + user.getPokemonNameOnHand(i);
        if(i == user.sizeOfHand() - 1){
          script += ")";
        }
        else{
          script += "/";
        }
      }
      script += ": ";
      System.out.print(script);
      Integer pokemonUserHandIndice = input.nextInt();

      while(pokemonUserHandIndice > user.sizeOfHand() - 1 || pokemonUserHandIndice < 0){
            System.out.print("Your input is in" +
                    "correct ! Please Retry : ");
            input = new Scanner(System.in);
            pokemonUserHandIndice = input.nextInt();
      }

      Pokemon newPokemonOnBattlefield = user.getPokemonOnHand(pokemonUserHandIndice);
      user.addPokemonOnBattlefield(newPokemonOnBattlefield);
      user.removePokemonOnHand(newPokemonOnBattlefield);

      //If the draw isn't empty, the player draws instantly a Pokemon

      if(!user.drawIsEmpty()){
        System.out.print("\nYou draw a card.\n");
        user.inputContinue();
        user.addPokemonOnHand(user.takeNextPokemonOnDraw());
      }
      System.out.println("Here's your new battlefield !");
      System.out.println(user.displayBattlefield());
    }

    // POWERS
    Integer maxSlots = 3;

    ///
    /// Display the game - Start of the game
    ///
    while(!user.hasLost() || bot.hasLost()){
      turn ++;
      displayEachTurn.displayTurn(turn, user, bot);

      // if the CPU starts
      if(whoStart == 0){

        // if there's territory Extension of the user
        for(Pokemon pokemonUser : user.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerWasAlreadyUsed()){
              maxSlots = 4;
            }
          }
        }

        // CPU plays its turn
        bot.turn(user);

        // starts of User turn (Here We check if User have to draw cards to add pokemons on his battlefield
        while(user.sizeOfBattlefield() < maxSlots){
          String script= "";
          script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
          for(int i = 0; i < user.sizeOfHand(); i++){
            script += i + " " + user.getPokemonNameOnHand(i);
            if(i == user.sizeOfHand() - 1){
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

          while(pokemonUserHandIndice > user.sizeOfHand() - 1 || pokemonUserHandIndice < 0){
            System.out.print("Your input is incorrect ! Please Retry : ");
            input = new Scanner(System.in);
            pokemonUserHandIndice = input.nextInt();
          }

          Pokemon newPokemonOnBattlefield = user.getPokemonOnHand(pokemonUserHandIndice);
          user.addPokemonOnBattlefield(newPokemonOnBattlefield);
          user.removePokemonOnHand(newPokemonOnBattlefield);
          if(user.drawIsEmpty()){
            System.out.println("You draw a card.");
            user.inputContinue();
            user.addPokemonOnHand(user.takeNextPokemonOnDraw());
          }
          System.out.println(user.displayBattlefield());
          System.out.println("Here's your new battlefield !");
        }

        user.turn(bot);
      }
      else{
        // if there's territory extension of the user
        for(Pokemon pokemonUser : user.getBattlefieldPokemons()){
          if(pokemonUser.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonUser.getPowerType() != null){
            if(pokemonUser.powerWasAlreadyUsed()){
              maxSlots = 4;
            }
          }
        }

        // starts of User turn (Here We check if User have to draw cards to add pokemons on his battlefield
        while(user.sizeOfBattlefield() < maxSlots){
          Scanner input = new Scanner(System.in);

          String script= "";
          script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
          for(int i = 0; i < user.sizeOfHand(); i++){
            script += i + " " + user.getPokemonNameOnHand(i);
            if(i == user.sizeOfHand() - 1){
              script += ")";
            }
            else{
              script += "/";
            }
          }
          script += ": ";
          System.out.print(script);

          Integer pokemonUserHandIndice = input.nextInt();

          while(pokemonUserHandIndice > user.sizeOfHand() - 1 || pokemonUserHandIndice < 0){
            System.out.print("Your input is incorrect ! Please Retry : ");
            input = new Scanner(System.in);
            pokemonUserHandIndice = input.nextInt();
          }

          Pokemon newPokemonOnBattlefield = user.getPokemonOnHand(pokemonUserHandIndice);
          user.addPokemonOnHand(newPokemonOnBattlefield);
          user.removePokemonOnHand(newPokemonOnBattlefield);
          if(!user.drawIsEmpty()){
            System.out.println("You draw a card.");
            user.inputContinue();
            user.addPokemonOnHand(user.takeNextPokemonOnDraw());
          }
          System.out.println(user.displayBattlefield());
          System.out.println("Here's your new battlefield !");
        }

        user.turn(bot);

        //starts of bot turn
        bot.turn(user);
      }
    }

    // The end of the game
    if(user.hasLost()){
      System.out.println("""
      ###
      CPU IS THE WINNER
      ###
      """);
    }
    else{
      System.out.println("""
      ###
      YOU ARE THE WINNER
      ###
      """);
    }

  }
}
