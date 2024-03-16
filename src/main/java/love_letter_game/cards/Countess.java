package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.Player;
public class Countess extends Card {
    /**
     * Constructs a Countess card by calling the superclass constructor with predefined values.
     */
    public Countess() {
        super("Countess", 7, "\nIf you ever have the Countess and either the King or Prince in your hand, you must discard the Countess.\n" +
                "Of course, you can also discard the Countess even if you do not have a royal family member in your hand.");
    }
    /**
     * This method is automatically triggered if the player has a either (prince or king) and countess in his hand.
     * By discarding this card the game proceeds to the next round, without any changes.
     * @param current_player The player attempting to play the card.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck){
        System.out.println("No effects applied");
    }
}
