/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_incidencias.util;

import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *Clase de diferentes utilidades
 * para componentes graficos de
 * interfaz de usuario
 * @author Osvaldo Sanchez
 */
public class SwingUtilities {

    /**
     * Obtiene un dialogo modal
     * cuando se requiere mostrar un mensaje de espera
     * para cualquier accion que se ejecute
     * @return
     */
    public static JDialog getDialogoEspera(JFrame frame){
        JDialog dialogo=new JDialog(frame);
         JPanel panel = new JPanel();
         panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
         JLabel busy=new JLabel();
         panel.add(busy,BorderLayout.CENTER);
         busy.setText("Espere por favor...");         
         dialogo.getContentPane().add(panel);
         dialogo.setUndecorated(true);
         dialogo.setSize(170,50);
         dialogo.setModal(false);
         dialogo.setLocationRelativeTo(null);        
        return dialogo;
    }
}
