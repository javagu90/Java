/*
 * Main.java
 *
 * Created on 23 de agosto de 2007, 12:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_guardias;

/**
 *
 * @author ocruz
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import tms_desktop.MDIDesktopPane;

/**
 *
 * @author ocruz
 */
public class Main extends JFrame{
    private JButton boton1 = new JButton("Pantallas");
    private JScrollPane jscpDesktop;
    //private MDIDesktopPane dskPrincipal;
    private JDesktopPane dskPrincipal = new JDesktopPane();
    /** Creates a new instance of Main */
    public Main() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception{
        this.setTitle("TMS - Guardias");
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
        datosIniciales.add(7);
        datosIniciales.add("8740");//9092 //8485 //8740 con permisos //583300 sin permisos
        datosIniciales.add("Otilio Cruz Leon");
        datosIniciales.add(354);
        datosIniciales.add(7);
        datosIniciales.add("192.168.16.13");
        datosIniciales.add(3700);
        JIFGuardia w = new JIFGuardia(datosIniciales);
        if(!w.getInicioGral()) return;
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