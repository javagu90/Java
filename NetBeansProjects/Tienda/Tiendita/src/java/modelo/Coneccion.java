/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jav
 */
public class Coneccion 
{
    public boolean exito;
    public Coneccion(){}
    public Connection conectar()
    {
        Connection conexion=null;
	try
	{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/buscador", "root", "root");
            System.out.println("Exito!!!");
            exito=true;
	}
	catch(Exception e)
	{
            System.out.println(e.getMessage());
	}
	return conexion;
    }
}


