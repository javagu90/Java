/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.estrellaroja.TMSFacturaElectronicaWS;

/**
 *
 * @author Osvaldo Sanchez
 */
public class GuiaFacturada implements java.io.Serializable{
    private Guia guia;
    private Cliente cliente;

    /**
     * @return the guia
     */
    public Guia getGuia() {
        return guia;
    }

    /**
     * @param guia the guia to set
     */
    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
