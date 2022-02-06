/*
 * buscarDireccionMAC.java
 *
 * Created on 31 de octubre de 2007, 05:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesosAcum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  
 * @author vgonzalez
 */
public class buscarDireccionMAC {
    String[] listadoMac = new String[20]; 
    int nmacs = 0;
    /** Creates a new instance of buscarDireccionMAC */
    public buscarDireccionMAC() throws IOException {
          // Se lanza el ejecutable. 
                  
                  //Process p=Runtime.getRuntime().exec("cmd /c ipconfig /all");
                  Process p=Runtime.getRuntime().exec("cmd /c getmac");

                //p.waitFor();

                  // Se obtiene el stream de salida del programa 
                  InputStream is = p.getInputStream(); 
                   
                  // Se prepara un bufferedReader para poder leer la salida más 
                  // comodamente. 
                  BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
                   
                  // Se lee la primera linea 
                  String aux = br.readLine(); 
                  aux = br.readLine(); 
                  aux = br.readLine(); 
                  aux = br.readLine(); 
 
                  // Mientras se haya leido alguna linea 
                  while (aux!=null) 
                  { 
                        StringTokenizer token =   new StringTokenizer(aux);
                        String MAC = token.nextToken();
                        listadoMac[nmacs] = MAC.trim();
                        nmacs++;
                      // y se lee la siguiente. 
                      aux = br.readLine(); 
                  }
    }
    public boolean buscarMac(String mac){
        boolean valida = false;
        for(int i=0; i<nmacs; i++)
        {
            //System.out.println ("Sistema: "+listadoMac[i]);
            if(listadoMac[i].equals(mac))
                valida = true;
        }        
    if(valida)
        return true;
    else
        return false;
    }
}