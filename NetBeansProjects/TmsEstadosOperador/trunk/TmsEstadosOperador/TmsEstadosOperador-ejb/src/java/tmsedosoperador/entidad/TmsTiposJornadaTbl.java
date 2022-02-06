/*
 * TmsTiposJornadaTbl.java
 *
 * Created on 9 de diciembre de 2007, 05:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
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
 * Entity class TmsTiposJornadaTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TIPOS_JORNADA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposJornadaTbl.findByTipoJornadaId", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.tipoJornadaId = :tipoJornadaId"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByNombreTipo", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.nombreTipo = :nombreTipo"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByDescripcion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByHabilitado", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByCreadoPor", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByFechaCreacion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByReplicacionEstado", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsTiposJornadaTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsTiposJornadaTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsTiposJornadaTbl implements Serializable {

    @Id
    @Column(name = "TIPO_JORNADA_ID", nullable = false)
    private BigDecimal tipoJornadaId;

    @Column(name = "NOMBRE_TIPO", nullable = false)
    private String nombreTipo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "HABILITADO")
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoJornadaId")
    private Collection<TmsJornadasServiciosTbl> tmsJornadasServiciosTblCollection;
    
    /** Creates a new instance of TmsTiposJornadaTbl */
    public TmsTiposJornadaTbl() {
    }

    /**
     * Creates a new instance of TmsTiposJornadaTbl with the specified values.
     * @param tipoJornadaId the tipoJornadaId of the TmsTiposJornadaTbl
     */
    public TmsTiposJornadaTbl(BigDecimal tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    /**
     * Creates a new instance of TmsTiposJornadaTbl with the specified values.
     * @param tipoJornadaId the tipoJornadaId of the TmsTiposJornadaTbl
     * @param nombreTipo the nombreTipo of the TmsTiposJornadaTbl
     * @param creadoPor the creadoPor of the TmsTiposJornadaTbl
     * @param fechaCreacion the fechaCreacion of the TmsTiposJornadaTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsTiposJornadaTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsTiposJornadaTbl
     */
    public TmsTiposJornadaTbl(BigDecimal tipoJornadaId, String nombreTipo, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.tipoJornadaId = tipoJornadaId;
        this.nombreTipo = nombreTipo;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tipoJornadaId of this TmsTiposJornadaTbl.
     * @return the tipoJornadaId
     */
    public BigDecimal getTipoJornadaId() {
        return this.tipoJornadaId;
    }

    /**
     * Sets the tipoJornadaId of this TmsTiposJornadaTbl to the specified value.
     * @param tipoJornadaId the new tipoJornadaId
     */
    public void setTipoJornadaId(BigDecimal tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposJornadaTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposJornadaTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the descripcion of this TmsTiposJornadaTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsTiposJornadaTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the habilitado of this TmsTiposJornadaTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsTiposJornadaTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsTiposJornadaTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposJornadaTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposJornadaTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposJornadaTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposJornadaTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposJornadaTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposJornadaTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposJornadaTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsTiposJornadaTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsTiposJornadaTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsTiposJornadaTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsTiposJornadaTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsTiposJornadaTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsTiposJornadaTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the tmsJornadasServiciosTblCollection of this TmsTiposJornadaTbl.
     * @return the tmsJornadasServiciosTblCollection
     */
    public Collection<TmsJornadasServiciosTbl> getTmsJornadasServiciosTblCollection() {
        return this.tmsJornadasServiciosTblCollection;
    }

    /**
     * Sets the tmsJornadasServiciosTblCollection of this TmsTiposJornadaTbl to the specified value.
     * @param tmsJornadasServiciosTblCollection the new tmsJornadasServiciosTblCollection
     */
    public void setTmsJornadasServiciosTblCollection(Collection<TmsJornadasServiciosTbl> tmsJornadasServiciosTblCollection) {
        this.tmsJornadasServiciosTblCollection = tmsJornadasServiciosTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.tipoJornadaId != null ? this.tipoJornadaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTiposJornadaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTiposJornadaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTiposJornadaTbl)) {
            return false;
        }
        TmsTiposJornadaTbl other = (TmsTiposJornadaTbl)object;
        if (this.tipoJornadaId != other.tipoJornadaId && (this.tipoJornadaId == null || !this.tipoJornadaId.equals(other.tipoJornadaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsedosoperador.entidad.TmsTiposJornadaTbl[tipoJornadaId=" + tipoJornadaId + "]";
    }
    
}
