package love_letter_game;

/**
 * The abstract class Card represents a card in the game.
 * Each card has a name, rank, and an effect that can be applied to the game.
 */
public abstract class Card {
    private String name;
    private int rank;
    private String effect;
    public static final int PRINCESS_RANK = 8;
    /**
     * Constructor to create a Card object with a name, rank, and effect.
     * @param name    The name of the card.
     * @param rank    The rank of the card.
     * @param effect The description of the card's effect.
     */
    public Card(String name, int rank, String effect) {
        this.name = name;
        this.rank = rank;
        this.effect = effect;
    }
    public String get_name() {
        return name;
    }
    public int getRank() {
        return rank;
    }
    public String getEffect(){
        return effect;
    }
    /**
     * Applies the effect of a specific card to the game.
     * This method is meant to be implemented by subclasses representing different card types.
     * It defines the action taken when a particular card is played by the current player.
     *
     * @param current_player The player who is playing the card.
     * @param current_player_index The index of the current player in the array of players.
     * @param players An array of all players in the game.
     * @param deck The game deck used for card interactions.
     */
    public abstract void apply_effect(Player current_player, int current_player_index, Player[] players, Deck deck);
}
