Création de la classe Pokemon : 
    Qui instancie un Pokémon qui aura un nom (_name), une vie (_life), une attaque (_attack) et une affinité (_affinity).
    On a un attribut _pokemonList qui contient tous les noms des Pokémon dans ce programme.
    Il est possible que cette gestion des noms des Pokémon change au futur dans une classe à part entière, 
    puisqu'un Pokémon ne possède pas une liste de noms.

Création de l'énumération PokemonType : 
    Cette énumération va permettre d'obtenir le type de façon aléatoire d'un Pokémon parmi ceux proposés dans l'énumération.

Création de la classe abstraite Player :
    Qui définit les "Joueurs" d'une partie parmi ses classes filles (CPU et User).
    Un joueur possède une pioche (_pokemonDraw), une main (_pokemonHand) et une défausse (_pokemonDiscard). 
    Il va obliger à ses classes filles d'instancier la méthode choicePokemonBattlefield
    afin de gérer les choix des Pokémon choisis par un joueur.

Création de la classe CPU :
    Qui instancie un joueur "ordinateur" donc géré par un algorithme.
    Notamment dans la méthode choicePokemonBattlefield() où l'ordinateur devra prendre les 3 premiers Pokémon dans sa main.

Création de la classe User :
    Qui instancie un joueur "utilisateur". C'est pourquoi on a décidé de séparer l'User du CPU afin de créer des méthodes
    choicePokemonBattlefield() différentes (puisque un utilisateur choisit ses Pokémon dans sa main en comparaison au CPU) 
    et de pouvoir afficher la main de l'utilisateur et non celle de l'ordinateur.

Création de la classe DisplayGame :
    Qui instancie un affichage d'une partie,
    Qui contient les méthodes d'affichages des différents tours (displayRoundUserAttacker(), displayRoundCPUAttacker()) 
    (en fonction du tour de l'utilisateur ou de l'ordinateur) et du jeu.

Création de la classe Game :
    La classe qui contient le main.

Création de la classe Battlefield :
    La classe qui va créer le champ de bataille d'un joueur et qui va choisir quel Pokémon va attaquer.
    Pour se faire, cette classe possède un attribut des Pokémon sur le champ de bataille d'un joueur
    (_pokemonBattlefieldList).

Dans le PUML, la grande énumération PokemonName ne fait pas partie de la conception de notre projet mais nous servira
plus tard dans la création d'une liste avec les noms des Pokémon.
 
Dans notre main : 
étant donné qu'à ce stade nous n'avons pas encore mis en place de méthode ou de classe pour générer des valeurs aléatoires, 
nous avons décidé, pour tester notre classe Pokémon, d'attribuer le même nom à chaque Pokémon (en attendant de mettre en place cette fonctionnalité)
