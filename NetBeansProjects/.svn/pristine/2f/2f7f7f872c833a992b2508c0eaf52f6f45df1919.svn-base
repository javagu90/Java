/*
 * Main.java
 *
 * Created on 15 de agosto de 2007, 11:18 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_psd;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import tms_desktop.MDIDesktopPane;

/**
 *
 * @author ocruz
 */
public class Main extends JFrame{
    JIFPSD w = null;
    private JButton boton1 = new JButton("Pantallas");
    private JScrollPane jscpDesktop;
    private MDIDesktopPane dskPrincipal;
    /** Creates a new instance of Main */
    public Main() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception{
        this.setTitle("Catalogo: Pantallas");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        boton1.setBounds(10, 10, 125, 22);
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicBoton1(e);
            }
        });
        jscpDesktop = new JScrollPane();
        dskPrincipal = new MDIDesktopPane();
        dskPrincipal.add(boton1,1);
        jscpDesktop.getViewport().add(dskPrincipal, null);
        this.setContentPane(jscpDesktop);
    }
    
    private void clicBoton1(ActionEvent e){
        Vector datosIniciales = new Vector();
        datosIniciales.add(6);
        datosIniciales.add("8485");
        datosIniciales.add("IVAN MUÑOZ MUÑOZ");
        datosIniciales.add(150211292);
        datosIniciales.add(11);
        datosIniciales.add("192.168.106.33");
        datosIniciales.add(3700);
        /*if(w!=null){
            if(w.isIconifiable()) 
                try {
                    w.setIcon(false);
                } catch (PropertyVetoException ex) {
                    ; // no es posible mostrar el JIF
                }
            return;
        }*/
        w = new JIFPSD(datosIniciales);
        /*w.setName("PSD");
        w.addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameActivated(InternalFrameEvent e) {
            }
            public void internalFrameClosed(InternalFrameEvent e) {
                w=null;
            }
            public void internalFrameClosing(InternalFrameEvent e) {
            }
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }
            public void internalFrameDeiconified(InternalFrameEvent e) {
                w.jPnlPsdX.setFoco();
            }
            public void internalFrameIconified(InternalFrameEvent e) {
                if(w.jPnlPsdX.getEventoTeclado()!=null){
                    if(w.jPnlPsdX.getEventoTeclado().getKeyCode() == KeyEvent.VK_TAB && w.jPnlPsdX.getEventoTeclado().isControlDown()){
                        System.out.println("ENTRE");
                        int i;
                        for(i=0; i<dskPrincipal.getAllFrames().length; i++)
                            if(dskPrincipal.getAllFrames()[i].getName().equals(w.getName())) break;
                        i = (i+1)%dskPrincipal.getAllFrames().length;
                        String nombreSigJIF = dskPrincipal.getAllFrames()[i].getName();
                        if(nombreSigJIF.equals("PSD")){
                            dskPrincipal.setSelectedFrame(w);
                            try { w.setIcon(false); } catch (PropertyVetoException ex) { ; }
                            System.out.println("SI HAY "+i+" --- "+nombreSigJIF);
                        }
                        else System.out.println("NO HAY");
                        w.jPnlPsdX.getEventoTeclado().consume();
                        w.jPnlPsdX.setEventoTeclado(null);
                        return;
                    }
                }
                boton1.requestFocusInWindow();
            }
            public void internalFrameOpened(InternalFrameEvent e) {
            }
        });*/
        dskPrincipal.add(w, 2);
        w.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
        w.setVisible(true); 
        w.requestFocusInWindow();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
    }
}
