/*
 * Main.java
 *
 * Created on 10 de noviembre de 2007, 09:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare;

import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
                    Vector datos = new Vector();
//            datos.add("7");
//            datos.add("6000");
//            datos.add("algun usuario");
//            datos.add("1149");
//            datos.add("309");
        datos.add("21");//"7");
        datos.add("800");//"6000");
        datos.add("algun usuario");
        datos.add("500198");
        datos.add("309");                    
            
            //JIFRecaudacion pr = new JIFRecaudacion(datos);
            //pr.setVisible(true);
            //jDesktopPane1.add(pr,2);
            new JIFLecturaDatafare(datos).setVisible(true);  
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      new Main();
    }
    
}
