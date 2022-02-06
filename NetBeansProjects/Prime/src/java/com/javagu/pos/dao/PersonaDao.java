/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javagu.pos.dao;

import com.javagu.pos.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Javy
 */
public class PersonaDao extends DAO 
{
    public void registrar(Persona p) throws Exception
    {
       try
       {
           this.conectar();
           PreparedStatement st= this.getConectar().prepareStatement("INSERT INTO Persona (nombre, sexo) values(?,?)");
           st.setString(1, p.getNombre());
           st.setString(2, p.getSexo());
           st.executeUpdate();
       }
       catch(Exception e)
       {
           throw e;
       }
       finally
       {
           this.cerrar();
       }
    }
    
    public String observarNombre() throws Exception
    {
        ResultSet rs;
        String nombre=null;
        try
        {
        this.conectar();
        PreparedStatement st= this.getConectar().prepareStatement("SELECT  nombre  FROM Persona  ");
        rs=st.executeQuery();
        while(rs.next())
        {
            Persona persona= new Persona();
            persona.setNombre(rs.getString("nombre"));
            nombre=persona.getNombre();
        }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
           return nombre;
    }
}
