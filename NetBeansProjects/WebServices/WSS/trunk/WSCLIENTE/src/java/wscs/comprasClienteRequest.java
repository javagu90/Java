/*
 * reservacionesClienteRequest.java
 *
 * Created on 4 de octubre de 2008, 04:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wscs;

/**
 *
 * @author vgonzalez
 */
public class comprasClienteRequest {
    private String uid;
    private String fechaInicial;
    private String fechaFinal;
    private long clienteId;
    /** Creates a new instance of reservacionesClienteRequest */
    public comprasClienteRequest() {
        
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }
    
}
