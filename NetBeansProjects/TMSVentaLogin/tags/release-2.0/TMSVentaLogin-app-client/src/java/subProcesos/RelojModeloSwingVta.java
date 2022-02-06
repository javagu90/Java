/*
 * RelojModeloSwingVta.java
 *
 * Created on 11 de enero de 2008, 03:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

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
import tms_venta.SesionVenta;

public class RelojModeloSwingVta extends Observable{
    private long vinicial; 
    private SesionVenta sesionVenta;
    private long lahora=0;
    private Timer timer;
    
    public RelojModeloSwingVta(long pvinicial, SesionVenta pSesionVenta){
         sesionVenta = pSesionVenta;
         vinicial = pvinicial;
         timer = new Timer (1000, new ActionListener (){
            public void actionPerformed(ActionEvent e){
                setChanged();
                lahora++;
                if(lahora==2700) // 45 minutos
                //if(lahora==3600)
                {
                    if(sesionVenta.fechaHoraBD()){
                        vinicial=sesionVenta.getFechaHoraSistemaVenta().getTime();
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
