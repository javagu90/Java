/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package er_control;


import er_control.entidades.ErPromocionesTbl;
import java.io.Serializable;


/**
 *
 * @author vgonzalez
 */
public class getOperacionesPromocionReq implements Serializable {
private long  sesionId;
private ErPromocionesTbl promocion;
private String operacion;

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
     * @return the promocion
     */
    public ErPromocionesTbl getPromocion() {
        return promocion;
    }

    /**
     * @param promocion the promocion to set
     */
    public void setPromocion(ErPromocionesTbl promocion) {
        this.promocion = promocion;
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
