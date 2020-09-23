/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.solver;

/**
 *
 * @author girig
 */
public class SudokuSolver extends Sudoku{
   
    public static boolean getPosition(int[][] puzzle){ 
        boolean noZero = true;
        int row = -1;
        int col = -1;
    
       for(int x=0; x<puzzle.length; x++){
           for(int y=0; y<puzzle.length; y++){
               if(puzzle[x][y]  == 0){
                   row = x;
                   col = y;
                   noZero = false;
                   break;
               }
           }
           if(!noZero){
               break;
           }
       }if(noZero){
           return true;
       }
       for(int n=1; n<=9; n++){
           if(addToPosition(row, col, n, puzzle)){
               puzzle[row][col] = n;
               if(getPosition(puzzle)){
                   return true;
               }else {
                   puzzle[row][col] = 0;
                  
               }
           }
       }
       return false;
    }
   
   /* public int[][] getSolvedPuzzle(){
        if(getPosition(puzzle)){
            return puzzle;
        } else return unsolvedPuzzle;
        
    }*/
    public static  boolean addToPosition(int x, int y, int n, int[][] puzzle){
        for(int i=0; i<9; i++){
            if(puzzle[x][i] == n){
                                //System.out.println("firstn" +puzzle[x][i]);
                return false;

            } else if(puzzle[i][y] == n){
                //System.out.println("secondn " +puzzle[i][y]);
                return false;
            }
            
            
        }
        int x0 = (x/3)*3;
            int y0 = (y/3)*3;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(puzzle[x0+i][y0+j] == n){
                        return false;
                    }
                }
            }
            return true;
    }
    public static int[][] createBoard()
    { 
  
        int[][] board = new int[][] { 
            { 0, 0, 6, 5, 0, 8, 4, 0, 0 }, 
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
            { 9, 0, 0, 0, 6, 3, 0, 0, 5 }, 
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
            { 0, 0, 5, 2, 0, 6, 0, 0, 0 } 
        }; 
 
        return board;

        
    } 
    public static int[][] solvedBoard(int[][] board){
        int N = board.length; 
  
        if (getPosition(board)) { 
            // print solution 
            return board;
        } 
        else { 
            System.out.println("No solution"); 
        } 
        return board;
    }
  
    public static void print( 
        int[][] board, int N) 
    { 
        // we got the answer, just print it 
        for (int r = 0; r < N; r++) { 
            for (int d = 0; d < N; d++) { 
                System.out.print(board[r][d]); 
                System.out.print(" "); 
            }   
System.out.print("\n"); 
  
            if ((r + 1) % (int)Math.sqrt(N) == 0) { 
                System.out.print(""); 
            } 
        } 
    } 
}
