package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.GameControls;
import love_letter_game.Player;

import java.util.Scanner;

public class Prince extends Card {
    /**
     * Constructs a Prince card by calling the superclass constructor with predefined values.
     */
    public Prince() {
        super("Prince", 5, "\nWhen you discard Prince, choose one player still in the round (including yourself).\n" +
                "That player discards his or her hand(but doesn’t apply its effect, unless it is the Princess) and draws a new one.\n" +
                "If the deck is empty, that player draws the card that was removed at the start of the round.\n" +
                "If all other players are protected by the Handmaid, you must choose yourself.");
    }
    /**
     * Applies the effect of the Prince card to the game.
     * When the Prince card is played, the current player selects another player and that player must discard his current card, and draw a new one.
     * If the discarded card is the Princess, the selected player is out of the round.
     * Additionally, if the game deck is empty, the selected player draws the top extra card, otherwise the player is out of round.
     * @param current_player      The player who is playing the Prince card.
     * @param current_player_index The index of the current player in the array of players.
     * @param players             An array of all players in the game.
     * @param deck                The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck) {
        int num_player;

        int num_of_players_to_choose_from = GameControls.show_players_to_choose_from(players, null);
        if (num_of_players_to_choose_from == 0) {
            System.out.println("All other players in round are protected by Handmaid or out of round\n Choosing yourself...");
            // index of current player
            num_player = current_player_index;
        } else {
            num_player = GameControls.select_player(players); //return players input
        }
        // selected player (can be self or other)
        Player selected_player = players[num_player];
        // chosen player removes card, no effect applied
        Card removed_card = selected_player.remove_card();
        deck.insert_into_discard_cards(removed_card);
        // If princess, player is out
        if (removed_card.getRank() == PRINCESS_RANK) {
            selected_player.set_out_of_round();
            System.out.println("Player: " + selected_player.get_name() + " out of round");
            return;
        }
        //check if stack is empty, get the extra card
        if (deck.get_deck_size() == 0) {
            if (deck.get_top_extra_card() == null) {
                System.out.println("Player: " + selected_player.get_name() + " out of round");
            } else {
                selected_player.add_card(deck.get_top_extra_card()); // player draws extra top card
                deck.set_top_extra_card(); // top card is null now
            }
        } else {
            selected_player.add_card(deck.getCard());
            System.out.println("Player: " + selected_player.get_name() + " gets a new card ");
        }
    }
}
