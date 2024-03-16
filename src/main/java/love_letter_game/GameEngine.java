package love_letter_game;

import java.util.*;

/**
 * The `GameEngine` class manages the setup and initialization of a card game.
 * It prompts the user to specify the number of players, their names, and the days since their last date.
 * It also creates player objects, assigns names and last date values, and sorts the players.
 */
public class GameEngine {

    private static Player[] game_players;
    private static Deck game_deck;
    static Scanner scanner = new Scanner(System.in);

    /**
     * Starts the card game by initializing the game setup, including the number of players,
     * player names, and days since their last date. The method enforces valid inputs, ensures that player names are unique,
     * and initializes player objects with the collected information.
     * The set is used to prevent the use of duplicate player names during the game setup.
     * When a player enters a name, the code checks whether the name is already in the set.
     * If the name is not in the set, it is added to mark it as used.
     * If the name is already in the set, the user is prompted to choose another name.
     * The resulting array of players is stored for use in the game.
     */
    public static void start_game() {
        int num_players;

        while (true) {
            System.out.println("Enter the number of players between 2 and 4: ");
            if (scanner.hasNextInt()) {
                num_players = scanner.nextInt();
                if (num_players < 2 || num_players > 4) {
                    System.out.println("Invalid number, try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input, try again.");
                scanner.next();
            }
        }

        game_players = new Player[num_players];

        Set<String> usedNames = new HashSet<>();

        for (int i = 0; i < num_players; i++) {
            String name;
            while (true) {
                System.out.println("Enter player " + (i + 1) + " name: ");
                name = scanner.next();
                if (usedNames.contains(name)) { // at the beginning hash set is empty
                    System.out.println("Name already taken. Please choose another name.");
                } else {
                    usedNames.add(name); // adding the name to the usedNames set
                    break;
                }
            }

            int number;
            while (true) {
                System.out.println("Enter days since your last date " + name + ": ");
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            Player player = new Player();
            player.set_name(name);
            player.set_last_date(number);
            game_players[i] = player;
        }
        /**
         * The `game_players` array is sorted in ascending order using the natural ordering of the player objects.
         * The natural ordering is determined by the implementation of the `Comparable` interface in the `Player` class.
         * Players are ordered based on the number of days since their last date.
         * @param game_players The array of player objects to be sorted.
         */
        Arrays.sort(game_players);
    }

    /**
     * Retrieves the number of players currently in the round.
     * This method counts the players who are still active and participating in the current round of the game.
     * It iterates through the array of player objects and increments the count for each active player.
     *
     * @return The number of players in the current round.
     */
    public static int get_num_players_in_round() {
        int num_players_in_round = 0;
        for (Player player : game_players) {
            if (!player.player_status()) {
                num_players_in_round++;
            }
        }
        return num_players_in_round;
    }

    /**
     * Determines the winner of the current round.
     * This method iterates through the array of player objects to find the first player who is no longer in the round
     * (i.e., the player who has won the round). The winning player is determined based on their status.
     *
     * @return The player who has won the current round, or `null` if there is no winner yet.
     */
    public static Player get_round_winner() {
        for (Player player : game_players) {
            if (!player.player_status()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Checks if the game has ended based on the points of the round winner player.
     * This method evaluates whether the game has ended by examining the number of players and the points of the round winner player.
     * If the game meets specific winning conditions, it prints a message indicating the winner and returns `true` to signal the end of the game.
     * Otherwise, it returns `false`, indicating that the game continues.
     *
     * @param round_winner_player The player who has won the current round.
     * @return `true` if the game has ended, `false` if the game continues.
     */
    public static boolean check_end_of_game(Player round_winner_player) {
        if (game_players.length == 2) {
            if (round_winner_player.get_points() == 7) {
                System.out.println("Player: " + round_winner_player.get_name() + " won the game.");
                return true;
            }
        } else if (game_players.length == 3) {
            if (round_winner_player.get_points() == 5) {
                System.out.println("Player: " + round_winner_player.get_name() + " won the game.");
                return true;
            }
        } else if (game_players.length == 4) {
            if (round_winner_player.get_points() == 4) {
                System.out.println("Player: " + round_winner_player.get_name() + " won the game.");
                return true;
            }
        }
        return false;
    }

    /**
     * Resets the state of all players at the beginning of a new round.
     * This method iterates through the array of player objects and resets their out-of-round status and handmaid status.
     * It also takes the first card from each player's hand and inserts it into the discard pile.
     * This method prepares the players for the next set of actions.
     */
    public static void reset_players() {
        for (Player player : game_players) {
            player.reset_out_of_round();
            player.set_handmaid_status_false();
            if (player.getCards().size() > 0)
                game_deck.insert_into_discard_cards(player.getCards().remove(0));
        }

    }

    /**
     * This method creates a new game deck object, represented by the Deck class,
     * and populates it with a standard set of game cards. The game deck is used to draw and discard cards during the game.
     */
    public static void initialize_deck() {
        game_deck = new Deck();
        game_deck.create_deck();
    }

    /**
     * The method retrieves and transfer the unused cards from the game deck into the discard pile until the game deck is empty.
     * It also checks the top extra card and if there's one it inserts it into the pile, and sets it to null.
     */
    public static void get_all_cards() {
        while (game_deck.get_deck_size() != 0) {
            game_deck.insert_into_discard_cards(game_deck.getCard());
        }
        if (game_deck.get_top_extra_card() != null) {
            game_deck.insert_into_discard_cards(game_deck.get_top_extra_card());
            game_deck.set_top_extra_card();
        }
    }

    /**
     * This method initiates a new round of the game by incrementing the round number,
     * resetting player states, transferring cards, shuffling the discard pile, rebuilding the game deck,
     * and having players draw new cards.
     */
    public static void start_new_round() {
        GameControls.increment_round();
        System.out.println("Playing round: " + GameControls.get_round());
        reset_players();
        get_all_cards();
        game_deck.shuffle_discard_cards();
        game_deck.rebuild_deck();
        for (int i = 0; i < game_players.length; i++) {
            game_players[i].drawCard(game_deck);
        }
    }

    /**
     * This method parses a player's input command and executes the corresponding action based on the provided command.
     * It supports various commands, listed below, including handling unknown commands.
     * If the command is valid, it performs the associated action.
     * If the command is not recognized, an error message is displayed.
     *
     * @param command     The player's input command to be parsed and executed.
     * @param player_turn The index of the current player's turn.
     * @return The updated index of the player's turn after executing the command.
     */
    public static int parse_input_command(String command, int player_turn) {
        Player player_in_turn = game_players[player_turn];
        if (command.startsWith("\\")) {
            switch (command) {
                case "\\help":
                    GameControls.displayHelp();
                    break;
                case "\\playCard":
                    if (player_in_turn.getCards().size() == 2) {
                        Card card = player_in_turn.playCard();
                        game_deck.insert_into_discard_cards(card);
                        card.apply_effect(player_in_turn, player_turn, game_players, game_deck);
                        if (player_turn < (game_players.length - 1))
                            player_turn++;
                        else
                            player_turn = 0;
                    } else {
                        System.out.println("You need to have 2 cards, in order to play a card.");
                    }
                    break;
                case "\\showHand":
                    GameControls.showHand(player_in_turn);
                    break;
                case "\\showScore":
                    GameControls.showScore(game_players);
                    break;
                case "\\start":
                    if (GameControls.game_start() == false) {
                        initialize_deck();
                        GameControls.startGame(game_players, game_deck);
                    }
                    break;
                case "\\showPlayers":
                    GameControls.showPlayers(game_players);
                    break;
                default:
                    System.out.println("Unknown command. Type '\\help' to see available commands.");
                    scanner.nextLine();
            }
        } else {
            System.out.println("Invalid command, try again.");
        }
        return player_turn;
    }

    /**
     * Start and manage the game, including rounds and player turns.
     * This method manages the overall flow of the game, including rounds, player turns, and interactions with the game deck and player actions.
     * It continuously loops to ensure that the game is ongoing. The key actions performed in this method include determining the round winner,
     * checking for game end conditions, starting new rounds, handling player turns, and processing player commands.
     */
    public static void play_game() {

        int player_turn = 0;

        while (true) {

            int num_players_in_round = get_num_players_in_round();

            if (num_players_in_round == 1) {
                Player round_winner_player = get_round_winner();
                if (round_winner_player == null)
                    System.out.println("No winner found.");
                else {
                    round_winner_player.set_points();
                    System.out.println("Player: " + round_winner_player.get_name() + " won the round.");
                }
                // check if the round_winner_player has enough score to win the game
                if (check_end_of_game(round_winner_player))
                    return;
                start_new_round();
                player_turn = findPlayerIndex(round_winner_player); // the player who won the previous round starts the next round
                if (player_turn <0 ){
                    System.out.println("Player not found");
                }
            }
            if (game_players[player_turn].getCards().size() == 1 && game_deck.get_deck_size() == 0) {
                Player highest_rank_player = null; // initialize a variable to track the player with the highest rank
                for (Player player : game_players) {
                    if (!player.player_status()) {
                        // check if the player has at least one card
                        if (!player.getCards().isEmpty()) {
                            int rank = player.getCards().get(0).getRank(); //current player's rank
                            // if highest_rank_player is null or the current player has a higher rank, update highest_rank_player
                            if (highest_rank_player == null || rank > highest_rank_player.getCards().get(0).getRank()) {
                                highest_rank_player = player;
                            }
                        }
                    }
                }
                if (highest_rank_player != null) {
                    System.out.println("Deck is empty, round is over\n" +
                            "Player with the highest rank is: " + highest_rank_player.get_name());
                    highest_rank_player.set_points();
                    System.out.println("Player: " + highest_rank_player.get_name() + " won the round.");
                } else {
                    System.out.println("No active player has cards with ranks.");
                }
                // check if the highest_rank_player has enough score to win the game
                if (check_end_of_game(highest_rank_player))
                    return;
                start_new_round();
                player_turn = findPlayerIndex(highest_rank_player); // the player with the highest card rank starts the next round
                if (player_turn <0 ){
                    System.out.println("Player not found");
                }
            }
            //check if player is in the round, start game set to true, player has a card, deck has a card
            if (game_players[player_turn].player_status() == false && GameControls.game_start() == true && game_players[player_turn].getCards().size() < 2 && game_deck.get_deck_size() > 0) {
                System.out.println("Player " + game_players[player_turn].get_name() + " your turn\n");
                game_players[player_turn].drawCard(game_deck);
            }
            //check if the player_turn is out of the round set to true
            if (game_players[player_turn].player_status()) {
                System.out.println("Player: " + game_players[player_turn].get_name() + " missing the round\n");
                if (player_turn < (game_players.length - 1)) {
                    player_turn++;// next player turn
                } else {
                    player_turn = 0; // reset to the 1. player turn
                }
                if (game_players[player_turn].getCards().size() == 1)
                    continue;
            }
            System.out.println("Enter the game command starting with \\: ");
            String command = scanner.next();
            player_turn = parse_input_command(command, player_turn);
        }
    }
    /**
     * Finds the index of a specified player in the array of game players.
     * This method iterates through the array of game players and compares each player
     * to the specified player object to find the index of the player.
     * @param player The player to find in the array.
     * @return The index of the specified player in the array of game players.
     *         If the player is not found, it returns -1.
     */
    public static int findPlayerIndex(Player player) {
        for (int i = 0; i < game_players.length; i++) {
            if (game_players[i] == player) {
                return i;
            }
        }
        return -1;
    }
}