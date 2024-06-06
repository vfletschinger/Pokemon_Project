package player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pokemon.*;
import power.PowerName;

public class User extends Player {
    //Constructor
    public User(Draw draw, Hand hand){
		super(draw,hand);
	}

    /* turn : function : void : plays the turn of a user
     * param :
     *  opponent : Player : the player that the user attacks
     * local :
     *  NONE
     * return :
     *  NONE
     */
    @Override
    public void turn(Player opponent){

        for(Pokemon pokemon: getBattlefieldPokemons()){
            if(pokemon.getPower() != null){
                usePower(opponent,pokemon);
            }
        }

        List<Boolean> pokemonsIsAlreadyUsed = new ArrayList<>();
        for(int i = 0; i < sizeOfBattlefield(); i++){
            pokemonsIsAlreadyUsed.add(false);
        }

        for(int j = 0; j < sizeOfBattlefield(); j++){

            List<Pokemon> pokemonsNotUsed = new ArrayList<>();
            for(int i = 0; i < sizeOfBattlefield(); i++){
                if(!pokemonsIsAlreadyUsed.get(i))
                    pokemonsNotUsed.add(getPokemonOnBattlefield(i));
                else
                    pokemonsNotUsed.add(null);
            }

            String script = "";
            script += "\nWhich Pokemon do you want to play ? Enter an number (";
            for(int k = 0; k < sizeOfBattlefield(); k++){
                Pokemon pokemonDisplay = pokemonsNotUsed.get(k);
                if(!pokemonsIsAlreadyUsed.get(k)) {
                    script += k + " " + pokemonDisplay.getName();
                    if (k == pokemonsNotUsed.size() - 1) {
                        script += ")";
                    } else {
                        script += "/";
                    }
                }
            }

            script += ": ";
            System.out.print(script);
            script = "";
            Scanner input = new Scanner(System.in);
            Integer pokemonUserIndice = input.nextInt();

            while(pokemonsIsAlreadyUsed.get(pokemonUserIndice) || pokemonUserIndice > sizeOfBattlefield()){
                System.out.print("Your input is incorrect ! Please Retry : ");
                input = new Scanner(System.in);
                pokemonUserIndice = input.nextInt();
            }
            pokemonsIsAlreadyUsed.set(pokemonUserIndice,true);

            Pokemon pokemonAttacker = this.getPokemonOnBattlefield(pokemonUserIndice);

            script = "";
            script += "\nWhich Pokemon do you want to attack ? Enter an number (";
            Integer pokemonOpponentIndice = this.inputUser(script,opponent.getBattlefieldPokemons());
            Pokemon pokemonOpponent = opponent.getPokemonOnBattlefield(pokemonOpponentIndice);

            action(opponent,pokemonOpponent,pokemonAttacker);
            pokemonAttacker.decreaseNumberOfAttacksLeft();

            if(pokemonAttacker.getAttacksLeft() >= 1){
                script += "\nWhich Pokemon do you want to attack ? Enter an number (";
                pokemonOpponentIndice = this.inputUser(script,opponent.getBattlefieldPokemons());
                pokemonOpponent = opponent.getPokemonOnBattlefield(pokemonOpponentIndice);
                action(opponent,pokemonOpponent,pokemonAttacker);
                pokemonAttacker.decreaseNumberOfAttacksLeft();
            }

            pokemonAttacker.increaseNumberOfAttacksLeft();
        }
    }

