package love_letter_game;
/**
 * The `Game` class serves as the entry point for the game application.
 * It contains the `main` method, which initializes and starts the game engine.
 */
public class Game {
    /**
     * This method invokes the start game method as well as play game method,
     * by using the `GameEngine` class.
     */
    public static void main(String[] args) {
        GameEngine.start_game();
        GameEngine.play_game();
    }
}
