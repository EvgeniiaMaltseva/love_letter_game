package love_letter_game.cards;

import love_letter_game.Card;
import love_letter_game.Deck;
import love_letter_game.GameControls;
import love_letter_game.Player;
/**
 * This class represents the Baron card, which is a specific type of card in the game.
 * The card extends the generic Card class to inherit common card attributes and behaviors.
 * Extending the Card class allows the Baron to be used interchangeably with other cards in the game.
 * The Baron class defines its unique behavior in the apply_effect method.
 */
public class Baron extends Card {
    /**
     * Constructs a Baron card by calling the superclass constructor with predefined values.
     */
    public Baron() {
        super("Baron", 3, "\nWhen you discard the Baron, choose another player still in the round.\n" +
                "You and that player secretly compare your hands. The player with the lower number is knocked out of the round.\n" +
                "In case of a tie, nothing happens");
    }
    /**
     * Applies the effect of the Baron card to the current player.
     * This method is called when the Baron card's effect is triggered. It allows the current player to
     * compare their card with another player's card and determine the outcome based on card ranks.
     * If the current player's card has a higher rank, the selected player is set as "out of round,"
     * and his card is discarded. If the selected player's card has a higher rank, the current player is set as "out of round,"
     * and his card is discarded. If both players have the same card ranks, a message is displayed indicating a tie.
     * If all other players are protected by a Handmaid or out of the round, the method notifies the player
     * and proceeds to the next round without any card effect.
     * @param current_player The player using the Baron card to compare their card with another player's card.
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
            num_player = GameControls.select_player(players); // return player's input
        }
        Player selected_player = players[num_player];
        // main baron logic
        if (current_player.getCards().get(0).getRank() > selected_player.getCards().get(0).getRank()) {
            selected_player.set_out_of_round();
            deck.insert_into_discard_cards(selected_player.remove_card());
            System.out.println("Player: " + selected_player.get_name() + " out of round");
        } else if (current_player.getCards().get(0).getRank() < selected_player.getCards().get(0).getRank()) {
            current_player.set_out_of_round();
            deck.insert_into_discard_cards(current_player.getCards().remove(0));
            System.out.println("Player: " + current_player.get_name() + " out of round");
        } else // players have same cards
            System.out.println("You both have the same cards... WOHOOOOO!!");
    }
}
