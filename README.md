
# <span style="color: blue;"> Projet Pokémon </span>

On souhaite réaliser une application simulant un jeu de cartes de type Pokémon.
C'est un jeu à deux joueurs qui s'affrontent en plaçant sur leur terrain différents Pokémons.

# Organisation
- Travail en binôme au sein d'un même groupe de TP
- Durée : 5 semaines
- Nombre de séances : 20h encadrées + 8h tutorées + travail non-encadré
- Évaluation : rendu final, régularité des rendus, soutenance
- Sujet dévoilé en deux phases : la phase 1 sur les deux premières semaines, la phase 2 pour le reste du projet sera dévoilée le **Lundi 20 mai**.

# Calendrier
- Lundi 22 avril : début du projet
- Lundi 20 mai : début de la phase 2
- Dimanche 9 Juin à minuit : rendu final
- Chaque dimanche à minuit (hors vacances scolaires) : rendu hebdomadaire.

# Le jeu
Il s'agit d'un jeu à deux joueurs l'un humain, l'autre étant joué par l'ordinateur.


### But du jeu
Le gagnant est celui qui parvient à éliminer tous les Pokémons de son adversaire.

### Composition du jeu

Chaque joueur a à sa disposition :
- un terrain pouvant accueillir 3 Pokémons maximum,
- une main de 5 Pokémons maximum,
- une pioche de 20 ou 21 Pokémons (lire partie Mise en place du jeu),
- une défausse contenant les Pokémons éliminés.


### Mise en place du jeu
Le premier joueur à jouer est choisi aléatoirement.
Le premier joueur possède une pioche de 20 Pokémons, le second de 21 Pokémons.
Chaque joueur pioche 5 Pokémons de sa pioche.
Le premier joueur place 3 Pokémons de sa main sur son terrain face visible, puis le second joueur en fait de même.
Le premier joueur commence à jouer.

### Déroulement d'un tour

Les joueurs jouent à tour de rôle.
Au début de chaque tour, le joueur pioche des Pokémons jusqu'à en avoir 5 en main ou que la pioche soit vide.
Pour chaque emplacement de Pokémon vide sur le terrain, il place un Pokémon de sa main face visible.
Il peut ensuite jouer chacun des Pokémons de son terrain dans l'ordre de son choix.
Chaque Pokémon attaque alors un Pokémon de son choix du terrain adverse dont le nombre de points de vie est diminué de la valeur de l'attaque du Pokémon joué.
Si à l'issue d'une attaque un Pokémon du terrain adverse n'a plus de points de vie, il est placé dans la défausse de l'adversaire.

**Le joueur géré par l'ordinateur**

Pour faire jouer le joueur IA, on suivra les règles suivantes :
- lorsqu'il place des Pokémons sur le terrain, le joueur IA prend le premier Pokémon de sa main.
- pour attaquer le joueur IA joue toujours dans le même ordre les pokémons de son terrain. Un pokémon joué par l'IA s'attaquera en priorité à un pokémon dont l'affinité avantagera l'IA et si cela ne suffit pas à déterminer le pokémon attaqué, le pokémon du joueur humain possédant le moins de points de vie.

### Les Pokémons
Chaque Pokémon possède, un nom, un nombre de points de vie, une valeur d'attaque et une affinité avec un élément.

**Génération procédurale des Pokémons**
Les Pokémons de chaque pioche sont générées procéduralement (c'est à dire déterminés selon l'algorithme suivant) :
- leur nom est tiré aléatoirement parmi une liste de noms que vous fixerez, mais deux Pokémons ne peuvent pas avoir le même nom,
- leur nombre de points de vie initial est un multiple de 10, est compris entre 100 et 200 et est déterminé aléatoirement
- leur valeur d'attaque est un multiple de 10 est comprise entre 10 et 40 et est déterminée aléatoirement
- leur affinité est tiré aléatoirement parmi la terre, l'eau, le feu et l'air.


**Les éléments** Il y a 4 éléments : la terre, l'eau, le feu et l'air.
Chaque élément a un avantage sur un autre élément : la terre sur l'eau, l'eau sur le feu, le feu sur l'air et l'air sur la terre.
Lorsqu'un Pokémon attaque un Pokémon sur lequel son affinité a un avantage, son attaque est augmentée de 10.
Lorsqu'un Pokémon attaque un Pokémon sur lequel son affinité a un désavantage, son attaque est diminuée de 10.







# L'application
L'application doit être réalisée en Java et doit proposer une interface textuelle.
Elle doit permettre à chaque tour du jeu de visualiser les Pokémons sur le terrain du joueur et de son adversaire, les Pokémons en main du joueur (mais pas celles de son adversaire).
Elle doit également indiquer clairement quelles sont les actions possibles et les entrées clavier correspondantes pour les effectuer.
Au tour du joueur IA, les actions du joueur IA seront affichées.

