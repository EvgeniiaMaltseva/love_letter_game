# love_letter_game

## Run the game

run jar file: 
open in terminal/shell src/main/resources/love_letter_game-1.0-SNAPSHOT.jar

java -jar love_letter_game-1.0-SNAPSHOT.jar

or

run main() from the IDEA src/main/java/love_letter_game/Game Class

## Introduction

Love Letter is a card game designed for 2 to 4 players. The game is set in a medieval court where players compete to deliver a love letter to the princess. However, they must navigate intrigue, deduction, and strategy to win her heart.

This repository contains a digital version of the Love Letter card game, [read the whole game description here](https://alderac.com/wp-content/uploads/2017/11/Love-Letter-Premium_Rulebook.pdf)!

## Game Rules: 

Before starting, here are the rules and important notes for playing the Love Letter game:

The game is designed for 2 to 4 players.
There are a total of 16 cards in the game.
Players can assign their names, but no two players can have the same name.
Each player must specify the number of days since their last date, which determines the turn order.
Players can enter commands during the game, and these commands must start with a backslash (\\). For example, use "\help" to see the list of available commands.

# How to play

**Starting the Game**

Once all players have entered their names and days since their last date, the game can start. Use the "\start" command to initiate the game.
The player who starts is determined based on the days since their last date. In case of a tie, the player who entered their name first will start.

**Taking Turns**

During your turn, you can see your cards with the "\showHand" command.
To play a card, use the "\playCard" command and follow the on-screen instructions for that card's effect.
Players who get thrown out of the round will rejoin the game in the next round.

**Winning the Round**

The round ends when only one player is left, and that player is declared the winner.
Alternatively, if the deck of cards is empty, the winner of the round will be determined by the highest-ranking card. In the case of a tie, the player who started the round will win.
Monitoring the Game
Throughout the game, you can keep track of active and inactive players using the "\showPlayers" command.
To check who is leading the game, enter "\showScore".

**Ending the Game**

The game ends when a player wins by accumulating a specific number of Tokens of Affection, which is based on the number of players:

- 2 Players: 7 tokens
- 3 Players: 5 tokens
- 4 Players: 4 tokens

