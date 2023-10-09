package atl.g48982.jeu2048.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This class puts random numbers in an array of numbers
 *
 * @author Jules
 */
public class Rand {

    public static final int TWO = 2;
    public static final int FOUR = 4;
    private final Random random;

    /**
     * Default constructor.
     */
    public Rand() {

        random = new Random();

    }

    /**
     * Checks if an array of integers has at least one number equal to zero.
     *
     * @param numbers the array to evaluate.
     * @return true if it contains at least one zero false otherwise.
     */
    boolean hasfreeSpots(int[] numbers) {
        
        Objects.requireNonNull(numbers);
        return Arrays.stream(numbers).anyMatch(p -> p == 0);
    }



    /**
     * Finds all rows containing at least oneZero.
     *
     * @param squares the array to check.
     * @return the list of free rows.
     */
    ArrayList<Integer> freeRows(int[][] squares) {
        
        Objects.requireNonNull(squares);
        ArrayList<Integer> freeLines = new ArrayList<>();

        for (int i = 0; i < squares.length; i++) {

            if (hasfreeSpots(squares[i])) {

                freeLines.add(i);
            }

        }

        if (!freeLines.isEmpty()) {

            Collections.shuffle(freeLines);
        }
        return freeLines;

    }

    /**
     * Gets A Rand column number from a row that contains zeros.
     *
     * @param row the row number to pick a column from.
     * @param squares the 2D array of numbers.
     * @return a column number from that row.
     */
    private int columnSelected(int row, int[][] squares) {

         Objects.requireNonNull(squares);
        ArrayList<Integer> columnselected = new ArrayList<>();

        for (int col = 0; col < squares[row].length; col++) {

            if (squares[row][col] == 0) {

                columnselected.add(col);
            }

        }

        Collections.shuffle(columnselected);

        return columnselected.get(0);

    }

    /**
     * Selects one value amongst the candidates to be added to the array.
     *
     * @return either of one value choices.
     */
    private int value() {

        int select = random.nextInt(2);
        if (select == 0) {
            return TWO;
        }

        return FOUR;
    }

    /**
     * Fills a list with a maximum of two values.
     *
     * @param squares the array containing the numbers.
     * @return the list wich can be either empty,have one or two values.
     */
    public List<Integer> spot(int[][] squares) {
        
        Objects.requireNonNull(squares);
        int rowSelected;
        int colSelected;
        int choice;
        choice = value();
        ArrayList<Integer> squareSelected = new ArrayList<>();

        if (!freeRows(squares).isEmpty()) {

            rowSelected = freeRows(squares).get(0);

            squareSelected.add(rowSelected);

            colSelected = columnSelected(rowSelected, squares);
            squareSelected.add(colSelected);
            squareSelected.add(choice);

        }

        return squareSelected;

    }
}
