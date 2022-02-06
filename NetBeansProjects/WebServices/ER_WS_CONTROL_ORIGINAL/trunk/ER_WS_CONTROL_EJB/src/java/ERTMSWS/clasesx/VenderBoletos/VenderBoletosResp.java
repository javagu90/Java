/*
 * VenderBoletosResp.java
 *
 * Created on 18 de junio de 2010, 05:48 PM
 *
 * Clase para conjuntar los resultados obtenidos de getVenderBoletos del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.VenderBoletos;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;
/**
 *
 * @author opalafox
 */
public class VenderBoletosResp extends ExchangeResp implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];
    
    /** Creates a new instance of VenderBoletosResp */
    public VenderBoletosResp() {
    }

    public FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }

    
}
