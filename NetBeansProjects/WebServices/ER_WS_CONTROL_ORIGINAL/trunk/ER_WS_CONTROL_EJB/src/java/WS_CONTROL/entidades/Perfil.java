/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vgonzalez
 */
@Entity
@Table(name = "ER_PERFILES_TBL")
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERFIL_ID")
    private BigDecimal perfilId;
    @Basic(optional = false)
    @Column(name = "PERFIL_NOMBRE")
    private String perfilNombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    private List<Funcion> funciones;

    public Perfil() {
    }

    public Perfil(String[] campos) {
        this.setPerfilId(new BigDecimal(campos[0]));
        this.setPerfilNombre(campos[1]);
        this.setDescripcion(campos[2]);
    }

    public Perfil(BigDecimal perfilId) {
        this.perfilId = perfilId;
    }

    public Perfil(BigDecimal perfilId, String perfilNombre, Date fechaInicial, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.perfilId = perfilId;
        this.perfilNombre = perfilNombre;
    }

    public BigDecimal getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(BigDecimal perfilId) {
        this.perfilId = perfilId;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String perfilNombre) {
        this.perfilNombre = perfilNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilId != null ? perfilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.perfilId == null && other.perfilId != null) || (this.perfilId != null && !this.perfilId.equals(other.perfilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.Perfil[perfilId=" + perfilId + "]";
    }

    /**
     * @return the funciones
     */
    public List<Funcion> getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

}
