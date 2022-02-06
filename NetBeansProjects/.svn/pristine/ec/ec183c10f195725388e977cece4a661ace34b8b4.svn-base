/*
 * RelojVisualVta.java
 *
 * Created on 11 de enero de 2008, 03:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class RelojVisualVta
 {
     private SimpleDateFormat format = new SimpleDateFormat ("EEEE dd/MM/yyyy HH:mm");
     private Date fechaX;
     private JLabel labelReloj;
     private RelojModeloSwingVta modelo;
     
     public RelojVisualVta(RelojModeloSwingVta pModelo, int ancho, int alto, JLabel label)
     {
         labelReloj = label;
         modelo = pModelo;
         modelo.addObserver (new Observer ()
         {
             public void update(java.util.Observable o, Object arg) 
             {
                 final Object fecha = arg;
                 fechaX = new Date(((Date) fecha).getTime());
                 SwingUtilities.invokeLater(new Runnable()
                 {
                     public void run()
                     {
                         labelReloj.setText(format.format(fecha).toUpperCase());
                     }
                 });
             }
         });
     }

     public void setFormat (SimpleDateFormat unFormato){
         format = unFormato;
     }
     
     public Date getFecha(){
         return fechaX;
     }
     
     public void finaliza(){
         modelo.detenerTimer();
         modelo.deleteObservers();
         modelo = null;
         System.out.println("hilo RelojVisual finalizado.");
     }
}
