/*
 * RecoleccionRequest.java
 *
 * Created on 11 de septiembre de 2009, 09:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.recoleccion;

import vendERMobile.clases.datos.TiposPago;

/**
 *
 * @author asolis
 */
public class RecoleccionRequest {
    String uid;
    int cantidadRecoleccion;
    float importeRecoleccion;
    String usuarioNumero;
    String contrasenia;
    TiposPago tiposPago;
    String cajaNombre;
    /** Creates a new instance of RecoleccionRequest */
    public RecoleccionRequest() {
    }
    
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    
    public void setCantidadRecoleccion(int cantidadRecoleccion){
        this.cantidadRecoleccion = cantidadRecoleccion;
    }
    public int getCantidadRecoleccion(){
        return this.cantidadRecoleccion;
    }
    
    public void setImporteRecoleccion(float importeRecoleccion){
        this.importeRecoleccion = importeRecoleccion;
    }
    public float getImporteRecoleccion(){
        return this.importeRecoleccion;
    }
    
    public void setUsuarioNumero(String usuarioNumero){
        this.usuarioNumero = usuarioNumero;
    }
    public String getUsuarioNumero(){
        return this.usuarioNumero;
    }
    
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }
    
    public void setTiposPagos(TiposPago tiposPago){
        this.tiposPago = tiposPago;
    }
    public TiposPago getTiposPagos(){
        return this.tiposPago;
    }
    
    public String getcajaNombre() {
        return cajaNombre;
    }
    public void setcajaNombre(String cajaNombre) {
        this.cajaNombre = cajaNombre;
    }
}
