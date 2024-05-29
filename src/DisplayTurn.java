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
        script += "############################################## TURN " + whichTurn.toString() + " ########################################\n";
        script += "\n";
        //script += "CPU attacked\n\n";
        script += "PLAYER 1 (CPU) : \n";
        script += _cpu.getBattlefield().displayBattlefield();
        script += "######################################## VERSUS ##############################################\n";
        script += _user.getBattlefield().displayBattlefield();
        script += "\nPLAYER 2 (USER) : \n";
        script += _user.displayHand();

        return script;
    }

}
