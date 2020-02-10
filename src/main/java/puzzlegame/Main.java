/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import puzzlegame.domain.Puzzle;

/**
 *
 * @author facuf
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3, 4);
        puzzle.drawMatrix();
        puzzle.getManhattanDistance();
    }
}
