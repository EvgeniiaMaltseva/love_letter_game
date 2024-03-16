package love_letter_game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class GameControls provides various game control and management methods
 * listed below for the game.
 */
public class GameControls {
    private static boolean start_game = false;
    private static int round_number = 1;

    /**
     * Checks whether the game has started or not.
     * This method returns a boolean value indicating whether the game has been started. It is used to control various aspects
     * of the game's behavior and ensures that certain actions can only be performed after the game has started.
     *
     * @return `true` if the game has started, `false` otherwise.
     */
    public static boolean game_start() {
        return start_game;
    }

    public static void increment_round() {
        round_number++;
    }

    /**
     * Get the updated current round number.
     *
     * @return The current round number.
     */
    public static int get_round() {
        return round_number;
    }

    /**
     * Display a list of available commands to the players.
     */
    public static void displayHelp() {
        System.out.println("Next commands are available: \n" +
                "\\start - to start the game \n" +
                "\\playCard - to play a card \n" +
                "\\showHand - to display the current player's hand \n" +
                "\\showScore - to show the score for each player\n" +
                "\\showPlayers - to show all players in the game\n");
    }

    /**
     * Start the game by initializing the players and their hands.
     * Set boolean start_game to true, after the function is invoked.
     *
     * @param players An array of Player objects representing the players in the game.
     * @param deck    The game deck to draw cards from.
     */
    public static void startGame(Player[] players, Deck deck) {
        System.out.println("Welcome to the Love Letter Game!");
        start_game = true;
        for (int i = 0; i < players.length; i++) {
            players[i].drawCard(deck);
        }
    }

    /**
     * Display a list of active and inactive players entire the whole game
     *
     * @param players An array of Player objects representing the players in the round or out of round.
     */
    public static void showPlayers(Player[] players) {
        System.out.println("The players in the game are: ");
        for (int i = 0; i < players.length; i++) {
            if (!players[i].player_status()) {
                System.out.println("Player: " + players[i].get_name() + " active in round");
            } else {
                System.out.println("Player: " + players[i].get_name() + " inactive in round");
            }
        }
    }

    /**
     * Display the hand of the current player, including card names, ranks, and effects.
     *
     * @param player_in_turn The current player.
     */
    public static void showHand(Player player_in_turn) {
        ArrayList<Card> player_hand = player_in_turn.getCards();
        int card_num = 1;
        for (Card it : player_hand) { //loop through the entire hand
            System.out.println("card: " + card_num + "\n");
            System.out.println("card name: " + it.get_name() + "\n");
            System.out.println("card rank: " + it.getRank() + "\n");
            System.out.println("card effect: " + it.getEffect() + "\n");
            card_num++;
        }
    }

    /**
     * Display the score of each player in the game.
     *
     * @param players An array of Player objects representing the players in the game.
     */
    public static void showScore(Player[] players) {
        for (Player player : players) {
            System.out.println("Player: " + player.get_name() + " Score: " + player.get_points());
        }
    }

    /**
     * Display the players to choose from, with the ability to filter based on player status and handmaid status.
     * The available players are listed along with their indices for selection.
     *
     * @param players        An array of Player objects representing the players in the game.
     * @param current_player The current player (used for filtering).
     * @return The number of players available to choose from.
     */
    public static int show_players_to_choose_from(Player[] players, Player current_player) {
        System.out.println("Choose the player.\nPlayers in the game are:");
        int i = 0;
        int num_of_players_to_choose_from = 0;
        for (Player player : players) {
            // prints all players (Prince case)
            if (!player.player_status() && !player.get_handmaid_status() && current_player == null) {
                System.out.println("To choose " + player.get_name() + " enter: " + i++);
                num_of_players_to_choose_from++;
            }
            // prints all players except yourself
            else if (current_player != null && !player.player_status() && !player.get_handmaid_status() && !player.get_name().equals(current_player.get_name())) {
                System.out.println("To choose " + player.get_name() + " enter: " + i++);
                num_of_players_to_choose_from++;
            } else
                i++;
        }
        return num_of_players_to_choose_from;
    }

    /**
     * Allows the current player to select another player in the game.
     * This method prompts the current player to enter the number of the player they want to choose.
     * It ensures that the input is a valid player number and that the selected player is not the current player,
     * not out of the round, and not protected by a Handmaid card's effect.
     *
     * @param players  An array of all players in the game.
     * @return The index of the selected player, as chosen by the current player.
     */
    public static int select_player(Player[] players) {
        int num_player;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the number of the player you want to choose: ");
            if (scanner.hasNextInt()) {
                num_player = scanner.nextInt();
                if (num_player < 0 || num_player >= players.length || players[num_player].player_status() || players[num_player].get_handmaid_status()) {
                    System.out.println("Invalid input, try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input, try again.");
                scanner.next();
            }
        }
        return num_player;
    }
}
