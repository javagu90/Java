/*
 * CorridasResp.java
 *
 * Created on 18 de junio de 2010, 04:47 PM
 *
 * Clase para conjuntar los resultados obtenidos por getCorridas al WebService y que serán procesadas por EJB
 */

package ERTMSWS.clasesx.Corridas;
import ERTMSWS.clases.Corrida;
import ERTMSWS.clasesx.ExchangeResp;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
/**
 *
 * @author opalafox
 */
public class CorridasResp extends ExchangeResp{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private Corrida corridas[];
    
    
    /** Creates a new instance of CorridasResp */
    public CorridasResp() {
    }

    public Corrida[] getcorridas() {
        return corridas;
    }

    public void setcorridas(Corrida[] corridas) {
        this.corridas = corridas;
    }

    
}
