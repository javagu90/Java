/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
/**
 *
 * @author Jav
 */
public class Coneccion 
{
    protected Statement st;
    protected ResultSet rs;
    public Connection conexion;
    public int error;
    public String tipo_user;
    public Coneccion()
    {
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "javagu90", "heil");
            st=conexion.createStatement();
            JOptionPane.showMessageDialog(null, "Exito!!!");
	}
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"mal "+ e.getMessage());
	}
    }
    
    public void insertarUsuario(String nombre, String apellidos, String correo, String usuario, String pass, String tipoUsuario)
    {
        try
        {
            st.executeUpdate("INSERT INTO Usuarios(nombre, apellidos, correo, usuario, password, tipo_usuario) VALUES('"+nombre+"','"+apellidos+"','"+correo+"','"+usuario+"','"+pass+"','"+tipoUsuario+"')");
            if(tipoUsuario.equals("alumno"))
            {
                insertarPuntaje(usuario,0);
            }
            //st.executeUpdate("INSERT INTO Usuarios(nombre, apellidos, correo, usuario, contraseña, tipo_usuario) VALUES('Javier','Vbno Gtz','javagu90@gmail.com','javagu90','123456','alumno')");
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    }
    
    public boolean insertarPuntaje(String usuario, int puntaje)
    {
        boolean insertado=false;
        try
        {
            rs=st.executeQuery("SELECT idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
            rs.next();
            int userId=rs.getInt("idUsuarios");  
            st.executeUpdate("INSERT INTO puntaje(puntaje, Usuarios_idUsuarios) VALUES('"+puntaje+"','"+userId+"')");
            insertado=true;
        }
        catch(SQLException sqle )
        {
            System.out.println(sqle.toString());
        }
        return insertado;
    }
    
    public boolean acesso(String user, String pass)
    {
        boolean ingresar=false;
        try
        {
            rs=st.executeQuery("SELECT usuario FROM Usuarios WHERE usuario='"+user+"'");
            rs.next();
            String usernom=rs.getString("usuario");
            rs=null;
            rs=st.executeQuery("SELECT password FROM Usuarios WHERE usuario='"+user+"'");
            rs.next();
            String userpass=rs.getString("password");
            if((usernom.equals(user))&&(userpass.equals(pass)))
            {
                ingresar=true;
            }
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            ingresar=false;
        }
        return ingresar;
    }
    
    public boolean repetido(String user)
    {
        boolean reply=false;
        try
        {
            rs=st.executeQuery("SELECT usuario FROM Usuarios WHERE usuario='"+user+"'");
            rs.next();
            String usernom=rs.getString("usuario");
            if(usernom.equals(user))
            {
                reply=true;
            }
        } 
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            reply=false;
        }    
        return reply;
    }
    
    public String tipoUser(String user)
    {
        String tipo=null;
        try
        {
            rs=st.executeQuery("SELECT tipo_usuario FROM Usuarios WHERE usuario='"+user+"'");
            rs.next();
            tipo=rs.getString("tipo_usuario");
            if(tipo.equals("profesor"))
            {
                tipo="profesor";
            }
            else
            {
                tipo="alumno";
            }
        } 
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }    
        return tipo;
    }
    
    public boolean cambiarUser(String userViejo, String userNuevo, String userNuevoR, String pass)
    {
        boolean cambio=false;
        try
        {
            rs=st.executeQuery("Select idUsuarios FROM Usuarios WHERE usuario='"+userViejo+"'");
            rs.next();
            int id=rs.getInt("idUsuarios");
            rs=st.executeQuery("SELECT password FROM Usuarios WHERE usuario='"+userViejo+"'");
            rs.next();
            String userpass=rs.getString("password");
          
            //if
            if((repetido(userNuevo)==false) && (userpass.equals(pass)) && (userNuevo.length()>=8)&& (userNuevo.equals(userNuevoR)))
            {
                st.executeUpdate("UPDATE Usuarios SET usuario='"+userNuevo+"' WHERE idUsuarios="+id);
                cambio=true;
            }
            else
            {
                if(repetido(userNuevo)==true)
                {
                    error=1;
                }
                else
                {
                    if(!userpass.equals(pass))
                    {
                        error=2;
                    }
                    else
                    {
                        if(userNuevo.length()<8)
                        error=3;
                        else
                            error=4;
                    }
                }
            }
            System.out.println(error);
            
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            cambio=false;
        }
        return cambio;
    }

    public boolean cambiarPass(String usuario, String passViejo, String passNuevo, String passNuevoR)
    {
        boolean cambio=false;
        try
        {
            rs=st.executeQuery("Select idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
            rs.next();
            int id=rs.getInt("idUsuarios");
            rs=st.executeQuery("SELECT password FROM Usuarios WHERE usuario='"+usuario+"'");
            rs.next();
            String userpass=rs.getString("password");
            
            //if
            if ((userpass.equals(passViejo)) && (passNuevo.length()>=8)&&(passNuevo.equals(passNuevoR)))
            {
                st.executeUpdate("UPDATE Usuarios SET password='"+passNuevo+"' WHERE idUsuarios="+id);
                cambio=true;
            }
            else
            {
                if(!userpass.equals(passViejo))
                {
                    error=5;
                }
                else
                {
                    if(!passNuevo.equals(passNuevoR))
                    {
                        error=6;
                    }
                    else
                    {
                        error=7;
                    }
                }
            }
            System.out.println(error);
            
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
            cambio=false;
        }
        return cambio;
    }
public boolean cambiarDatos(String nombre, String apellidos, String correo, String pass, String usuario)
{
    boolean exito=false;
    try
    {
     rs=st.executeQuery("Select idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
     rs.next();
     int id=rs.getInt("idUsuarios");
     rs=st.executeQuery("Select password FROM Usuarios WHERE usuario='"+usuario+"'");
     rs.next();
     String userpass=rs.getString("password");
     
     if((userpass.equals(pass)) && (nombre.length()>=2) && (apellidos.length()>=3)&& (correo.length()>=6) )
     {
         st.executeUpdate("UPDATE Usuarios SET nombre='"+nombre+"' WHERE idUsuarios="+id);
         st.executeUpdate("UPDATE Usuarios SET apellidos='"+apellidos+"' WHERE idUsuarios="+id);
         st.executeUpdate("UPDATE Usuarios SET correo='"+correo+"' WHERE idUsuarios="+id);
         exito=true;
     }
     else
     {
         if(!userpass.equals(pass))
         {
             error=8;
           /*else
           {
            if()         
           }*/
         }
     }
    }
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
        exito=false;
    }
    return exito;
}

public boolean agregarArea(String area)
{
    boolean agregado=false;
    boolean reply=false;
    System.out.println("ENTREEEEEEE!!!!");
    try
    {
     rs=st.executeQuery("SELECT nombre_area FROM area WHERE nombre_area='"+area+"'");
     rs.next();
     String areanom=rs.getString("nombre_area");
     if(areanom.equals(area))
        {
         reply=true;
        }
    } 
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
        reply=false;
    }
    
    if(reply==false)
    {
        try
        {
            st.executeUpdate("INSERT INTO area(nombre_area) VALUES('"+area+"')");
            agregado=true;
        }
        catch(SQLException sqle)
        {
        System.out.println(sqle.toString());    
        }
    }
    
    return agregado;
}

