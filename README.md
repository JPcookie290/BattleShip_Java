# Battleship Game

## Overview

This is a classic Battleship game where a player competes against the computer. Players can choose to manually place ships or have them randomly placed, and the objective is to sink all the opponent's ships before they sink yours. The game features an easy-to-use graphical interface and offers both Easy and Normal difficulty modes.

## Features

- **Player vs Computer:** Play against the computer, with the computer randomly selecting moves.
- **Manual or Random Ship Placement:** You can either place your ships manually or let the computer randomly place them for you.
- **Game Modes:** Choose between Easy or Normal mode.
- **Reset and Start Buttons:** Easily restart the game or randomize ship placements with the provided buttons.
- **Graphical Gameboard:** The gameboard is visualized using a graphical user interface for ease of play.

## How to Run the Project

1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/battleship-game.git
    ```

2. Navigate to the project directory:
    ```bash
    cd battleship-game
    ```

3. Compile the Java files:
    ```bash
    javac *.java
    ```

4. Run the game:
    ```bash
    java Main
    ```

## How to Play

1. Start the game by entering your name and selecting a game mode (Easy or Normal).
2. Either place your ships manually on the gameboard or click "Random Placement" to automatically place your ships.
3. Click "Start Game" to begin.
4. On your turn, click on the opponent's board to fire at their ships.
5. The game alternates between the player and the computer. The goal is to sink all the opponent's ships.

## Game Logic Overview

- **Player Turns:** Each player (you and the computer) take turns firing at the other’s ships.
- **Ship Placement:** Ships can be placed manually or randomized.
- **Winning Condition:** The first player to sink all of their opponent’s ships wins.

## Future Improvements

- Add animations for hits and misses.
- Implement multiplayer mode so two users can play against each other.
- Improve the AI for more strategic gameplay.

## Known Issues

- Gameboard reset may not always clear all ships from the previous round.
- No visual feedback when placing ships manually.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
