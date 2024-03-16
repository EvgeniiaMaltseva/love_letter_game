package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.GameControls;
import love_letter_game.Player;

import java.util.Scanner;

public class Guard extends Card {
    /**
     * Constructs a Guard card by calling the superclass constructor with predefined values.
     */
    public Guard() {
        super("Guard", 1, "\nWhen you discard the Guard, choose a player and name a number (other than 1).\n" +
                "If that player has that number in their hand, that player is knocked out of the round.\n" +
                "If all other players still in the round cannot be chosen (eg. due to Handmaid or),\n" +
                "this card is discarded without effect.");
    }
    /**
     * Applies the effect of the Guard card to the current player.
     * This method is called when the Guard card's effect is triggered. It allows the current player
     * to guess the rank of another player's card by entering a number between 1 and 8.
     * If the guess is correct, the selected player is marked as "out of round," and his card is discarded.
     * If the guess is incorrect, a message is displayed indicating that the guess is wrong.
     * If all other players are protected by a Handmaid or out of the round, the method notifies the player
     * and proceeds to the next round without any guessing or card effect.
     * @param current_player The player using the Guard card to guess another player's card rank.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck){
        int num_player;
        int num_of_players_to_choose_from = GameControls.show_players_to_choose_from(players, current_player);
        if (num_of_players_to_choose_from == 0) {
            System.out.println("All other players in round are protected by Handmaid or out of round\n" + "Moving to the next round");
            return;
        } else {
            num_player = GameControls.select_player(players); //returns player's input
        }
        Player selected_player = players[num_player];
        Scanner scanner = new Scanner(System.in);
        int number;
        // main guard logic
        do {
            System.out.print("Enter a number between 1 and 8: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.next(); //consume invalid input
            }
            number = scanner.nextInt();
            if (number < 1 || number > 8) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
            }
        } while (number < 1 || number > 8);
        // Guessed right
        if (number == selected_player.getCards().get(0).getRank()) {
            selected_player.set_out_of_round();
            // discard other player's card
            deck.insert_into_discard_cards(selected_player.remove_card());
            System.out.println("You guessed it right the player: " + selected_player.get_name() + " is out of round!");
        } else {
            System.out.println("Wrong guess.");
        }
    }
}
