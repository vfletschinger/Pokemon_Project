import player.*;
public class DisplayTurn {
    private Player _user;
    private Player _cpu;
    public DisplayTurn(Player user, Player cpu){
        _user = user;
        _cpu = cpu;
    }

    public String displayTurn(Integer whichTurn){
        String script = "\n";
        script += "##############################################################################################\n";
        script += "########################################## TURN " + whichTurn.toString() + " ############################################\n";
        script += "\n";
        //script += "CPU attacked\n\n";
        script += "PLAYER 1 (CPU) : \n";
        script += _cpu.displayBattlefield();
        script += "######################################## VERSUS ##############################################\n";
        script += _user.displayBattlefield();
        script += "\nPOWERS : \nWarriorFervor : choose an ally Pokemon. For the rest of the game or until it dies this Pokemon deals 10 more damage." +
                  "\nFear : choose an enemy Pokemon. For the rest of the game or until it dies this Pokemon deals 10 less damage." +
                  "\nTotalHeal : choose an ally Pokemon. This Pokemon regains all its life." +
                  "\nKamikaze : choose an enemy Pokemon. This Pokemon and the one that used this power die." +
                  "\nEtherAffinity : choose an ally Pokemon. The type of this Pokemon becomes Ether which is super effective against all other types." +
                  "\nLeadAffinity : choose an enemy Pokemon. The type of this Pokemon becomes Lead. All other types are super effective against it." +
                  "\nAlreadySeen : choose an ally Pokemon. This Pokemon can attack twice at the next turn." +
                  "\nTerritoryExtension : the battlefield of the player who owns this Pokemon gains a fourth slot on which the player can immediately play another Pokemon. When the Pokemon that has used this power dies, the battlefield again has 3 slots.\n";
        script += "\nPLAYER 2 (USER) : \n";
        script += _user.displayHand();

        return script;
    }

}
