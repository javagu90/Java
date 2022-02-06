/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/** 
 *
 * @author vgonzalez
 */
public class Transaccion  implements Serializable {
    private BigInteger NumeroTransaccion;
    private Date fechaViaje;
    private ERTMSWS.clases.Asientos asientos[];
    private ERTMSWS.clases.Pagos pagos[];

public Transaccion(){

}

    /**
     * @return the asientos
     */
    public Asientos[] getAsientos() {
        return asientos;
    }

    /**
     * @param asientos the asientos to set
     */
    public void setAsientos(Asientos[] asientos) {
        this.asientos = asientos;
    }

    /**
     * @return the pagos
     */
    public Pagos[] getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(Pagos[] pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the NumeroTransaccion
     */
    public BigInteger getNumeroTransaccion() {
        return NumeroTransaccion;
    }

    /**
     * @param NumeroTransaccion the NumeroTransaccion to set
     */
    public void setNumeroTransaccion(BigInteger NumeroTransaccion) {
        this.NumeroTransaccion = NumeroTransaccion;
    }

    /**
     * @return the fechaViaje
     */
    public Date getFechaViaje() {
        return fechaViaje;
    }

    /**
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }
}
