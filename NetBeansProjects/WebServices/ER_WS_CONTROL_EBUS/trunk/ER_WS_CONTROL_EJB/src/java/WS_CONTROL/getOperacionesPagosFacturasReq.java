/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import WS_CONTROL.entidades.PagoFactura;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vgonzalez
 */
public class getOperacionesPagosFacturasReq implements Serializable{
    private long  sesionId;
    private List<PagoFactura> pagos;
    private String operacion = "";

    /**
     * @return the sesionId
     */
    public long getSesionId() {
        return sesionId;
    }

    /**
     * @param sesionId the sesionId to set
     */
    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * @return the pagos
     */
    public List<PagoFactura> getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(List<PagoFactura> pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}
