/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clasesx.VenderBoletos;

import ERTMSWS.clasesx.ExchangeResp;
import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getTokensClienteResp extends ExchangeResp implements Serializable{
    private ERTMSWS.clases.Token tokens[];

    public getTokensClienteResp(){

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
