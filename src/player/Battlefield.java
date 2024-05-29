package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
public class Battlefield {
    private final List<Pokemon> pokemonBattlefield = new ArrayList<>();
    private final Integer maxSlots = 3;
    public Battlefield() {
    }

    /* addPokemonToBattlefield : constructor : it creates an instance of the Pokemon class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addPokemonToBattlefield(Pokemon pokemon) {
        this.pokemonBattlefield.add(pokemon);
    }

    public String displaySimpleLineBattlefield(char extrimity, char stroke, int lengthMinusTwo) {

        String display = "";
        display += extrimity;

        for (int i = 0; i < lengthMinusTwo; i++) {
            display += stroke;
        }
        display += extrimity;

        return display;
    }

    public String displayBattlefield() {
        String display = "";
        Integer maxLines = 113;
        Integer maxSlots = 3;
        boolean isPowerActivated = false;

        // une ligne d'affichage équivaut à 92 char max
        if(pokemonBattlefield.size() > 3){
            isPowerActivated = true;
            maxLines += 37;
            maxSlots = 4;
        }

        display += displaySimpleLineBattlefield('*', '-', maxLines); // 115 (+37) char
        display += "\n";
        display += displaySimpleLineBattlefield('|', ' ', maxLines); // 115 (+37) char
        display += "\n";
        display += displaySimpleLineBattlefield('|', ' ', maxLines); // 115 (+37) char
        display += "\n";


        for (int i = 1; i < 8; i++) {
            display += "|  "; // 3 char
            for (Pokemon pokemonDisplay : this.pokemonBattlefield) {
                if(i == 1) {
                    display += displaySimpleLineBattlefield('#', '-', 35); // 28 char (*3)
                    display += "  "; // 2 char (*3)
                }
                else if(i == 2) {
                    if(pokemonDisplay.getPower().getName().equals("Warrior Fervor") && pokemonDisplay.getPower().getWasAlreadyUsed()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[+10]      |  "; // 14 char (*3)
                    }
                    else if(pokemonDisplay.getPenalty()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[-10]      |  "; // 14 char (*3)
                    }
                    else{
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "           |  "; // 14 char (*3)
                    }
                }
                else if(i == 3) {
                    display += "|    Vie: " + pokemonDisplay.getLife().toString(); // 10 + 1/2/3 char (*3) (8)
                    switch(pokemonDisplay.getLife().toString().length()){
                        case 1:
                            display += "                       ";
                            break;
                        case 2:
                            display += "                      ";
                            break;
                        case 3:
                            display += "                     ";
                            break;
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 4) {
                    display += "|    Affinite: " + pokemonDisplay.getAffinity().toString(); // 16 char (*3) (12)
                    for (int k = 0; k < 19 - pokemonDisplay.getAffinity().toString().length(); k++) {
                        display += " ";
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 5) {
                    // Show the power
                    display += "|  Power: " + pokemonDisplay.getPower().getName(); // 10 + x char (*3)
                    for (int k = 0; k < 26 - pokemonDisplay.getName().length(); k++) {
                        display += " ";
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 6) {
                    display += "|  " + pokemonDisplay.getName(); // 2 + x char (*3)  (24)
                    for (int k = 0; k < 31 - pokemonDisplay.getName().length(); k++) {
                        display += " ";
                    }
                    display += "|  "; // 14 char (*3)
                }
                else if(i == 7) {
                    display += displaySimpleLineBattlefield('#', '-', 33); // 35 char (*3)
                    display += "  "; // 2 char (*3)
                }
            }
            for(int j = 0; j < maxSlots - pokemonBattlefield.size() ; j++){
                // if there's less than 2 or 3 pokemon on battlefield
                display += "                                     ";
            }

            display += "|\n";
        }

        display += displaySimpleLineBattlefield('|', ' ', maxLines);
        display += "\n";
        display += displaySimpleLineBattlefield('*','-',maxLines); // 94 char
        display += "\n";

        return display;
    }

    public Pokemon chooseCurrentPokemon(Integer pokemonIndice){
        return pokemonBattlefield.get(pokemonIndice);
    }

    public List<Pokemon> getPokemonBattlefieldList() {
        return pokemonBattlefield;
    }

    public Integer getMaxSlots() {
        return maxSlots;
    }
}