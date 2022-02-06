/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import ERTMSWS.clases.BoletoTransaccion;
import ERTMSWS.clases.TransaccionViajesCliente;
import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author vgonzalez
 */ 
public class getViajesClienteResp extends ExchangeResp implements Serializable{
        private BigInteger cantidadTotaTransacciones;
        private TransaccionViajesCliente[] TransaccionesCliente;

    public getViajesClienteResp(){

    }

    /**
     * @return the TransaccionesCliente
     */
    public TransaccionViajesCliente[] getTransaccionesCliente() {
        return TransaccionesCliente;
    }

    /**
     * @param TransaccionesCliente the TransaccionesCliente to set
     */
    public void setTransaccionesCliente(TransaccionViajesCliente[] TransaccionesCliente) {
        this.TransaccionesCliente = TransaccionesCliente;
    }

    /**
     * @return the cantidadTotaTransacciones
     */
    public BigInteger getCantidadTotaTransacciones() {
        return cantidadTotaTransacciones;
    }

    /**
     * @param cantidadTotaTransacciones the cantidadTotaTransacciones to set
     */
    public void setCantidadTotaTransacciones(BigInteger cantidadTotaTransacciones) {
        this.cantidadTotaTransacciones = cantidadTotaTransacciones;
    }

}
