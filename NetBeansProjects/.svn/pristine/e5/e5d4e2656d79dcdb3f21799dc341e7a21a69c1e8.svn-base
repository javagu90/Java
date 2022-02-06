/*
 * Cliente.java
 *
 * Created on 21 de marzo de 2008, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package textomostrarcorridas;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Cliente
{
   public static String IP_SERVER;
   JFrameTextoDinamico vent;
   DataInputStream entrada = null;
   DataOutputStream salida = null;
   DataInputStream entrada2 = null;
   Socket comunication = null;//para la comunicacion
   Socket comunication2 = null;//para recivir msg
   private static boolean exito = true;
   
   String nomCliente= "SUPERVISOR";
   /** Creates a new instance of Cliente */
   public Cliente(JFrameTextoDinamico vent) throws IOException
   {      
      this.vent=vent;
   }
   
   public void conexion() throws IOException 
   {
      try {
         comunication = new Socket(Cliente.IP_SERVER, 8081);
         comunication2 = new Socket(Cliente.IP_SERVER, 8082);
         entrada = new DataInputStream(comunication.getInputStream());
         salida = new DataOutputStream(comunication.getOutputStream());
         entrada2 = new DataInputStream(comunication2.getInputStream());
         //nomCliente = JOptionPane.showInputDialog("Introducir Nick :");
         InetAddress i = InetAddress.getLocalHost();
         nomCliente = i.getHostName().toUpperCase();
         vent.setNombreUser(nomCliente);         
         salida.writeUTF(nomCliente);
         setExito(true);
      } catch (IOException e) {
         System.out.println("\tNo se encontro la aplicacion de Corridas");
         System.out.println("\t=============================");
         setExito(false);
      }
      if(isExito())
        new threadCliente(entrada2, vent).start();
   }
   public String getNombre()
   {
      return nomCliente;
   }
   public Vector<String> pedirUsuarios()
   {
      Vector<String> users = new Vector();
      try {         
         salida.writeInt(2);
         int numUsers=entrada.readInt();
         for(int i=0;i<numUsers;i++)
            users.add(entrada.readUTF());
      } catch (IOException ex) {
         Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
      }
      return users;
   }
   public void flujo(String mens) 
   {
      try {             
         System.out.println("El Texto a mostrar es :"
             + mens);
         salida.writeInt(1);
         salida.writeUTF(mens);
      } catch (IOException e) {
         System.out.println("error...." + e);
      }
   }
   
   public void flujo(String amigo,String mens) 
   {
      try {             
         System.out.println("El Texto a mostrar es :"
             + mens);
         salida.writeInt(3);//opcion de mensage a amigo
         salida.writeUTF(amigo);
         salida.writeUTF(mens);
      } catch (IOException e) {
         System.out.println("error...." + e);
      }
   }

    /**
     * @return the exito
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * @param exito the exito to set
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }
   
  
}
