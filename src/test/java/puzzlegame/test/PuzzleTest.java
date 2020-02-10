package puzzlegame.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
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
    
    @Test
    public void getNextSteps_ShouldReturnCorrectSteps() {
        int[][] matrix = {{0, 1, 3}, 
                          {5, 4, 2}, 
                          {6, 7, 8}};
        Puzzle puzzle = new Puzzle(matrix);
        List<Puzzle> nextSteps = puzzle.getNextSteps();
        assertEquals(2, nextSteps.size());
        assertEquals(1, nextSteps.get(0).getMatrix()[0][0]);
        assertEquals(0, nextSteps.get(0).getMatrix()[0][1]);
        assertEquals(5, nextSteps.get(1).getMatrix()[0][0]);
        assertEquals(0, nextSteps.get(1).getMatrix()[1][0]);
        
        int[][] matrix2 = {{8, 1, 3}, 
                          {5, 4, 2}, 
                          {6, 7, 0}};
        puzzle = new Puzzle(matrix2);
        nextSteps = puzzle.getNextSteps();
        assertEquals(2, nextSteps.size());
        assertEquals(7, nextSteps.get(0).getMatrix()[2][2]);
        assertEquals(0, nextSteps.get(0).getMatrix()[2][1]);
        assertEquals(2, nextSteps.get(1).getMatrix()[2][2]);
        assertEquals(0, nextSteps.get(1).getMatrix()[1][2]);
        
        int[][] matrix3 = {{8, 1, 3}, 
                          {5, 0, 2}, 
                          {6, 7, 4}};
        puzzle = new Puzzle(matrix3);
        nextSteps = puzzle.getNextSteps();
        assertEquals(4, nextSteps.size());
        assertEquals(5, nextSteps.get(0).getMatrix()[1][1]);
        assertEquals(0, nextSteps.get(0).getMatrix()[1][0]);
        assertEquals(2, nextSteps.get(1).getMatrix()[1][1]);
        assertEquals(0, nextSteps.get(1).getMatrix()[1][2]);
        assertEquals(1, nextSteps.get(2).getMatrix()[1][1]);
        assertEquals(0, nextSteps.get(2).getMatrix()[0][1]);
        assertEquals(7, nextSteps.get(3).getMatrix()[1][1]);
        assertEquals(0, nextSteps.get(3).getMatrix()[2][1]);
    }
}
