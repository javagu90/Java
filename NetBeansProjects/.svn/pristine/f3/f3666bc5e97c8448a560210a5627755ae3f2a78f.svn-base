/*
 * minimoCliente.java
 *
 * Created on 31 de octubre de 2007, 08:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_global_login_recaudar;

/**
 *
 * @author vgonzalez
 */
import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class minimoCliente {
    public minimoServidor servdior;
    public minimoCliente() throws IOException {
        int c;
        Socket s = null;
        InputStream sIn = null;
        boolean conexion = true;
        PcInfo estaTerminal = new PcInfo();
        // Abrimos una conexión con breogan en el puerto 4321
        
        try {
            s = new Socket( estaTerminal.getHostName().toUpperCase(),4321 );
        } catch( IOException e ) {
            System.out.println( e );
            System.out.println("No hubo conexion se abre el servidor");
            conexion = false;
            servdior = new minimoServidor();
            }
    if(conexion)
    {
        // Obtenemos un controlador de fichero de entrada del socket y
        // leemos esa entrada
            sIn = s.getInputStream();
        while( ( c = sIn.read() ) != -1 )
            System.out.print( (char)c );
            JOptionPane.showMessageDialog(null,"La aplicacion ya esta en ejecución","Acceso al Sistema",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
    
        // Cuando se alcance el fin de fichero, cerramos la conexión y
        // abandonamos
        s.close();            
    }  
        
    }
    
    public static void main( String args[] ) throws IOException {
  
        }
    }