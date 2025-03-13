# OOP Pokemon Project

Application inspired by the game **Pokemon**.
It's a **single player game** where trainers fight by sending their Pokemon on the battlefield.

## Organization

- **Pair work**
- Duration : 5 weeks

## The Game

This is a **turn-based** game with one human player versus the computer. The winner is the player who manages to **eliminate all of their opponent's Pokémon**.


### Game Composition

Each player has at their disposal:

- a **field** that can accommodate 3 Pokemon,
- a **hand** of up to 5 Pokemon,
- a **draw pile** of 20 or 21 Pokemon (if the player starts or not),
- a **discard pile** containing eliminated Pokemon.

### Setting up the game

1. The first player to play is chosen randomly.
1. Each player **draws 5 Pokemon**.
1. The first player **places 3 Pokemon** from their hand face up on their side of the field, then the second player does the same.
1. The first player begins to play.

### Turn Process

During his/her turn, the human player:

1. **Draws** Pokemon until having 5 in hand or the draw pile is empty.
1. **Places** one Pokemon from hand face up on each empty space on the field.
1. **Can attack** once with each Pokemon on the field, in any order.

During its turn, the computer player:

1. **Draws** Pokemon until having 5 in hand or the draw pile is empty.
1. **Places** one Pokemon from hand face up on each empty space on the field, **in the order of its hand**.
1. **Attacks** once with each Pokemon on the field **in the order of its fields**.

## Pokemon

Each Pokemon has :

- a **name**
- **heal points**
- **attack points**
- an **element afinity**.

### Elements

There are four elements: earth, water, fire, and air. Each element has an advantage over another:

- earth has an advantage over water
- water has an advantage over fire
- fire has an advantage over air
- air has an advantage over earth.

### Pokemon attack

- When a Pokemon attacks another Pokemon, it **decreases its opponent's Life Points** by the value of its Attack Power.
- If, following an attack, a Pokemon has **no Life Points** left, it is placed in the opponent's **discard pile**.
- An **Affinity Advantage** increases an Attack by 10.
- An **Affinity Disadvantage** decreases an Attack by 10.

### End of Game

The game ends when a player has no more living Pokemon.