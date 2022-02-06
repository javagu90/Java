/*
 * minimoServidor.java
 *
 * Created on 31 de octubre de 2007, 08:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmstraficomain;

/**
 *
 * @author vgonzalez
 */
import java.awt.*;
import java.net.*;
import java.io.*;

public class minimoServidor {
       boolean ejecutar = true;
    
    public minimoServidor(){
        ServerSocket s = (ServerSocket)null;
        Socket s1;
        String sendString = "Ya hay una aplicación abierta!";
        int longCad;
        OutputStream s1out;

        // Establece el servidor en el socket 4319 (espera 300 segundos)
        try {
            s = new ServerSocket( 4319 );
            System.out.println("Se abrio el socket en la pantalla de logeo");
        } catch( IOException e ) {
            System.out.println( e );
            }

        // Ejecuta un bucle infinito de listen/accept
        while( ejecutar ) {
            try {
                // Espera para aceptar una conexión
                s1 = s.accept();
                // Obtiene un controlador de fichero de salida asociado
                // con el socket
                s1out = s1.getOutputStream();

                // Enviamos nuestro texto
                longCad = sendString.length();
                for( int i=0; i < longCad; i++ )
                    s1out.write( (int)sendString.charAt( i ) );

                // Cierra la conexión, pero no el socket del servidor
                s1.close();
            } catch( IOException e ) {
                System.out.println( e );
                }
            }
    }

        public void setEjecutar(boolean pejecutar){
            this.ejecutar = pejecutar;
        }
    
    public static void main( String args[] ) {

        }
    }