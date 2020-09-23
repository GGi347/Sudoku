/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.solver;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author girig
 */
public class TimerSetter {
    Timer timer;
    public TimerSetter(int seconds){
        timer = new Timer();
        timer.schedule(new ReminderTask(), seconds*1000);
    }
    class ReminderTask extends TimerTask{
        public void run(){
            System.out.println("Time out");
            timer.cancel();
        }
    }
}
