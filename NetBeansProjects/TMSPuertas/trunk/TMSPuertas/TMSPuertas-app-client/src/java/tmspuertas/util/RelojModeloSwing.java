/*
 * RelojModeloSwing.java
 *
 * Created on 11 de enero de 2008, 03:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.util;

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
import tmspuertas.TmsPuertasManagedBean;

/**
 * Modelo de reloj utilizando javax.swing.Timer.
 */

public class RelojModeloSwing extends Observable
 {
     /**
      * Lanza un timer cada segundo, avisando a los observadores de este
      * modelo del cambio. 
      */
    private long vinicial; 
    private TmsPuertasManagedBean busquedas;
    private long lahora=0;
     public RelojModeloSwing(long pvinicial,TmsPuertasManagedBean pbusquedas)
     {
         busquedas = pbusquedas;
         vinicial = pvinicial;
         Timer timer = new Timer (1000, new ActionListener ()
         {
             public void actionPerformed(ActionEvent e)
             {
                 setChanged();
                 lahora++;
                 if(lahora==3600)
                 {  
                     Timestamp fecha_servidor = null;
                     Vector x = (Vector) busquedas.facadeGeneralPuertasRemote.fechaServidor();
                     fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                     vinicial = fecha_servidor.getTime();
                     lahora = 0;
                 }
                     notifyObservers (new Date(vinicial));
                     vinicial = vinicial+1000;
             }
         });
         timer.start();
     }
     
     /**
      * Main para prueba de esta clase.
      */
//     public static void main (String [] args)
//     {
//         RelojModeloSwing modelo = new RelojModeloSwing();
//         modelo.addObserver(new Observer()
//         {
//             public void update (Observable unObservable, Object dato)
//             {
//                 System.out.println (dato);
//             }
//         });
//         
//         // Espera de 10 segundos para que el programa no termine
//         // inmediatamente
//         try
//         {
//            Thread.currentThread().sleep (10000);
//         } catch (Exception e)
//         {
//         }
//     }
}
