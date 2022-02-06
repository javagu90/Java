/*
 * TmsActividadesSesionTbl.java
 *
 * Created on 15 de agosto de 2007, 05:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Entity class TmsActividadesSesionTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ACTIVIDADES_SESION_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsActividadesSesionTbl.findByActividadSesionId", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.actividadSesionId = :actividadSesionId"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByClaveActividad", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.claveActividad = :claveActividad"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByNombreActividad", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.nombreActividad = :nombreActividad"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByHabilitado", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByCreadoPor", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByFechaCreacion", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsActividadesSesionTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsActividadesSesionTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsActividadesSesionTbl implements Serializable {

    @Id
    @Column(name = "ACTIVIDAD_SESION_ID", nullable = false)
    private BigDecimal actividadSesionId;

    @Column(name = "CLAVE_ACTIVIDAD")
    private String claveActividad;

    @Column(name = "NOMBRE_ACTIVIDAD", nullable = false)
    private String nombreActividad;

    @Column(name = "HABILITADO")
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadSesionId")
    private Collection<TmsSesionActividadesTbl> tmsSesionActividadesTblCollection;
    
    /** Creates a new instance of TmsActividadesSesionTbl */
    public TmsActividadesSesionTbl() {
    }

    /**
     * Creates a new instance of TmsActividadesSesionTbl with the specified values.
     * @param actividadSesionId the actividadSesionId of the TmsActividadesSesionTbl
     */
    public TmsActividadesSesionTbl(BigDecimal actividadSesionId) {
        this.actividadSesionId = actividadSesionId;
    }

    /**
     * Creates a new instance of TmsActividadesSesionTbl with the specified values.
     * @param actividadSesionId the actividadSesionId of the TmsActividadesSesionTbl
     * @param nombreActividad the nombreActividad of the TmsActividadesSesionTbl
     * @param creadoPor the creadoPor of the TmsActividadesSesionTbl
     * @param fechaCreacion the fechaCreacion of the TmsActividadesSesionTbl
     */
    public TmsActividadesSesionTbl(BigDecimal actividadSesionId, String nombreActividad, int creadoPor, Date fechaCreacion) {
        this.actividadSesionId = actividadSesionId;
        this.nombreActividad = nombreActividad;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the actividadSesionId of this TmsActividadesSesionTbl.
     * @return the actividadSesionId
     */
    public BigDecimal getActividadSesionId() {
        return this.actividadSesionId;
    }

    /**
     * Sets the actividadSesionId of this TmsActividadesSesionTbl to the specified value.
     * @param actividadSesionId the new actividadSesionId
     */
    public void setActividadSesionId(BigDecimal actividadSesionId) {
        this.actividadSesionId = actividadSesionId;
    }

    /**
     * Gets the claveActividad of this TmsActividadesSesionTbl.
     * @return the claveActividad
     */
    public String getClaveActividad() {
        return this.claveActividad;
    }

    /**
     * Sets the claveActividad of this TmsActividadesSesionTbl to the specified value.
     * @param claveActividad the new claveActividad
     */
    public void setClaveActividad(String claveActividad) {
        this.claveActividad = claveActividad;
    }

    /**
     * Gets the nombreActividad of this TmsActividadesSesionTbl.
     * @return the nombreActividad
     */
    public String getNombreActividad() {
        return this.nombreActividad;
    }

    /**
     * Sets the nombreActividad of this TmsActividadesSesionTbl to the specified value.
     * @param nombreActividad the new nombreActividad
     */
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    /**
     * Gets the habilitado of this TmsActividadesSesionTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsActividadesSesionTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsActividadesSesionTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsActividadesSesionTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsActividadesSesionTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsActividadesSesionTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsActividadesSesionTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsActividadesSesionTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsActividadesSesionTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsActividadesSesionTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsSesionActividadesTblCollection of this TmsActividadesSesionTbl.
     * @return the tmsSesionActividadesTblCollection
     */
    public Collection<TmsSesionActividadesTbl> getTmsSesionActividadesTblCollection() {
        return this.tmsSesionActividadesTblCollection;
    }

    /**
     * Sets the tmsSesionActividadesTblCollection of this TmsActividadesSesionTbl to the specified value.
     * @param tmsSesionActividadesTblCollection the new tmsSesionActividadesTblCollection
     */
    public void setTmsSesionActividadesTblCollection(Collection<TmsSesionActividadesTbl> tmsSesionActividadesTblCollection) {
        this.tmsSesionActividadesTblCollection = tmsSesionActividadesTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.actividadSesionId != null ? this.actividadSesionId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsActividadesSesionTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsActividadesSesionTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsActividadesSesionTbl)) {
            return false;
        }
        TmsActividadesSesionTbl other = (TmsActividadesSesionTbl)object;
        if (this.actividadSesionId != other.actividadSesionId && (this.actividadSesionId == null || !this.actividadSesionId.equals(other.actividadSesionId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertms.entidad.TmsActividadesSesionTbl[actividadSesionId=" + actividadSesionId + "]";
    }
    
}
