package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.GameControls;
import love_letter_game.Player;

public class Priest extends Card {
    /**
     * Constructs a Priest card by calling the superclass constructor with predefined values.     */
    public Priest() {
        super("Priest", 2, "\nWhen you discard the Priest, you can look at another player’s hand.\n" +
                "Do not reveal the hand to any other players.");
    }
    /**
     * Applies the effect of the Priest card to the current player.
     * This method is called when the Priest card's effect is triggered. It allows the current player
     * to choose another player to see his card.
     * If all other players are protected by a Handmaid or out of the round, the method notifies the player and proceeds to the next round.
     * @param current_player The player using the Priest card to inspect another player's card.
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
        // main priest logic
        Card to_check_other_player_card = selected_player.getCards().get(0);
        System.out.println("Player: " + selected_player.get_name() + " has this card: " + to_check_other_player_card.get_name());
    }

}
