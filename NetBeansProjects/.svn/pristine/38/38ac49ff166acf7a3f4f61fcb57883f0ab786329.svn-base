/*
 * Direccion.java
 *
 * Created on 3 de diciembre de 2007, 01:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 *
 * @author galbarran
 */
@Embeddable
public class Direccion implements Serializable {
    private String domicilio= "";
    @Transient
    private String numExt= "";
    @Transient
    private String numInt= "";
    private String colonia= "";
    private String ciudad= "";
    private String estado= "";    
    private String pais= "";
    private String codigoPostal= "";
    
    /** Creates a new instance of Direccion */
    public Direccion() {
    }
    
    public Direccion(String domicilio, String colonia, String ciudad, String estado, String pais, String codigoPostal) {
        this.domicilio = domicilio;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    // puebla, puebla
    public String getCiudad_Estado(){
        return ciudad + "," + estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }
    
}
