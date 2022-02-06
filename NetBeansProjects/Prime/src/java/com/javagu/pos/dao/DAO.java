
package com.javagu.pos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javy
 */
public class DAO {
    
    private Connection conectar;

    public Connection getConectar() {
        return conectar;
    }

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }
    
    public void conectar ()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/primefacesdb?user=javagu90&password=heil");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void cerrar()
    {
        if(conectar!=null)
        {
            try {
                if(conectar.isClosed()==false)
                {
                    conectar.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
