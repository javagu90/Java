/*
 * TmsRecoleccionesTbl.java
 *
 * Created on 16 de agosto de 2007, 11:53 AM
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
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.SequenceGenerator;

/**
 * Entity class TmsRecoleccionesTbl
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_RECOLECCIONES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRecoleccionesTbl.findByRecoleccionId", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.recoleccionId = :recoleccionId"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findBySesionActividadId", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.sesionActividadId = :sesionActividadId"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByReferencia", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.referencia = :referencia"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByCantidad", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.cantidad = :cantidad"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByMonto", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.monto = :monto"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByCreadoPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByFechaCreacion", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRecoleccionesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRecoleccionesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRecoleccionesTbl implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_RECOLECCIONES_GENERADOR")
    @SequenceGenerator(name = "TMS_RECOLECCIONES_GENERADOR", 
                       sequenceName = "TMS_RECOLECCIONES_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name = "RECOLECCION_ID", nullable = false)
    private Long recoleccionId;

    @Column(name = "SESION_ACTIVIDAD_ID", nullable = false)
    private Long sesionActividadId;

    @Column(name = "REFERENCIA")
    private String referencia;

    @Column(name = "CANTIDAD")
    private Long cantidad;

    @Column(name = "MONTO")
    private Double monto;

    @Column(name = "AUTORIZADO_POR", nullable = false)
    private Long autorizadoPor;

    @Column(name = "CREADO_POR", nullable = false)
    private Long creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    @Column(name = "TIPO_PASAJERO_ID")
    private Long tipoPasajeroId;

    @Column(name = "TIPO_PAGO_ID")
    private Long tipoPagoId;
    
    /** Creates a new instance of TmsRecoleccionesTbl */
    public TmsRecoleccionesTbl() {
    }

    /**
     * Creates a new instance of TmsRecoleccionesTbl with the specified values.
     * @param recoleccionId the recoleccionId of the TmsRecoleccionesTbl
     */
    public TmsRecoleccionesTbl(Long recoleccionId) {
        this.recoleccionId = recoleccionId;
    }

    /**
     * Creates a new instance of TmsRecoleccionesTbl with the specified values.
     * @param recoleccionId the recoleccionId of the TmsRecoleccionesTbl
     * @param sesionActividadId the sesionActividadId of the TmsRecoleccionesTbl
     * @param autorizadoPor the autorizadoPor of the TmsRecoleccionesTbl
     * @param creadoPor the creadoPor of the TmsRecoleccionesTbl
     * @param fechaCreacion the fechaCreacion of the TmsRecoleccionesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRecoleccionesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRecoleccionesTbl
     */
    public TmsRecoleccionesTbl(Long recoleccionId, Long sesionActividadId, Long autorizadoPor, Long creadoPor, Timestamp fechaCreacion, Long ultimaActualizacionPor, Timestamp ultimaFechaActualizacion) {
        this.recoleccionId = recoleccionId;
        this.sesionActividadId = sesionActividadId;
        this.autorizadoPor = autorizadoPor;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recoleccionId of this TmsRecoleccionesTbl.
     * @return the recoleccionId
     */
    public Long getRecoleccionId() {
        return this.recoleccionId;
    }

    /**
     * Sets the recoleccionId of this TmsRecoleccionesTbl to the specified value.
     * @param recoleccionId the new recoleccionId
     */
    public void setRecoleccionId(Long recoleccionId) {
        this.recoleccionId = recoleccionId;
    }

    /**
     * Gets the sesionActividadId of this TmsRecoleccionesTbl.
     * @return the sesionActividadId
     */
    public Long getSesionActividadId() {
        return this.sesionActividadId;
    }

    /**
     * Sets the sesionActividadId of this TmsRecoleccionesTbl to the specified value.
     * @param sesionActividadId the new sesionActividadId
     */
    public void setSesionActividadId(Long sesionActividadId) {
        this.sesionActividadId = sesionActividadId;
    }

    /**
     * Gets the referencia of this TmsRecoleccionesTbl.
     * @return the referencia
     */
    public String getReferencia() {
        return this.referencia;
    }

    /**
     * Sets the referencia of this TmsRecoleccionesTbl to the specified value.
     * @param referencia the new referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Gets the cantidad of this TmsRecoleccionesTbl.
     * @return the cantidad
     */
    public Long getCantidad() {
        return this.cantidad;
    }

    /**
     * Sets the cantidad of this TmsRecoleccionesTbl to the specified value.
     * @param cantidad the new cantidad
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the monto of this TmsRecoleccionesTbl.
     * @return the monto
     */
    public Double getMonto() {
        return this.monto;
    }

    /**
     * Sets the monto of this TmsRecoleccionesTbl to the specified value.
     * @param monto the new monto
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Gets the autorizadoPor of this TmsRecoleccionesTbl.
     * @return the autorizadoPor
     */
    public Long getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsRecoleccionesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(Long autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the creadoPor of this TmsRecoleccionesTbl.
     * @return the creadoPor
     */
    public Long getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRecoleccionesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRecoleccionesTbl.
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRecoleccionesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRecoleccionesTbl.
     * @return the ultimaActualizacionPor
     */
    public Long getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRecoleccionesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRecoleccionesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRecoleccionesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tipoPasajeroId of this TmsRecoleccionesTbl.
     * @return the tipoPasajeroId
     */
    public Long getTipoPasajeroId() {
        return this.tipoPasajeroId;
    }

    /**
     * Sets the tipoPasajeroId of this TmsRecoleccionesTbl to the specified value.
     * @param tipoPasajeroId the new tipoPasajeroId
     */
    public void setTipoPasajeroId(Long tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Gets the tipoPagoId of this TmsRecoleccionesTbl.
     * @return the tipoPagoId
     */
    public Long getTipoPagoId() {
        return this.tipoPagoId;
    }

    /**
     * Sets the tipoPagoId of this TmsRecoleccionesTbl to the specified value.
     * @param tipoPagoId the new tipoPagoId
     */
    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }
}
