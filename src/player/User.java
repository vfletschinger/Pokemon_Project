package player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pokemon.*;
import power.PowerName;

public class User extends Player {
    public User(Draw draw, Hand hand){
		super(draw,hand);
	}
    @Override
    public void turn(Player opponent){

        for(Pokemon pokemon: getBattlefieldPokemons()){
            if(pokemon.getPower() != null){
                usePower(opponent,pokemon);
            }
        }

        List<Boolean> pokemonsAlreadyUsed = new ArrayList<>();
        for(int i = 0; i < sizeOfBattlefield(); i++){
            pokemonsAlreadyUsed.add(false);
        }

        for(int j = 0; j < sizeOfBattlefield(); j++){
            String script = "";
            script += "\nQuel Pokemon voulez-vous jouez ? Entrez un chiffre (";
            for(int k = 0; k < sizeOfBattlefield() - j; k++){
                if(pokemonsAlreadyUsed.get(k) == false) {
                    script += k + " " + getPokemonNameOnBattlefield(k);
                }

                if(k == sizeOfBattlefield() - 1 - j){
                    script += ")";
                }
                else{
                    script += "/";
                }
            }

            script += ": ";
            System.out.print(script);
            script = "";
            Scanner input = new Scanner(System.in);
            Integer pokemonUserIndice = input.nextInt();

            while(pokemonsAlreadyUsed.get(pokemonUserIndice) == true || pokemonUserIndice > sizeOfBattlefield()){
                System.out.println("Votre entrée est fausse, remetter le bon integer");
                input = new Scanner(System.in);
                pokemonUserIndice = input.nextInt();
            }

            Pokemon pokemonAttacker = this.getPokemonOnBattlefield(pokemonUserIndice);

            script = "";
            script += "\nQuel Pokemon voulez-vous attaquer ? Entrez un chiffre (";
            Integer pokemonOpponentIndice = this.inputUser(script,opponent.getBattlefieldPokemons());
            Pokemon pokemonOpponent = opponent.getPokemonOnBattlefield(pokemonOpponentIndice);

            action(opponent,pokemonOpponent,pokemonAttacker);
            pokemonAttacker.decreaseNumberOfAttacksLeft();

            if(pokemonAttacker.getAttacksLeft() >= 1){
                script += "\nQuel Pokemon voulez-vous attaquer ? Entrez un chiffre (";
                pokemonOpponentIndice = this.inputUser(script,opponent.getBattlefieldPokemons());
                pokemonOpponent = opponent.getPokemonOnBattlefield(pokemonOpponentIndice);
                action(opponent,pokemonOpponent,pokemonAttacker);
                pokemonAttacker.decreaseNumberOfAttacksLeft();
            }

            pokemonAttacker.increaseNumberOfAttacksLeft();
        }
    }
    private void action(Player opponent,Pokemon pokemonTarget, Pokemon pokemonAttacker){
            Integer bonus = 0;
            int damage = 0;

            String script = "Détails de l'attaque :\n";
            script += "Vous attaquez avec " + pokemonAttacker.getName();
            script += "\n" + pokemonAttacker.getName() + " attaque " + pokemonTarget.getName() + " du CPU";

            if(pokemonAttacker.getAffinity().isStrongAgainst(pokemonTarget.getAffinity())){
                bonus += 10;
                script += "\nC'est super efficace !";
            }
            else if (pokemonTarget.getAffinity().isStrongAgainst(pokemonAttacker.getAffinity())){
                bonus -= 10;
                script += "\nCe n'est pas très efficace !";
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
                script += "\n-" + damage + "[+" + bonusAttack + "]" + " à " + pokemonTarget.getName();
            }
            else if(pokemonAttacker.getPenalty()){
                script += "\nDue to Warrior Fervor, your " + pokemonAttacker.getName() + " gets -10 of attack :(";
                Integer penaltyAttack = 10;
                script += "\n-" + damage + "[+" + penaltyAttack + "]" + " à " + pokemonTarget.getName();
            }
            else{
                script += "\n-" + damage + " à " + pokemonTarget.getName();
            }

            pokemonAttacker.attackPokemon(pokemonTarget,bonus);

            script += "\nLa vie de " + pokemonTarget.getName() + " du CPU est égale à " + pokemonTarget.getLife();
            System.out.println(script);

            if(pokemonTarget.isKO()){
                System.out.println("Vous avez tué un pokemon de l'ordinateur");
                script = pokemonAttacker.getName() + " a tué " + pokemonTarget.getName() + " du CPU";
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
        System.out.println(script);
        Integer inputUser = input.nextInt();
        System.out.println();
        return inputUser;
    }

    private void usePower(Player opponent, Pokemon pokemonAttacker){
        if(pokemonAttacker.getPower() != null && !pokemonAttacker.powerWasAlreadyUsed()){

            // faire une fonction affichage du string
            String script= "";
            script += "\nVoulez vous utilisez le pouvoir de " + pokemonAttacker.getName() + " ?\n";
            script += "Pour rappel, son pouvoir est " + pokemonAttacker.getPower().getName();
            script += "\nEntrez un chiffre (0/ OUI | 1/ NON) : ";
            System.out.println(script);

            Scanner input = new Scanner(System.in);
            int yesOrNo = input.nextInt();

            if(yesOrNo == 0){
                if(pokemonAttacker.powerOnAllies()){
                    script= "";
                    script += "\nSur quel allié voulez vous utiliser votre pouvoir ? Entrez un chiffre (";
                    for(int i = 0; i < super.sizeOfBattlefield() ; i++){
                        script += i + " " + getPokemonNameOnBattlefield(i);
                        if(i == super.sizeOfBattlefield() - 1){
                            script += ")";
                        }
                        else{
                            script += "/";
                        }
                    }
                    System.out.println(script);
                    Integer pokemonIndice = input.nextInt();
                    pokemonAttacker.usePower(getPokemonOnBattlefield(pokemonIndice));
                }
                else if(pokemonAttacker.powerOnEnemies()){
                    script= "";
                    script += "\nSur quel ennemi voulez vous utiliser votre pouvoir ? Entrez un chiffre (";
                    for(int i = 0; i < opponent.sizeOfBattlefield() ; i++){
                        script += i + " " + opponent.getPokemonNameOnBattlefield(i);
                        if(i == opponent.sizeOfBattlefield() - 1){
                            script += ")";
                        }
                        else{
                            script += "/";
                        }
                    }
                    System.out.println(script);
                    Integer pokemonIndice = input.nextInt();
                    Pokemon opponentPokemon = opponent.getPokemonOnBattlefield(pokemonIndice);
                    pokemonAttacker.usePower(opponentPokemon);
                    if(pokemonAttacker.getPowerType() == PowerName.KAMIKAZE && pokemonAttacker.powerWasAlreadyUsed()){
                        script = pokemonAttacker.getName() + " explose avec " + opponentPokemon.getName() + "\n";

                        opponent.addPokemonToDiscard(opponentPokemon);
                        opponent.removePokemonOnBattlefield(opponentPokemon);
                        opponent.addPokemonOnBattlefield(opponent.takeNextPokemonOnHand());
                        if(!opponent.drawIsEmpty())
                            opponent.addPokemonOnHand(opponent.takeNextPokemonOnHand());

                        this.addPokemonToDiscard(pokemonAttacker);
                        removePokemonOnBattlefield(pokemonAttacker);
                    }
                }
                else if(pokemonAttacker.powerOnHimself()){
                    if(pokemonAttacker.getPowerType() == PowerName.TERRITORYEXTENSION && pokemonAttacker.powerWasAlreadyUsed()){
                        script= "";
                        script += "\nQuel Pokemon voulez-vous mettre en combat ? Entrez un chiffre (";
                        for(int i = 0; i < sizeOfHand(); i++){
                            script += i + " " + getPokemonNameOnHand(i);
                            if(i == sizeOfHand() - 1){
                                script += ")";
                            }
                            else{
                                script += "/";
                            }
                        }
                        System.out.println(script);

                        //Scanner input = new Scanner(System.in);
                        Integer pokemonUserHandIndice = input.nextInt();

                        Pokemon newPokemonOnBattlefield = getPokemonOnHand(pokemonUserHandIndice);
                        addPokemonOnBattlefield(newPokemonOnBattlefield);
                        removePokemonOnHand(newPokemonOnBattlefield);
                    }
                    script = "";
                    script += "\nL'effet a été appliqué !";
                    super.usePokemonPower(pokemonAttacker, pokemonAttacker);
                }
                System.out.println("\nLe pouvoir a bien été utilisé\n");
                System.out.println("Voici le changement : \n");
                System.out.println(displayBattlefield());
            }
        }
    }
}
