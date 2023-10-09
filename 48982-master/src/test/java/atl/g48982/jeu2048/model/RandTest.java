package atl.g48982.jeu2048.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Rand class.
 *
 * @author Jules
 */
public class RandTest {

    /**
     * Test of constructor method, of class Rand.
     */
    public RandTest() {

        assertEquals(2, Rand.TWO);
        assertEquals(4, Rand.FOUR);
    }

    /**
     * Test of hasfreeSpots method, of class Rand.
     */
    @Test
    public void testHasfreeSpots_CaseNoFreeSpots() {

        Rand random = new Rand();
        int[] tab = {2, 2, 2, 2};
        assertEquals(false,random.hasfreeSpots(tab));
    }



    /**
     * Test of hasfreeSpots method, of class Rand.
     */
    @Test
    public void testHasfreeSpots_CaseOneFreeSpotsAtBeginning() {

        Rand random = new Rand();
        int[] tab = {0, 2, 2, 2};
        assertEquals(true,random.hasfreeSpots(tab) );
    }



    /**
     * Test of hasfreeSpots method, of class Rand.
     */
    @Test
    public void testHasfreeSpots_CaseOneFreeSpotsAtEnd() {

        Rand random = new Rand();
        int[] tab = {4, 2, 2, 0};
        assertEquals(true,random.hasfreeSpots(tab));
    }



    /**
     * Test of freeRows method, of class Rand.
     */
    @Test
    public void testFreeRows_CaseOneArrayWithFreeSpots() {

        int[][] squares = {{0, 0, 4, 4}, {2, 4, 8, 16}, {2, 4, 8, 4}, {2, 32, 4, 4}};
        Rand random = new Rand();
        ArrayList<Integer> result = random.freeRows(squares);
        assertTrue(result.contains(0));
    }

    /**
     * Test of freeRows method, of class Rand.
     */
    @Test
    public void testFreeRows_CaseNoArrayWithFreeSpots() {


        int[][] squares = {{2, 2, 4, 4}, {2, 4, 8, 16}, {2, 4, 8, 4}, {2, 32, 4, 4}};
        Rand random = new Rand();
        ArrayList<Integer> result = random.freeRows(squares);
        assertTrue(result.isEmpty());

    }

    /**
     * Test of freeRows method, of class Rand.
     */
    @Test
    public void testFreeRows_CaseMultipleArraysWithFreeSpots() {

        int[][] squares = {{2, 2, 0, 4}, {2, 4, 8, 0}, {2, 0, 8, 4}, {0, 32, 4, 4}};
        Rand random = new Rand();
        ArrayList<Integer> result = random.freeRows(squares);
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    /**
     * Test of spot method, of class Rand.
     */
    @Test
    public void testSpot_OneRowWithZero() {

        int[][] squares = {{2, 2, 0, 4}, {2, 4, 8, 4}, {2, 4, 8, 4}, {16, 32, 4, 4}};
        Rand rand = new Rand();

        List<Integer> result = rand.spot(squares);
        assertTrue(result.contains(0));
        assertTrue(result.contains(2));
    }

    /**
     * Test of spot method, of class Rand.
     */
    @Test
    public void testSpot_OneRowWithZero_case2() {

        int[][] squares = {{2, 2, 8, 4}, {2, 4, 8, 4}, {2, 4, 8, 4}, {16, 32, 4, 0}};
        Rand rand = new Rand();

        List<Integer> result = rand.spot(squares);
        assertTrue(result.contains(3));
        assertTrue(result.contains(3));
    }

    /**
     * Test of spot method, of class Rand.
     */
    @Test
    public void testSpot_NoFreeRows() {

        int[][] squares = {{2, 2, 8, 4}, {2, 4, 8, 4}, {2, 4, 8, 4}, {16, 32, 4, 2}};
        Rand rand = new Rand();

        List<Integer> result = rand.spot(squares);
        assertTrue(result.isEmpty());

    }

}
