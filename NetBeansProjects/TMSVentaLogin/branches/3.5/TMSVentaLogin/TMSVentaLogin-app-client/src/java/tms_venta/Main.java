/*
 * Main.java
 *
 * Created on 23 de agosto de 2007, 12:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta;

/**
 *
 * @author ocruz
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private JButton boton1 = new JButton("Venta");
    private JButton boton2 = new JButton("Cerrar Ventana");
    private JScrollPane jscpDesktop;
    private JDesktopPane dskPrincipal = new JDesktopPane();
    /** Creates a new instance of Main */
    public Main() {
//        String curDir = System.getProperty("user.home");
//         File dir = new File(curDir);//"c:\\boleteraTMS\\Usedata\\");
//         File[] arrayFiles = dir.listFiles();
//         for(int i=0; i<arrayFiles.length; i++)
//         {
//             File archivo = arrayFiles[i];
//             System.out.println("El nombre del archivo es: "+archivo.getName());
//         }
//         File b = new File(curDir+"\\default.pls");
//             if(b.exists())
//                 System.out.println("El archivo existe...");
//             else
//                 System.out.println("El archivo No existe...");
//            File TextFile = new File(curDir+"\\LogVenta.txt");
//        FileWriter TextOut;
//        try {
//            TextOut = new FileWriter(TextFile, true);
//            TextOut.write("Prueba de Grabación de Archivo_5\r\n");
//            TextOut.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
         
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception{
        this.setTitle("TMS - Venta");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        boton1.setBounds(10, 10, 125, 22);
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicBoton1(e);
            }
        });
        boton2.setBounds(140, 10, 125, 22);
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
                clicBoton2(e2);
            }
        });
        jscpDesktop = new JScrollPane();
        dskPrincipal.add(boton1,1);
        dskPrincipal.add(boton2,1);
        jscpDesktop.getViewport().add(dskPrincipal, null);
        this.setContentPane(jscpDesktop);
    }
    
    private void clicBoton1(ActionEvent e){
        Vector datosIniciales = new Vector();

//        datosIniciales.add(118);//133); //208 Otilio //6 Ivan //118 manuel
//        datosIniciales.add("4616");//7051");//8618 otilio //8485 Ivan // 4616 manuel
//        datosIniciales.add("VICTOR MANUEL GUTIERREZ");//Otilio Cruz León");//VICTOR MANUEL GUTIERREZ
//        datosIniciales.add(15019861);
//        datosIniciales.add(11); // limitado (7)Edecan taquillas (11)Call Center
//        datosIniciales.add("192.168.16.25");
//        datosIniciales.add(10751);//5002465);//10751);
//        datosIniciales.add("CENTER");//CENTER"); //Venta nombre opcion de menu
        
//        datosIniciales.add(275);//133); //208 Otilio //6 Ivan //118 manuel
//        datosIniciales.add("9005");//7051");//8618 otilio //8485 Ivan // 4616 manuel
//        datosIniciales.add("ARIADNA");//Otilio Cruz León");//VICTOR MANUEL GUTIERREZ
//        datosIniciales.add(356);
//        datosIniciales.add(11); // limitado (7)Edecan taquillas (11)Call Center
//        datosIniciales.add("192.168.16.25");
//        datosIniciales.add(5003069);//5002465);//10751);
//        datosIniciales.add("Venta");//CENTER"); //Venta nombre opcion de menu
        
        datosIniciales.add(524);//133); //208 Otilio //6 Ivan //118 manuel
        datosIniciales.add("8617");//7051");//8618 otilio //8485 Ivan // 4616 manuel
        datosIniciales.add("Asesor de Ventas Asignado");//VICTOR MANUEL GUTIERREZ
        datosIniciales.add(15019861);//CAPU
        //datosIniciales.add(19052200);//AERO
        datosIniciales.add(7); // limitado (7)Edecan taquillas (11)Call Center
        datosIniciales.add("192.168.16.19");  // 25
        datosIniciales.add(10751);//5002465);//10751);
        datosIniciales.add("Venta");//CENTER"); //Venta nombre opcion de menu
        //datosIniciales.add("CENTER");//CENTER"); //Venta nombre opcion de menu
        
        
        
        
        
        
        
        
//        datosIniciales.add(208);//133); //208 Otilio //6 Ivan //118 manuel
//        datosIniciales.add("8618");//7051");//8618 otilio //8485 Ivan // 4616 manuel
//        datosIniciales.add("Otilio Cruz León");//VICTOR MANUEL GUTIERREZ
//        datosIniciales.add(356);
//        datosIniciales.add(11); // limitado (7)Edecan taquillas (11)Call Center
//        datosIniciales.add("192.168.16.25");
//        datosIniciales.add(10751);//5002465);//10751);
//        datosIniciales.add("CENTER");//CENTER"); //Venta nombre opcion de menu        
        
        JIFVenta w = new JIFVenta(datosIniciales);
        if(!w.getInicioGral()) return;
        dskPrincipal.add(w, 2);
        w.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
        w.setVisible(true);
        w.requestFocusInWindow();
    }
    
    private void clicBoton2(ActionEvent e){
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
    }
}