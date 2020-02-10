/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame.test;

import org.junit.Test;
import static org.junit.Assert.*;
import puzzlegame.domain.LocalBeam;
import puzzlegame.domain.Puzzle;

/**
 *
 * @author facuf
 */
public class LocalBeamTest {
    
    public LocalBeamTest() {
    }
    
    @Test
    public void iterateMultipleTimes_ShouldSolvePuzzle() {
        int[][] matrix = {{1, 2, 3}, 
                          {4, 0, 5}, 
                          {7, 8, 6}};
        Puzzle puzzle = new Puzzle(matrix);
        
        LocalBeam localBeam = new LocalBeam(2, puzzle);
        localBeam.generateNextIteration();
        assertEquals(2, localBeam.getCurrentIteration().size());
        assertEquals(1, localBeam.getCurrentBestPuzzle().getManhattanDistance());
        assertEquals(3, localBeam.getCurrentIteration().get(1).getManhattanDistance());
        localBeam.generateNextIteration();
        assertEquals(2, localBeam.getCurrentIteration().size());
        assertEquals(0, localBeam.getCurrentBestPuzzle().getManhattanDistance());
        assertEquals(2, localBeam.getCurrentIteration().get(1).getManhattanDistance());
    }
    
    @Test
    public void generateNextIterationWithExessiveStates_ShouldCreateProperAmountOfStates() {
        int[][] matrix = {{8, 1, 3}, 
                          {5, 0, 2}, 
                          {6, 7, 4}};
        Puzzle puzzle = new Puzzle(matrix);
        
        LocalBeam localBeam = new LocalBeam(6, puzzle);
        localBeam.generateNextIteration();
        assertEquals(4, localBeam.getCurrentIteration().size());
    }
    
}
