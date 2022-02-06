/*
 * TmsEstadosTbl.java
 *
 * Created on 16 de octubre de 2008, 09:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TMS_ESTADOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEstadosTbl.getAll", query = "SELECT t FROM TmsEstadosTbl t where t.tipoComponente = 'TERMINAL' ORDER BY t.estadoNombre"),
        @NamedQuery(name = "TmsEstadosTbl.findByEstadoId", query = "SELECT t FROM TmsEstadosTbl t WHERE t.estadoId = :estadoId"),
        @NamedQuery(name = "TmsEstadosTbl.findByClaveEstado", query = "SELECT t FROM TmsEstadosTbl t WHERE t.claveEstado = :claveEstado"),
        @NamedQuery(name = "TmsEstadosTbl.findByEstadoNombre", query = "SELECT t FROM TmsEstadosTbl t WHERE t.estadoNombre = :estadoNombre"),
        @NamedQuery(name = "TmsEstadosTbl.findByDescripcion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsEstadosTbl.findByTipoComponente", query = "SELECT t FROM TmsEstadosTbl t WHERE t.tipoComponente = :tipoComponente"),
        @NamedQuery(name = "TmsEstadosTbl.findByActivo", query = "SELECT t FROM TmsEstadosTbl t WHERE t.activo = :activo"),
        @NamedQuery(name = "TmsEstadosTbl.findByCreadoPor", query = "SELECT t FROM TmsEstadosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEstadosTbl.findByFechaCreacion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEstadosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEstadosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEstadosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsEstadosTbl.findByReplicacionEstado", query = "SELECT t FROM TmsEstadosTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsEstadosTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsEstadosTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsEstadosTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsEstadosTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsEstadosTbl implements Serializable {

    @Id
    @Column(name = "ESTADO_ID", nullable = false)
    private BigDecimal estadoId;

    @Column(name = "CLAVE_ESTADO")
    private String claveEstado;

    @Column(name = "ESTADO_NOMBRE")
    private String estadoNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "TIPO_COMPONENTE")
    private String tipoComponente;

    @Column(name = "ACTIVO")
    private String activo;

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
    
    /** Creates a new instance of TmsEstadosTbl */
    public TmsEstadosTbl() {
    }

    /**
     * Creates a new instance of TmsEstadosTbl with the specified values.
     * @param estadoId the estadoId of the TmsEstadosTbl
     */
    public TmsEstadosTbl(BigDecimal estadoId) {
        this.estadoId = estadoId;
    }

    /**
     * Creates a new instance of TmsEstadosTbl with the specified values.
     * @param estadoId the estadoId of the TmsEstadosTbl
     * @param creadoPor the creadoPor of the TmsEstadosTbl
     * @param fechaCreacion the fechaCreacion of the TmsEstadosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsEstadosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsEstadosTbl
     */
    public TmsEstadosTbl(BigDecimal estadoId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.estadoId = estadoId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the estadoId of this TmsEstadosTbl.
     * @return the estadoId
     */
    public BigDecimal getEstadoId() {
        return this.estadoId;
    }

    /**
     * Sets the estadoId of this TmsEstadosTbl to the specified value.
     * @param estadoId the new estadoId
     */
    public void setEstadoId(BigDecimal estadoId) {
        this.estadoId = estadoId;
    }

    /**
     * Gets the claveEstado of this TmsEstadosTbl.
     * @return the claveEstado
     */
    public String getClaveEstado() {
        return this.claveEstado;
    }

    /**
     * Sets the claveEstado of this TmsEstadosTbl to the specified value.
     * @param claveEstado the new claveEstado
     */
    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }

    /**
     * Gets the estadoNombre of this TmsEstadosTbl.
     * @return the estadoNombre
     */
    public String getEstadoNombre() {
        return this.estadoNombre;
    }

    /**
     * Sets the estadoNombre of this TmsEstadosTbl to the specified value.
     * @param estadoNombre the new estadoNombre
     */
    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    /**
     * Gets the descripcion of this TmsEstadosTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsEstadosTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the tipoComponente of this TmsEstadosTbl.
     * @return the tipoComponente
     */
    public String getTipoComponente() {
        return this.tipoComponente;
    }

    /**
     * Sets the tipoComponente of this TmsEstadosTbl to the specified value.
     * @param tipoComponente the new tipoComponente
     */
    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    /**
     * Gets the activo of this TmsEstadosTbl.
     * @return the activo
     */
    public String getActivo() {
        return this.activo;
    }

    /**
     * Sets the activo of this TmsEstadosTbl to the specified value.
     * @param activo the new activo
     */
    public void setActivo(String activo) {
        this.activo = activo;
    }

    /**
     * Gets the creadoPor of this TmsEstadosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEstadosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsEstadosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsEstadosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsEstadosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEstadosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsEstadosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsEstadosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsEstadosTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsEstadosTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsEstadosTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsEstadosTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsEstadosTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsEstadosTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.estadoId != null ? this.estadoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsEstadosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsEstadosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsEstadosTbl)) {
            return false;
        }
        TmsEstadosTbl other = (TmsEstadosTbl)object;
        if (this.estadoId != other.estadoId && (this.estadoId == null || !this.estadoId.equals(other.estadoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsEstadosTbl[estadoId=" + estadoId + "]";
    }
    
}