    /* action : function : void : method that make a Pokemon attack another one
     * param :
     *  cpuPokemon : Pokemon : Pokemon that attacks
     *  pokemonTarget : Pokemon : target of the attack
     *  attackNumber : Integer : number of the attack
     * local :
     *  NONE
     * return :
     *  NONE
     */
    private void action(Player opponent,Pokemon pokemonTarget, Pokemon pokemonAttacker){
            Integer bonus = 0;
            int damage = 0;

            String script = "Attack details :\n";
            script += "You attack with " + pokemonAttacker.getName();
            script += "\n" + pokemonAttacker.getName() + " attacks " + pokemonTarget.getName() + " of the CPU";

            if(pokemonAttacker.getAffinity().isStrongAgainst(pokemonTarget.getAffinity())){
                bonus += 10;
                script += "\nIt's super effective !";
            }
            else if (pokemonTarget.getAffinity().isStrongAgainst(pokemonAttacker.getAffinity())){
                bonus -= 10;
                script += "\nIt's not very effective !";
            }

            damage = pokemonAttacker.getAttack() + bonus;

            boolean isWarriorFervor = false;

            if(pokemonAttacker.getPower() != null){
                if(pokemonAttacker.getPowerType() == PowerName.WARRIORFERVOR){
                    isWarriorFervor = true;
                }
            }

            if(isWarriorFervor && pokemonAttacker.powerWasAlreadyUsed()){
                script += "\nThanks to Warrior Fervor, your " + pokemonAttacker.getName() + " gets +10 of attack !";
                Integer bonusAttack = 10;
                script += "\n-" + damage + "[+" + bonusAttack + "]" + " to " + pokemonTarget.getName();
            }
            else if(pokemonAttacker.getPenalty()){
                script += "\nDue to Warrior Fervor, your " + pokemonAttacker.getName() + " gets -10 of attack :(";
                Integer penaltyAttack = 10;
                script += "\n-" + damage + "[+" + penaltyAttack + "]" + " to " + pokemonTarget.getName();
            }
            else{
                script += "\n-" + damage + " to " + pokemonTarget.getName();
            }

            pokemonAttacker.attackPokemon(pokemonTarget,bonus);

            script += "\nThe life of " + pokemonTarget.getName() + " of the CPU is equals to " + pokemonTarget.getLife();
            System.out.println(script);

            if(pokemonTarget.isKO()){
                System.out.println("You killed the pokemon of the CPU");
                script = pokemonAttacker.getName() + " killed " + pokemonTarget.getName() + " of the CPU";
                System.out.println(script);

                if(pokemonTarget.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonTarget.powerWasAlreadyUsed()){
                    opponent.addPokemonToDiscard(pokemonTarget);
                    opponent.removePokemonOnBattlefield(pokemonTarget);
                }
                else{
                    opponent.addPokemonToDiscard(pokemonTarget);
                    opponent.removePokemonOnBattlefield(pokemonTarget);
                    opponent.addPokemonOnBattlefield(opponent.takeNextPokemonOnHand());
                    if(!opponent.drawIsEmpty())
                        opponent.addPokemonOnHand(opponent.takeNextPokemonOnHand());
                }
            }
    }

    /* inputUser : function : Integer : return the input of the user
     * param :
     *  script : String : script of the display to increase
     *  pokemonList : List<Pokemon> : to display the Pokemon available to play
     * local :
     *  NONE
     * return :
     *  Integer : input of the user
     */
    private Integer inputUser(String script, List<Pokemon> pokemonList){
        System.out.print(script);
        script = "";
        Scanner input = new Scanner(System.in);

        for(int i = 0; i < pokemonList.size(); i++){
            script += i + " " + pokemonList.get(i).getName();
            if(i == pokemonList.size() - 1){
                script += ")";
            }
            else{
                script += "/";
            }
        }

        script += ": ";
        System.out.print(script);
        Integer inputUser = input.nextInt();
        System.out.println();
        return inputUser;
    }

