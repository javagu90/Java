/*
 * JObsProgreso.java
 *
 * Created on 11 de enero de 2008, 03:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xer_emv_dll;

/**
 *
 * @author vgonzalez
 */
import java.util.Observable;
import javax.swing.Timer;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

public class JObsBProgreso extends Observable{
    private Timer timer;
    private int vinicial;
    
    public JObsBProgreso(int pvinicial, int tempo){
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
