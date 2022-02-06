/*
 * TmsEstadosTbl.java
 *
 * Created on 21 de octubre de 2007, 07:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
 * Entity class TmsEstadosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ESTADOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEstadosTbl.findByEstadoId", query = "SELECT t FROM TmsEstadosTbl t WHERE t.estadoId = :estadoId"),
        @NamedQuery(name = "TmsEstadosTbl.findByEstadoNombre", query = "SELECT t FROM TmsEstadosTbl t WHERE t.estadoNombre = :estadoNombre"),
        @NamedQuery(name = "TmsEstadosTbl.findByDescripcion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsEstadosTbl.findByTipoComponente", query = "SELECT t FROM TmsEstadosTbl t WHERE t.tipoComponente = :tipoComponente"),
        @NamedQuery(name = "TmsEstadosTbl.findByActivo", query = "SELECT t FROM TmsEstadosTbl t WHERE t.activo = :activo"),
        @NamedQuery(name = "TmsEstadosTbl.findByCreadoPor", query = "SELECT t FROM TmsEstadosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEstadosTbl.findByFechaCreacion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEstadosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEstadosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEstadosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEstadosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsEstadosTbl.findByClaveEstado", query = "SELECT t FROM TmsEstadosTbl t WHERE t.claveEstado = :claveEstado")
    })
public class TmsEstadosTbl implements Serializable {

    @Id
    @Column(name = "ESTADO_ID", nullable = false)
    private BigDecimal estadoId;

    @Column(name = "ESTADO_NOMBRE")
    private String estadoNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "TIPO_COMPONENTE")
    private String tipoComponente;

    @Column(name = "ACTIVO")
    private String activo;

    @Column(name = "CREADO_POR")
    private Integer creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "CLAVE_ESTADO")
    private String claveEstado;

    @OneToMany(mappedBy = "actividadId")
    private Collection<TmsReglasSustTbl> tmsReglasSustTblCollection;
    
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
     * @param fechaCreacion the fechaCreacion of the TmsEstadosTbl
     */
    public TmsEstadosTbl(BigDecimal estadoId, Date fechaCreacion) {
        this.estadoId = estadoId;
        this.fechaCreacion = fechaCreacion;
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
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEstadosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
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
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEstadosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
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
     * Gets the tmsReglasSustTblCollection of this TmsEstadosTbl.
     * @return the tmsReglasSustTblCollection
     */
    public Collection<TmsReglasSustTbl> getTmsReglasSustTblCollection() {
        return this.tmsReglasSustTblCollection;
    }

    /**
     * Sets the tmsReglasSustTblCollection of this TmsEstadosTbl to the specified value.
     * @param tmsReglasSustTblCollection the new tmsReglasSustTblCollection
     */
    public void setTmsReglasSustTblCollection(Collection<TmsReglasSustTbl> tmsReglasSustTblCollection) {
        this.tmsReglasSustTblCollection = tmsReglasSustTblCollection;
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
        return "tmsreglassustit.entidad.TmsEstadosTbl[estadoId=" + estadoId + "]";
    }
    
}
