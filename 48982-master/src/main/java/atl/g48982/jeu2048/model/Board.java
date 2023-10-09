package atl.g48982.jeu2048.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * This class is the four by four playing board.
 *
 * @author jules
 */
public class Board {

    public static final int SIZE = 4;

    private int[][] numbers;

    private int score;

    /**
     * Accessor for score.
     *
     * @return the score at any given point.
     */
    public int getScore() {
        return score;
    }

    /**
     * Default constructor creates a 4*4 array.All initial values are zeros but
     * one.
     */
    public Board() {

        int row;
        int column;
        Random rand = new Random();
        this.numbers = new int[SIZE][SIZE];

        this.score = 0;
        column = rand.nextInt(4);
        row = rand.nextInt(4);
        numbers[row][column] = 2;

    }
    
   public void initBoard(){

        int row;
        int column;
        Random rand = new Random();
        this.numbers = new int[SIZE][SIZE];

        this.score = 0;
        column = rand.nextInt(4);
        row = rand.nextInt(4);
        numbers[row][column] = 2;

       }

    /**
     * Accessor for numbers.
     *
     * @return the numbers array.
     */
    public int[][] getNumbers() {

        return Arrays.copyOf(numbers, numbers.length);

    }

    /**
     * Checks if a location on the board does not contain a multiple of 2.
     *
     * @param row the row number of the location.
     * @param column the column number of the location.
     * @return true if it does not contain a multiple of 2 false otherwise.
     */
    public boolean isEmpty(int row, int column) {

        return this.numbers[row][column] == 0;

    }

    /**
     * Checks if a combination of a row and column are inside the board.
     *
     * @param row the row value.
     * @param column the column value..
     * @return true if the location formed by row and column is inside the board
     * false otherwise.
     */
    public boolean isInside(int row, int column) {

        return (row >= 0 && row < 4) && (column >= 0 && column < 4);

    }

    /**
     * Keeps squares numbers values updated as movements in directions occur.
     *
     * @param originRow the starting row.
     * @param originCol the starting column.
     * @param destRow the destination row.
     * @param destCol the destination column.
     */
    void upDateNumber(int originRow, int originCol, int destRow, int destCol) {

        numbers[originRow][originCol] += numbers[destRow][destCol];

        score += numbers[originRow][originCol];

        setNumber(destRow, destCol, 0);

    }

    /**
     * Checks if two squares on the board have same value other than zero.
     *
     * @param fromRow the row number of square one.
     * @param fromCol the column number of square one.
     * @param toRow the row number of square two.
     * @param toCol the column number of square two.
     * @return true if they both have the same value false otherwise.
     */
    private boolean nextIsSame(int fromRow, int fromCol, int toRow, int toCol) {

        return (!isEmpty(fromRow, fromCol) && !isEmpty(toRow, toCol))
                && (numbers[fromRow][fromCol] == numbers[toRow][toCol]);

    }

    /**
     * Moves a number upward and processes any similar neighbouring value it
     * might have downward. it subsequently deals with zeros that appear as a
     * consequence.
     *
     * @param row the row number.
     * @param column the column number.
     * @return true if a similar neighbouring value was detected false
     * otherwise.
     */
    public boolean moveUp(int row, int column) {

        boolean sameFound = false;

        int destRow;
        int destCol;

        destRow = row + 1;

        destCol = column;

        if (isInside(destRow, destCol)) {

            if (nextIsSame(row, column, destRow, destCol) && (!isEmpty(destRow, destCol))) {

                sameFound = true;
                upDateNumber(row, column, destRow, destCol);

            }

            if (isEmpty(row, column) && !isEmpty(destRow, destCol)) {

                setNumber(row, column, numbers[destRow][destCol]);

                setNumber(destRow, destCol, 0);

                while (isInside(destRow, destCol) && isEmpty(destRow, destCol)) {

                    destRow++;

                }

                if (destRow < numbers[0].length && nextIsSame(row, column, destRow, destCol)) {

                    upDateNumber(row, column, destRow, column);

                }

            }

        }
        return sameFound;

    }

    /**
     * Moves a number downward and processes any similar neighbouring value it
     * might have upward. it subsequently deals with zeros that appear as a
     * consequence.
     *
     * @param row the row number.
     * @param column the column number.
     * @return true if a similar neighbouring value was detected false
     * otherwise.
     */
    public boolean moveDown(int row, int column) {

        boolean sameFound = false;

        int destRow;
        int destCol;
        destRow = row - 1;
        destCol = column;

        if (isInside(destRow, destCol)) {

            if (nextIsSame(row, column, destRow, destCol) && (!isEmpty(destRow, destCol))) {

                sameFound = true;
                upDateNumber(row, column, destRow, destCol);

            }

            if (isEmpty(row, column) && !isEmpty(destRow, destCol)) {

                setNumber(row, column, numbers[destRow][destCol]);

                setNumber(destRow, destCol, 0);

                while (isInside(destRow, destCol) && isEmpty(destRow, destCol)) {

                    destRow--;

                }

                if (destRow >= 0 && nextIsSame(row, column, destRow, destCol)) {

                    upDateNumber(row, column, destRow, column);

                }

            }

        }
        return sameFound;

    }

