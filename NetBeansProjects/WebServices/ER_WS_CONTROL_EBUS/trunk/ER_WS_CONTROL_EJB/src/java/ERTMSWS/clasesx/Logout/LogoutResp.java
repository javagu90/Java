/*
 * LogoutResp.java
 *
 * Created on 18 de junio de 2010, 06:13 PM
 *
 * Clase para conjuntar los resultados obtenidos de getLogout del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.Logout;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;
/**
 *
 * @author opalafox
 */
public class LogoutResp extends ExchangeResp implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    
    /** Creates a new instance of LogoutResp */
    public LogoutResp() {
    }

    
}
