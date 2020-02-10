/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import java.util.Iterator;
import puzzlegame.domain.LocalBeam;
import puzzlegame.domain.Puzzle;

/**
 *
 * @author facuf
 */
public class Main {
    public static void main(String[] args) {
        
        //These paramenters can be configured
        int rows = 3;
        int columns = 3;
        int k = 2;
        int maxIterations = 1000;
        
        Puzzle randomPuzzle = new Puzzle(rows, columns);
        randomPuzzle.drawMatrix();
        
        LocalBeam localBeam = new LocalBeam(k, randomPuzzle);
        while (localBeam.getCurrentBestPuzzle().getManhattanDistance() > 0 && maxIterations > 0){
            localBeam.generateNextIteration();
            for (int i = 0; i < localBeam.getCurrentIteration().size(); i++) {
                System.out.println("Matrix K = " + i + " with Manhattan Distance = " + localBeam.getCurrentIteration().get(i).getManhattanDistance());
                localBeam.getCurrentIteration().get(i).drawMatrix();
            }
            maxIterations--;
        }
        
        System.out.println("Final Matrix: ");
        localBeam.getCurrentBestPuzzle().drawMatrix();
    }
}
