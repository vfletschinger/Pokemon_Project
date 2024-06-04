package player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pokemon.*;
import power.PowerName;

public class CPU extends Player {

    //Constructor
    public CPU(Draw draw, Hand hand){
        super(draw,hand);
    }

    /* turn : function : void : plays the turn of a CPU
     * param :
     *  opponent : Player : the player that the CPU attacks
     * local :
     *  NONE
     * return :
     *  NONE
     */
    @Override
    public void turn(Player opponent) {
        List<Pokemon> opponentPokemons = opponent.getBattlefieldPokemons();
        int attackNumber = 0;

        for(Pokemon pokemon: getBattlefieldPokemons()){
            if(pokemon.getPower() != null){
                usePower(opponent,pokemon);
            }
        }

        for(Pokemon cpuPokemon : this.getBattlefieldPokemons()){
            attackNumber ++;
            Pokemon pokemonTarget = pokemonTarget(cpuPokemon,opponentPokemons);

            action(cpuPokemon,pokemonTarget,attackNumber);

            if(pokemonTarget.isKO()){

                System.out.println("L'ordinateur a tué un de vos pokémons :");
                String
                        script = "\n" + cpuPokemon.getName() + " a tué " + pokemonTarget.getName() + " de l'utilisateur";
                System.out.println(script);
                System.out.println();

                opponent.addPokemonToDiscard(pokemonTarget);
                opponentPokemons.remove(pokemonTarget);
            }
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
    private void action(Pokemon cpuPokemon, Pokemon pokemonTarget, Integer attackNumber){
        Integer bonus = 0;
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
        boolean isWarriorFervor = false;

        if(cpuPokemon.getPower() != null){
            if(cpuPokemon.getPowerType() == PowerName.WARRIORFERVOR){
                isWarriorFervor = true;
            }
        }

        if(isWarriorFervor && cpuPokemon.powerWasAlreadyUsed()){
            script += "\nThanks to Warrior Fervor, your " + cpuPokemon.getName() + " gets +10 of attack !";
            Integer bonusAttack = 10;
            script += "\n-" + damage + "[+" + bonusAttack + "]" + " à " + pokemonTarget.getName();
        }
        else if(cpuPokemon.getPenalty()){
            script += "\nDue to Warrior Fervor, your " + cpuPokemon.getName() + " gets -10 of attack :(";
            Integer penaltyAttack = 10;
            script += "\n-" + damage + "[+" + penaltyAttack + "]" + " à " + pokemonTarget.getName();
        }
        else{
            script += "\n-" + damage + " à " + pokemonTarget.getName();
        }

        cpuPokemon.attackPokemon(pokemonTarget,bonus);

        script += "\nLa vie de " + pokemonTarget.getName() + "de l'utilisateur est égale à " + pokemonTarget.getLife();
        System.out.println(script);
    }

    /* pokemonTarget : function : Pokemon : search the best target for the CPU
     * param :
     *  attackingPokemon : Pokemon : Pokemon who will attack
     *  opponentPokemons : List<Pokemon> : Pokemon list of the opponent
     * local :
     *  NONE
     * return :
     *  Pokemon : Pokemon who will be targeted
     */
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

    /* usePower : function : void : use the power of a Pokemon to an enemy Pokemon
     * param :
     *  opponent : Player : opponent Player
     *  cpuPokemon : Pokemon : Pokemon who uses its power
     * local :
     *  NONE
     * return :
     *  NONE
     */
    private void usePower(Player opponent, Pokemon cpuPokemon){

        Pokemon opponentPokemon = pokemonTarget(cpuPokemon,opponent.getBattlefieldPokemons());
        String script = "";

        if(cpuPokemon.getPower() != null){
            if(cpuPokemon.getPowerType() == PowerName.TOTALHEAL && !cpuPokemon.powerWasAlreadyUsed()) {
                Integer minimumLifePoints = 999;
                List<Pokemon> pokemonsToHeal = new ArrayList<>();

                for (Pokemon pokemon : this.getBattlefieldPokemons()) {
                    if (pokemon.getLife() < minimumLifePoints) {
                        minimumLifePoints = pokemon.getLife();
                        pokemonsToHeal.add(pokemon);
                    } else if (pokemon.getLife().equals(minimumLifePoints)) {
                        pokemonsToHeal.add(pokemon);
                    }
                }

                Pokemon pokemonAllie = pokemonsToHeal.getFirst();
                usePokemonPower(cpuPokemon,pokemonAllie);

                script = "L'utilisateur a utilisé le pouvoir " + cpuPokemon.getPowerName() + " sur ";
                script += pokemonAllie.getName();
            }
            else if(opponentPokemon != null){
                script = "L'utilisateur a utilisé le pouvoir " + cpuPokemon.getPowerName() + " sur ";
                if(cpuPokemon.powerOnHimself()){
                    usePokemonPower(cpuPokemon,cpuPokemon);
                    script += "lui même";
                }
                else if(cpuPokemon.powerOnAllies()){
                    usePokemonPower(cpuPokemon,this.getPokemonOnBattlefield(0));
                    script += getPokemonNameOnBattlefield(0) + " !";
                }
                else if(cpuPokemon.powerOnEnemies()) {
                    usePokemonPower(cpuPokemon,opponentPokemon);
                    script += opponentPokemon + " !";
                }
            }

            if(cpuPokemon.getPowerType() == PowerName.TERRITORYEXTENSION){
                addPokemonOnBattlefield(takeNextPokemonOnHand());
                addPokemonOnHand(takeNextPokemonOnDraw());
            }
            if(cpuPokemon.getPowerType() == PowerName.KAMIKAZE && cpuPokemon.powerWasAlreadyUsed()){
                        script = cpuPokemon.getName() + " explose avec " + opponentPokemon.getName() + "\n";

                        opponent.addPokemonToDiscard(cpuPokemon);
                        opponent.removePokemonOnBattlefield(cpuPokemon);
                        opponent.addPokemonOnBattlefield(this.takeNextPokemonOnHand());
                        if(!opponent.drawIsEmpty())
                            opponent.addPokemonOnHand(this.takeNextPokemonOnHand());

                        this.addPokemonToDiscard(opponentPokemon);
                        removePokemonOnBattlefield(opponentPokemon);
            }
        }
        System.out.println(script);
    }
}
