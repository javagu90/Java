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
//import javax.swing.*;
/**
 *
 * @author Jav
 */
public class Coneccion 
{
    public Statement st;
    public ResultSet rs;
    public Connection conexion;
    public int error;
    public String tipo_user;
    public String tituloOA;
    public String RM;
    public Coneccion()
    {
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "javagu90", "heil");
            st=conexion.createStatement();
            //JOptionPane.showMessageDialog(null, "Exito!!!");
	}
	catch(Exception e)
	{
            System.out.println("mal "+ e.getMessage());
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
    
    //updatepuntaje UPDATE puntaje SET puntaje = puntajeNuevo WHERE Usuarios_idUsuarios = id;
    public void updatePuntaje(int puntajeNuevo, int idUser)
    {
        try
        {
            st.executeUpdate("UPDATE puntaje SET puntaje = "+ puntajeNuevo +" WHERE Usuarios_idUsuarios = "+idUser);
           // UPDATE puntaje SET puntaje = 19 WHERE Usuarios_idUsuarios = 2;
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en updatePuntaje "+sqle.toString());
        }
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
            System.out.println("ERROR EN TIPO USER: "+sqle.toString());
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

public void agregarNotificacion(String usuario, String mensaje)
{
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
    
}
//contar notificaciones
    //si hay mas de 10 notificaciones solo poner 10 select count(*) from tabla

public int contarNotificaciones()
{
    int numNotificaciones=0;
    try
    {
        rs=st.executeQuery("SELECT COUNT(*) as total FROM notificaciones");
        rs.next();
        numNotificaciones=Integer.parseInt(rs.getString("total")); 
        if(numNotificaciones>=10)
        {
            numNotificaciones=10;
        }
            
    }
    catch(SQLException sqle)
    {
        System.out.println(sqle.toString());
    }
    return numNotificaciones;
}

//ver notificaciones
    //solo ver 10 notificaciones SELECT NOTIFICACION FROM `notificaciones` LIMIT 0, 10

/*public String verNotificacion()
{
    String mensaje=null;
    int userRandom=1;
        try
        {
          rs=st.executeQuery("SELECT COUNT(*) as total FROM usuarios");
          rs.next();
          int n=Integer.parseInt(rs.getString("total"));
          userRandom=(int)(Math.random()*n + 1);
            System.out.println(userRandom);
            rs=st.executeQuery("SELECT notificacion FROM notificaciones WHERE Usuarios_idUsuarios='"+userRandom+"'");
            rs.next();
            mensaje=rs.getString("notificacion");
            System.out.println(mensaje);
        }
        catch(SQLException sqle)
        {
            System.out.println("ERROR EN FUNCION VER NOTIFICACIONES: "+sqle.toString());
        }

    return mensaje;
}*/

public void datosOA1(String tituloOA, String objetivo, String descripcion, int idArea, int idCategoria, String usuario)
{
    this.tituloOA=tituloOA;
        try
        {
            rs=st.executeQuery("SELECT nombre, apellidos FROM usuarios WHERE usuario='"+usuario+"'");
            rs.next();
            String nombreautor=rs.getString("nombre");
            String apellidosautor=rs.getString("apellidos");
            String creador= nombreautor+" "+apellidosautor;
          
            st.executeUpdate("INSERT INTO objetos_aprendizaje(titulo, objetivo, descripcion, autor, Area_idArea, Categoria_idCategoria) VALUES ('"+tituloOA+"','"+objetivo+"','"+descripcion+"','"+creador+"','"+idArea+"','"+idCategoria+"')");
        }
        catch(SQLException sqle)
        {
            System.out.println("error en funcion datosOA1 "+sqle.toString());
        }
}

public void agregarAviso(String aviso, String remitente, String tituloaviso)
{
    try
    {
        st.executeUpdate("INSERT INTO Avisos(aviso,remitente,tituloAviso ) VALUES('"+aviso+"','"+remitente+"','"+tituloaviso+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR AVISO: "+sqle.toString());
    }
}
public int verPuntajePropio(String usuario)
{
    int puntajePropio=0;
    int idUser=0;
      try
    {
        rs=st.executeQuery("SELECT idUsuarios From Usuarios WHERE usuario='"+usuario+"'");
        //select usuarios.usuario,puntaje.puntaje from usuarios inner join puntaje on puntaje.Usuarios_idUsuarios=usuarios.idUsuarios where usuarios.idusuarios='1'
        //rs=st.executeQuery("select usuarios.usuario,puntaje.puntaje from usuarios inner join puntaje on puntaje.Usuarios_idUsuarios=usuarios.idUsuarios where usuarios.idusuarios='"+usuario+"'");
        rs.next();
        idUser=rs.getInt("idUsuarios");
        rs=st.executeQuery("SELECT puntaje From puntaje WHERE Usuarios_idUsuarios='"+idUser+"'");
        rs.next();
        puntajePropio=rs.getInt("puntaje");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN VER PUNTAJE PROPIO: "+sqle.toString());
    }
    return puntajePropio;
}
public void datosActividad(String tituloAct, String descripcionAct, String objetivoAct, String tipoSaber, String ruta, String tipoRecurso, int puntosAct)
{
    try
    {
        //INSERT INTO palabras_clave(palabras) SELECT 'sumatoria' FROM dual WHERE NOT EXISTS (SELECT palabras FROM palabras_clave WHERE palabras = 'sumatoria')LIMIT 1
       rs=st.executeQuery("SELECT idObjetos_Aprendizaje FROM objetos_aprendizaje WHERE titulo='"+tituloOA+"'");
        rs.next();
        int idOA=rs.getInt("idObjetos_Aprendizaje");
        
        st.executeUpdate("INSERT INTO actividades(titulo_actividad, descripcion_actividad, objetivo_actividad, tipo_saber, direccion, tipo_recurso, puntosActividad, Objetos_Aprendizaje_idObjetos_Aprendizaje) VALUES ('"+tituloAct+"','"+descripcionAct+"','"+objetivoAct+"','"+tipoSaber+"','"+ruta+"','"+tipoRecurso+"','"+puntosAct+"','"+idOA+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN DATOS ACTIVIDAD: "+sqle.toString());
    }
}
public void palabraClave(String palabra)
{
    try
    {
        //INSERT INTO palabras_clave(palabras) SELECT 'sumatoria' FROM dual WHERE NOT EXISTS (SELECT palabras FROM palabras_clave WHERE palabras = 'sumatoria')LIMIT 1
        st.executeUpdate("INSERT INTO palabras_clave(palabras) SELECT '"+palabra+"' FROM dual WHERE NOT EXISTS (SELECT palabras FROM palabras_clave WHERE palabras ='"+palabra+"')LIMIT 1");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR PALABRA CLAVE: "+sqle.toString());
    }
}
public void llenarRelacionOAHPC(String palabra)
{
    //BUSCAR ID DONDE TITULO SELECT ID FROM OAS WHERE TITULOOA='TITULOOA'
     try
    {
        System.out.println("Titulo del OA para hacer la relacion: "+tituloOA);
        rs=st.executeQuery("SELECT idObjetos_Aprendizaje FROM objetos_aprendizaje WHERE titulo='"+tituloOA+"'");
        rs.next();
        int idOA=rs.getInt("idObjetos_Aprendizaje");
        
        rs=st.executeQuery("SELECT idPalabras_Clave FROM palabras_clave  WHERE palabras='"+palabra+"'");
        rs.next();
        int idPC=rs.getInt("idPalabras_Clave");
        
        st.executeUpdate("INSERT INTO objetos_aprendizaje_has_palabras_clave(Objetos_Aprendizaje_idObjetos_Aprendizaje,Palabras_Clave_idPalabras_Clave) VALUES('"+idOA+"','"+idPC+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR LLENAR RELACION OAHPC: "+sqle.toString());
    }
}
public void llenarRelacionOAHI(int idInteligencia)
{
     try
    {
        System.out.println("Titulo del OA para hacer la relacion: "+tituloOA);
        rs=st.executeQuery("SELECT idObjetos_Aprendizaje FROM objetos_aprendizaje WHERE titulo='"+tituloOA+"'");
        rs.next();
        int idOA=rs.getInt("idObjetos_Aprendizaje");
        st.executeUpdate("INSERT INTO objetos_aprendizaje_has_inteligencias(Objetos_Aprendizaje_idObjetos_Aprendizaje,inteligencias_idInteligencias) VALUES('"+idOA+"','"+idInteligencia+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR LLENAR RELACION OAHI: "+sqle.toString());
    }
     
}
public String llenarRelacionUHOAs(String usuario)
{
    try
    {
        System.out.println("Titulo del OA para hacer la relacion: "+tituloOA);
        rs=st.executeQuery("SELECT idObjetos_Aprendizaje FROM objetos_aprendizaje WHERE titulo='"+tituloOA+"'");
        rs.next();
        int idOA=rs.getInt("idObjetos_Aprendizaje");
        rs=st.executeQuery("SELECT idUsuarios FROM Usuarios WHERE usuario='"+usuario+"'");
        rs.next();
        int idUsuario=rs.getInt("idUsuarios");
        
        st.executeUpdate("INSERT INTO usuarios_has_objetos_aprendizaje(Objetos_Aprendizaje_idObjetos_Aprendizaje,Usuarios_idUsuarios) VALUES('"+idOA+"','"+idUsuario+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR LLENAR RELACION UHOAs: "+sqle.toString());
    }
    return tituloOA;
}
public void llenarRelacionAHEAP(String tituloActividad, int estrategia)
{
    try
    {
        System.out.println("Titulo del OA para hacer la relacion: "+tituloActividad);
        rs=st.executeQuery("SELECT idActividades FROM actividades WHERE titulo_actividad='"+tituloActividad+"'");
        rs.next();
          int idAc=rs.getInt("idActividades");
        st.executeUpdate("INSERT INTO actividades_has_estrategia_aprendizaje(Actividades_idActividades,estrategia_aprendizaje_idestrategia_aprendizaje) VALUES('"+idAc+"','"+estrategia+"')");
    }
    catch(SQLException sqle)
    {
        System.out.println("ERROR EN AGREGAR LLENAR RELACION AHEAP: "+sqle.toString());
    }
}


public void setRutaMultimedia(String rutaRecursoMultimedia)
{
    RM=rutaRecursoMultimedia;
}

public String getRutaMultimedia()
{
    return RM;
}
public void EliminarOA(int idOa)
{
    try
    {
      st.executeUpdate("SET FOREIGN_KEY_CHECKS = 0"); 
      st.executeUpdate("DELETE FROM objetos_aprendizaje WHERE idObjetos_Aprendizaje="+idOa);
      st.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    }
    catch(SQLException sqle)
    {
        System.out.println("Error al borrar oa "+sqle.toString());
    }
}

}