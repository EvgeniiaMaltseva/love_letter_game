package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.Player;

public class Princess extends Card {
    /**
     * Constructs a Princess card by calling the superclass constructor with predefined values.
     */
    public Princess() {
        super("Princess", 8, "\nIf you discard the Princess — you are immediately knocked out of the round.\n" +
                "If the Princess was discarded by a card effect, any remaining effects of that card do not apply\n" +
                "(you do not draw a card from the Prince, for example).");
    }
    /**
     * Applies the effect of the card to the current player. This method is called when the card's effect is triggered.
     * In this specific case, the method sets the current player as "out of round" and prints a message indicating
     * that the player is out of the round.
     * @param current_player The player on whom the card's effect is applied.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    @Override
    public void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck){
        current_player.set_out_of_round();
        System.out.println("You are out of round!");
    }
}
