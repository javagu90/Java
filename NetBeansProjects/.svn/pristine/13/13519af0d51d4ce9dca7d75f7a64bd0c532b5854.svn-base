/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "ER_TIPOS_USUARIO_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErTiposUsuarioTbl.findAll", query = "SELECT e FROM ErTiposUsuarioTbl e"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByTipoUsuarioId", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.tipoUsuarioId = :tipoUsuarioId"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByNombreTipo", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByDescripcion", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByAdicional1", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByAdicional2", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByAdicional3", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByAdicional4", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByAdicional5", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByCreadoPor", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByFechaCreacion", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErTiposUsuarioTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErTiposUsuarioTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 *
 *
 */
public class ErTiposUsuarioTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_USUARIO_ID")
    private BigDecimal tipoUsuarioId;
    @Column(name = "NOMBRE_TIPO")
    private String nombreTipo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ADICIONAL1")
    private String adicional1;
    @Column(name = "ADICIONAL2")
    private String adicional2;
    @Column(name = "ADICIONAL3")
    private String adicional3;
    @Column(name = "ADICIONAL4")
    private String adicional4;
    @Column(name = "ADICIONAL5")
    private String adicional5;
    @Column(name = "CREADO_POR")
    private BigInteger creadoPor;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;
   // @OneToMany(mappedBy = "erTiposUsuarioTbl")
   // private Collection<ErUsuariosTbl> erUsuariosTblCollection;

    public ErTiposUsuarioTbl() {
    }

    public ErTiposUsuarioTbl(BigDecimal tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public BigDecimal getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(BigDecimal tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    public String getAdicional5() {
        return adicional5;
    }

    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    public BigInteger getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
/*
    public Collection<ErUsuariosTbl> getErUsuariosTblCollection() {
        return erUsuariosTblCollection;
    }

    public void setErUsuariosTblCollection(Collection<ErUsuariosTbl> erUsuariosTblCollection) {
        this.erUsuariosTblCollection = erUsuariosTblCollection;
    }

 *
 */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoUsuarioId != null ? tipoUsuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErTiposUsuarioTbl)) {
            return false;
        }
        ErTiposUsuarioTbl other = (ErTiposUsuarioTbl) object;
        if ((this.tipoUsuarioId == null && other.tipoUsuarioId != null) || (this.tipoUsuarioId != null && !this.tipoUsuarioId.equals(other.tipoUsuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErTiposUsuarioTbl[tipoUsuarioId=" + tipoUsuarioId + "]";
    }

}