public boolean agregarCategoria(String categoria, int idArea)
{
    boolean agregado=false;
    boolean reply=false;
    try
    {
     rs=st.executeQuery("SELECT nombre_categoria FROM categoria WHERE nombre_categoria='"+categoria+"'");
     rs.next();
     String catenom=rs.getString("nombre_categoria");
     if(catenom.equals(categoria))
        {
         reply=true;
        }
    } 
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
        reply=false;
    }
    
    if(reply==false)
    {
        try
        {
            st.executeUpdate("INSERT INTO categoria(nombre_categoria, Area_idArea) VALUES('"+categoria+"','"+idArea+"')");
            agregado=true;
        }
        catch(SQLException sqle)
        {
        System.out.println(sqle.toString());    
        }
    }
    
    return agregado;
}

public boolean agregarNotificacion(String usuario, String mensaje)
{
    boolean agregado=false;
        try
        {
            rs=st.executeQuery("SELECT idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
            rs.next();
            int userId=rs.getInt("idUsuarios");
            st.executeUpdate("INSERT INTO notificaciones(notificacion, Usuarios_idUsuarios) VALUES ('"+mensaje+"','"+userId+"')");
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    
    return agregado;
}
//contar notificaciones
    //si hay mas de 10 notificaciones solo poner 10 select count(*) from tabla

public String contarNotificaciones()
{
    int numNotificaciones=0;
    String notify=null;
    try
    {
        rs=st.executeQuery("SELECT COUNT(idnotificaciones) FROM notificaciones");
        rs.next();
        numNotificaciones=rs.getInt("idnotificaciones");
        notify=numNotificaciones+"";
    }
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
    }
    return notify;
}

//ver notificaciones
    //solo ver 10 notificaciones SELECT NOTIFICACION FROM `notificaciones` LIMIT 0, 10

public String verNotificacion(int userRandom)
{
    String mensaje=null;
    try
    {
        rs=st.executeQuery("SELECT notificacion FROM notificaciones WHERE Usuarios_idUsuarios='"+userRandom+"'");
        rs.next();
        mensaje=rs.getString("notificacion");
        System.out.println(mensaje);
    }
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
    }
    return mensaje;
}


/*ver puntajes
public String verPuntajes()
{
    int puntos=0;
    String puntaje=null;
    try
    {
        rs=st.executeQuery("SELECT  FROM notificaciones WHERE Usuarios_idUsuarios='"+userRandom+"'");
        rs.next();
        mensaje=rs.getString("notificacion");
        System.out.println(mensaje);
    }
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
    }
    return mensaje;
}   

    //hacer ranking

//agregar palabras clave
    //separandolo por comas

//busqueda de oa
    //que contenga la palabra dada y buscar en autor, palabra clave y area o categoria

//ver tabla de recursos  agregados al oa
*/

}