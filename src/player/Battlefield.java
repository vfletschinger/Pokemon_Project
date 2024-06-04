package player;
import java.util.ArrayList;
import java.util.List;
import pokemon.*;
import power.PowerName;

public class Battlefield {
    private final List<Pokemon> _pokemons = new ArrayList<>();

    public Battlefield() {
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

    public String display() {
        String display = "";
        Integer maxLines = 113;
        Integer maxSlots = 3;
        boolean isTerritoryExtensionActivated = false;

        // une ligne d'affichage équivaut à 92 char max
        if(_pokemons.size() > 3){
            isTerritoryExtensionActivated = true;
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
            for (Pokemon pokemonDisplay : this._pokemons) {
                if(i == 1) {
                    display += displaySimpleLineBattlefield('#', '-', 33); // 28 char (*3)
                    display += "  "; // 2 char (*3)
                }
                else if(i == 2) {
                    
                    boolean isWarriorFervor = false;
                    if(pokemonDisplay.getPowerType() == PowerName.WARRIORFERVOR){
                        isWarriorFervor = true;
                    }
                    
                    if(isWarriorFervor && pokemonDisplay.getPowerType() != null && pokemonDisplay.powerWasAlreadyUsed()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[+10]             |  "; // 14 char (*3)
                    }
                    else if(pokemonDisplay.getPenalty()){
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "[-10]             |  "; // 14 char (*3)
                    }
                    else{
                        display += "|    Attaque: " + pokemonDisplay.getInitialAttack(); // 16 char (*3)
                        display += "                  |  "; // 14 char (*3)
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
                    if(pokemonDisplay.getPower() != null){
                        display += "|  Power: " + pokemonDisplay.getPowerName(); // 10 + x char (*3)
                        for (int k = 0; k < 24 - pokemonDisplay.getPowerName().length(); k++) {
                            display += " ";
                        }
                        display += "|  "; // 14 char (*3)
                    }
                    else{
                        display += "|  Power: Aucun                   |  ";
                    }
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
            for(int j = 0; j < maxSlots - _pokemons.size() ; j++){
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

    public Pokemon get(Integer index){
        return this._pokemons.get(index);
    }
    public String getName(Integer index){ return get(index).getName(); }
    public List<Pokemon> getPokemonList(){
        List<Pokemon> newPokemonList = new ArrayList<>(_pokemons);
        return newPokemonList;
    }
    public boolean isEmpty(){
        return this._pokemons.isEmpty();
    }
    public void remove(Pokemon pokemon){
        this._pokemons.remove(pokemon);
    }
    public void add(Pokemon pokemon) {this._pokemons.add(pokemon);}
    public Integer size(){
        return this._pokemons.size();
    }
}