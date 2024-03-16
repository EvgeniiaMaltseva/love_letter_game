package love_letter_game;
import love_letter_game.cards.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
/**
 * The `Deck` class represents a deck of cards used in the game.
 * It manages the creation, shuffling, and maintenance of the card deck, as well as providing access to the top extra card.
 */
public class Deck {
    private ArrayList<Card> dicarded_cards;
    private Stack<Card> deck;
    private Card top_extra_card;
    private static int total_cards = 16; //16
    /**
     * Creates a new deck of cards by initializing and shuffling the main deck.
     * The deck includes one Princess, one Countess, one King, two each of Prince, Handmaid, Baron, and Priest, and five Guards.
     */
    public void create_deck() {
        ArrayList<Card> cards = new ArrayList<>();
        dicarded_cards = new ArrayList<>();

        cards.add(new Princess());
        cards.add(new Countess());
        cards.add(new King());

        for (int i = 0; i < 2; i++) {
            cards.add(new Prince());    // x2
            cards.add(new Handmaid());  // x2
            cards.add(new Baron());     // x2
            cards.add(new Priest());    // x2
        }
        for (int i = 0; i < 5; i++) {
            cards.add(new Guard());     // x5
        }
        /**
         * Shuffles the provided list of cards randomly.
         * @param cards The list of cards to be shuffled.
         */
        Collections.shuffle(cards);

        deck = new Stack<>();
        for (Card it : cards) {
            deck.push(it);
        }
        top_extra_card = deck.pop();
    }
    /**
     * This method is used to store a played card into the pile of discarded cards.
     * @param card The card to be inserted into the discarded cards pile.
     */
    public void insert_into_discard_cards(Card card) {
        dicarded_cards.add(card);
    }
    /**
     * Shuffles the discarded cards pile if it contains all the cards from the deck.
     * If not all the cards were collected, a notifying massage appears
     */
    public void shuffle_discard_cards() {
        if (dicarded_cards.size() == total_cards)
            Collections.shuffle(dicarded_cards);
        else
            System.out.println("We got missing cards... Let's try to find it!");
    }
    /**
     * Rebuilds the main deck using the discarded shuffled cards pile.
     * Checks if the main deck is empty and if there are any missing cards.
     */
    public void rebuild_deck() {
        if (!deck.empty())
            System.out.println("Previous deck not empty");
        if (dicarded_cards.size() != total_cards)
            System.out.println("We got missing cards... Let's try to find it!");

        for (int i = 0; i < total_cards; i++) {
            deck.push(dicarded_cards.remove(0));
        }
    }
    /**
     * Retrieves the top card from the main deck.
     * @return The top card from the deck.
     */
    public Card getCard() {
        return deck.pop();
    }
    /**
     * Returns the current size of the main deck.
     * @return The number of cards remaining in the main deck.
     */
    public int get_deck_size() {
        return deck.size();
    }
    /**
     * Retrieves the top extra card, which is not part of the main deck.
     * The card is used if the main deck is empty.
     * @return The top extra card.
     */
    public Card get_top_extra_card() {
        return top_extra_card;
    }
    /**
     * After collecting all the cards into discarded card pile,
     * or if the card was drawn by the player.
     * Sets the top extra card to null.
     */
    public void set_top_extra_card() {
        top_extra_card = null;
    }
}

