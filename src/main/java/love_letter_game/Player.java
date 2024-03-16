package love_letter_game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Player class represents a player in the game.
 * Players are compared based on the number of days since their last date.
 * This class implements the Comparable interface to enable comparison between players.
 */
public class Player implements Comparable<Player> {
    private String name;
    private int days_since_last_date;
    private int score;
    private ArrayList<Card> cards;
    private boolean handmaid_activated = false;
    private boolean out_of_round = false;
    /**
     * Set the player's points by incrementing their score after winning the round.
     */
    public void set_points() {
        this.score++;
    }
    /**
     * Get the player's current score.
     * @return The player's score.
     */
    public int get_points() {
        return this.score;
    }
    /**
     * Create a new Player object with an empty card hand.
     */
    public Player() {
        cards = new ArrayList<>();
    }
    public String get_name() {
        return this.name;
    }
    public void set_name(String name) {
        this.name = name;
    }
    /**
     * Compare players based on the number of days since their last date. This comparison method is
     * implemented as required by the Comparable interface. It allows players to be sorted in ascending
     * order of days since their last date, which is used in determining the order of play in the game.
     * @param otherPlayer Another player to compare with.
     * @return 0 if both players have the same number of days, a negative value if this player has fewer days,
     * and a positive value if this player has more days.
     */
    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(this.days_since_last_date, otherPlayer.get_last_date());
    }
    public int get_last_date() {
        return this.days_since_last_date;
    }
    public void set_last_date(int last_date) {
        this.days_since_last_date = last_date;
    }
    /**
     * This method sets the current player's status to "out of the round," after some card effect was applied,
     * indicating that the player is no longer active in the current game round.
     */
    public void set_out_of_round() {
        this.out_of_round = true;
    }
    /**
     * Reset the player's status 'in_round' before the new round starts.
     */
    public void reset_out_of_round() {
        this.out_of_round = false;
    }
    /**
     * Check if the player is in the current round.
     * @return 'true' if the player is out of the round, otherwise 'false'.
     */
    public boolean player_status() {
        return this.out_of_round;
    }
    /**
     * Adds a card to the player's hand.
     * @param card The card to be added to the player's hand.
     */
    public void add_card(Card card) {
        cards.add(card);
    }
    /**
     * Removes and returns the first card in the player's hand.
     * @return The first card in the player's hand.
     */
    public Card remove_card() {
        return cards.remove(0);
    }
    /**
     * Checks the handmaid status of the player.
     * @return 'true' if the player's handmaid status is active, otherwise 'false'.
     */
    public boolean get_handmaid_status() {
        return handmaid_activated;
    }
    /**
     * Resets players status to false, before the new round starts.
     */
    public void set_handmaid_status_false() {
        handmaid_activated = false;
    }
    /**
     * Sets the player's handmaid status to true, after applying the handmaid effect.
     */
    public void set_handmaid_status_true() {
        handmaid_activated = true;
    }
    /**
     * Distribute the card from the deck to each player at the game_start /
     * during the round as 2. card / at the start_new_round
     * Resets handmaid status during the round.
     * @param deck The deck of cards to draw from.
     */
    public void drawCard(Deck deck) {
        //if handmaid was played in last round reset it
        if (handmaid_activated)
            handmaid_activated = false;
        cards.add(deck.getCard());
    }

    /**
     * Allows the player to select and play a card from their hand, following game rules.
     * If the player has a Countess and either a Prince or King card, the Countess is automatically played.
     * Otherwise, the player is prompted to choose a card to discard.
     * @return The card that the player has selected and played.
     */
    public Card playCard() {
        Card card_to_be_played = null;
        // count the number of Prince and Countess cards in the player's hand
        int princeCount = 0;
        int kingCount = 0;
        int countessCount = 0;

        for (Card card : cards) {
            if (card.getRank() == 5) {
                princeCount++;
            } else if (card.getRank() == 7) {
                countessCount++;
            } else if (card.getRank() == 6) {
                kingCount++;
            }
        }
        if ((princeCount == 1 || kingCount == 1) && countessCount == 1) {
            System.out.println("Play countess");
            for (int i = 0; i < 2; i++)
                if (cards.get(i).getRank() == 7) {
                    card_to_be_played = cards.remove(i);
                    return card_to_be_played;
                }
        }
        System.out.println("What card would you like to discard?\n" +
                "Please select 1 or 2:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int card_number = scanner.nextInt();
                if (card_number < 1 || card_number > 2)
                    System.out.println("Invalid card number, try again.");
                else {
                    ArrayList<Card> card = cards;
                    card_to_be_played = card.get(card_number - 1);
                    card.remove(card_number - 1);
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return card_to_be_played;
    }
    /**
     * Get the player's current hand of cards.
     * @return An ArrayList of Card objects representing the player's hand.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
}
