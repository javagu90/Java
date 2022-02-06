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
public class getAsociarTokensClienteReq implements Serializable{
    private int SesionId;
    private int clienteId=-1;
    private ERTMSWS.clases.Token tokens[];

    public getAsociarTokensClienteReq(){

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
     * @return the tokens
     */
    public ERTMSWS.clases.Token[] getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(ERTMSWS.clases.Token[] tokens) {
        this.tokens = tokens;
    }

}
