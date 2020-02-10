/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 *
 * @author facuf
 */
public class Puzzle {

    private int rows;
    private int columns;
    private int[][] matrix;

    public Puzzle(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = createMatrix(rows, columns);
    }

    public Puzzle(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private int[][] createMatrix(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            return new int[0][0];
        }

        int[] numbers = IntStream.range(0, rows * columns).toArray();
        this.shuffleArray(numbers);
        return createMatrixFromArray(numbers, rows, columns);
    }

    public int getManhattanDistance() {
        int[] numbers = IntStream.range(0, rows * columns).toArray();
        int manhattanCounter = 0;
        int correctRow;
        int correctColumn;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                correctRow = ((int) (matrix[i][j] - 1) / columns) + 1;
                correctColumn = (matrix[i][j] % columns) == 0 ? columns : (matrix[i][j] % columns);
                manhattanCounter += Math.abs((i + 1) - correctRow) + Math.abs((j + 1) - correctColumn);
            }
        }
        return manhattanCounter;
    }

    public void drawMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private int[][] createMatrixFromArray(int[] array, int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = array[k];
                k++;
            }
        }
        return matrix;
    }

    public List<Puzzle> getNextSteps() {
        List<Puzzle> nextSteps = new ArrayList<Puzzle>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    
                    //Move 0 to the left
                    if (j > 0) {
                        int[][] newMatrixMoveLeft = cloneMatrix(matrix);
                        newMatrixMoveLeft[i][j] = newMatrixMoveLeft[i][j - 1];
                        newMatrixMoveLeft[i][j - 1] = 0;
                        nextSteps.add(new Puzzle(newMatrixMoveLeft));
                    }
                    
                    //Move 0 to the right
                    if (j < columns - 1) {
                        int[][] newMatrixMoveRight = cloneMatrix(matrix);
                        newMatrixMoveRight[i][j] = newMatrixMoveRight[i][j + 1];
                        newMatrixMoveRight[i][j + 1] = 0;
                        nextSteps.add(new Puzzle(newMatrixMoveRight));
                    }
                    
                    //Move 0 up
                    if (i > 0) {
                        int[][] newMatrixMoveUp = cloneMatrix(matrix);
                        newMatrixMoveUp[i][j] = newMatrixMoveUp[i - 1][j];
                        newMatrixMoveUp[i - 1][j] = 0;
                        nextSteps.add(new Puzzle(newMatrixMoveUp));
                    }
                    
                    //Move 0 down
                    if (i < rows - 1) {
                        int[][] newMatrixMoveDown = cloneMatrix(matrix);
                        newMatrixMoveDown[i][j] = newMatrixMoveDown[i + 1][j];
                        newMatrixMoveDown[i + 1][j] = 0;
                        nextSteps.add(new Puzzle(newMatrixMoveDown));
                    }

                    return nextSteps;
                }
            }
        }
        return nextSteps;
    }

    private int[][] cloneMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = matrix[i].clone();
        }
        return newMatrix;
    }

}
