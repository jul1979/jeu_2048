package atl.g48982.jeu2048.model;

/**
 * This is an Interface.
 *
 * @author Jules
 */
public interface Model {

    /**
     * Checks if the current game round is finished.
     *
     * @return true if one square has a number equal to 2048 or no numbers can
     * be moved in either of the four possible directions.
     */
    boolean isOver();

    /**
     * moves numbers in the way provided by direction.
     *
     * @param dir the way to move to amongst UP,DOWN LEFT OR RIGHT.
     */
    void play(Direction dir);

    /**
     * Gets the score at any given time.
     *
     * @return the score for the current game.
     */
    int score();

    /**
     * Adds a number from one of two predefined multiple of two. In this case a
     * two or four to the board if at least one number has moved from its
     * previous position and the board is not full.
     *
     * @param numbersBefore the array of numbers before a play.
     */
    void addNumberToBoard(int[][] numbersBefore);


     /**
     * gets the game status.
     * @return true if the game has been won false otherwise.
     */
    boolean gameWon();
 
}


