/*
 * TmsActDatosAdicionalesTbl.java
 *
 * Created on 2 de octubre de 2007, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsActDatosAdicionalesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ACT_DATOS_ADICIONALES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByActividadDatoId", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.actividadDatoId = :actividadDatoId"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByGuardarValorEn", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.guardarValorEn = :guardarValorEn"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByHabilitado", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByCreadoPor", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByFechaCreacion", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsActDatosAdicionalesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsActDatosAdicionalesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsActDatosAdicionalesTbl implements Serializable {

    @Id
    @Column(name = "ACTIVIDAD_DATO_ID", nullable = false)
    private BigDecimal actividadDatoId;

    @Column(name = "GUARDAR_VALOR_EN", nullable = false)
    private String guardarValorEn;

    @Column(name = "HABILITADO", nullable = false)
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @JoinColumn(name = "TIPO_ACTIVIDAD_ADICIONAL_ID", referencedColumnName = "TIPO_ACTIVIDAD_ADICIONAL_ID")
    @ManyToOne
    private TmsActAdicionalesTbl tipoActividadAdicionalId;

    @JoinColumn(name = "DATO_ADICIONAL_ID", referencedColumnName = "DATO_ADICIONAL_ID")
    @ManyToOne
    private TmsDatosAdicionalesTbl datoAdicionalId;
    
    /** Creates a new instance of TmsActDatosAdicionalesTbl */
    public TmsActDatosAdicionalesTbl() {
    }

    /**
     * Creates a new instance of TmsActDatosAdicionalesTbl with the specified values.
     * @param actividadDatoId the actividadDatoId of the TmsActDatosAdicionalesTbl
     */
    public TmsActDatosAdicionalesTbl(BigDecimal actividadDatoId) {
        this.actividadDatoId = actividadDatoId;
    }

    /**
     * Creates a new instance of TmsActDatosAdicionalesTbl with the specified values.
     * @param actividadDatoId the actividadDatoId of the TmsActDatosAdicionalesTbl
     * @param guardarValorEn the guardarValorEn of the TmsActDatosAdicionalesTbl
     * @param habilitado the habilitado of the TmsActDatosAdicionalesTbl
     * @param creadoPor the creadoPor of the TmsActDatosAdicionalesTbl
     * @param fechaCreacion the fechaCreacion of the TmsActDatosAdicionalesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsActDatosAdicionalesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsActDatosAdicionalesTbl
     */
    public TmsActDatosAdicionalesTbl(BigDecimal actividadDatoId, String guardarValorEn, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.actividadDatoId = actividadDatoId;
        this.guardarValorEn = guardarValorEn;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the actividadDatoId of this TmsActDatosAdicionalesTbl.
     * @return the actividadDatoId
     */
    public BigDecimal getActividadDatoId() {
        return this.actividadDatoId;
    }

    /**
     * Sets the actividadDatoId of this TmsActDatosAdicionalesTbl to the specified value.
     * @param actividadDatoId the new actividadDatoId
     */
    public void setActividadDatoId(BigDecimal actividadDatoId) {
        this.actividadDatoId = actividadDatoId;
    }

    /**
     * Gets the guardarValorEn of this TmsActDatosAdicionalesTbl.
     * @return the guardarValorEn
     */
    public String getGuardarValorEn() {
        return this.guardarValorEn;
    }

    /**
     * Sets the guardarValorEn of this TmsActDatosAdicionalesTbl to the specified value.
     * @param guardarValorEn the new guardarValorEn
     */
    public void setGuardarValorEn(String guardarValorEn) {
        this.guardarValorEn = guardarValorEn;
    }

    /**
     * Gets the habilitado of this TmsActDatosAdicionalesTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsActDatosAdicionalesTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsActDatosAdicionalesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsActDatosAdicionalesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsActDatosAdicionalesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsActDatosAdicionalesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsActDatosAdicionalesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsActDatosAdicionalesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsActDatosAdicionalesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsActDatosAdicionalesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tipoActividadAdicionalId of this TmsActDatosAdicionalesTbl.
     * @return the tipoActividadAdicionalId
     */
    public TmsActAdicionalesTbl getTipoActividadAdicionalId() {
        return this.tipoActividadAdicionalId;
    }

    /**
     * Sets the tipoActividadAdicionalId of this TmsActDatosAdicionalesTbl to the specified value.
     * @param tipoActividadAdicionalId the new tipoActividadAdicionalId
     */
    public void setTipoActividadAdicionalId(TmsActAdicionalesTbl tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
    }

    /**
     * Gets the datoAdicionalId of this TmsActDatosAdicionalesTbl.
     * @return the datoAdicionalId
     */
    public TmsDatosAdicionalesTbl getDatoAdicionalId() {
        return this.datoAdicionalId;
    }

    /**
     * Sets the datoAdicionalId of this TmsActDatosAdicionalesTbl to the specified value.
     * @param datoAdicionalId the new datoAdicionalId
     */
    public void setDatoAdicionalId(TmsDatosAdicionalesTbl datoAdicionalId) {
        this.datoAdicionalId = datoAdicionalId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.actividadDatoId != null ? this.actividadDatoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsActDatosAdicionalesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsActDatosAdicionalesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsActDatosAdicionalesTbl)) {
            return false;
        }
        TmsActDatosAdicionalesTbl other = (TmsActDatosAdicionalesTbl)object;
        if (this.actividadDatoId != other.actividadDatoId && (this.actividadDatoId == null || !this.actividadDatoId.equals(other.actividadDatoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsactividadesadicionales.entidad.TmsActDatosAdicionalesTbl[actividadDatoId=" + actividadDatoId + "]";
    }
    
}
