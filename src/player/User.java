package player;
import java.util.Scanner;
import pokemon.*;
import player.*;
public class User extends Player {
    public User(Draw draw, Hand hand){
		super(draw,hand);
	}

	@Override
	public String displayHand() {
        String display = "En main:\n";
        for (int i = 0; i < super.getHand().getPokemonHand().size(); i++) {
            display += "- " + super.getHand().getPokemonHand().get(i).getName() + ", " + super.getHand().getPokemonHand().get(i).getAffinity() + ", ";
            display += "Vie : " + super.getHand().getPokemonHand().get(i).getLife() + ", Attaque : " + super.getHand().getPokemonHand().get(i).getAttack() + "\n";
        }

        display += "\n";

        return display;
    }

    @Override
    public void turn(Player opponent){
        for(int j = 0; j < getBattlefield().getPokemonBattlefieldList().size(); j++){
            Scanner input = new Scanner(System.in);

            String script = "";
            script += "\nQuel Pokemon voulez-vous jouez ? Entrez un chiffre (";
            for(int i = 0; i < this.getBattlefield().getPokemonBattlefieldList().size(); i++){
                script += i + " " + this.getBattlefield().getPokemonBattlefieldList().get(i).getName();
                if(i == this.getBattlefield().getPokemonBattlefieldList().size() - 1){
                    script += ")";
                }
                else{
                    script += "/";
                }
            }
            script += ": ";
            System.out.print(script);

            Integer pokemonUserIndice = input.nextInt();
            System.out.println();

            Pokemon pokemonAttacker = this.getBattlefield().chooseCurrentPokemon(pokemonUserIndice);

            script = "";
            script += "\nQuel Pokemon voulez-vous attaquer ? Entrez un chiffre (";
            for(int i = 0; i < opponent.getBattlefield().getPokemonBattlefieldList().size(); i++){
                script += i + " " + opponent.getBattlefield().getPokemonBattlefieldList().get(i).getName();
                if(i == opponent.getBattlefield().getPokemonBattlefieldList().size() - 1){
                    script += ")";
                }
                else{
                    script += "/";
                }
            }
            script += ": ";
            System.out.print(script);
            Integer pokemonOpponentIndice = input.nextInt();
            System.out.println();
            Pokemon pokemonOpponent = opponent.getBattlefield().chooseCurrentPokemon(pokemonOpponentIndice);

            action(opponent,pokemonOpponent,pokemonAttacker);
        }
    }
    private void action(Player opponent,Pokemon pokemonTarget, Pokemon pokemonAttacker){
            Integer bonus = 0;

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

            int damage = pokemonAttacker.getAttack() + bonus;
            script += "\n-" + damage + " à " + pokemonTarget.getName();

            pokemonAttacker.attackPokemon(pokemonTarget,bonus);

            script += "\nLa vie de " + pokemonTarget.getName() + " du CPU est égale à " + pokemonTarget.getLife();
            System.out.println(script);

            if(pokemonTarget.isKO()){
                System.out.println("Vous avez tué un pokemon de l'ordinateur");
                script = pokemonAttacker.getName() + " a tué " + pokemonTarget.getName() + " du CPU";
                System.out.println(script);
                opponent.addPokemonToDiscard(pokemonTarget);
                opponent.getBattlefield().getPokemonBattlefieldList().remove(pokemonTarget);
                opponent.getBattlefield().addPokemonToBattlefield(opponent.getHand().getPokemonHand().remove(0));
                if(opponent.getDraw().getPokemonDraw().size() != 0){
                    opponent.getHand().addPokemonToHand(opponent.getDraw().getPokemonDraw().get(0));
                }
            }
    }

}
