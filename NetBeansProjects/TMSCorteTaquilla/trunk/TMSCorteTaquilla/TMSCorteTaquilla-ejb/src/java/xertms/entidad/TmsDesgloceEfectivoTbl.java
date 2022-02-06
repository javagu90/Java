/*
 * TmsDesgloceEfectivoTbl.java
 *
 * Created on 17 de agosto de 2007, 03:16 PM
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.SequenceGenerator;

/**
 * Entity class TmsDesgloceEfectivoTbl
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_DESGLOCE_EFECTIVO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByDesgloceEfectivoId", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.desgloceEfectivoId = :desgloceEfectivoId"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByCantidad", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.cantidad = :cantidad"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByDenominacion", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.denominacion = :denominacion"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByMonto", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.monto = :monto"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByReferencia", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.referencia = :referencia"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByCreadoPor", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByFechaCreacion", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsDesgloceEfectivoTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsDesgloceEfectivoTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsDesgloceEfectivoTbl implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_DESGLOCE_GENERADOR")
    @SequenceGenerator(name = "TMS_DESGLOCE_GENERADOR", 
                       sequenceName = "TMS_DESGLOCE_EFECTIVO_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name = "DESGLOCE_EFECTIVO_ID", nullable = false)
    private Long desgloceEfectivoId;

    @Column(name = "CANTIDAD")
    private Long cantidad;

    @Column(name = "DENOMINACION")
    private Double denominacion;

    @Column(name = "MONTO")
    private Double monto;

    @Column(name = "REFERENCIA")
    private String referencia;

    @Column(name = "CREADO_POR", nullable = false)
    private Long creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    @Column(name = "RECOLECCION_ID", nullable = false)
    private Long recoleccionId;
    
    /** Creates a new instance of TmsDesgloceEfectivoTbl */
    public TmsDesgloceEfectivoTbl() {
    }

    /**
     * Gets the desgloceEfectivoId of this TmsDesgloceEfectivoTbl.
     * @return the desgloceEfectivoId
     */
    public Long getDesgloceEfectivoId() {
        return this.desgloceEfectivoId;
    }

    /**
     * Sets the desgloceEfectivoId of this TmsDesgloceEfectivoTbl to the specified value.
     * @param desgloceEfectivoId the new desgloceEfectivoId
     */
    public void setDesgloceEfectivoId(Long desgloceEfectivoId) {
        this.desgloceEfectivoId = desgloceEfectivoId;
    }

    /**
     * Gets the cantidad of this TmsDesgloceEfectivoTbl.
     * @return the cantidad
     */
    public Long getCantidad() {
        return this.cantidad;
    }

    /**
     * Sets the cantidad of this TmsDesgloceEfectivoTbl to the specified value.
     * @param cantidad the new cantidad
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the denominacion of this TmsDesgloceEfectivoTbl.
     * @return the denominacion
     */
    public Double getDenominacion() {
        return this.denominacion;
    }

    /**
     * Sets the denominacion of this TmsDesgloceEfectivoTbl to the specified value.
     * @param denominacion the new denominacion
     */
    public void setDenominacion(Double denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * Gets the monto of this TmsDesgloceEfectivoTbl.
     * @return the monto
     */
    public Double getMonto() {
        return this.monto;
    }

    /**
     * Sets the monto of this TmsDesgloceEfectivoTbl to the specified value.
     * @param monto the new monto
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Gets the referencia of this TmsDesgloceEfectivoTbl.
     * @return the referencia
     */
    public String getReferencia() {
        return this.referencia;
    }

    /**
     * Sets the referencia of this TmsDesgloceEfectivoTbl to the specified value.
     * @param referencia the new referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Gets the creadoPor of this TmsDesgloceEfectivoTbl.
     * @return the creadoPor
     */
    public Long getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsDesgloceEfectivoTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsDesgloceEfectivoTbl.
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsDesgloceEfectivoTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsDesgloceEfectivoTbl.
     * @return the ultimaActualizacionPor
     */
    public Long getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsDesgloceEfectivoTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsDesgloceEfectivoTbl.
     * @return the ultimaFechaActualizacion
     */
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsDesgloceEfectivoTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recoleccionId of this TmsDesgloceEfectivoTbl.
     * @return the recoleccionId
     */
    public Long getRecoleccionId() {
        return this.recoleccionId;
    }

    /**
     * Sets the recoleccionId of this TmsDesgloceEfectivoTbl to the specified value.
     * @param recoleccionId the new recoleccionId
     */
    public void setRecoleccionId(Long recoleccionId) {
        this.recoleccionId = recoleccionId;
    }   
}