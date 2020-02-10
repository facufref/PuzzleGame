package puzzlegame.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzlegame.domain.Puzzle;

/**
 *
 * @author facuf
 */
public class PuzzleTest {
    
    public PuzzleTest() {
    }
    
    @Test
    public void createPuzzle_ShouldReturnCorrectSizeMatrix() {
        Puzzle puzzle = new Puzzle(8, 5);
        assertEquals(8, puzzle.getMatrix().length);
        assertEquals(5, puzzle.getMatrix()[0].length);
    }
    
    @Test
    public void createPuzzleWithSizeZero_ShouldReturnEmptyMatrix() {
        Puzzle puzzle = new Puzzle(0, 0);
        assertEquals(0, puzzle.getMatrix().length);
    }
    
    @Test
    public void createPuzzleWithNegativeSize_ShouldReturnEmptyMatrix() {
        Puzzle puzzle = new Puzzle(-1, -1);
        assertEquals(0, puzzle.getMatrix().length);
    }
    
    @Test
    public void calculateManhattanDistance_ShouldReturnCorrectValue() {
        int[][] matrix = {{2, 1, 3}, 
                          {5, 4, 0}, 
                          {6, 7, 8}};
        Puzzle puzzle = new Puzzle(matrix);
        assertEquals(9, puzzle.getManhattanDistance());
        
        int[][] matrix2 = {{6, 4, 7}, 
                          {8, 5, 0}, 
                          {3, 2, 1}};
        Puzzle puzzle2 = new Puzzle(matrix2);
        assertEquals(21, puzzle2.getManhattanDistance());
    }
}
