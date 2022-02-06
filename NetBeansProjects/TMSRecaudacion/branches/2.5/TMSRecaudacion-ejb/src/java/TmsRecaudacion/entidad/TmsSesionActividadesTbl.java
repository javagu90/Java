/*
 * TmsSesionActividadesTbl.java
 *
 * Created on 23 de octubre de 2007, 08:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsSesionActividadesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_SESION_ACTIVIDADES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsSesionActividadesTbl.findBySesionActividadId", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.sesionActividadId = :sesionActividadId"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByValorActividad", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.valorActividad = :valorActividad"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByFechaHoraActividad", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.fechaHoraActividad = :fechaHoraActividad"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByValorSecuencial", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.valorSecuencial = :valorSecuencial"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByCreadoPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByFechaCreacion", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsSesionActividadesTbl implements Serializable {

    @SequenceGenerator(name="TMS_SESACT_SEQ",sequenceName="TMS_SESION_ACTIVIDAD_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_SESACT_SEQ")
    @Id
    @Column(name = "SESION_ACTIVIDAD_ID", nullable = false)
    private BigDecimal sesionActividadId;

    @Column(name = "VALOR_ACTIVIDAD")
    private String valorActividad;

    @Column(name = "FECHA_HORA_ACTIVIDAD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraActividad;

    @Column(name = "AUTORIZADO_POR")
    private BigInteger autorizadoPor;

    @Column(name = "VALOR_SECUENCIAL")
    private String valorSecuencial;

    @Column(name = "CREADO_POR", nullable = false)
    private long creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private long ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @JoinColumn(name = "ACTIVIDAD_SESION_ID", referencedColumnName = "ACTIVIDAD_SESION_ID")
    @ManyToOne
    private TmsActividadesSesionTbl actividadSesionId;

    @JoinColumn(name = "CAJA_ID", referencedColumnName = "CAJA_ID")
    @ManyToOne
    private TmsCajasTbl cajaId;

    @JoinColumn(name = "CORTE_ID", referencedColumnName = "CORTE_ID")
    @ManyToOne
    private TmsCortesTbl corteId;

    @JoinColumn(name = "EMPRESA_ID", referencedColumnName = "EMPRESA_ID")
    @ManyToOne
    private TmsEmpresasTbl empresaId;
    
    /** Creates a new instance of TmsSesionActividadesTbl */
    public TmsSesionActividadesTbl() {
    }

    /**
     * Creates a new instance of TmsSesionActividadesTbl with the specified values.
     * @param sesionActividadId the sesionActividadId of the TmsSesionActividadesTbl
     */
    public TmsSesionActividadesTbl(BigDecimal sesionActividadId) {
        this.sesionActividadId = sesionActividadId;
    }

    /**
     * Creates a new instance of TmsSesionActividadesTbl with the specified values.
     * @param sesionActividadId the sesionActividadId of the TmsSesionActividadesTbl
     * @param fechaHoraActividad the fechaHoraActividad of the TmsSesionActividadesTbl
     * @param creadoPor the creadoPor of the TmsSesionActividadesTbl
     * @param fechaCreacion the fechaCreacion of the TmsSesionActividadesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsSesionActividadesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsSesionActividadesTbl
     */
    public TmsSesionActividadesTbl(BigDecimal sesionActividadId, Date fechaHoraActividad, long creadoPor, Date fechaCreacion, long ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.sesionActividadId = sesionActividadId;
        this.fechaHoraActividad = fechaHoraActividad;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the sesionActividadId of this TmsSesionActividadesTbl.
     * @return the sesionActividadId
     */
    public BigDecimal getSesionActividadId() {
        return this.sesionActividadId;
    }

    /**
     * Sets the sesionActividadId of this TmsSesionActividadesTbl to the specified value.
     * @param sesionActividadId the new sesionActividadId
     */
    public void setSesionActividadId(BigDecimal sesionActividadId) {
        this.sesionActividadId = sesionActividadId;
    }

    /**
     * Gets the valorActividad of this TmsSesionActividadesTbl.
     * @return the valorActividad
     */
    public String getValorActividad() {
        return this.valorActividad;
    }

    /**
     * Sets the valorActividad of this TmsSesionActividadesTbl to the specified value.
     * @param valorActividad the new valorActividad
     */
    public void setValorActividad(String valorActividad) {
        this.valorActividad = valorActividad;
    }

    /**
     * Gets the fechaHoraActividad of this TmsSesionActividadesTbl.
     * @return the fechaHoraActividad
     */
    public Date getFechaHoraActividad() {
        return this.fechaHoraActividad;
    }

    /**
     * Sets the fechaHoraActividad of this TmsSesionActividadesTbl to the specified value.
     * @param fechaHoraActividad the new fechaHoraActividad
     */
    public void setFechaHoraActividad(Date fechaHoraActividad) {
        this.fechaHoraActividad = fechaHoraActividad;
    }

    /**
     * Gets the autorizadoPor of this TmsSesionActividadesTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsSesionActividadesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the valorSecuencial of this TmsSesionActividadesTbl.
     * @return the valorSecuencial
     */
    public String getValorSecuencial() {
        return this.valorSecuencial;
    }

    /**
     * Sets the valorSecuencial of this TmsSesionActividadesTbl to the specified value.
     * @param valorSecuencial the new valorSecuencial
     */
    public void setValorSecuencial(String valorSecuencial) {
        this.valorSecuencial = valorSecuencial;
    }

    /**
     * Gets the creadoPor of this TmsSesionActividadesTbl.
     * @return the creadoPor
     */
    public long getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsSesionActividadesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(long creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsSesionActividadesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsSesionActividadesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsSesionActividadesTbl.
     * @return the ultimaActualizacionPor
     */
    public long getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsSesionActividadesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsSesionActividadesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsSesionActividadesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the actividadSesionId of this TmsSesionActividadesTbl.
     * @return the actividadSesionId
     */
    public TmsActividadesSesionTbl getActividadSesionId() {
        return this.actividadSesionId;
    }

    /**
     * Sets the actividadSesionId of this TmsSesionActividadesTbl to the specified value.
     * @param actividadSesionId the new actividadSesionId
     */
    public void setActividadSesionId(TmsActividadesSesionTbl actividadSesionId) {
        this.actividadSesionId = actividadSesionId;
    }

    /**
     * Gets the cajaId of this TmsSesionActividadesTbl.
     * @return the cajaId
     */
    public TmsCajasTbl getCajaId() {
        return this.cajaId;
    }

    /**
     * Sets the cajaId of this TmsSesionActividadesTbl to the specified value.
     * @param cajaId the new cajaId
     */
    public void setCajaId(TmsCajasTbl cajaId) {
        this.cajaId = cajaId;
    }

    /**
     * Gets the corteId of this TmsSesionActividadesTbl.
     * @return the corteId
     */
    public TmsCortesTbl getCorteId() {
        return this.corteId;
    }

    /**
     * Sets the corteId of this TmsSesionActividadesTbl to the specified value.
     * @param corteId the new corteId
     */
    public void setCorteId(TmsCortesTbl corteId) {
        this.corteId = corteId;
    }

    /**
     * Gets the empresaId of this TmsSesionActividadesTbl.
     * @return the empresaId
     */
    public TmsEmpresasTbl getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsSesionActividadesTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(TmsEmpresasTbl empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.sesionActividadId != null ? this.sesionActividadId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsSesionActividadesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsSesionActividadesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsSesionActividadesTbl)) {
            return false;
        }
        TmsSesionActividadesTbl other = (TmsSesionActividadesTbl)object;
        if (this.sesionActividadId != other.sesionActividadId && (this.sesionActividadId == null || !this.sesionActividadId.equals(other.sesionActividadId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsSesionActividadesTbl[sesionActividadId=" + sesionActividadId + "]";
    }
    
}
