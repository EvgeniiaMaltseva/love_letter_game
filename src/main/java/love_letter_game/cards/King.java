package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.GameControls;
import love_letter_game.Player;

public class King extends Card {
    /**
     * Constructs a King card by calling the superclass constructor with predefined values.
     */
    public King() {
        super("King", 6, "\nWhen you discard King, trade the card in your hand with the card held by another player of your choice.\n" +
                "You cannot trade with a player who is out of the round.");
    }
    /**
     * Applies the effect of the King card to the current player.
     * This method is called when the King card's effect is triggered. It allows the current player to exchange the card with another player's card.
     * If all other players are protected by a Handmaid or out of the round, the method notifies the player
     * and proceeds to the next round without any card exchange.
     * @param current_player The player using the King card to exchange their hand.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck) {
        int num_player;
        int num_of_players_to_choose_from = GameControls.show_players_to_choose_from(players, current_player);
        if (num_of_players_to_choose_from == 0) {
            System.out.println("All other players in round are protected by Handmaid or out of round\n" + "Moving to the next round");
            return;
        } else {
            num_player = GameControls.select_player(players); //return players input
        }
        Player selected_player = players[num_player];
        // main king logic
        Card player2_card = selected_player.remove_card();
        Card player1_card = current_player.getCards().remove(0);
        selected_player.add_card(player1_card);
        current_player.getCards().add(player2_card);
        System.out.println("Cards Exchanged!");
    }
}
