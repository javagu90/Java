/*
 * CancelarBoletosReq.java
 *
 * Created on 18 de junio de 2010, 05:52 PM
 *
 * Clase para conjuntar los argumentos dados a getCancelarBoletos del WebService y que serán procesadas por EJB
 */

package ERTMSWS.clasesx.CancelarBoletos;
import ERTMSWS.clases.FoliosBoletos;
/**
 *
 * @author opalafox
 */
public class CancelarBoletosReq {
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];
    private String MotivoCancelación;

    /** Creates a new instance of CancelarBoletosReq */
    public CancelarBoletosReq() {
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    public FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }

    public String getMotivoCancelación() {
        return MotivoCancelación;
    }

    public void setMotivoCancelación(String MotivoCancelación) {
        this.MotivoCancelación = MotivoCancelación;
    }
    
}
