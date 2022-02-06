/*
 * TmsVtaTiposPagoTbl.java
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
 * Entity class TmsVtaTiposPagoTbl
 * 
 * @author ocruz
 */
@Entity
@Table(name = "TMS_VTA_TIPOS_PAGO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findAll", query = "SELECT t FROM TmsVtaTiposPagoTbl t"),
        @NamedQuery(name = "TmsVtaTiposPagoTbl.findByTipoPagoId", query = "SELECT t FROM TmsVtaTiposPagoTbl t WHERE t.tipoPagoId = :tipoPagoId")
    })
public class TmsVtaTiposPagoTbl implements Serializable {

    @Id
    @Column(name = "TIPO_PAGO_ID", nullable = false)
    private Long tipoPagoId;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CLAVE", nullable = false)
    private String clave;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_DESDE", nullable = false)
    private Timestamp fechaDesde;

    @Column(name = "FECHA_HASTA")
    private Timestamp fechaHasta;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    /** Creates a new instance of TmsVtaTiposPagoTbl */
    public TmsVtaTiposPagoTbl() {
    }

    /**
     * Gets the tipoPagoId of this TmsVtaTiposPagoTbl.
     * @return the tipoPagoId
     */
    public Long getTipoPagoId() {
        return this.tipoPagoId;
    }

    /**
     * Sets the tipoPagoId of this TmsVtaTiposPagoTbl to the specified value.
     * @param tipoPagoId the new tipoPagoId
     */
    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    /**
     * Gets the nombre of this TmsVtaTiposPagoTbl.
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre of this TmsVtaTiposPagoTbl to the specified value.
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the clave of this TmsVtaTiposPagoTbl.
     * @return the clave
     */
    public String getClave() {
        return this.clave;
    }

    /**
     * Sets the clave of this TmsVtaTiposPagoTbl to the specified value.
     * @param clave the new clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Gets the descripcion of this TmsVtaTiposPagoTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsVtaTiposPagoTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the fechaDesde of this TmsVtaTiposPagoTbl.
     * @return the fechaDesde
     */
    public Timestamp getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsVtaTiposPagoTbl.
     * @return the fechaHasta
     */
    public Timestamp getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this TmsVtaTiposPagoTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsVtaTiposPagoTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsVtaTiposPagoTbl.
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsVtaTiposPagoTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsVtaTiposPagoTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsVtaTiposPagoTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsVtaTiposPagoTbl.
     * @return the ultimaFechaActualizacion
     */
    public Timestamp getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsVtaTiposPagoTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }    
}
