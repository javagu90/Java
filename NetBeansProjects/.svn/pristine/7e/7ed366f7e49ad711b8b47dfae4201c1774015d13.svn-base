/*
 * Main.java
 *
 * Created on 15 de agosto de 2007, 11:18 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_cortetaquilla;

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
    private JButton boton1 = new JButton("Pantallas");
    private JScrollPane jscpDesktop;
    private JDesktopPane dskPrincipal;
    /** Creates a new instance of Main */
    public Main() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception{
        this.setTitle("Corte");
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
        System.out.println("entra a la accion del boton...");
        Vector datosIniciales = new Vector();
        datosIniciales.add(4);
        datosIniciales.add("9003");
        datosIniciales.add("Otilio Cruz Leon");
        datosIniciales.add(100);
        datosIniciales.add(282);
        datosIniciales.add("192.168.106.22");
        datosIniciales.add(3700);
        JIFCorteTaquilla w = new JIFCorteTaquilla(datosIniciales);
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
