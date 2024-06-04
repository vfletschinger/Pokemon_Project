package power;

public enum PowerName {
    ALREADYSEEN,
    ETHERTYPE,
    FEAR,
    KAMIKAZE,
    LEADTYPE,
    TERRITORYEXTENSION,
    TOTALHEAL,
    WARRIORFERVOR;

    //Returns the name of the powers

    public String get(){
        switch(this){
            case ALREADYSEEN: //sur lui même
                return "Already Seen";
            case ETHERTYPE: //sur lui même
                return "Ether Type";
            case FEAR: //sur un ennemi
                return "Fear";
            case KAMIKAZE: //sur un ennemi
                return "Kamikaze";
            case LEADTYPE: //sur un ennemi
                return "Lead Type";
            case TERRITORYEXTENSION:
                return "Territory Extension"; //sur lui même
            case TOTALHEAL:
                return "Total Heal"; // sur lui même ou un allié
            case WARRIORFERVOR:
                return "Warrior Fervor"; // sur lui même ou un allié
            default:
                return "";
        }
    }

    //Returns on which Pokemon was used the power

    public boolean onHimself(){
        switch(this){
            case ALREADYSEEN: //sur lui même
                return true;
            case ETHERTYPE: //sur lui même
                return true; //sur lui même
            case TOTALHEAL:
                return true; // sur lui même ou un allié
            default:
                return false;
        }
    }
    public boolean onEnemies(){
        switch(this){
            case FEAR: //sur un ennemi
                return true;
            case KAMIKAZE: //sur un ennemi
                return true;
            case LEADTYPE: //sur un ennemi
                return true;
            default:
                return false;
        }
    }
    public boolean onAllies(){
        switch(this){
            case TOTALHEAL:
                return true; // sur lui même ou un allié
            case WARRIORFERVOR:
                return true; // sur lui même ou un allié
            default:
                return false;
        }
    }
}
