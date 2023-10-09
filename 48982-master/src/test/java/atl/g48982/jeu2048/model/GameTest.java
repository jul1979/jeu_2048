package atl.g48982.jeu2048.model;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Game class
 *
 * @author Jules
 */
public class GameTest {

    /**
     * Test of getBoard method, of class Game.
     */
    @Test
    public void testGetBoard() {

        Game game = new Game();
        Board result = game.getBoard();
        assertTrue(result!=null);

    }

    /**
     * Test of numbers method, of class Game.
     */
    @Test
    public void testNumbers() {

        Game game = new Game();
        int expResult = 4;
        int result = game.numbers().length;
        assertEquals(expResult, result);
        assertEquals(expResult, game.numbers()[3].length);

    }

    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlay_RowfilledWith2s() {

        Game game = new Game();
        int expected = 4;
        Direction direction = Direction.LEFT;

        for (int i = 0; i < game.numbers().length; i++) {

            for (int j = 0; j < game.numbers()[i].length; j++) {

                if (i == 0) {

                    game.getBoard().setNumber(i, j, 2);

                }
            }

        }
        game.play(direction);
        assertEquals(expected, game.numbers()[0][0]);
        assertEquals(expected, game.numbers()[0][1]);

    }

    /**
     * Test of score method, of class Game.
     */
    @Test
    public void testScore_AtBeginning() {

        Game game = new Game();
        int expResult = 0;
        int result = game.score();
        assertEquals(expResult, result);

    }

    /**
     * Test of score method, of class Game.
     */
    @Test
    public void testScore_caseDiffentZero() {

        Game game = new Game();

        for (int i = 0; i < game.numbers().length; i++) {

            for (int j = 0; j < game.numbers()[i].length; j++) {

                game.getBoard().setNumber(i, j, 2);
            }

        }
        game.play(Direction.LEFT);
        int expResult = 32;
        int result = game.score();
        assertEquals(expResult, result);

    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver_atBeginning() {

        Game game = new Game();
        boolean result = game.isOver();
        assertEquals(false, result);

    }

    /**
     * Test of isOver method, of class Game.
     */
    
    @Test
    public void testIsOver_2048Present() {

        Game game = new Game();
        game.getBoard().setNumber(0, 0, 2048);
        assertEquals(true,game.isOver());
        assertEquals(true,game.gameWon());

    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver_allSquaresFilled() {

        Game game = new Game();
        int value;
        int count = 1;

        for (int i = 0; i < game.numbers().length; i++) {

            for (int j = 0; j < game.numbers()[i].length; j++) {

                value = (int) Math.pow(2, count);
                game.getBoard().setNumber(i, j, value);
                count++;
            }

        }
    assertEquals(true,game.isOver());

    }

    /**
     * Test of addNumberToBoard method, of class Game.
     */
    @Test
    public void testAddNumberToBoard_CaseNoNumberMoved() {

        Game game = new Game();
        int value;
        int count = 1;
        int[][] numbersBefore;

        for (int i = 0; i < game.numbers().length; i++) {

            for (int j = 0; j < game.numbers()[i].length; j++) {

                if (i == 3 && j == 3) {
                    game.getBoard().setNumber(i, j, 0);
                } else {

                    value = (int) Math.pow(2, count);
                    game.getBoard().setNumber(i, j, value);
                    count++;

                }

            }

        }

        numbersBefore = Game.copy(game.numbers());
        game.play(Direction.UP);
        game.addNumberToBoard(numbersBefore);

        assertTrue(Arrays.deepEquals(numbersBefore, game.numbers()));

    }

    /**
     * Test of addNumberToBoard method, of class Game.
     */
    @Test
    public void testAddNumberToBoard_CaseNumberMoved() {

        Game game = new Game();

        int[][] numbersBefore;

        for (int i = 0; i < game.numbers().length; i++) {

            for (int j = 0; j < game.numbers()[i].length; j++) {

                if (i == 2) {
                    game.getBoard().setNumber(i, j, 2);
                } else {

                    game.getBoard().setNumber(i, j, 0);

                }

            }

        }

        numbersBefore = Game.copy(game.numbers());
        game.play(Direction.DOWN);
        game.addNumberToBoard(numbersBefore);

        assertFalse(Arrays.deepEquals(numbersBefore, game.numbers()));

    }

    /**
     * Test of numberHasMoved method,of classGame.
     */
    @Test
    public void testNumberHasMoved_Case_SameArrays() {

        int value;
        int count = 1;
        int[][] numbersBefore = new int[4][4];
        int[][] numbersAfter = new int[4][4];

        for (int i = 0; i < numbersBefore.length; i++) {

            for (int j = 0; j < numbersBefore[i].length; j++) {

                value = (int) Math.pow(2, count);

                numbersBefore[i][j] = value;
                numbersAfter[i][j] = value;

                count++;
            }

        }

        assertTrue(Arrays.deepEquals(numbersBefore, numbersAfter));

    }

    /**
     * Test of numberHasMoved method,of classGame.
     */
    @Test
    public void testNumberHasMoved_Case_DifferentArrays() {

        int value;
        int value2;
        int count = 1;
        int[][] numbersBefore = new int[4][4];
        int[][] numbersAfter = new int[4][4];

        for (int i = 0; i < numbersBefore.length; i++) {

            for (int j = 0; j < numbersBefore[i].length; j++) {

                value = (int) Math.pow(2, count);
                value2 = (int) Math.pow(4, count);

                numbersBefore[i][j] = value;
                numbersAfter[i][j] = value2;

                count++;
            }

        }

        assertFalse(Arrays.deepEquals(numbersBefore, numbersAfter));

    }

}
