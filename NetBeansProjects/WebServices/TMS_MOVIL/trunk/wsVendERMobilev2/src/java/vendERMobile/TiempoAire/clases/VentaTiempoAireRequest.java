/*
 * ParametrosReq.java
 *
 * Created on 4 de marzo de 2011, 07:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.TiempoAire.clases;

/**
 *
 * @author brojasa
 */
public class VentaTiempoAireRequest {
    
    // Parametros para traer los datos de configuracion
   
   
    
    String compania  = null;
    String telefono = null;
    String monto  = null;
    String tipoPago  = null;
    String noUsuario = null;
    String caja = null;
    String corteId = null;
    String canalVenta = null; 

    
    /** Creates a new instance of ParametrosReq */
       
    public VentaTiempoAireRequest() {
    }
    
      
    public VentaTiempoAireRequest(   String compania, String telefono,  
                                      String monto, String tipoPago, String noUsuario, String caja,  
                                      String corteId, String canalVenta) {
            this.compania  = compania ;
            this.telefono  = telefono ;
            this.monto     = monto ;
            this.tipoPago  = tipoPago ;
            this.noUsuario = tipoPago ;
            
            this.caja      = caja ; 
            this.corteId   = corteId ;
            
            this.canalVenta  =  canalVenta ;
     }
        
    
  
    /**
     * @return the compania
     */
    public String getCompania() {
        return compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the noUsuario
     */
    public String getNoUsuario() {
        return noUsuario;
    }

    /**
     * @param noUsuario the noUsuario to set
     */
    public void setNoUsuario(String noUsuario) {
        this.noUsuario = noUsuario;
    }

    

    /**
     * @return the caja
     */
    public String getCaja() {
        return caja;
    }

    /**
     * @param caja the caja to set
     */
    public void setCaja(String caja) {
        this.caja = caja;
    }

    /**
     * @return the corteId
     */
    public String getCorteId() {
        return corteId;
    }

    /**
     * @param corteId the corteId to set
     */
    public void setCorteId(String corteId) {
        this.corteId = corteId;
    }

    

    /**
     * @return the canalVenta
     */
    public String getCanalVenta() {
        return canalVenta;
    }

    /**  
     * @param canalVenta the canalVenta to set
     */
    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

}    
    
    
    

