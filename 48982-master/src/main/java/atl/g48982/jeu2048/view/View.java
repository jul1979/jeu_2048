package atl.g48982.jeu2048.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

/**
 * This class interacts with the user.It collects input from user and displays
 * the game state in the console
 *
 * @author jules
 */
public class View implements PropertyChangeListener {

    private final Scanner keyBoard;

    /**
     * Default Constructor.
     */
    public View() {
        this.keyBoard = new Scanner(System.in);
    }

    /**
     * Gets the Scanner field.
     *
     * @return the input reader object.
     */
    public Scanner getKeyBoard() {
        return keyBoard;
    }

    /**
     * Displays an error message to inform the player that some invalid input
     * has been detected.
     */
    public void displayErrorMessage() {

        System.out.println("Your selection is invalid.Try AGAIN!");

    }

    /**
     * Displays the Game score.
     *
     * @param score,the current score.
     */
    public void displayScore(int score) {

        System.out.println("your score is : " + score + ".\n");

    }

    /**
     * Displays the board on the console.
     *
     * @param array being to be displayed.
     */
    public void displayBoard(int[][] array) {

        for (int[] ints : array) {

            for (int j = 0; j < ints.length; j++) {

                if (j == ints.length - 1) {

                    System.out.printf(" %4d%n", ints[j]);

                } else {

                    System.out.printf(" %4d", ints[j]);

                }

            }

        }

    }

    /**
     * Displays guidance information to the player to play a round.
     */
    public void displayInfo() {

        System.out.println("1-PRESS ONE TO MOVE RIGHT");
        System.out.println("2-PRESS TWO TO MOVE LEFT");
        System.out.println("3-PRESS THREE TO MOVE UP");
        System.out.println("4-PRESS FOUR TO MOVE DOWN");

    }

    /**
     * Displays a message at the end of the Game.
     *
     * @param gameWon boolean value depicting the state of the Game.
     *
     */
    public void endMessage(boolean gameWon) {

        System.out.println("The Game is over.");

        if (gameWon) {

            System.out.println("CONGRATULATIONS YOU WON!");

        } else {

            System.out.println("Sorry,YOU DID NOT WIN THIS TIME !");
        }

    }

    /**
     * Displays a welcome message on the Board.
     */
    public void welcome() {

        System.out.print("\nWELCOME TO 2048 GAME!\n");
    }

    /**
     * Informs the user the selection does not yield any movement on the board.
     */
    public void impossibleMove() {

        System.out.println("\n IMPOSSIBLE MOVE.");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        this.displayBoard((int[][]) evt.getNewValue());

    }
}
