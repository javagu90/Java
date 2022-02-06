/*
 * clienteValidoRequest.java
 *
 * Created on 23 de septiembre de 2008, 01:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;

import wsc.entidades.Cliente;

/**
 *
 * @author vgonzalez
 */
public class altaClienteRequest {
    private String uid = "";
    private Cliente cliente ;
    
    /** Creates a new instance of clienteValidoRequest */
    public altaClienteRequest() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
