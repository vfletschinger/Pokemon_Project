package pokemon;
import java.util.ArrayList;
import java.util.Random;

import power.*;

public class Pokemon {
    private final String _name;
    private PokemonType _type;
    private Integer _life;
    private final Integer _initialLife;
    private Integer _attack;
    private Integer _initialAttack;
    private boolean _penalty = false;
    private Integer _attacksLeft;
    private Power _power;

    //Constructor
    public Pokemon(String name){

        _name = name;

        //Creation of a list containing a number of values equals to the number of type in the enumeration PokemonType
        ArrayList<PokemonType> listType = new ArrayList<PokemonType>();
        for (PokemonType type : PokemonType.values() ){
            listType.add(type);
        }
        //Using the function Random to affect a random type from the list
        Random random = new Random();
        _type = listType.get(random.nextInt(4));

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
        _attacksLeft = 1;
        _power = null;
    }

    //Constructor when the Pokemon has a power

    public Pokemon(String name, Power power){
        this(name);
        _power = power;
    }

    /* attackPokemon : function : void : when a Pokemon attacks another one, it decreases the life of the other Pokemon (with a bonus if a power was used)
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
        if(pokemon._life < 0){
            setLife(0);
        }
    }

    /* isKO : function : Boolean : returns true if the Pokemon is still alive
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : if the Pokemon is KO or not
     */
    public boolean isKO(){
        return this.getLife() <= 0;
    }

    /* getName : function : String : it gets you the name of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the name of a Pokemon instance
     */
    public String getName(){
        return this._name;
    }

    /* getAffinity : function : PokemonType : it gets you the affinity of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  PokemonType : the affinity of a Pokemon instance
     */
    public PokemonType getAffinity(){
        return this._type;
    }

    /* getLife : function : Integer : it gets you the life of a Pokemon instance
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : the life of a Pokemon instance
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
     *  Integer : the attack of a Pokemon instance
     */
    public Integer getAttack(){
        return this._attack;
    }

    /* usePower : function : void : it uses the power of the Pokemon
     * param :
     *  otherPokemon : Pokemon : it's the Pokemon on which this Pokemon uses its power
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void usePower(Pokemon otherPokemon) {
        this._power.use(this,otherPokemon);
    }

    /* getPowerName : function : String : returns the name of the power
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : the name of this Pokemon's power
     */
    public String getPowerName(){
        if(this._power != null){
            return this._power.getName();
        }
        else{
            return null;
        }
    }
    /* getPowerType : function : PowerName : it gets you the type of the power
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  PowerName : the type of this Pokemon's power
     */
    public PowerName getPowerType(){
        if(this._power != null){
            return this._power.getType();
        }
        else{
            return null;
        }
    }
    /* getPenalty : function : Boolean : returns if this Pokemon has a penalty
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if this Pokemon has a penalty
     */
    public Boolean getPenalty() { return _penalty;}

    /* getInitialAttack : function : Integer : returns the initial attack of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : returns the initial attack of this Pokemon
     */
    public Integer getInitialAttack() { return _initialAttack; }

    /* addAttack : function : void : add attack to this Pokemon
     * param :
     *  value : Integer : value to add to the attack of this Pokemon
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void addAttack(Integer value){
        _attack += value;
    }

    /* setLife : function : void : set life to this Pokemon
     * param :
     *  value : Integer : value to set to the life of this Pokemon
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void setLife(Integer value) { _life = value; }

    /* setPenalty : function : void : set penalty to this Pokemon
     * param :
     *  value : Boolean : value to set to the penalty of this Pokemon
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void setPenalty(Boolean value) { _penalty = value;}

    /* resetLife : function : void : reset the life of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void resetLife(){
        _life = _initialLife;
    }

    /* getPower : function : Power : returns the power of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Power : the power of this Pokemon
     */
    public Power getPower(){
        return this._power;
    }

    /* powerOnAllies : function : Boolean : returns true if the power is used on Allies Pokemons
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the power is used on Allies Pokemons
     */
    public boolean powerOnAllies(){ return this._power.onAllies(); }

    /* powerOnEnemies : function : Boolean : returns true if the power is used on Enemies Pokemons
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the power is used on Enemies Pokemons
     */
    public boolean powerOnEnemies(){ return this._power.onEnemies(); }

    /* powerOnHimself : function : Boolean : returns true if the power is used on this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the power is used on this Pokemon
     */
    public boolean powerOnHimself(){ return this._power.onHimself(); }

    /* powerWasAlreadyUsed : function : Boolean : returns true if the power was already used
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Boolean : returns true if the power was already used
     */
    public boolean powerWasAlreadyUsed(){ return this._power.getWasAlreadyUsed(); }

    /* becomeEtherType : function : void : set the type of this Pokemon to Ether
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void becomeEtherType(){
        _type = PokemonType.ETHER;
    }

    /* becomeLeadType : function : void : set the type of this Pokemon to Lead
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void becomeLeadType(){
        _type = PokemonType.LEAD;
    }

    /* increaseNumberOfAttacksLeft : function : void : increase by 1 the number of attacks left of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void increaseNumberOfAttacksLeft(){
        _attacksLeft ++;
    }

    /* getAttacksLeft : function : Integer : returns the number of attacks left of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  Integer : the number of attacks left of this Pokemon
     */
    public Integer getAttacksLeft(){ return _attacksLeft; }

    /* decreaseNumberOfAttacksLeft : function : void : decrease by 1 the number of attacks left of this Pokemon
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  NONE
     */
    public void decreaseNumberOfAttacksLeft(){  _attacksLeft --; }

    /* toString : function : String : returns a recap of the class
     * param :
     *  NONE
     * local :
     *  NONE
     * return :
     *  String : recap of the class
     */
    public String toString (){
        String res = "NAME : " + _name + "\nLIFE : " + _life + "\nATTACK : " + _attack + "\nTYPE : " + _type;
        if (_power != null){
            res += "\nPOWER : " + _power.getName();
        }
        return res;
    }
}
