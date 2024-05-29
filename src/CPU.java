import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CPU extends Player {

    public CPU(Draw draw, Hand hand){
        super(draw,hand);
    }

    @Override
    public void turn(Player opponent) {
        List<Pokemon> opponentPokemons = opponent.getBattlefield().getPokemonBattlefieldList();
        int attackNumber = 0;

        for(Pokemon cpuPokemon : this.getBattlefield().getPokemonBattlefieldList()){
            attackNumber ++;
            Integer bonus = 0;
            Pokemon pokemonTarget = pokemonTarget(cpuPokemon,opponentPokemons);

            String script = "Attaque numéro " + attackNumber + ":\n";
            script += "L'ordinateur attaque avec " + cpuPokemon.getName();
            script += "\n"+ cpuPokemon.getName() + " attaque " + pokemonTarget.getName() + " de l'utilisateur";

            if(cpuPokemon.getAffinity().isStrongAgainst(pokemonTarget.getAffinity())){
                bonus += 10;
                script += "\nC'est super efficace !";
            }
            else if (pokemonTarget.getAffinity().isStrongAgainst(cpuPokemon.getAffinity())){
                bonus -= 10;
                script += "\nCe n'est pas très efficace !";
            }

            int damage = cpuPokemon.getAttack() + bonus;
            script += "\n-" + damage + " à " + pokemonTarget.getName();

            cpuPokemon.attackPokemon(pokemonTarget,bonus);

            script += "\nLa vie de " + pokemonTarget.getName() + "de l'utilisateur est égale à " + pokemonTarget.getLife();
            System.out.println(script);

            if(pokemonTarget.isKO()){

                System.out.println("L'ordinateur a tué un de vos pokémons :");
                script = "\n" + cpuPokemon.getName() + " a tué " + pokemonTarget.getName() + " de l'utilisateur";
                System.out.println(script);

                System.out.println();
                opponent.addPokemonToDiscard(pokemonTarget);
                opponentPokemons.remove(pokemonTarget);
            }
        }
    }

    private Pokemon pokemonTarget(Pokemon attackingPokemon, List<Pokemon> opponentPokemons){
        Random random = new Random();
        List<Pokemon> pokemonsTarget = new ArrayList<>();
        Integer minimumLifePoints;

        // We search if there's pokemon who's weak against the cpu pokemon
        for (Pokemon opponentPokemon : opponentPokemons){
            if(attackingPokemon.getAffinity().isStrongAgainst(opponentPokemon.getAffinity())){
                pokemonsTarget.add(opponentPokemon);
            }
        }

        // if yes, We search the one's who has the lowest life
        if(!pokemonsTarget.isEmpty()) {
            List<Pokemon> newPokemonTarget = new ArrayList<>();
            minimumLifePoints = 999;
            for(Pokemon opponentPokemon : pokemonsTarget){
                if(opponentPokemon.getLife() < minimumLifePoints){
                    newPokemonTarget.clear();
                    minimumLifePoints = opponentPokemon.getLife();
                    newPokemonTarget.add(opponentPokemon);
                }
                // if there's more that one pokemon with the same life that are weak
                else if(opponentPokemon.getLife().equals(minimumLifePoints)){
                    newPokemonTarget.add(opponentPokemon);
                }
            }
            // then we choose randomly in the created list
            return newPokemonTarget.get(random.nextInt(newPokemonTarget.size()));
        }

        minimumLifePoints = 999;
        for(Pokemon opponentPokemon : opponentPokemons){
            if(opponentPokemon.getLife() < minimumLifePoints){
                pokemonsTarget.clear();
                minimumLifePoints = opponentPokemon.getLife();
                pokemonsTarget.add(opponentPokemon);
            }
            else if(opponentPokemon.getLife().equals(minimumLifePoints)){
                pokemonsTarget.add(opponentPokemon);
            }
        }

        if (!pokemonsTarget.isEmpty()){
            return pokemonsTarget.get(random.nextInt(pokemonsTarget.size()));
        }

        if (!opponentPokemons.isEmpty()){
            return opponentPokemons.get(random.nextInt(opponentPokemons.size()));
        }

        return null;
    }

}
