# Rapport semaine 4 

Rapport de la 4eme semaine du projet SAE_POOkemon

## Table des Matières 
- [Affichage](#affichage)
- [Déroulement du jeu](#Déroulement du jeu)
- [Pouvoirs](#pouvoirs)
- [Correction](#correction)

## Affichage
### classe Display Turn
- classe qui gère l'affichage des tours du jeu
#### fonction private void displayTurn(Integer whichTurn)
Fonction qui gère l'affichage des différents battlefields à chaque tour du jeu +
gère l'affichage des règles des différents pouvoirs et la main du joueur

## Dérouleuement du jeu
### classe User
#### fonction : public void turn(Player opponent)
turn gère la conception d'un tour par l'utilisateur, elle gère son affichage pour le moment (ce qui est potentiellement à changer)
Le code se compose en 2 grandes parties : 
- la demande à l'utilisateur d'utilisation d'un pouvoir (s'il y'en a un)
- la demande à l'utilisateur de l'ordre d'attaque de ses pokémons
  (Le code réalise une boucle sur le nombre de pokémons qu'il y'a sur le terrain (Battlefield))
#### fonction : private void action(Player opponent,Pokemon pokemonTarget, Pokemon pokemonAttacker)
action gère la conception d'une attaque par le pokemon de l'utilisateur, elle gère son affichage (compte rendu de l'attaque + à changer potentiellement)
Elle modifie l'attaque en fonction d'un bonus ou d'un malus que le pokemon peut avoir (via les pouvoirs ou les affinitées des types)
#### fonction : private Integer inputUser(String script, List<Pokemon> pokemonList)
inputUser gère un input à l'utilisateur pour choisir un pokemon dans pokemonList et prend comme retour l'input de l'utilisateur
Elle gère une partie de l'affichage de la liste de pokemonList pour choisir son pokemon (à changer potentiellement)
#### fonction private void usePower(Player opponent, Pokemon pokemonAttacker)
usePower utilise la fonction usePower de la classe Pokemon (par une encapsulation de la classe Player) pour qu'un pokemon puisse utiliser son pouvoir 
### classe CPU
#### public void turn(Player opponent)
Techniquement la même chose que la classe User mais dans la conception d'un Ordinateur. C'est à dire, sans besoin de
prendre en compte les inputs de l'utilisateur
#### private void action(Pokemon cpuPokemon, Pokemon pokemonTarget, Integer attackNumber)
Techniquement la même chose que la classe User mais dans la conception d'un Ordinateur. C'est à dire, sans besoin de
prendre en compte les inputs de l'utilisateur. (j'ai ajouté attackNumber juste pour le moment à voir plus tard si je dois
changer action de User pour concorder tout ça (c'est à dire le déclarer dans la classe abstraite Player))
#### private Pokemon pokemonTarget(Pokemon attackingPokemon, List<Pokemon> opponentPokemons)
pokemonTarget est une fonction qui trouve le meilleur pokemon à attaquer pour l'ordinateur. Puisque l'ordinateur n'est 
pas un humain, il faut traiter ses choixs de pokémons automatiquement. C'est ce que fait cette fonction.
#### private void usePower(Player opponent, Pokemon cpuPokemon)
Techniquement la même chose que la classe User mais dans la conception d'un Ordinateur. C'est à dire, sans besoin de
prendre en compte les inputs de l'utilisateur. (et plus algorithmique)
### classe Game
La classe Game comporte le code des grandes lignes du jeu : 
Elle initialise le battlefield du joueur et de l'ordinateur et réalise une grande boucle while qui tourne jusqu'à 
que l'un des deux perd : elle est composé de deux grands if, qui gère l'ordre du déroulement du jeu en fonction du joueur
qui commence
### classe Player
#### public Boolean hasLost()
hasLost est la fonction qui permet de sortir de la grande boucle while du Game pour terminer le jeu
Elle vérifie si un joueur n'a plus de cartes en jeu (si sa main (_hand) et son terrain (_battlefield) est vide)
#### public abstract void turn(Player opponent);
cette implémentation suit la logique que j'ai dis ultérieurement avec les turns de l'ordinateur (CPU) et de l'utilisateur (User)
#### public void usePokemonPower(Pokemon pokemonAttacker, Pokemon opponentPokemon)
cette implémentation suit la logique que j'ai dis ultérieurement avec les  de l'ordinateur (CPU) et de l'utilisateur (User)
## Pouvoirs
### classe abstraite Power
#### fonctions abstraites 
Voici toutes les classes abstraites de la fonction Power :  
- public abstract void use(Pokemon thisPokemon,Pokemon otherPokemon);
- public abstract Boolean getWasAlreadyUsed();
- public abstract PowerName getType()
use sert à utiliser un pouvoir selon un pokemon et le pokemon visé "otherPokemon" (elle est encapsulée par la fonction usePower() de Pokemon)
getWasAlreadyUsed sert à savoir si le pouvoir a déjà été utilisé 
getType() sert à avoir le type de pouvoir (c'est utile l'attribut _power du pokemon comme par exemple)
#### fonction public String getName()
getName permet, comme son nom l'indique, le nom d'un pouvoir (pour les affichages notamment dans le battlefield)
#### Groupe de fonction "onSomething"
Voici les différentes fonctions qu gèrent les différents groupes de pouvoirs : 
public boolean onAllies()  - permet de regrouper les types de pouvoirs qui visent des alliées
public boolean onEnnemies() - permet de regrouper les types de pouvoris qui visent des ennemis
public boolean onHimself() - permet de regrouper les types de pouvoirs qui visent le lanceur
Ces fonctions sont utiles pour l'affichage ! ça permet de tout regrouper sans faire une grosse forêt de 8 ifs !
### classes filles de Power : 
Voici toutes les classes de filles de Power, et donc les différents types de pouvoirs de notre jeu : 
AlreadySeen, EtherType, Fear, Kamikaze, LeadType, TerritoryExtension, TotalHeal, WarriorFervor.
Elles implémentent les fonctions abstraites et ont (presque) toutes une encapsulation dans Pokemon

## Correction - Les nouvelles fonctions pour les encapsulations
Puisque que nous n'avons pas respecté l'encapsulation dans l'entiéreté de notre code avant la semaine 4, on a décidé de 
refaire tout nos fonctions avec des encapsulations !  
### classe Player
Voici les nouvelles fonctions de la classe Player (les noms parlent d'eux mêmes) :
- pour la classe Hand :
public Pokemon getPokemonOnHand(Integer index)
public String getPokemonNameOnHand(Integer index)
public Pokemon takeNextPokemonOnHand()
public Integer sizeOfHand()
public void addPokemonOnHand(Pokemon pokemon)
public boolean handIsEmpty()
public String displayHand()
public void removePokemonOnHand(Pokemon pokemon)
- pour la classe Battlefield :
public Pokemon getPokemonOnBattlefield(Integer index)
public String getPokemonNameOnBattlefield(Integer index)
public void removePokemonOnBattlefield(Pokemon pokemon)
public void addPokemonOnBattlefield(Pokemon pokemon)
public Integer sizeOfBattlefield()
public boolean battlefieldIsEmpty()
public List<Pokemon> getBattlefieldPokemons()
- pour la classe Draw : 
public boolean drawIsEmpty()
public Pokemon takeNextPokemonOnDraw()
public String displayBattlefield()
### classe Hand
Voici les nouvelles fonctions de la classe Hand (les noms parlent d'eux mêmes) :
public Pokemon get(Integer index)
public String getName(Integer index)
public Integer getLife(Integer index)
public Integer getAttack(Integer index)
public PokemonType getType(Integer index)
public Integer size()
public boolean isEmpty()
public Pokemon takeNext()
public void removePokemon(Pokemon pokemon)
### classe Pokemon
Voici les nouvelles fonctions de la classe Pokemon (les noms parlent d'eux mêmes) :
public void usePower(Pokemon otherPokemon) - pour qu'un pokemon utilise un pouvoir
public Boolean getPenalty() - c'est pour savoir si un pokemon a un malus
public Integer getInitialAttack() - à effacer (on ne l'utilise pas)
public void addAttack(Integer value)
public void setLife(Integer value) { _life = value; }
public void setPenalty(Boolean value) { _penalty = value;}
public void resetLife()
- Power : 
public Power getPower() 
On retrouve les fonctions citées précèdemment pour Power (+ d'autres pour l'utilisation d'un pouvoir) dans Pokemon : 
public boolean powerOnAllies()
public boolean powerOnEnnemies()
public boolean powerOnHimself()
public boolean powerWasAlreadyUsed()
public void becomeEtherType()
public void becomeLeadType()
public void increaseNumberOfAttacksLeft()
public Integer getAttacksLeft()
public void decreaseNumberOfAttacksLeft()
### classe Battlefield
Voici les nouvelles fonctions de la classe Battlefield (les noms parlent d'eux mêmes) :
public Pokemon get(Integer index)
public String getName(Integer index)
public List<Pokemon> getPokemonList()
public boolean isEmpty()
public void remove(Pokemon pokemon)
public void add(Pokemon pokemon)
public Integer size()

### classe Draw
Voici les nouvelles fonctions de la classe Draw (les noms parlent d'eux mêmes) :
public Pokemon takeNext()
public boolean isEmpty()

