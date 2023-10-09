package atl.g48982.jeu2048.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class englobes the game elements.
 *
 * @author Jules
 */
public class Game implements Model {

    private final Board board;
    private final Rand generator;
    private boolean arrayChanged;
    private PropertyChangeSupport support;

    /**
     * Default constructor
     */
    public Game() {

        this.board = new Board();
        this.generator = new Rand();
        this.arrayChanged = false;
        this.support = new PropertyChangeSupport(board);
    }

    /**
     * Accessor for arrayChanged.
     *
     * @return true if numbers has changed false otherwise.
     */
    public boolean isArrayChanged() {

        return arrayChanged;
    }

    /**
     * gets the game status.
     *
     * @return true if the game has been won false otherwise.
     */
    @Override
    public boolean gameWon() {
        return isOver() && magicNumber();
    }

    /**
     * Accessor for Board.
     *
     * @return the board of the game.
     */
    public Board getBoard() {

        return board;
    }

    /**
     * Gets the array of numbers.
     *
     * @return the array depicting the numbers on the Board.
     */
    public int[][] numbers() {

        return board.getNumbers();
    }

    /**
     * moves numbers in the way provided by direction.
     *
     * @param direction the way to move to amongst UP,DOWN LEFT OR RIGHT.
     */
    @Override
    public void play(Direction direction) {

        Objects.requireNonNull(direction);
        arrayChanged = false;
        int[][] copyNumbers = copy(this.numbers());
        board.moveAll(direction);
        addNumberToBoard(copyNumbers);

    }

    /**
     * Gets the score at any given time.
     *
     * @return the score for the current game.
     */
    @Override
    public int score() {
        return board.getScore();
    }

    /**
     * Checks if the current game round is finished.
     *
     * @return true if one square has a number equal to 2048 or no numbers can
     * be moved in either of the four possible directions.
     */
    @Override
    public boolean isOver() {

        return magicNumber() || ((board.isFull()) && (!board.twinsHorizontal() && !board.twinsVertical()));

    }

    /**
     * Checks if the "magic" number has been reached.
     *
     * @return true if one square has a number 2048 false otherwise.
     */
    private boolean magicNumber() {

        long count = Arrays.stream(numbers()).flatMapToInt(Arrays::stream)
                .takeWhile(p -> p != 2048)
                .count();

        return (count != numbers().length * numbers()[0].length);

    }

    /**
     * Adds a number from one of two predefined multiple of two. In this case a
     * two or four to the board if at least one number has moved from its
     * previous position and the board is not full.
     *
     * @param numbersBefore the array of numbers before a play.
     */
    @Override
    public void addNumberToBoard(int[][] numbersBefore) {

        Objects.requireNonNull(numbersBefore);

        boolean same;

        same = numberHasMoved(numbersBefore, numbers());

        if (!same) {

            arrayChanged = true;
        }

        List<Integer> coordinates;

        coordinates = generator.spot(board.getNumbers());

        if (!same && coordinates.size() == 3) {

            board.setNumber(coordinates.get(0), coordinates.get(1), coordinates.get(2));
            this.support.firePropertyChange("numbers", numbersBefore, numbers());
        }

    }

    /**
     * Checks if two bi-dimensional Arrays are equal.
     *
     * @param numbersBefore the first array of integers.
     * @param numbersAfter the second array of integers.
     * @return true if they are equal false otherwise.
     */
    private boolean numberHasMoved(int[][] numbersBefore, int[][] numbersAfter) {

        Objects.requireNonNull(numbersBefore);
        Objects.requireNonNull(numbersAfter);

        return Arrays.deepEquals(numbersBefore, numbersAfter);

    }

    /**
     * Helper method to make a copy of an array.
     *
     * @param numbersToCopy the array to make a copy of.
     * @return a copy of the array.
     */
    public static int[][] copy(int[][] numbersToCopy) {

        Objects.requireNonNull(numbersToCopy);

        final int[][] res = new int[numbersToCopy.length][];

        for (int i = 0; i < numbersToCopy.length; i++) {

            res[i] = Arrays.copyOf(numbersToCopy[i], numbersToCopy[i].length);
        }
        return res;

    }

    /**
     * adds an observer to the list of observers.
     *
     * @param listener the observer to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * removes an observer to the list of observers.
     *
     * @param listener the observer to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Sets the various elements to begin a game round to initial states.
     */
    public void initNewGame() {

        for (int[] array : this.numbers()) {

            for (int a : array) {

                a = 0;
            }

        }

        this.arrayChanged = false;

        this.board.initBoard();

    }
}
