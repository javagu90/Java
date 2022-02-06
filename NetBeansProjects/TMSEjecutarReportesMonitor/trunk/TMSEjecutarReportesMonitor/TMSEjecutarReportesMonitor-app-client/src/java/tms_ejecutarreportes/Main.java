/*
 * Main.java
 *
 * Created on 23 de agosto de 2007, 12:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ejecutarreportes;

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

/**
 *
 * @author ocruz
 */
public class Main extends JFrame{
    private JButton boton1 = new JButton("Pantalla");
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
        this.setTitle("TMS - Reportes");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        boton1.setBounds(10, 10, 125, 22);
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicBoton1(e);
            }
        });
        jscpDesktop = new JScrollPane();
        dskPrincipal = new JDesktopPane();
        dskPrincipal.add(boton1,1);
        jscpDesktop.getViewport().add(dskPrincipal, null);
        this.setContentPane(jscpDesktop);
    }
    
    private void clicBoton1(ActionEvent e){
        Vector datosIniciales = new Vector();
        datosIniciales.add(6);
        datosIniciales.add("8485");
        datosIniciales.add("Ivan Muñoz Muñoz");
        datosIniciales.add(354);
        datosIniciales.add(7);
        datosIniciales.add("192.168.16.25");
        datosIniciales.add(10751);
        System.out.println("Crea la el JInternal...");
        TMSEjecutarReportesMonitor w = new TMSEjecutarReportesMonitor(datosIniciales);
        if(!w.getInicioGral()) {
            System.out.println("se sale por que cumple la condicion if(!w.getInicioGral())");
            return;
        }
        System.out.println("agrega a desktop");
        w.setVisible(true);
        w.requestFocusInWindow();
        dskPrincipal.add(w, 2);
        //w.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
    }
}