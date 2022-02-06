/*
 * LoginResp.java
 *
 * Created on 18 de junio de 2010, 04:51 PM
 *
 *  Clase para conjuntar los resultados obtenidos por getLogin al WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.Login;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;
/**
 *
 * @author opalafox
 */
public class LoginResp extends ExchangeResp implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    
    /** Creates a new instance of LoginResp */
    public LoginResp() {
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    
 
    
}
