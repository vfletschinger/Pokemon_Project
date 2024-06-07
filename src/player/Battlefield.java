package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.PowerName;

public class Battlefield {
    private final List<Pokemon> _pokemons = new ArrayList<>();

    //Constructor
    public Battlefield() {
    }


    /* displaySimpleLineBattlefield : function : String : returns the display of a Battlefield
     * param :
     *  extremity : char : the extremity of a String for the display
     *  stroke : char : a line between the extremities
     *  lengthMinusTwo : int : the length of the stroke (length - extremities length)
     * local :
     *  NONE
     * return :
     *  String : the display of a Battlefield
     */
    public String displaySimpleLineBattlefield(char extremity, char stroke, int lengthMinusTwo) {

        String display = "";
        display += extremity;

        for (int i = 0; i < lengthMinusTwo; i++) {
            display += stroke;
        }
        display += extremity;

        return display;
    }

    /* display : function : String : returns the display of the battlefield
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the display of the battlefield
     */
    public String display() {
        String display = "";
        Integer maxLines = 125;
        Integer maxSlots = 3;
        boolean isTerritoryExtensionActivated = false;

        // une ligne d'affichage équivaut à 92 char max
        if(_pokemons.size() > 3){
            isTerritoryExtensionActivated = true;
            maxLines += 41;
            maxSlots = 4;
        }

        display += displaySimpleLineBattlefield('*', '-', maxLines); // 115 (+37) char
        display += "\n";
        display += displaySimpleLineBattlefield('|', ' ', maxLines); // 115 (+37) char
        display += "\n";

        for (int i = 1; i < 8; i++) {
            display += "|  "; // 3 char
            for (Pokemon pokemonDisplay : this._pokemons) {
                if(i == 1) {
                    display += displaySimpleLineBattlefield('#', '-', 37); // 28 char (*3)
                    display += "  "; // 2 char (*3)
                }
                else if(i == 2) {
                    
                    boolean isWarriorFervor = false;
                    if(pokemonDisplay.getPowerType() == PowerName.WARRIORFERVOR){
                        isWarriorFervor = true;
                    }
                    
                    if(isWarriorFervor && pokemonDisplay.getPowerType() != null && pokemonDisplay.powerWasAlreadyUsed()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[+10]                 |  "; // 14 char (*3)
                    }
                    else if(pokemonDisplay.getPenalty()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[-10]                 |  "; // 14 char (*3)
                    }
                    else{
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "                      |  "; // 14 char (*3)
                    }
                }
                else if(i == 3) {
                    display += "|    Vie: " + pokemonDisplay.getLife().toString(); // 10 + 1/2/3 char (*3) (8)
                    switch(pokemonDisplay.getLife().toString().length()){
                        case 1:
                            display += "                           ";
                            break;
                        case 2:
                            display += "                          ";
                            break;
                        case 3:
                            display += "                         ";
                            break;
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 4) {
                    display += "|    Affinite: " + pokemonDisplay.getAffinity().toString(); // 16 char (*3) (12)
                    for (int k = 0; k < 23 - pokemonDisplay.getAffinity().toString().length(); k++) {
                        display += " ";
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 5) {
                    // Show the power
                    if(pokemonDisplay.getPower() != null){
                        if(pokemonDisplay.powerWasAlreadyUsed()){
                            display += "|  Power: " + pokemonDisplay.getPowerName(); // 10 + x char (*3)
                            display += " [USED]  "; // + 9 char
                            for (int k = 0; k < 19 - pokemonDisplay.getPowerName().length(); k++) {
                                display += " ";
                            }
                            display += "|  "; // 14 char (*3)
                        }
                        else{
                            display += "|  Power: " + pokemonDisplay.getPowerName(); // 10 + x char (*3)
                            for (int k = 0; k < 28 - pokemonDisplay.getPowerName().length(); k++) {
                                display += " ";
                            }
                            display += "|  "; // 14 char (*3)
                        }
                    }
                    else{
                        display += "|  Power: Aucun                       |  ";
                    }
                }
                else if(i == 6) {
                    display += "|  " + pokemonDisplay.getName(); // 2 + x char (*3)  (24)
                    for (int k = 0; k < 35 - pokemonDisplay.getName().length(); k++) {
                        display += " ";
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 7) {
                    display += displaySimpleLineBattlefield('#', '-', 37); // 35 char (*3)
                    display += "  "; // 2 char (*3)
                }
            }
            for(int j = 0; j < maxSlots - _pokemons.size() ; j++){
                // if there's less than 2 or 3 pokemon on battlefield
                display += "                                         ";
            }

            display += "|\n";
        }

        display += displaySimpleLineBattlefield('|', ' ', maxLines);
        display += "\n";
        display += displaySimpleLineBattlefield('*','-',maxLines); // 94 char
        display += "\n";

        return display;
    }

    /* get : function : Pokemon : return the Pokemon at a certain index
     * param :
     *  index : Integer : index of the Pokemon to return
     * local :
     *  NONE
     * return :
     *  Pokemon : the Pokemon at a certain index
     */
    public Pokemon get(Integer index){
        return this._pokemons.get(index);
    }

    /* getName : function : String : return the name of the Pokemon at a certain index
     * param :
     *  index : Integer : the index of the Pokemon
     * local :
     *  NONE
     * return :
     *  String : the name of the Pokemon at a certain index
     */
    public String getName(Integer index){ return get(index).getName(); }

    /* getPokemonList : function : List<Pokemon> : return the list of the Pokemon of the battlefield to display
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  List<Pokemon> : the list of the Pokemon of the battlefield
     */
    public List<Pokemon> getPokemonList(){
        List<Pokemon> newPokemonList = new ArrayList<>(_pokemons);
        return newPokemonList;
    }

    /* isEmpty : function : Boolean : return true if the battlefield is empty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : return true is the battlefield is empty
     */
    public boolean isEmpty(){
        return this._pokemons.isEmpty();
    }

    /* remove : function : void : remove a Pokemon
     * param :
     *  pokemon : Pokemon : Pokemon to remove
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void remove(Pokemon pokemon){
        this._pokemons.remove(pokemon);
    }

    /* add : function : void : add a Pokemon to the battlefield
     * param :
     *  pokemon : Pokemon : Pokemon to add to the battlefield
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void add(Pokemon pokemon) {this._pokemons.add(pokemon);}

    /* size : function : Integer : return the number of Pokemons on the battlefield
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : the number of Pokemons on the battlefield
     */
    public Integer size(){
        return this._pokemons.size();
    }

    /* toString : function : String : returns a recap of the class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : recap of the class
     */
    public String toString (){
        String res = "";
        for (Pokemon pokemon : _pokemons) {
            res += pokemon + " ";
        }
        return res;
    }
}