*Exemple d'affichage*
```console
********************************************************************************
Tour 3:
                                    Joueur 2

********************************************************************************

                                    Joueur 1

pioche: 15 pokemons
defausse: 0 pokemons
  *--------------------*    *--------------------*    *--------------------*  
  | Attaque: 20        |    | Attaque: 40        |    | Attaque: 20        |  
  | Vie: 50/120        |    | Vie: 50/150        |    | Vie: 50/130        |  
  | Affinite : Air     |    | Affinite : Air     |    | Affinite : Feu     |  
  |     Herbizarre     |    |     Florizarre     |    |     Salamèche      |  
  *--------------------*    *--------------------*    *--------------------*  

----------------------------------------------------------------------------------------------------

  *--------------------*    *--------------------*    *--------------------*  
  |     Dracaufeu      |    |     Bulbizarre     |    |      Pikachu       |  
  | Affinite : Feu     |    | Affinite : Eau     |    | Affinite : Terre   |  
  | Vie: 50/180        |    | Vie: 50/150        |    | Vie: 50/100        |  
  | Attaque: 40        |    | Attaque: 30        |    | Attaque: 10        |  
  *--------------------*    *--------------------*    *--------------------*  

pioche: 12 pokemons
defausse: 0 pokemons
main:
- Reptincel, Feu, Vie: 160, Attaque: 20
- Carapuce, Eau, Vie: 100, Attaque: 40
- Tortank, Eau, Vie: 200, Attaque: 20
- Papilusion, Air, Vie: 120, Attaque: 20
- Roucool, Air, Vie: 150, Attaque: 30

                                    Joueur 2

Quel pokemon souhaitez-vous jouer? (Dracaufeu/Bulbizarre/Pikachu):
```
Il n'est pas nécessaire de reproduire le visuel tel quel mais toutes les informations doivent être présentes.

**Fin du jeu**
A la fin du jeu, un écran affiche "Vous avez perdu" ou "Vous avez gagné" et indique le nombre de cartes restantes au gagnant.


# Rendus
Votre projet sera situé sur un fork du projet, celui sera placé dans un groupe possédant pour nom ```<nom_etudiant_1>-<nom_etudiant_2>``` et aura pour nom ```Projet_Pokemon```.
Votre enseignant en TP et le responsable auront le rôle reporter sur votre projet.

Il y a un rendu par semaine à produire (la régularité des rendus sera notée) à déposer au plus tard le dimanche soir à minuit.
Une branche devra être créee sur chacun des rendus avec pour nom "Rendu<numéro rendu>" (ou si vous préférez un tag avec le numéro du rendu).
Le dernier rendu sera évalué en tant que rendu final.
Chaque rendu devra contenir :
- un programme qui compile dont les sources sont dans le répertoire src/,
- un diagramme de classes à jour placé dans le répertoire uml/ ayant pour nom ```semaine<numero>.puml```,
- un rapport dans le fichier ```semaine<numero>.md``` indiquant quelles fonctionnalités ont été implémentées, la responsabilité de chaque classe.

Les rendus seront à faire sur la branche principale du dépôt git.
Vous devrez créer une branche à part pour travailler.

La structure du dépôt git sera la suivante :
```bash
.
├── README.md
├── rapports/
    ├── semaine1.md
    ├──...
├── .gitignore
├── src/
    ├── Pokemon.java
    ├──...
├── uml/
    ├── semaine1.puml
    ├──...
```
Le .gitignore à trouver sur moodle vous permettra d'ignorer les fichiers produits par la compilation.

## Planning indicatif
- rendu 1 : affichage permettant seulement d'afficher le plateau des Pokémons (terrain et main)
- rendu 2 : le jeu est jouable : les Pokémons peuvent s'attaquer, il est possible de piocher des Pokémons et de les placer sur le terrain.

## Quelques consignes
- Votre projet sera situé sur un fork du projet, celui sera placé dans un groupe possédant pour nom ```<nom_etudiant_1>-<nom_etudiant_2>``` et aura pour nom ```Projet_Pokemon```.
Votre enseignant en TP et le responsable auront le rôle reporter sur votre projet.
- Vous veillerez à respecter les [P21 Guidelines](https://moodle.unistra.fr/pluginfile.php/2390971/mod_resource/content/2/p21_guidelines.pdf)
- Vous veillerez à ce que l'affichage soit géré par plusieurs classes dédiées.
- Vous veillerez à la bonne conception du code : le code doit être lisible,  facile à corriger, facile à réutiliser, facile à modifier et facile à étendre.

## Quelques conseils
- N'essayez pas d'implémenter toutes les fonctionnalités en une seule fois. Commencez par un programme simple mais fonctionnel et intégrez progressivement les fonctionnalités.
- Faites des commits très réguliers sur vos branches de travail (dès que vous avez une nouvelle classe avec un minimum de méthodes, dès que vous avez intégré des nouvelles méthodes qui compilent, ...). Cela vous permettra de revenir facilement à une version fonctionnelle du code si besoin.
- Concevez votre code pour qu'il soit facile de le modifier et d'y ajouter de nouvelles fonctionnalités (notamment en prévision de la phase 2).
- La qualité du code produit est plus importante que le nombre de fonctionnalités intégrées.
