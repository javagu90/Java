/*
 * JObsProgreso.java
 *
 * Created on 11 de enero de 2010, 04:13 PM   
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author brojasa
 */

package tmslealtad_carga;

import java.util.Observable;
import javax.swing.Timer;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;


public  class JObsProgreso extends Observable {
    
    private Timer timer;
    private int vinicial;
    
    public JObsProgreso(int pvinicial, int tempo){
        vinicial = pvinicial;
        timer = new Timer (tempo, new ActionListener(){
         public void actionPerformed(ActionEvent e){
             setChanged();
             notifyObservers (++vinicial);
         }
        });
        timer.start();
    }
     
    public void detenerTimer(){
        timer.stop();
    }
}


    

