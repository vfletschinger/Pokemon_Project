public enum PokemonType {
    AIR,  // 1
    EARTH, // 2
    WATER, // 3
    FIRE; // 4

    public boolean isStrongAgainst(PokemonType otherType){

        switch(this){
            case AIR:
                return otherType == EARTH;
            case EARTH:
                return otherType == WATER;
            case WATER:
                return otherType == FIRE;
            case FIRE:
                return otherType == AIR;
            default:
                return false;
        }

    }
}
