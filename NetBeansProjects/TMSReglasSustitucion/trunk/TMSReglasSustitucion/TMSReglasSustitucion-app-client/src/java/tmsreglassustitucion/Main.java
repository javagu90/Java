/*
 * Main.java
 *
 * Created on 21 de octubre de 2007, 04:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustitucion;

import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Vector datos = new Vector();
        datos.add("60");
        datos.add("6000");
        datos.add("algun usuario");
        datos.add("01");
        datos.add("309");
        new JIFRaglasSustitucion(datos).setVisible(true);
    }
    
}