    /**
     * Moves a number on the right and processes any similar neighbouring value
     * it might have on the left. it subsequently deals with zeros that appear
     * as a consequence.
     *
     * @param row the row number.
     * @param column the column number.
     * @return true if a similar neighbouring value was detected false
     * otherwise.
     */
    public boolean moveRight(int row, int column) {

        boolean sameFound = false;

        int destRow;
        int destCol;

        destRow = row;

        destCol = column - 1;

        if (isInside(destRow, destCol)) {

            if (nextIsSame(row, column, destRow, destCol) && (!isEmpty(destRow, destCol))) {

                sameFound = true;
                upDateNumber(row, column, destRow, destCol);

            }

            if (isEmpty(row, column) && !isEmpty(destRow, destCol)) {

                setNumber(row, column, numbers[destRow][destCol]);

                setNumber(destRow, destCol, 0);

                while (isInside(destRow, destCol) && isEmpty(destRow, destCol)) {

                    destCol--;

                }

                if (destCol >= 0 && nextIsSame(row, column, destRow, destCol)) {

                    upDateNumber(row, column, row, destCol);

                }

            }

        }
        return sameFound;
    }

    /**
     * sets the value of the board element to a specified value.
     *
     *
     * @param row the row number.
     * @param column the column number.
     * @param value the number to be set.
     */
    void setNumber(int row, int column, int value) {

        numbers[row][column] = value;
    }

    /**
     * Moves a number from a square to another in a specific direction.
     *
     * @param row the row number of the element.
     * @param column the column number of the element.
     * @param dir the direction of the movement.
     * @return true if a neighbouring non zero number was encountered false
     * otherwise.
     */
    public boolean move(int row, int column, Direction dir) {

        boolean matchFound = false;

        switch (dir) {

            case UP:

                matchFound = moveUp(row, column);

                break;

            case DOWN:

                matchFound = moveDown(row, column);

                break;

            case LEFT:
                matchFound = moveLeft(row, column);

                break;

            case RIGHT:

                matchFound = moveRight(row, column);

                break;

        }

        return matchFound;
    }

    /**
     * Moves a number on the left and processes any similar neighbouring value
     * it might have on the right. it subsequently deals with zeros that appear
     * as a consequence.
     *
     * @param row the row number.
     * @param column the column number.
     * @return true if a similar neighbouring value was detected false
     * otherwise.
     */
    public boolean moveLeft(int row, int column) {

        boolean sameFound = false;

        int destRow;
        int destCol;
        destRow = row;

        destCol = column + 1;

        if (isInside(destRow, destCol)) {

            if (nextIsSame(row, column, destRow, destCol)
                    && (!isEmpty(destRow, destCol))) {

                sameFound = true;
                upDateNumber(row, column, destRow, destCol);

            }
            if (isEmpty(row, column) && !isEmpty(destRow, destCol)) {

                setNumber(row, column, numbers[destRow][destCol]);

                setNumber(destRow, destCol, 0);

                while (isInside(destRow, destCol) && isEmpty(destRow, destCol)) {

                    destCol++;

                }

                if (destCol < numbers[row].length && nextIsSame(row, column, destRow, destCol)) {

                    upDateNumber(row, column, row, destCol);

                }

            }

        }
        return sameFound;
    }

    /**
     * Clears any empty spaces between any two non empty squares horizontally
     * and shifts non empty squares elements to the right.
     */
    public void clearRight() {

        int temp;

        for (int row = 0; row < numbers.length; row++) {

            for (int col = numbers[row].length - 1; col >= 0; col--) {

                temp = col;

                if (!isEmpty(row, temp)) {

                    continue;
                }
                while (isInside(row, temp) && isEmpty(row, temp)) {

                    temp--;

                }

                if (temp >= 0 && !isEmpty(row, temp)) {

                    setNumber(row, col, numbers[row][temp]);

                    setNumber(row, temp, 0);

                }
            }

        }

    }

    /**
     * Clears any empty spaces between any two non empty squares horizontally
     * and shifts non empty squares elements to the left.
     */
    public void clearLeft() {

        int temp;

        for (int row = 0; row < numbers.length; row++) {

            for (int col = 0; col < numbers[row].length; col++) {

                temp = col;

                if (!isEmpty(row, temp)) {

                    continue;
                }
                while (isInside(row, temp) && isEmpty(row, temp)) {

                    temp++;

                }

                if (temp < numbers[row].length && !isEmpty(row, temp)) {

                    setNumber(row, col, numbers[row][temp]);

                    setNumber(row, temp, 0);

                }
            }

        }

    }

