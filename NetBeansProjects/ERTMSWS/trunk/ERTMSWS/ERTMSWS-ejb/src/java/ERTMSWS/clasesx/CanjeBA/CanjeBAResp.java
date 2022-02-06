/*
 * CanjeBAResp.java
 *
 * Created on 18 de junio de 2010, 06:07 PM
 *
 * Clase para conjuntar los resultados obtenidos de getCanjeBA del WebService y que serán procesadas por EJB
 */

package ERTMSWS.clasesx.CanjeBA;
import ERTMSWS.clasesx.ExchangeResp;
/**
 *
 * @author opalafox
 */
public class CanjeBAResp extends ExchangeResp{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];
    

    
    /** Creates a new instance of CanjeBAResp */
    public CanjeBAResp() {
    }

    public ERTMSWS.clases.FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }

}
