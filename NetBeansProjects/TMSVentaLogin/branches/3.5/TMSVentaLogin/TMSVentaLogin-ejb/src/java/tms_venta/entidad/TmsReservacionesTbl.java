/*
 * TmsReservacionesTbl.java
 *
 * Created on 8 de septiembre de 2007, 11:08 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity class TmsReservacionesTbl
 * 
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_RESERVACIONES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsReservacionesTbl.findByReservacionId", query = "SELECT t FROM TmsReservacionesTbl t WHERE t.reservacionId = :reservacionId")
    })
public class TmsReservacionesTbl implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_ENC_RVN_GENERADOR")
    @SequenceGenerator(name = "TMS_ENC_RVN_GENERADOR", 
                       sequenceName = "TMS_RESERVACIONES_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name = "RESERVACION_ID", nullable = false)
    private Long reservacionId;
    @Column(name = "CLAVE_RESERVACION", nullable = false)
    private String claveReservacion;
    @Column(name = "CLIENTE_ID")
    private Long clienteId;
    @Column(name = "RESPONSABLE_RESERVACION", nullable = false)
    private String responsableReservacion;
    @Column(name = "CANCELABLE")
    private String cancelable;
    @Column(name = "NO_ASIENTO", nullable = false)
    private Long noAsiento;
    @Column(name = "TIPO_PASAJERO")
    private String tipoPasajero;
    @Column(name = "ESTADO_RESERVACION")
    private String estadoReservacion;
    @Column(name = "CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;
    @Column(name = "CORRIDA_ID", nullable = false)
    private Long corridaId;
    @Column(name = "CIUDAD_RESERVACION")
    private String ciudadReservacion;
    @Column(name = "TIPO_TRANSACCION")
    private String tipoTransaccion;
    
    /**
     * Creates a new instance of TmsReservacionesTbl
     */
    public TmsReservacionesTbl() {
    }

    public Long getReservacionId() {
        return this.reservacionId;
    }

    /**
     * Sets the reservacionEncId of this TmsReservacionesEncTbl to the specified value.
     * @param reservacionEncId the new reservacionEncId
     */
    public void setReservacionId(Long reservacionId) {
        this.reservacionId = reservacionId;
    }
    
    /**
     * Gets the reservacionEncId of this TmsReservacionesEncTbl.
     * @return the reservacionEncId
     */
    public Long getClienteId() {
        return this.clienteId;
    }

    /**
     * Sets the reservacionEncId of this TmsReservacionesEncTbl to the specified value.
     * @param reservacionEncId the new reservacionEncId
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Gets the claveReservacion of this TmsReservacionesEncTbl.
     * @return the claveReservacion
     */
    public String getClaveReservacion() {
        return this.claveReservacion;
    }

    /**
     * Sets the claveReservacion of this TmsReservacionesEncTbl to the specified value.
     * @param claveReservacion the new claveReservacion
     */
    public void setClaveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
    }

    /**
     * Gets the responsableReservacion of this TmsReservacionesEncTbl.
     * @return the responsableReservacion
     */
    public String getResponsableReservacion() {
        return this.responsableReservacion;
    }

    /**
     * Sets the responsableReservacion of this TmsReservacionesEncTbl to the specified value.
     * @param responsableReservacion the new responsableReservacion
     */
    public void setResponsableReservacion(String responsableReservacion) {
        this.responsableReservacion = responsableReservacion;
    }

    /**
     * Gets the cancelable of this TmsReservacionesEncTbl.
     * @return the cancelable
     */
    public String getCancelable() {
        return this.cancelable;
    }

    /**
     * Sets the cancelable of this TmsReservacionesEncTbl to the specified value.
     * @param cancelable the new cancelable
     */
    public void setCancelable(String cancelable) {
        this.cancelable = cancelable;
    }
    
    /**
     * Gets the noAsiento of this TmsReservacionesTbl.
     * 
     * @return the noAsiento
     */
    public Long getNoAsiento() {
        return this.noAsiento;
    }

    /**
     * Sets the noAsiento of this TmsReservacionesTbl to the specified value.
     * 
     * @param noAsiento the new noAsiento
     */
    public void setNoAsiento(Long noAsiento) {
        this.noAsiento = noAsiento;
    }

    /**
     * Gets the TipoPasajero of this TmsReservacionesTbl.
     * 
     * @return the TipoPasajero
     */
    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }
    
    public String getEstadoReservacion() {
        return this.estadoReservacion;
    }

    public void setEstadoReservacion(String estadoReservacion) {
        this.estadoReservacion = estadoReservacion;
    }

    /**
     * Gets the creadoPor of this TmsReservacionesTbl.
     * 
     * @return the creadoPor
     */
    public Long getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsReservacionesTbl to the specified value.
     * 
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsReservacionesTbl.
     * 
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsReservacionesTbl to the specified value.
     * 
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsReservacionesTbl.
     * 
     * @return the ultimaActualizacionPor
     */
    public Long getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsReservacionesTbl to the specified value.
     * 
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsReservacionesTbl.
     * 
     * @return the ultimaFechaActualizacion
     */
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsReservacionesTbl to the specified value.
     * 
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the corridaId of this TmsReservacionesTbl.
     * 
     * @return the corridaId
     */
    public Long getCorridaId() {
        return this.corridaId;
    }

    /**
     * Sets the corridaId of this TmsReservacionesTbl to the specified value.
     * 
     * @param corridaId the new corridaId
     */
    public void setCorridaId(Long corridaId) {
        this.corridaId = corridaId;
    }

    public String getCiudadReservacion() {
        return this.ciudadReservacion;
    }

    public void setCiudadReservacion(String ciudadReservacion) {
        this.ciudadReservacion = ciudadReservacion;
    }
    
    public String getTipoTransaccion() {
        return this.tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
}
