package player;
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
        for(int j = 0; j < sizeOfBattlefield(); j++){

            String script = "";
            script += "\nQuel Pokemon voulez-vous jouez ? Entrez un chiffre (";
            Integer pokemonUserIndice = this.inputUser(script,this.getBattlefieldPokemons());
            System.out.println();

            Pokemon pokemonAttacker = this.getPokemonOnBattlefield(pokemonUserIndice);

            script = "";
            script += "\nQuel Pokemon voulez-vous attaquer ? Entrez un chiffre (";
            Integer pokemonOpponentIndice = this.inputUser(script,opponent.getBattlefieldPokemons());
            Pokemon pokemonOpponent = opponent.getPokemonOnBattlefield(pokemonOpponentIndice);

            action(opponent,pokemonOpponent,pokemonAttacker);
        }
    }
    private void action(Player opponent,Pokemon pokemonTarget, Pokemon pokemonAttacker){
            Integer bonus = 0;
            int damage = 0;

            if(pokemonAttacker.getPower() != null){
                Scanner input = new Scanner(System.in);

                // faire une fonction affichage du string
                String script= "";
                script += "\nVoulez vous utilisez le pouvoir de" + pokemonAttacker.getName() + " ?";
                script += "Pour rappel, son pouvoir est " + pokemonAttacker.getPower().getName();
                script += "\nEntrez un chiffre (0/ OUI | 1/ NON) : ";
                Integer yesOrNo = input.nextInt();

                if(yesOrNo == 0){
                    if(pokemonAttacker.powerOnAllies() && pokemonAttacker.powerWasAlreadyUsed()){
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
                    else if(pokemonAttacker.powerOnEnnemies() && pokemonAttacker.powerWasAlreadyUsed()){
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
                        pokemonAttacker.usePower(opponent.getPokemonOnBattlefield(pokemonIndice));
                    }
                    else if(pokemonAttacker.powerOnHimself() && pokemonAttacker.powerWasAlreadyUsed()){
                        script = "";
                        script += "\nL'effet a été appliqué !";
                        super.usePokemonPower(pokemonAttacker, pokemonTarget);
                    }

                    if(pokemonAttacker.getPower().getName().equals("Already Seen") && !pokemonAttacker.getPower().getWasAlreadyUsed()){
                        script = "";
                        script += "\nL'effet a été appliqué !";
                        super.usePokemonPower(pokemonAttacker, pokemonTarget);
                    }
                    else if(pokemonAttacker.getPower().getName().equals("Ether Affinity") && !pokemonAttacker.getPower().getWasAlreadyUsed()){
                        script = "";
                        script += "\nL'effet a été appliqué !";
                        super.usePokemonPower(pokemonAttacker, pokemonTarget);
                    }
                    else if(pokemonAttacker.getPower().getName().equals("Fear") && !pokemonAttacker.getPower().getWasAlreadyUsed()){
                        script = "";
                        script += "\nSur quel pokemon adverse voulez vous utilisez le pouvoir de " + pokemonAttacker.getName();
                        script += " ? Entrez un chiffre (";
                        Integer pokemonIndice = inputUser(script,opponent.getBattlefieldPokemons());
                        super.usePokemonPower(pokemonAttacker,opponent.getPokemonOnBattlefield(pokemonIndice));
                    }

                    switch(pokemonAttacker.getPowerType()){
                    }

                }

            }

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
        System.out.println(script);
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
}
