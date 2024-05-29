package pokemon;
public enum PokemonType {
    AIR,  // 1
    EARTH, // 2
    WATER, // 3
    FIRE, // 4
    ETHER,
    LEAD;
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
                return false;
            default:
                return false;
        }

    }
}
