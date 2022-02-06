/*
 * CancelarBoletosResp.java
 *
 * Created on 18 de junio de 2010, 05:55 PM
 *
 * Clase para conjuntar los resultados obtenidos de getCancelarBoletos del WebService y que serán procesadas por EJB
 */

package ERTMSWS.clasesx.CancelarBoletos;
import ERTMSWS.clasesx.ExchangeResp;
import ERTMSWS.clases.Asientos;
/**
 *
 * @author opalafox
 */



public class CancelarBoletosResp extends ExchangeResp{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private ERTMSWS.clases.Asientos Asientos [];

    public ERTMSWS.clases.Asientos[] getAsientos() {
        return Asientos;
    }

    public void setAsientos(ERTMSWS.clases.Asientos[] Asientos) {
        this.Asientos = Asientos;
    }
    
    
}
