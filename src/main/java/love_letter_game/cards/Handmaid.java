package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.Player;

public class Handmaid extends Card {
    /**
     * Constructs a Handmaid card by calling the superclass constructor with predefined values.
     */
    public Handmaid() {
        super("Handmaid", 4, "\nWhen you discard the Handmaid, you are immune to the effects of other players’ cards\n" +
                "until the start of your next turn. If all players other than the player whose turn it is are protected by the Handmaid,\n" +
                "the player must choose him or herself for a card’s effects, if possible.");
    }
    /**
     * Applies the effect of the Handmaid card to the current player.
     * This method is called when the Handmaid card's effect is triggered. It sets the current player's
     * handmaid status to true, making him immune to other players' card effects until his next turn.
     * The player is informed about his immunity.
     * @param current_player The player using the Handmaid card to become immune to other effects.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck) {
        current_player.set_handmaid_status_true();
        System.out.println("Player " + current_player.get_name() + " now you are immune to the other effect until your next turn\n");
    }

}
