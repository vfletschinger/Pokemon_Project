import player.*;
public class DisplayTurn {

    //Constructor
    public DisplayTurn(){
    }

    /* displayTurn : function : String : returns the display of a turn
     * param :
     *  whichTurn : Integer : indicates which turn it is
     * local :
     *  NONE
     * return :
     *  String : the display of the turn
     */
    public void displayTurn(Integer whichTurn, Player p1, Player p2){
        String script = "\n";
        script += "##################################################################################################################\n";
        script += "#################################################### TURN " + whichTurn.toString() + " ######################################################\n";
        System.out.println(script);
        p1.inputContinue();
        script = "\nLIST OF THE POWERS : \nWarriorFervor : choose an ally Pokemon. For the rest of the game or until it dies this Pokemon deals 10 more damage." +
                  "\nFear : choose an enemy Pokemon. For the rest of the game or until it dies this Pokemon deals 10 less damage." +
                  "\nTotalHeal : choose an ally Pokemon. This Pokemon regains all its life." +
                  "\nKamikaze : choose an enemy Pokemon. This Pokemon and the one that used this power die." +
                  "\nEtherAffinity : choose an ally Pokemon. The type of this Pokemon becomes Ether which is super effective against all other types." +
                  "\nLeadAffinity : choose an enemy Pokemon. The type of this Pokemon becomes Lead. All other types are super effective against it." +
                  "\nAlreadySeen : choose an ally Pokemon. This Pokemon can attack twice at the next turn." +
                  "\nTerritoryExtension : the battlefield of the player who owns this Pokemon gains a fourth slot on which the player can immediately play another Pokemon. When the Pokemon that has used this power dies, the battlefield again has 3 slots.\n";
        System.out.println(script);
        p1.inputContinue();
        //script += "CPU attacked\n\n";
        script = "\nPLAYER 1 (CPU) : \n";
        script += p2.displayBattlefield();
        script += "################################################## VERSUS ########################################################\n";
        script += p1.displayBattlefield();
        script += "\nPLAYER 2 (USER) : \n";
        System.out.print(script);
        p1.inputContinue();
    }

}
