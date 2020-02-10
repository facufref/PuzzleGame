/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame.domain;

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
    private int [][] matrix;

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
    
    private int [][] createMatrix(int rows, int columns){
        if (rows < 0 || columns < 0)
            return new int[0][0];
                    
        int [] numbers = IntStream.range(0, rows*columns).toArray();
        this.shuffleArray(numbers);
        return createMatrixFromArray(numbers, rows, columns);
    }
  
  public int getManhattanDistance(){
      int [] numbers = IntStream.range(0, rows*columns).toArray();
      int manhattanCounter = 0;
      int correctRow;
      int correctColumn;
      
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
              if (matrix[i][j] == 0)
                  continue;
              
              correctRow = ((int)(matrix[i][j] - 1) / columns) + 1 ;
              correctColumn = (matrix[i][j] % columns) == 0 ? columns : (matrix[i][j] % columns);
              manhattanCounter += Math.abs((i+1)-correctRow) + Math.abs((j+1)-correctColumn);
          }
      }
      return manhattanCounter;
  }
    
    public void drawMatrix(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
  
  private static void shuffleArray(int[] ar)
  {
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
  
  private int [][] createMatrixFromArray(int [] array, int rows, int columns){
      int [][] matrix = new int[rows][columns];
      int k = 0;
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
              matrix[i][j] = array[k];
              k++;
          }
      }
      return matrix;
  }
        
}
