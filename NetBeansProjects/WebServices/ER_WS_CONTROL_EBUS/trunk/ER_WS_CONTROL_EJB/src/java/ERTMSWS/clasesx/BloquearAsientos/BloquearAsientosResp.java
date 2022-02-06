/*
 * BloquearAsientosResp.java
 *
 * Created on 18 de junio de 2010, 05:38 PM
 *
 * Clase para conjuntar los resultados obtenidos de getBloquearAsientos del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.BloquearAsientos;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;
/**
 *
 * @author opalafox
 */
public class BloquearAsientosResp extends ExchangeResp  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    
    
}
