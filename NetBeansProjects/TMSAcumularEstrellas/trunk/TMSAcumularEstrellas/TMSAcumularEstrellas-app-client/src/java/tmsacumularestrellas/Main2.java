/*
 * Main.java
 *
 * Created on 5 de febrero de 2010, 06:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsacumularestrellas;

import java.util.Vector;

/**
 *
 * @author asolis
 */
public class Main2 {
    
    /** Creates a new instance of Main */
    public Main2() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vector datosIniciales = new Vector();

//        datosIniciales.add(118);//133); //208 Otilio //6 Ivan //118 manuel
//        datosIniciales.add("4616");//7051");//8618 otilio //8485 Ivan // 4616 manuel
//        datosIniciales.add("VICTOR MANUEL GUTIERREZ");//Otilio Cruz León");//VICTOR MANUEL GUTIERREZ
//        datosIniciales.add(15019861);
//        datosIniciales.add(11); // limitado (7)Edecan taquillas (11)Call Center
//        datosIniciales.add("192.168.16.25");
//        datosIniciales.add(10751);//5002465);//10751);
//        datosIniciales.add("CENTER");//CENTER"); //Venta nombre opcion de menu
        

        datosIniciales.add(524);//133); //208 Otilio //6 Ivan //118 manuel
        datosIniciales.add("8618");//7051");//8618 otilio //8485 Ivan // 4616 manuel
        datosIniciales.add("Otilio Cruz León");//VICTOR MANUEL GUTIERREZ
        datosIniciales.add(15019861);
        datosIniciales.add(7); // limitado (7)Edecan taquillas (11)Call Center
        datosIniciales.add("192.168.16.25");
        datosIniciales.add(10751);//5002465);//10751);
        datosIniciales.add("Venta");//CENTER"); //Venta nombre opcion de menu
                
        
        JIFAcumulaEstrellas ac = new JIFAcumulaEstrellas(datosIniciales);
        ac.setVisible(true);
    }
    
}
