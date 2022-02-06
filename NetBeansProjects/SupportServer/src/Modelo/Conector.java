package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Javagu
 */
public class Conector 
{
    private String host;
    private String user;
    private String pass;
    private String BBDD;
    private Connection conect;
    protected PreparedStatement pst;
    public String mensaje;
    
    public Conector(){}
    public Conector(String host, String user, String pass, String BBDD)
    {
        this.host=host;
        this.user=user;
        this.pass=pass;
        this.BBDD=BBDD;
    }
    
    public String establecerConexion()
    {
        try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conect = DriverManager.getConnection( "jdbc:mysql://"+host+"/"+BBDD, user , pass );
         if ( conect!=null ){
            System.out.println("Conexión a la base de datos "+this.BBDD+"...... Listo ");
            mensaje="bien";
         }
      }catch(SQLException | ClassNotFoundException e){
         System.err.println( e.getMessage() );
         mensaje=e.getMessage();
      }
        return mensaje;
    }
}