    /* usePower : function : void : use the power of a Pokemon to an enemy Pokemon
     * param :
     *  opponent : Player : opponent Player
     *  pokemonAttacker : Pokemon : Pokemon who uses its power
     * local :
     *  NONE
     * return :
     *  NONE
     */
    private void usePower(Player opponent, Pokemon pokemonAttacker){
        if(pokemonAttacker.getPower() != null && !pokemonAttacker.powerWasAlreadyUsed()){

            // faire une fonction affichage du string
            String script= "";
            script += "\nDo you want to use the power of " + pokemonAttacker.getName() + " ?\n";
            script += "As a reminder, his/her power is " + pokemonAttacker.getPower().getName();
            script += "\nEnter an input (0/ OUI | 1/ NON) : ";
            System.out.println(script);

            Scanner input = new Scanner(System.in);
            int yesOrNo = input.nextInt();

            if(yesOrNo == 0){
                if(pokemonAttacker.powerOnAllies()){
                    script= "";
                    script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
                    for(int i = 0; i < super.sizeOfBattlefield() ; i++){
                        script += i + " " + getPokemonNameOnBattlefield(i);
                        if(i == super.sizeOfBattlefield() - 1){
                            script += ")";
                        }
                        else{
                            script += "/";
                        }
                    }
                    System.out.print(script);
                    Integer pokemonIndice = input.nextInt();
                    pokemonAttacker.usePower(getPokemonOnBattlefield(pokemonIndice));
                    script = "\nThe power has been used\n";
                    script += "Here the changes : \n";
                    script += "\nYour Pokemons :\n";
                    System.out.print(script);
                    System.out.println(displayBattlefield());
                    this.inputContinue();
                }
                else if(pokemonAttacker.powerOnEnemies()){
                    script= "";
                    script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
                    for(int i = 0; i < opponent.sizeOfBattlefield() ; i++){
                        script += i + " " + opponent.getPokemonNameOnBattlefield(i);
                        if(i == opponent.sizeOfBattlefield() - 1){
                            script += ")";
                        }
                        else{
                            script += "/";
                        }
                    }
                    script += ": ";
                    System.out.println(script);
                    Integer pokemonIndice = input.nextInt();

                    Pokemon opponentPokemon = opponent.getPokemonOnBattlefield(pokemonIndice);
                    pokemonAttacker.usePower(opponentPokemon);

                    if(pokemonAttacker.getPowerType() == PowerName.KAMIKAZE && pokemonAttacker.powerWasAlreadyUsed()){
                        script = pokemonAttacker.getName() + " explodes with " + opponentPokemon.getName() + "\n";

                        opponent.addPokemonToDiscard(opponentPokemon);
                        opponent.removePokemonOnBattlefield(opponentPokemon);
                        opponent.addPokemonOnBattlefield(opponent.takeNextPokemonOnHand());
                        if(!opponent.drawIsEmpty())
                            opponent.addPokemonOnHand(opponent.takeNextPokemonOnHand());

                        this.addPokemonToDiscard(pokemonAttacker);
                        removePokemonOnBattlefield(pokemonAttacker);

                        script = "";
                        script += "\nThe power has been used\n";
                        script += "\nThe CPU draw a new card\n";
                        script += "\nThe CPU add a new pokemon in its battlefield\n";
                        script += "Here the changes : \n";
                        script += "\nPokemons of the CPU :\n";
                        System.out.print(script);
                        System.out.println(opponent.displayBattlefield());
                        this.inputContinue();
                        System.out.println("Your Pokemons :");
                        System.out.println(displayBattlefield());
                        this.inputContinue();
                    }
                    else{
                        script = "";
                        script += "\nThe power has been used\n";
                        script += "Here the changes : \n";
                        script += "\nPokemons of the CPU :\n";
                        System.out.print(script);
                        System.out.println(opponent.displayBattlefield());
                        this.inputContinue();
                    }
                }
                else if(pokemonAttacker.powerOnHimself()){
                    super.usePokemonPower(pokemonAttacker, pokemonAttacker);
                    if(pokemonAttacker.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonAttacker.powerWasAlreadyUsed()){
                        this.displayHand();
                        script= "";
                        script += "\nWhich Pokemon do you want to put into battle ? Enter a number (";
                        for(int i = 0; i < sizeOfHand(); i++){
                            script += i + " " + getPokemonNameOnHand(i);
                            if(i == sizeOfHand() - 1){
                                script += ")";
                            }
                            else{
                                script += "/";
                            }
                        }
                        script += ": ";
                        System.out.println(script);

                        //Scanner input = new Scanner(System.in);
                        Integer pokemonUserHandIndice = input.nextInt();

                        Pokemon newPokemonOnBattlefield = getPokemonOnHand(pokemonUserHandIndice);
                        addPokemonOnBattlefield(newPokemonOnBattlefield);
                        removePokemonOnHand(newPokemonOnBattlefield);
                    }
                    script = "";
                    script += "\nThe power has been played\n";
                    script += "Here's the changes : \n";
                    System.out.print(script);
                    System.out.println(displayBattlefield());
                    this.inputContinue();
                }
            }
        }
    }
}
