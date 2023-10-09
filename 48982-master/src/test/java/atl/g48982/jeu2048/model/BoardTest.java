package atl.g48982.jeu2048.model;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for Board class
 *
 * @author Jules
 */
public class BoardTest {

    /**
     * Helper method for tests purposes. Fills the Board with Zeros values only.
     *
     * @param board the board to whose numbers to clear.
     */
    private void clearAll(Board board) {

        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                board.setNumber(i, j, 0);
            }

        }
    }

    /**
     * tests isEmpty method of Board class
     */
    @Test
    void testIsEmpty() {

        Board board = new Board();

        int count = 0;
        int Zeros = 15;
        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                if (board.isEmpty(i, j)) {

                    count++;
                }

            }

        }

        assertEquals(Zeros, count);
    }

    /**
     * Tests Board default constructor
     */
    @Test
    void testBoard_Constructor() {

        Board board = new Board();

        long zeros = 0;

        int expected = 4;

        long expectedZeros = 15;

        long nonZeros = 0;

        for (int i = 0; i < board.getNumbers().length; i++) {

            zeros += Arrays.stream(board.getNumbers()[i]).filter(p -> p == 0).count();
            nonZeros += Arrays.stream(board.getNumbers()[i]).filter(p -> p == 2).count();
        }

        assertEquals(expectedZeros, zeros);
        assertEquals(1, nonZeros);
        assertEquals(expected, board.getNumbers().length);
        assertEquals(expected, board.getNumbers()[0].length);
        assertEquals(expected, board.getNumbers()[1].length);
        assertEquals(expected, board.getNumbers()[2].length);
        assertEquals(expected, board.getNumbers()[3].length);
        assertEquals(0, board.getScore());
    }

    /**
     *
     * The various square locations to be test by testIsInside.
     *
     * @return a Stream of board laocations.
     */
    private static Stream<Arguments> rowsAndColumns() {

        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(3, 3),
                Arguments.of(0, 3),
                Arguments.of(0, 0),
                Arguments.of(2, 3),
                Arguments.of(2, 2),
                Arguments.of(3, 0)
        );
    }

    /**
     * Test of isInside method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumns")
    void testIsInside(int rw, int col) {

        Board board = new Board();

        assertTrue(board.isInside(rw, col));
    }

    /**
     * Test of isInside method , of class Board.
     */
    @Test
    void testIsInside_caseOut() {

        int rw = 4;
        int col = 4;
        Board board = new Board();
        assertFalse(board.isInside(rw, col));

    }

    /**
     * Test of move method, of class Board
     */
    @Test

    void testMove_CaseLeft_emptyDestination() {

        Board board = new Board();
        clearAll(board);
        board.setNumber(0, 0, 0);
        board.setNumber(0, 1, 2);
        board.setNumber(0, 2, 0);
        board.setNumber(0, 2, 0);
        board.move(0, 0, Direction.LEFT);
        assertEquals(2, board.getNumbers()[0][0]);
    }

    /**
     * Test of move method, of class Board
     */
    @Test

    void testMove_CaseLeft_DestinationNotFree() {

        Board board = new Board();
        clearAll(board);
        board.setNumber(0, 2, 2);
        board.setNumber(0, 3, 2);

        board.move(0, 2, Direction.LEFT);
        assertEquals(4, board.getNumbers()[0][2]);
    }

    /**
     * Test of move method, of class Board
     */
    @Test

    void testMove_CaseRight_DestinationNotFree() {

        Board board = new Board();
        clearAll(board);
        board.setNumber(1, 2, 2);
        board.setNumber(1, 3, 2);
        board.move(1, 3, Direction.RIGHT);
        assertEquals(4, board.getNumbers()[1][3]);
    }

    /**
     * Test of move method, of class Board
     */
    @Test

    void testMove_CaseRight_emptyDestination() {

        Board board = new Board();
        clearAll(board);
        board.setNumber(1, 1, 2);
        board.setNumber(1, 2, 0);
        board.move(1, 2, Direction.RIGHT);
        assertEquals(2, board.getNumbers()[1][2]);
    }

    /**
     * Test of isFree Method, of class Board
     */
    @Test
    void testisFree() {

        Board board = new Board();
        clearAll(board);

        board.setNumber(0, 0, 2);
        board.setNumber(3, 3, 0);
        assertTrue(board.isEmpty(3, 3));
        assertFalse(board.isEmpty(0, 0));

    }

    /**
     *
     * The various square locations to be tested by moveUp, case bottom board
     * line.
     *
     * @return a Stream of board laocations.
     */
    private static Stream<Arguments> rowsAndColumnsUp() {

        return Stream.of(
                Arguments.of(2, 0),
                Arguments.of(2, 1),
                Arguments.of(2, 2),
                Arguments.of(2, 3)
        );
    }

    /**
     * 
     * Test of moveUp method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsUp")
    void testmoveUp_BottomBoardLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);
        board.setNumber(3, 0, 2);
        board.setNumber(3, 1, 2);
        board.setNumber(3, 2, 2);
        board.setNumber(3, 3, 2);
        board.setNumber(rw, col, 2);
        board.moveUp(rw, col);

        assertEquals(4,board.getNumbers()[rw][col]);
    }

    /**
     * Test of moveup ,of class Board.
     */
    @Test
    void testmoveUp_CaseNoZerosBetween() {

        Board board = new Board();
        clearAll(board);
        board.setNumber(0, 0, 2);
        board.setNumber(1, 0, 2);
        board.setNumber(2, 0, 2);
        board.setNumber(3, 0, 2);

        board.moveUp(0, 0);
        board.moveUp(2, 0);

        assertEquals(4,board.getNumbers()[0][0]);
        assertEquals(4,board.getNumbers()[2][0]);

    }

    /**
     *
     * The various square locations to be tested by moveUp, case top board line
     *
     * @return a Stream of board locations.
     */
    private static Stream<Arguments> rowsAndColumnsUp2() {

        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(0, 1),
                Arguments.of(0, 2),
                Arguments.of(0, 3)
        );
    }

    /**
     * Test of moveUp method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsUp2")
    void testmoveUp_TopBoardLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);

        board.setNumber(rw, col, 2);
        board.moveUp(rw, col);

        assertEquals(2,board.getNumbers()[rw][col] );
    }

    /**
     * Test of moveDown method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsUp")
    void testmoveDown_topBoardLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);

        board.setNumber(rw, col, 2);

        board.moveDown(rw, col);

        assertEquals(2,board.getNumbers()[rw][col]);
    }

    /**
     * Test of moveDown method,of class Board.
     */
    @Test
    void testmoveDown_midlleLine() {

        Board board = new Board();
        clearAll(board);

        board.setNumber(0, 3, 2);
        board.setNumber(1, 3, 2);
        board.setNumber(2, 3, 2);
        board.setNumber(3, 3, 2);

        board.moveDown(1, 3);
        board.moveDown(3, 3);

         assertEquals(4,board.getNumbers()[1][3] );
         assertEquals(0,board.getNumbers()[0][3] );
         assertEquals(0,board.getNumbers()[2][3] );
         assertEquals(4,board.getNumbers()[3][3] );
    }

    /**
     *
     * The various square locations to be tested by moveRight,case border line
     *
     * @return a Stream of board locations.
     */
    private static Stream<Arguments> rowsAndColumnsRight2() {

        return Stream.of(
                Arguments.of(0, 3),
                Arguments.of(1, 3),
                Arguments.of(2, 3),
                Arguments.of(3, 3)
        );
    }

    /**
     * Test of moveRight method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsRight2")
    void testmoveRightmidlleLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);

        board.setNumber(rw, col, 16);

        board.moveRight(rw, col);

      assertEquals(16,board.getNumbers()[rw][col] );
    }

    /**
     *
     * The various square locations to be tested by moveLeft,case middle line
     *
     * @return a Stream of board locations.
     */
    private static Stream<Arguments> rowsAndColumnsLeft1() {

        return Stream.of(
                Arguments.of(0, 2),
                Arguments.of(1, 2),
                Arguments.of(2, 2),
                Arguments.of(3, 2)
        );
    }

    /**
     * Test of moveLeft method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsLeft1")

    void testmoveLefttmidlleLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);

        board.setNumber(rw, col, 16);
        board.setNumber(rw, col + 1, 16);

        board.moveLeft(rw, col);

        assertEquals(32,board.getNumbers()[rw][col] );
    }

    /**
     *
     * The various square locations to be tested by moveLeft,case border line
     *
     * @return a Stream of board locations.
     */
    private static Stream<Arguments> rowsAndColumnsLeft2() {

        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 0),
                Arguments.of(2, 0),
                Arguments.of(3, 0)
        );
    }

    /**
     * Test of moveLeft method,of class Board.
     *
     * @param rw a row number.
     * @param col a column number.
     */
    @ParameterizedTest
    @MethodSource("rowsAndColumnsLeft2")
    void testmoveLeftBorderLine(int rw, int col) {

        Board board = new Board();
        clearAll(board);

        board.setNumber(rw, col, 16);

        board.moveLeft(rw, col);

        assertEquals(16,board.getNumbers()[rw][col] );
    }

    /**
     * Test of isFull method,of class Board.
     */
    @Test
    @DisplayName("testisFull no empty spots")
    void testisFull_CaseFull() {

        int value;

        Board board = new Board();
        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                value = (int) Math.pow(2, j);
                board.setNumber(i, j, value);
            }
        }
        assertTrue(board.isFull());
    }

    /**
     * Test of isFull method,of class Board.
     */
    @Test
    @DisplayName("testisFull with empty spots")
    void testisFull_CaseNotFull() {

        int value;

        Board board = new Board();
        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                if (j % 2 == 0) {

                    value = (int) Math.pow(2, j);
                    board.setNumber(i, j, value);

                } else {

                    board.setNumber(i, j, 0);

                }

            }
        }
        assertFalse(board.isFull());
    }

    /**
     * Test of twinsVertical method,of class Board.
     */
    @Test
    void testTwinsVertical_CaseValuestoMergePresent() {

        Board board = new Board();

        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                if (j == 0) {

                    board.setNumber(i, j, 16);
                } else {

                    board.setNumber(i, j, 0);

                }

            }
        }

        assertTrue(board.twinsVertical());

    }

    /**
     * Test of twinsVertical method,of class Board.
     */
    @Test
    void testTwinsVertical_CaseValuestoMergeNotPresent() {

        Board board = new Board();

        int value;
        int count = 1;

        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                value = (int) Math.pow(2, count);

                board.setNumber(i, j, value);

                count++;

            }
        }

        assertFalse(board.twinsVertical());

    }

    /**
     * Test of twinsHorizontal method,of class Board.
     */
    @Test
    void testTwinsHorizontal_CaseValuestoMergeNotPresent() {

        Board board = new Board();

        int value;
        int count = 1;

        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                value = (int) Math.pow(2, count);

                board.setNumber(i, j, value);

                count++;

            }

        }

        assertFalse(board.twinsHorizontal());
    }

    /**
     * Test of twinsHorizontal method,of class Board.
     */
    @Test
    void testTwinsHorizontal_CaseValuestoMergePresent() {

        Board board = new Board();

        int value;
        int count = 1;

        for (int i = 0; i < board.getNumbers().length; i++) {

            for (int j = 0; j < board.getNumbers()[i].length; j++) {

                if ((i == 3 && j == 2) || (i == 3 && j == 3)) {

                    board.setNumber(i, j, 64);
                } else {

                    value = (int) Math.pow(2, count);

                    board.setNumber(i, j, value);

                    count += 1;
                }

            }

        }

        assertTrue(board.twinsHorizontal());
    }

    /**
     * Test of clearLeft method,of class Board.
     */
    @Test
    void testClearLeft_CaseZerosInBetween() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 0);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearLeft();
        assertEquals(16,board.getNumbers()[2][0] );
        assertEquals(16,board.getNumbers()[2][1] );
        assertEquals(0,board.getNumbers()[2][2] );
        assertEquals(0,board.getNumbers()[2][3] );

    }

    /**
     * Test of clearLeft method,of class Board.
     */
    @Test
    void testClearLeft_CaseZerosBefore() {

        Board board = new Board();
        board.setNumber(2, 0, 0);
        board.setNumber(2, 1, 16);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearLeft();
        assertEquals(16,board.getNumbers()[2][0] );
        assertEquals(16,board.getNumbers()[2][1] );
        assertEquals(0,board.getNumbers()[2][2] );
        assertEquals(0,board.getNumbers()[2][3] );
    }

    /**
     * Test of clearRight method,of class Board.
     */
    @Test
    void testClearRight_CaseZerosInBetween() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 0);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearRight();
        assertEquals(0,board.getNumbers()[2][0] );
        assertEquals(0,board.getNumbers()[2][1] );
        assertEquals(16,board.getNumbers()[2][2]);
        assertEquals(16,board.getNumbers()[2][3]);

    }

    /**
     * Test of clearLeft method,of class Board.
     */
    @Test
    void testClearRight_CaseZerosBefore() {

        Board board = new Board();
        board.setNumber(2, 0, 0);
        board.setNumber(2, 1, 16);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearRight();
        assertEquals(0,board.getNumbers()[2][0] );
        assertEquals(0,board.getNumbers()[2][1] );
        assertEquals(16,board.getNumbers()[2][2] );
        assertEquals(16,board.getNumbers()[2][3] );
    }

    /**
     * Test of runLeft method,of class Board.
     */
    @Test
    void testrunLeft_Case1() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 16);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearLeft();
        board.runLeft();
        assertEquals(32,board.getNumbers()[2][0]);
        assertEquals(16,board.getNumbers()[2][1]);
        assertEquals(0,board.getNumbers()[2][2] );
        assertEquals(0,board.getNumbers()[2][3] );

    }

    /**
     * Test of runLeft method,of class Board.
     */
    @Test
    void testrunLeft_Case2() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 0);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearLeft();
        board.runLeft();
       assertEquals(32,board.getNumbers()[2][0] );
       assertEquals(0,board.getNumbers()[2][1] );
       assertEquals(0,board.getNumbers()[2][2] );
       assertEquals(0,board.getNumbers()[2][3] );

    }

    /**
     * Test of runRight method,of class Board.
     */
    @Test
    void testrunRight_Case1() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 16);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearRight();
        board.runRight();
        assertEquals(0,board.getNumbers()[2][0] );
        assertEquals(0,board.getNumbers()[2][1] );
        assertEquals(16,board.getNumbers()[2][2]);
        assertEquals(32,board.getNumbers()[2][3]);
    }

    /**
     * Test of runRight method,of class Board.
     */
    @Test
    void testrunRight_Case2() {

        Board board = new Board();
        board.setNumber(2, 0, 16);
        board.setNumber(2, 1, 0);
        board.setNumber(2, 2, 0);
        board.setNumber(2, 3, 16);
        board.clearRight();
        board.runRight();
        assertEquals(0,board.getNumbers()[2][0] );
        assertEquals(0,board.getNumbers()[2][1] );
        assertEquals(0,board.getNumbers()[2][2] );
        assertEquals(32,board.getNumbers()[2][3]);

    }

    /**
     * Test of clearDown ,of Board class
     *
     */
    @Test
    void testClearDown_CaseZeroBetween() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 0);
        board.setNumber(3, 3, 16);
        board.clearDown();
        assertEquals(16,board.getNumbers()[2][3] );
        assertEquals(16,board.getNumbers()[3][3] );
    }

    /**
     * Test of clearDown ,of Board class
     *
     */
    @Test
    void testClearDown_CaseMixedWithZeros() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 16);
        board.setNumber(3, 3, 0);
        board.clearDown();
        assertEquals(16,board.getNumbers()[2][3] );
        assertEquals(16,board.getNumbers()[3][3] );
    }

    /**
     * Test of runDown ,of Board class
     *
     */
    @Test
    void testrunDown_CaseZeroBetween() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 0);
        board.setNumber(3, 3, 16);

        board.setNumber(0, 2, 2);
        board.setNumber(1, 2, 2);
        board.setNumber(2, 2, 2);
        board.setNumber(3, 2, 2);

        board.clearDown();
        board.runDown();
        assertEquals(32,board.getNumbers()[3][3] );
        assertEquals(4,board.getNumbers()[2][2] );
        assertEquals(4,board.getNumbers()[3][2] );
    }

    /**
     * Test of runDown ,of Board class
     *
     */
    @Test
    void testrunDown_CaseMixedWithZeros() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 16);
        board.setNumber(3, 3, 0);
        board.clearDown();
        board.runDown();
        assertEquals(32,board.getNumbers()[3][3] );
    }

    /**
     * Test of clearDown ,of Board class
     *
     */
    @Test
    void testClearUP_CaseZeroBetween() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 0);
        board.setNumber(3, 3, 16);

        board.clearUp();
       assertEquals(16,board.getNumbers()[0][3] );
       assertEquals(16,board.getNumbers()[1][3] );
    }

    /**
     * Test of clearDown ,of Board class
     *
     */
    @Test
    void testClearUP_CaseMixedWithZeros() {

        Board board = new Board();
        board.setNumber(0, 3, 16);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 16);
        board.setNumber(3, 3, 0);
        board.clearUp();
        assertEquals(16,board.getNumbers()[0][3] );
        assertEquals(16,board.getNumbers()[1][3] );

    }

    /**
     * Test of runUp ,of Board class
     *
     */
    @Test
    void testRunUp_CaseZeroBetween() {

        Board board = new Board();
        board.setNumber(0, 3, 8);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 0);
        board.setNumber(3, 3, 8);

        board.setNumber(0, 2, 2);
        board.setNumber(1, 2, 2);
        board.setNumber(2, 2, 2);
        board.setNumber(3, 2, 2);

        board.clearUp();
        board.runUp();
        assertEquals(16,board.getNumbers()[0][3] );
        assertEquals(4,board.getNumbers()[0][2] );
        assertEquals(4,board.getNumbers()[1][2] );
    }

    /**
     * Test of runUp ,of Board class
     *
     */
    @Test
    void testRunUp_CaseMixedWithZeros() {

        Board board = new Board();
        board.setNumber(0, 3, 4);
        board.setNumber(1, 3, 0);
        board.setNumber(2, 3, 4);
        board.setNumber(3, 3, 0);
        board.clearUp();
        board.runUp();
        assertEquals(8,board.getNumbers()[0][3]);

    }

}
