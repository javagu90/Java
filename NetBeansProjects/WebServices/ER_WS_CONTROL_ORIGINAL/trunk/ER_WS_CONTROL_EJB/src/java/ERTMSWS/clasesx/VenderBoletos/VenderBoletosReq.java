/*
 * VenderBoletosReq.java
 *
 * Created on 18 de junio de 2010, 05:41 PM
 *
 * Clase para conjuntar los argumentos dados a getVenderBoletos del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.VenderBoletos;
import ERTMSWS.clases.Asientos;
import java.io.Serializable;
/**
 *
 * @author opalafox
 */
public class VenderBoletosReq  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private int clienteId;
    private ERTMSWS.clases.Asientos Asientos[];

    /** Creates a new instance of VenderBoletosReq */
    public VenderBoletosReq() {
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    public ERTMSWS.clases.Asientos[] getAsientos() {
        return Asientos;
    }

    public void setAsientos(ERTMSWS.clases.Asientos[] Asientos) {
        this.Asientos = Asientos;
    }

    /**
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
}
