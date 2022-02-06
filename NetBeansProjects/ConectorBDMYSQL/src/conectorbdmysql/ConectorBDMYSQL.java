/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectorbdmysql;

/**
 *
 * @author Javy
 */
import java.util.Date;
import javax.swing.JOptionPane;
import model.Conecta;
public class ConectorBDMYSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        Conecta conexion= new Conecta("placeho6_constructora", "placeho6_ivi", "constructora2015");
        if(conexion.mensaje.equals("bien"))
        {
            JOptionPane.showMessageDialog(null, "Conexión a la base de datos "+conexion.getDb()+"...... Listo ", "Conexion Exitosa", JOptionPane.INFORMATION_MESSAGE);
           /* conexion.insert(5, "JVG", "2222222", "ALGO@ALGO.COM", "NADA", "NADA", "NADA","99999999999", 
                    "VAGJ900526HPLLTV03", "NADA", "NADA", "NADA", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                    "PRUEBA", "PRUEBA", "PRUEBA", "PRUEBA", "PRUEBA", "PRIEBA", "PRUEBA",
           "PRUEBA", "PRUEBA", 72590, "PRUEBA", "MASCULINO", "PRUEBA", 30, 0, 0, new Date(),new Date(), 
           new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), 
           new Date(), new Date(), new Date(), "USER",new Date() , new Date(), new Date(), 90, 90, 90, 90,
           90, 90);*/
            conexion.select("Select * from registros");
            //conexion.delete("5");
//GUI interfaz= new GUI(conexion.getConect());
        }
        else
            JOptionPane.showMessageDialog(null, "Conexión a la base de datos "+conexion.getDb()+"...... Fallida\n"+ conexion.mensaje, "Conexion Fallida", JOptionPane.ERROR_MESSAGE);        
    }
    
}
