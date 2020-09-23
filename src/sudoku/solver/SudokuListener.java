
package sudoku.solver;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static sudoku.solver.SudokuLayout.f;

/**
 *
 * @author girig
 */
public class SudokuListener extends SudokuLayout implements ActionListener, KeyListener{
    
    //calls the SudokuLayout constructor to set the layout of the game.
    public SudokuListener(){
        super();      
        
    }
    
    public void addListenerToButton(){
         
        solve.addActionListener((ActionListener) this);
        reset.addActionListener((ActionListener) this);
     
    }
    //this method adds 3X3 boxes to the panel. Each of these boxes holds 9 cells for data.
     public void setTextFields(){    
         //Nine boxes are added in 3X3 table.
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                box = new JPanel(new GridLayout(3, 3));
                box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    //Nine nodes are added to each box in 3X3 table format.
                    for(int x=0; x<3; x++){
                        for(int y=0; y<3; y++){
                            JTextField node ;
                            //if the input data is zero, no text is added to the textfield
                            if(data[(3*row)+x][(3*col)+y] == 0){
                                      node  = new JTextField("");  
                                      node.addKeyListener((KeyListener) this);
                                      
                            }else {
                                  node = new JTextField(""+data[(3*row)+x][(3*col)+y]);
                                  node.setEditable(false) ;
                            }
                           
                            box.add(node);
                            
                        }
                    }
                  
               //}
                board.add(box);      
            }
        }
    }
     //for the buttons
       public void actionPerformed(ActionEvent e){     
        if(e.getSource() == solve){
            solved = true;
            int[][] solution = s.solvedBoard(data);
             resetTextFields(solution);
        }
        else if((e.getSource() == reset) && solved) {
            userInputData = s.createBoard();
            resetTextFields( s.createBoard());
           
        }                 
    }
    
    public void keyPressed(KeyEvent k){      
        JTextField choosenField = (JTextField) k.getSource();
        choosenField.setEditable(true);
        //System.out.println(j.getParent());
        if(((Character.isDigit(k.getKeyChar())) && k.getKeyChar() != '0') ||  k.getKeyCode() == 8){
            Character key = k.getKeyChar();
            int row =0;
            int col = 0;
            //int datachanged = -1
            for(Component segments: board.getComponents()){  
                int x=0;
                int y=0;
                for(Component c: ((JPanel)segments).getComponents()){                 
                    if(c == choosenField){
                       // System.out.println(row+" "+col+" "+x+ " "+y);
                       if(k.getKeyCode() == 8){
                            String text = choosenField.getText();
                            if(text !=null){
                                choosenField.setText("");
                                userInputData[(row*3)+x][(col*3)+y] = 0;
                            }
                       }
                       else if(s.addToPosition((row*3)+x, (col*3)+y, Integer.parseInt(Character.toString(k.getKeyChar())), userInputData)){
                          // choosenField.setText(Character.toString(k.getKeyChar()));
                           solved = true;
                           userInputData[(row*3)+x][(col*3)+y] = Integer.parseInt(Character.toString(k.getKeyChar()));
                           //choosenField.setEditable(false);
                           break;
                       }
                     
                       else{
                            choosenField.setEditable(false);
                       }

                    }
                    if(y>=2){
                        x++;
                        y=0;
                    }else y++;

                }
                if(col>=2){
                        row++;
                        col=0;
                    }else col++;

            }         
            
        }  
        
        else{
            choosenField.setEditable(false);
        }
          //j.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         if(checkIfSolved()){
             String answer = JOptionPane.showInputDialog(f, "You Won! \n Play Again?");
             if (answer == "yes" || answer == "y"){
                 resetTextFields(s.createBoard());
             }
         }
        
    }
    //if none of the data in the userInputData is 0 then the game is solved
    public boolean checkIfSolved(){
       for(int x=0; x<userInputData.length; x++){
           for(int y=0; y<userInputData.length; y++){
               if(userInputData[x][y]  == 0){
                   return false;
                }
           }
       }return true;
    }
    //overrided method
    public void keyReleased(KeyEvent k){
        JTextField choosenField = (JTextField) k.getSource();
        choosenField.setEditable(true);
    }
    //solved is true to make sure the reset button will work
    public void keyTyped(KeyEvent k){
        solved = true;
      
    }
    private void resetTextFields(int[][] solution){   
        int row =0;
        int col = 0;
        for(Component segments: board.getComponents()){  
            int x=0;
            int y=0;
            for(Component c: ((JPanel)segments).getComponents()){                 
                if(c instanceof JTextField){
                   // System.out.println(row+" "+col+" "+x+ " "+y);
                   if(solution[(row*3)+x][(col*3)+y] == 0){
                       ((JTextField) c).setText("");
                   }else{
                        ((JTextField) c).setText(""+solution[(row*3)+x][(col*3)+y]);
                   }
                   
                }
                if(y>=2){
                    x++;
                    y=0;
                }else y++;
                
            }
            if(col>=2){
                    row++;
                    col=0;
                }else col++;
    
        }
    }
}
