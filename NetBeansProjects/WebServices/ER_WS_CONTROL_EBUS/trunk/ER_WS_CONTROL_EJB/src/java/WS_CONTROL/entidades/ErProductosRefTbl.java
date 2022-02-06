/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class ErProductosRefTbl implements Serializable{
private String nombre="";
private String referencia="" ;
private String folio="" ;
private String origen="" ;
private String noAsiento="" ;
private String productoCancelado="";

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the noAsiento
     */
    public String getNoAsiento() {
        return noAsiento;
    }

    /**
     * @param noAsiento the noAsiento to set
     */
    public void setNoAsiento(String noAsiento) {
        this.noAsiento = noAsiento;
    }

    /**
     * @return the productoCancelado
     */
    public String getProductoCancelado() {
        return productoCancelado;
    }

    /**
     * @param productoCancelado the productoCancelado to set
     */
    public void setProductoCancelado(String productoCancelado) {
        this.productoCancelado = productoCancelado;
    }
}
