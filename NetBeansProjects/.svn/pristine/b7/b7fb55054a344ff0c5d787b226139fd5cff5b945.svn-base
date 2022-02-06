/*
 * TmsSesionActividadesTbl.java
 *
 * Created on 17 de agosto de 2007, 11:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity class TmsSesionActividadesTbl
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_SESION_ACTIVIDADES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsSesionActividadesTbl.findBySesionActividadId", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.sesionActividadId = :sesionActividadId"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByValorActividad", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.valorActividad = :valorActividad"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByFechaHoraActividad", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.fechaHoraActividad = :fechaHoraActividad"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByCreadoPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByFechaCreacion", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsSesionActividadesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsSesionActividadesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsSesionActividadesTbl implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_SESION_ACTIVIDADES_GENERADOR")
    @SequenceGenerator(name = "TMS_SESION_ACTIVIDADES_GENERADOR", 
                       sequenceName = "TMS_SESION_ACTIVIDAD_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name = "SESION_ACTIVIDAD_ID", nullable = false)
    private Long sesionActividadId;
    
    @Column(name = "EMPRESA_ID")
    private Long empresaId;
    
    @Column(name = "ACTIVIDAD_SESION_ID", nullable = false)
    private Long actividadSesionId;

    @Column(name = "VALOR_ACTIVIDAD")
    private String valorActividad;

    @Column(name = "FECHA_HORA_ACTIVIDAD", nullable = false)
    private Timestamp fechaHoraActividad;

    @Column(name = "AUTORIZADO_POR")
    private Long autorizadoPor;

    @Column(name = "CREADO_POR", nullable = false)
    private long creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private long ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    @Column(name = "CORTE_ID", nullable = false)
    private Long corteId;
    
    /** Creates a new instance of TmsSesionActividadesTbl */
    public TmsSesionActividadesTbl() {
    }

    /**
     * Gets the sesionActividadId of this TmsSesionActividadesTbl.
     * @return the sesionActividadId
     */
    public Long getSesionActividadId() {
        return this.sesionActividadId;
    }

    /**
     * Sets the sesionActividadId of this TmsSesionActividadesTbl to the specified value.
     * @param sesionActividadId the new sesionActividadId
     */
    public void setSesionActividadId(Long sesionActividadId) {
        this.sesionActividadId = sesionActividadId;
    }
    
    /**
     * Gets the sesionActividadId of this TmsSesionActividadesTbl.
     * @return the sesionActividadId
     */
    public Long getActividadSesionId() {
        return this.actividadSesionId;
    }

    /**
     * Sets the sesionActividadId of this TmsSesionActividadesTbl to the specified value.
     * @param sesionActividadId the new sesionActividadId
     */
    public void setActividadSesionId(Long ActividadSesionId) {
        this.actividadSesionId = ActividadSesionId;
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
    public Timestamp getFechaHoraActividad() {
        return this.fechaHoraActividad;
    }

    /**
     * Sets the fechaHoraActividad of this TmsSesionActividadesTbl to the specified value.
     * @param fechaHoraActividad the new fechaHoraActividad
     */
    public void setFechaHoraActividad(Timestamp fechaHoraActividad) {
        this.fechaHoraActividad = fechaHoraActividad;
    }

    /**
     * Gets the autorizadoPor of this TmsSesionActividadesTbl.
     * @return the autorizadoPor
     */
    public Long getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsSesionActividadesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(Long autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
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
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsSesionActividadesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
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
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsSesionActividadesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the corteId of this TmsSesionActividadesTbl.
     * @return the corteId
     */
    public Long getCorteId() {
        return this.corteId;
    }

    /**
     * Sets the corteId of this TmsSesionActividadesTbl to the specified value.
     * @param corteId the new corteId
     */
    public void setCorteId(Long corteId) {
        this.corteId = corteId;
    }    
    
    public Long getEmpresaId() {
        return this.empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
}
