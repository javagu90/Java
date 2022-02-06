/*
 * CanjeBAReq.java
 *
 * Created on 18 de junio de 2010, 06:05 PM
 *
 * Clase para conjuntar los argumentos dados a getCanjeBA del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.CanjeBA;

import java.io.Serializable;

/**
 *
 * @author opalafox
 */
public class CanjeBAReq  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];
    private ERTMSWS.clases.Asientos Asientos[];

    /** Creates a new instance of CanjeBAReq */
    public CanjeBAReq() {
        
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    public ERTMSWS.clases.FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }

    public ERTMSWS.clases.Asientos[] getAsientos() {
        return Asientos;
    }

    public void setAsientos(ERTMSWS.clases.Asientos[] Asientos) {
        this.Asientos = Asientos;
    }
    
}
