package controlador;
import modelo.Conector;
/**
 *
 * @author Javagu
 */
public class Conexion {
    
    public Conexion(){}
    public Conexion(String host, String user, String pass, String BBDD)
    {
        Conector conector= new Conector(host,user, pass,BBDD);
        conector.establecerConexion();
    }
}
