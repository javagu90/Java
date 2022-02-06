/*
 * TmsCortesTbl.java
 *
 * Created on 15 de agosto de 2007, 05:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
 * Entity class TmsCortesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_CORTES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsCortesTbl.findByCorteId", query = "SELECT t FROM TmsCortesTbl t WHERE t.corteId = :corteId"),
        @NamedQuery(name = "TmsCortesTbl.findByNombreSesion", query = "SELECT t FROM TmsCortesTbl t WHERE t.nombreSesion = :nombreSesion"),
        @NamedQuery(name = "TmsCortesTbl.findByEstadoCorte", query = "SELECT t FROM TmsCortesTbl t WHERE t.estadoCorte = :estadoCorte"),
        @NamedQuery(name = "TmsCortesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsCortesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsCortesTbl.findByCreadoPor", query = "SELECT t FROM TmsCortesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsCortesTbl.findByFechaCreacion", query = "SELECT t FROM TmsCortesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsCortesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsCortesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsCortesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsCortesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsCortesTbl implements Serializable {

    @Id
    @Column(name = "CORTE_ID", nullable = false)
    private BigDecimal corteId;

    @Column(name = "NOMBRE_SESION")
    private String nombreSesion;

    @Column(name = "ESTADO_CORTE", nullable = false)
    private String estadoCorte;

    @Column(name = "AUTORIZADO_POR", nullable = false)
    private BigInteger autorizadoPor;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private int ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private TmsUsuariosTbl usuarioId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corteId")
    private Collection<TmsSesionActividadesTbl> tmsSesionActividadesTblCollection;
    
    /** Creates a new instance of TmsCortesTbl */
    public TmsCortesTbl() {
    }

    /**
     * Creates a new instance of TmsCortesTbl with the specified values.
     * @param corteId the corteId of the TmsCortesTbl
     */
    public TmsCortesTbl(BigDecimal corteId) {
        this.corteId = corteId;
    }

    /**
     * Creates a new instance of TmsCortesTbl with the specified values.
     * @param corteId the corteId of the TmsCortesTbl
     * @param estadoCorte the estadoCorte of the TmsCortesTbl
     * @param autorizadoPor the autorizadoPor of the TmsCortesTbl
     * @param creadoPor the creadoPor of the TmsCortesTbl
     * @param fechaCreacion the fechaCreacion of the TmsCortesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsCortesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsCortesTbl
     */
    public TmsCortesTbl(BigDecimal corteId, String estadoCorte, BigInteger autorizadoPor, int creadoPor, Date fechaCreacion, int ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.corteId = corteId;
        this.estadoCorte = estadoCorte;
        this.autorizadoPor = autorizadoPor;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the corteId of this TmsCortesTbl.
     * @return the corteId
     */
    public BigDecimal getCorteId() {
        return this.corteId;
    }

    /**
     * Sets the corteId of this TmsCortesTbl to the specified value.
     * @param corteId the new corteId
     */
    public void setCorteId(BigDecimal corteId) {
        this.corteId = corteId;
    }

    /**
     * Gets the nombreSesion of this TmsCortesTbl.
     * @return the nombreSesion
     */
    public String getNombreSesion() {
        return this.nombreSesion;
    }

    /**
     * Sets the nombreSesion of this TmsCortesTbl to the specified value.
     * @param nombreSesion the new nombreSesion
     */
    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
    }

    /**
     * Gets the estadoCorte of this TmsCortesTbl.
     * @return the estadoCorte
     */
    public String getEstadoCorte() {
        return this.estadoCorte;
    }

    /**
     * Sets the estadoCorte of this TmsCortesTbl to the specified value.
     * @param estadoCorte the new estadoCorte
     */
    public void setEstadoCorte(String estadoCorte) {
        this.estadoCorte = estadoCorte;
    }

    /**
     * Gets the autorizadoPor of this TmsCortesTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsCortesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the creadoPor of this TmsCortesTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsCortesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsCortesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsCortesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsCortesTbl.
     * @return the ultimaActualizacionPor
     */
    public int getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsCortesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(int ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsCortesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsCortesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the usuarioId of this TmsCortesTbl.
     * @return the usuarioId
     */
    public TmsUsuariosTbl getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuarioId of this TmsCortesTbl to the specified value.
     * @param usuarioId the new usuarioId
     */
    public void setUsuarioId(TmsUsuariosTbl usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the tmsSesionActividadesTblCollection of this TmsCortesTbl.
     * @return the tmsSesionActividadesTblCollection
     */
    public Collection<TmsSesionActividadesTbl> getTmsSesionActividadesTblCollection() {
        return this.tmsSesionActividadesTblCollection;
    }

    /**
     * Sets the tmsSesionActividadesTblCollection of this TmsCortesTbl to the specified value.
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
        hash += (this.corteId != null ? this.corteId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsCortesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsCortesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsCortesTbl)) {
            return false;
        }
        TmsCortesTbl other = (TmsCortesTbl)object;
        if (this.corteId != other.corteId && (this.corteId == null || !this.corteId.equals(other.corteId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertms.entidad.TmsCortesTbl[corteId=" + corteId + "]";
    }
    
}
