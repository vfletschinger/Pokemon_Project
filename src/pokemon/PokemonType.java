package pokemon;
public enum PokemonType {
    AIR,  // 1
    EARTH, // 2
    WATER, // 3
    FIRE, // 4
    ETHER,
    LEAD;

    /* isStrongAgainst : function : Boolean : return true if the type of a Pokemon is strong against another one
     * param :
     *  otherType : PokemonType : the type of the other Pokemon
     * local :
     *  NONE
     * return :
     *  Boolean : return true if the type of a Pokemon is strong against another one
     */
    public boolean isStrongAgainst(PokemonType otherType){
        switch(this){
            case AIR:
                return (otherType == EARTH || otherType == LEAD);
            case EARTH:
                return (otherType == WATER || otherType == LEAD);
            case WATER:
                return (otherType == FIRE || otherType == LEAD);
            case FIRE:
                return (otherType == AIR || otherType == LEAD);
            case ETHER:
                return otherType != ETHER;
            case LEAD:
            default:
                return false;
        }
    }
}
