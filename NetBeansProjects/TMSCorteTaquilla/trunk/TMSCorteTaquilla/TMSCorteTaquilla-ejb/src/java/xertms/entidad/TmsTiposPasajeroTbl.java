/*
 * TmsTiposPasajeroTbl.java
 *
 * Created on 16 de agosto de 2007, 11:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
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
 * Entity class TmsTiposPasajeroTbl
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_TIPOS_PASAJERO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposPasajeroTbl.findAll", query = "SELECT t FROM TmsTiposPasajeroTbl t"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByTipoPasajeroId", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.tipoPasajeroId = :tipoPasajeroId")
    })
public class TmsTiposPasajeroTbl implements Serializable {

    @Id
    @Column(name = "TIPO_PASAJERO_ID", nullable = false)
    private Long tipoPasajeroId;

    @Column(name = "NOMBRE_TIPO", nullable = false)
    private String nombreTipo;

    @Column(name = "LETRA_TIPO")
    private String letraTipo;

    @Column(name = "PCT_DESCUENTO", nullable = false)
    private Long pctDescuento;

    @Column(name = "FECHA_DESDE")
    private Timestamp fechaDesde;

    @Column(name = "FECHA_HASTA")
    private Timestamp fechaHasta;

    @Column(name = "CREADO_POR")
    private Integer creadoPor;

    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;
    
    /** Creates a new instance of TmsTiposPasajeroTbl */
    public TmsTiposPasajeroTbl() {
    }

    /**
     * Gets the tipoPasajeroId of this TmsTiposPasajeroTbl.
     * @return the tipoPasajeroId
     */
    public Long getTipoPasajeroId() {
        return this.tipoPasajeroId;
    }

    /**
     * Sets the tipoPasajeroId of this TmsTiposPasajeroTbl to the specified value.
     * @param tipoPasajeroId the new tipoPasajeroId
     */
    public void setTipoPasajeroId(Long tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposPasajeroTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the letraTipo of this TmsTiposPasajeroTbl.
     * @return the letraTipo
     */
    public String getLetraTipo() {
        return this.letraTipo;
    }

    /**
     * Sets the letraTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param letraTipo the new letraTipo
     */
    public void setLetraTipo(String letraTipo) {
        this.letraTipo = letraTipo;
    }

    /**
     * Gets the pctDescuento of this TmsTiposPasajeroTbl.
     * @return the pctDescuento
     */
    public Long getPctDescuento() {
        return this.pctDescuento;
    }

    /**
     * Sets the pctDescuento of this TmsTiposPasajeroTbl to the specified value.
     * @param pctDescuento the new pctDescuento
     */
    public void setPctDescuento(Long pctDescuento) {
        this.pctDescuento = pctDescuento;
    }

    /**
     * Gets the fechaDesde of this TmsTiposPasajeroTbl.
     * @return the fechaDesde
     */
    public Timestamp getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsTiposPasajeroTbl.
     * @return the fechaHasta
     */
    public Timestamp getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this TmsTiposPasajeroTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposPasajeroTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposPasajeroTbl.
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposPasajeroTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl.
     * @return the ultimaFechaActualizacion
     */
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
}