    /**
     * Clears any empty spaces between any two non empty squares vertically and
     * shifts non empty squares elements downWard.
     */
    public void clearDown() {

        int temp;

        for (int col = 0; col < numbers[0].length; col++) {

            for (int row = numbers.length - 1; row >= 0; row--) {

                temp = row;

                if (!isEmpty(temp, col)) {

                    continue;
                }
                while (isInside(temp, col) && isEmpty(temp, col)) {

                    temp--;

                }

                if (temp >= 0 && !isEmpty(temp, col)) {

                    setNumber(row, col, numbers[temp][col]);

                    setNumber(temp, col, 0);

                }
            }

        }

    }

    /**
     * Clears any empty spaces between any two non empty squares vertically and
     * shifts non empty squares elements UpWard.
     */
    public void clearUp() {

        int temp;

        for (int col = 0; col < numbers[0].length; col++) {

            for (int row = 0; row < numbers.length; row++) {

                temp = row;

                if (!isEmpty(temp, col)) {

                    continue;
                }
                while (isInside(temp, col) && isEmpty(temp, col)) {

                    temp++;

                }

                if (temp < numbers.length && !isEmpty(temp, col)) {

                    setNumber(row, col, numbers[temp][col]);

                    setNumber(temp, col, 0);

                }
            }

        }

    }

    /**
     * Merges equal numbers DownWard. Any subsequent "holes" are filled with the
     * closest non zero number.
     */
    void runDown() {

        for (int col = 0; col < numbers[0].length; col++) {

            for (int row = numbers.length - 1; row >= 0; row--) {

                move(row, col, Direction.DOWN);

            }

        }
    }

    /**
     * Merges equal numbers Upward. Any subsequent "holes" are filled with the
     * closest non zero number.
     */
    void runUp() {

        for (int col = 0; col < numbers[0].length; col++) {

            for (int row = 0; row < numbers.length; row++) {

                move(row, col, Direction.UP);

            }

        }
    }

    /**
     * Merges equal numbers towards the left. Any subsequent "holes" are filled
     * with the closest non zero number.
     */
    void runLeft() {

        for (int row = 0; row < numbers.length; row++) {

            for (int col = 0; col < numbers[row].length; col++) {

                move(row, col, Direction.LEFT);

            }

        }
    }

    /**
     * Merges any equal numbers towards the right. Any subsequent "holes" are
     * filled with the closest non zero number.
     */
    void runRight() {

        for (int row = 0; row < numbers.length; row++) {

            for (int col = numbers[row].length - 1; col >= 0; col--) {

                move(row, col, Direction.RIGHT);
            }

        }

    }

    /**
     * Moves numbers in the direction passed if any are present.
     *
     * @param direction the orientation to move the numbers in.
     */
    public void moveAll(Direction direction) {

        Objects.requireNonNull(direction);
        
        switch (direction) {

            case UP:
                clearUp();
                runUp();
                break;

            case DOWN:

                clearDown();
                runDown();
                break;

            case LEFT:

                clearLeft();
                runLeft();
                break;

            case RIGHT:
                clearRight();
                runRight();

                break;

        }

    }

    /**
     * checks if two twins and neighbouring values are present and therefore can
     * be merged horizontally.
     *
     * @return true if two similar values exists horizontally false .
     */
    boolean twinsHorizontal() {



        for (int i = 0; i < getNumbers().length; i++) {

            for (int j = 0; j < getNumbers()[i].length; j++) {

                if (isInside(i, j + 1) && nextIsSame(i, j, i, j + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if two twins and neighbouring values are present and therefore can
     * be merged vertically.
     *
     * @return true if two similar values exists vertically false .
     */
    boolean twinsVertical() {



        for (int j = 0; j < numbers[0].length; j++) {

            for (int i = 0; i < numbers.length; i++) {

                if (isInside(i + 1, j) && nextIsSame(i, j, i + 1, j)) {
                    return true;
                }

            }

        }

        return false;
    }

    /**
     * Checks if the all squares of the board are occupied.
     *
     * @return true if there are no empty spots on the board false otherwise.
     */
    public boolean isFull() {

        int i = 0;

        int count = 0;

        while (i < numbers.length) {

            int j = 0;

            while (j < numbers[i].length) {

                if (!isEmpty(i, j)) {

                    count += 1;
                }

                j++;
            }
            i++;
        }
        return count == Math.pow(SIZE, 2);
    }

    

}
