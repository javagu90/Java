/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.VenderBoletos;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getAsociarComprasClienteReq implements Serializable{
    private int SesionId;
    private int clienteId=-1;
    private ERTMSWS.clases.FoliosBoletos FoliosBoletos[];

    public getAsociarComprasClienteReq(){
         
    }

    /**
     * @return the SesionId
     */
    public int getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
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

    /**
     * @return the FoliosBoletos
     */
    public ERTMSWS.clases.FoliosBoletos[] getFoliosBoletos() {
        return FoliosBoletos;
    }

    /**
     * @param FoliosBoletos the FoliosBoletos to set
     */
    public void setFoliosBoletos(ERTMSWS.clases.FoliosBoletos[] FoliosBoletos) {
        this.FoliosBoletos = FoliosBoletos;
    }
}


