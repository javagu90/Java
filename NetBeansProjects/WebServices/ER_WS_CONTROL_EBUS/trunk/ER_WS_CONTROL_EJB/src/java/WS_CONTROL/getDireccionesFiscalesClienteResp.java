/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import ERTMSWS.clasesx.ExchangeResp;
import WS_CONTROL.entidades.ErClientesDirFiscalesTbl;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getDireccionesFiscalesClienteResp extends ExchangeResp implements Serializable{
    private ErClientesDirFiscalesTbl direccionesFiscales[];
    
 public getDireccionesFiscalesClienteResp(){

 }

    /**
     * @return the direccionesFiscales
     */
    public ErClientesDirFiscalesTbl[] getDireccionesFiscales() {
        return direccionesFiscales;
    }

    /**
     * @param direccionesFiscales the direccionesFiscales to set
     */
    public void setDireccionesFiscales(ErClientesDirFiscalesTbl[] direccionesFiscales) {
        this.direccionesFiscales = direccionesFiscales;
    }



}
