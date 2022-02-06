/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**  
 *
 * @author brojas
 */

public class Estado implements Serializable {
   
    private Long estadoId;
    private String nombre;
    private String pais;


    public Estado() {
    }

     public Estado(String[] campos) {

        this.setEstadoId(new Long(campos[0]));
        this.setNombre(campos[1]);
        this.setPais(campos[2]);

    }    

    public Estado(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Estado(Long estadoId, String nombre) {
        this.estadoId = estadoId;
        this.nombre = nombre;
       
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoId != null ? estadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.estadoId == null && other.estadoId != null) || (this.estadoId != null && !this.estadoId.equals(other.estadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.Ventour.entidades.Estados[estadoId=" + estadoId + "]";
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

}
