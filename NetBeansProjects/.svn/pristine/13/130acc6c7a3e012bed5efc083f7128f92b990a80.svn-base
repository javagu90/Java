/*
 * RelojModeloSwing.java
 *
 * Created on 11 de enero de 2008, 03:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesosAcum;

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

public class RelojModeloSwing extends Observable{
    private long vinicial; 
    private long lahora=0;
    private Timer timer;
    
    public RelojModeloSwing(long pvinicial){
         vinicial = pvinicial;
         timer = new Timer (1000, new ActionListener (){
            public void actionPerformed(ActionEvent e){
                setChanged();
                lahora++;
                if(lahora==2700) // 45 minutos
                //if(lahora==3600)
                {
                    if(true){
                        vinicial=2700;//sesionVenta.getFechaHoraSistemaVenta().getTime();
                        System.out.println("Despues de Ajuste HDB");
                    }
                    lahora = 0;
                }
                notifyObservers (new Date(vinicial));
                vinicial = vinicial+1000;
            }
        });
        timer.start();
    }
     
    public void detenerTimer(){
        timer.stop();
    }
}
