/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "ER_FUNCIONES_TBL")
public class Funcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FUNCION_ID")
    private BigDecimal funcionId;
    @Basic(optional = false)
    @Column(name = "FUNCION_NUMERO")
    private String funcionNumero;
    @Basic(optional = false)
    @Column(name = "FUNCION_NOMBRE")
    private String funcionNombre;
    @Basic(optional = false)
    @Column(name = "FUNCION_NOMBRE_USUARIO")
    private String funcionNombreUsuario;
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Funcion() {
    }

    public Funcion(String[] campos) {
        this.setFuncionId(new BigDecimal(campos[0]));
        this.setFuncionNumero(campos[1]);
        this.setFuncionNombre(campos[2]);
        this.setFuncionNombreUsuario(campos[3]);
        this.setDescripcion(campos[4]);
    }

    public Funcion(BigDecimal funcionId) {
        this.funcionId = funcionId;
    }

    public Funcion(BigDecimal funcionId, String funcionNumero, String funcionNombre, String funcionNombreUsuario, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.funcionId = funcionId;
        this.funcionNumero = funcionNumero;
        this.funcionNombre = funcionNombre;
        this.funcionNombreUsuario = funcionNombreUsuario;
    }

    public BigDecimal getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(BigDecimal funcionId) {
        this.funcionId = funcionId;
    }

    public String getFuncionNumero() {
        return funcionNumero;
    }

    public void setFuncionNumero(String funcionNumero) {
        this.funcionNumero = funcionNumero;
    }

    public String getFuncionNombre() {
        return funcionNombre;
    }

    public void setFuncionNombre(String funcionNombre) {
        this.funcionNombre = funcionNombre;
    }

    public String getFuncionNombreUsuario() {
        return funcionNombreUsuario;
    }

    public void setFuncionNombreUsuario(String funcionNombreUsuario) {
        this.funcionNombreUsuario = funcionNombreUsuario;
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
        hash += (funcionId != null ? funcionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.funcionId == null && other.funcionId != null) || (this.funcionId != null && !this.funcionId.equals(other.funcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.Funcion[funcionId=" + funcionId + "]";
    }

}
