package pokemon;
import java.util.ArrayList;
import java.util.Random;

import power.*;

public class Pokemon {
    private final String _name;

    private PokemonType _affinity;
    private Integer _life;
    private final Integer _initialLife;
    private Integer _attack;
    private final Integer _initialAttack;

    private Boolean _isDead;
    private Integer _attacksLeft;

    private Boolean _hasExtends;

    private Power _power;


    /* Pokemon : constructor : it creates an instance of the Pokemon class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public Pokemon(String name){

        _name = name;

        //Creation of a list containing a number of values equals to the number of type in the enumeration PokemonType
        ArrayList<PokemonType> listType = new ArrayList<PokemonType>();
        for (PokemonType type : PokemonType.values() ){
            listType.add(type);
        }
        //Using the function Random to affect a random type from the list
        Random random = new Random();
        _affinity = listType.get(random.nextInt(listType.size()));

        //Using the function Random and a list of possible life value to affect the life of the Pokemon
        ArrayList<Integer> lifeValues = new ArrayList<Integer>();
        for (Integer i = 100; i<= 200; i+=10){
            lifeValues.add(i);
        }
        int lf = lifeValues.get(random.nextInt(lifeValues.size()));
        _life = lf;

        //Using the function Random and a list of possible attack value to affect the attack of the Pokemon
        ArrayList<Integer> attackValues = new ArrayList<Integer>();
        for (Integer j = 10 ; j <=40 ; j+=10){
            attackValues.add(j);
        }
        int atk = attackValues.get(random.nextInt(attackValues.size()));
        _attack = atk;

        _initialAttack = atk;
        _initialLife = lf;
        _isDead = false;
        _attacksLeft = 1;
        _hasExtends = false;
        _power = null;
    }

    public Pokemon(String name, Power power){
        this(name);
        _power = power;
    }

    /* attackPokemon : function : void : when a Pokemon attacks another one, it will do damage so
     * in consequence the targeted pokemon's life will decrease
     * param :
     *  pokemon : Pokemon : an instance of a Pokemon class, it's the targeted pokemon
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void attackPokemon(Pokemon pokemon){
        pokemon._life -= this._attack;
    }

    /* attackPokemon : function : void : when a Pokemon attacks another one, it will do damage so
     * in consequence the targeted pokemon's life will decrease
     * param :
     *  pokemon : Pokemon : an instance of a Pokemon class, it's the targeted pokemon
     *  bonus : Integer: a bonus of attack than can be negative or positive
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void attackPokemon(Pokemon pokemon, Integer bonus){
        pokemon._life -= this._attack + bonus;
    }

    public boolean isKO(){
        if(this.getLife() <= 0){
            return true;
        }

        return false;
    }

    /* getName : function : Integer : it gets you the name of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._name : the name of a Pokemon instance
     */
    public String getName(){
        return this._name;
    }

    /* getAffinity : function : Integer : it gets you the affinity of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._affinity : the affinity of a Pokemon instance
     */
    public PokemonType getAffinity(){
        return this._affinity;
    }

    /* getLife : function : Integer : it gets you the life of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._life : the life of a Pokemon instance
     */
    public Integer getLife(){
        return this._life;
    }

    /* getAttack : function : Integer : it gets you the attack of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  this._attack : the attack of a Pokemon instance
     */
    public Integer getAttack(){
        return this._attack;
    }

    public void addAttack(Integer value){
        _attack += value;
    }

    public void resetLife(){
        _life = _initialLife;
    }
    public void killPokemon(){
        _isDead = true;
    }

    public void becomeEtherAffinity(){
        _affinity = PokemonType.ETHER;
    }

    public void becomeLeadAffinity(){
        _affinity = PokemonType.LEAD;
    }

    public void increaseNumberOfAttacksLeft(){
        _attacksLeft ++;
    }

    public void extendsTerritory(){
        _hasExtends = true;
    }
}
