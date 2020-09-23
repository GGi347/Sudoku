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
public class Sudoku {
    
    public static void main(String args[]) {
     
        SudokuListener newGame = new SudokuListener();   
        newGame.setTextFields();
        newGame.setLayout();
        newGame.addListenerToButton();

       
    }
}
