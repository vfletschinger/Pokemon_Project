import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    private final List<Pokemon> _pokemonBattlefieldList = new ArrayList<>();
    public Battlefield(){
    }

     /* addPokemonToBattlefield : constructor : it creates an instance of the Pokemon class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addPokemonToBattlefield(Pokemon pokemon){
        for(int i = 0; i < _pokemonBattlefieldList.size(); i++){
            if(_pokemonBattlefieldList.get(i) == null){
                _pokemonBattlefieldList.set(i,pokemon);
                break;
            }
        }
    }

    public String displaySimpleLineBattlefield(char extrimity, char stroke, int lengthMinusTwo){

        String display = "";
        display += extrimity;

        for(int i = 0; i < lengthMinusTwo; i++){
            display += stroke;
        }
        display += extrimity;
        display += "\n";

        return display;
    }
    public String displayBattlefield(){

        String display = "";

        // une ligne d'affichage équivaut à 92 char max

        display += displaySimpleLineBattlefield('*','-',90); // 92 char
        display += displaySimpleLineBattlefield('|',' ',90); // 92 char
        display += displaySimpleLineBattlefield('|',' ',90); // 92 char

        for(int i = 1; i < 7; i++){
            display += "|  "; // 3 char
            for(int j = 0; i < 3; j++){
                if(_pokemonBattlefieldList.get(j) != null){
                    switch(i){
                        case 1:
                            display += displaySimpleLineBattlefield('#', '-',26); // 28 char (*3)
                            display += "  "; // 2 char (*3)
                        case 2:
                            display += "|    Attaque: " + _pokemonBattlefieldList.get(j).getAttack().toString(); // 16 char (*3)
                            display += "           |  "; // 14 char (*3)
                        case 3:
                            display +="|     Vie: " + _pokemonBattlefieldList.get(j).getLife().toString(); // 16 char (*3) (8)
                            for(int k = 0; k < 8 - _pokemonBattlefieldList.get(j).getName().length(); k++){
                                display += " ";
                            }
                            display +="|  "        ;
                        case 4:
                            display += "|    Affinite: " + _pokemonBattlefieldList.get(j).getAttack().toString(); // 16 char (*3) (12)
                            for(int k = 0; k < 12 - _pokemonBattlefieldList.get(j).getName().length(); k++){
                                display += " ";
                            }
                            display += "           |  "; // 14 char (*3)
                        case 5:
                            display += "|  " + _pokemonBattlefieldList.get(j).getName(); // 2 + x char (*3)  (24)
                            for(int k = 0; k < 24 - _pokemonBattlefieldList.get(j).getName().length(); k++){
                                display += " ";
                            }
                            display += "| "; // 14 char (*3)
                        case 6:
                            display += displaySimpleLineBattlefield('#', '-',26); // 28 char (*3)
                            display += "  "; // 2 char (*3)
                    }
                }
                else{
                    display += displaySimpleLineBattlefield(' ',' ',88);
                }
            }
            display += "|\n";
        }

        display += displaySimpleLineBattlefield('|',' ',90); // 92 char
        display += displaySimpleLineBattlefield('|',' ',90); // 92 char
        display += displaySimpleLineBattlefield('*','-',90); // 92 char

        return display;
    }

    public Pokemon chooseCurrentPokemon(String pokemonName){

        for(int i = 0; i < _pokemonBattlefieldList.size(); i++){
            if(_pokemonBattlefieldList.get(i) != null && _pokemonBattlefieldList.get(i).getName().equals(pokemonName)){
                return _pokemonBattlefieldList.get(i);
            }
        }
        return  null;

    }

    public List<Pokemon> getPokemonBattlefieldList() {
        return _pokemonBattlefieldList;
    }
}
