/*
 * TmsDatosAdicionalesTbl.java
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
 * Entity class TmsDatosAdicionalesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_DATOS_ADICIONALES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByDatoAdicionalId", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.datoAdicionalId = :datoAdicionalId"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByDatoNombre", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.datoNombre = :datoNombre"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByDescripcionDato", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.descripcionDato = :descripcionDato"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByHabilitado", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByCreadoPor", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByFechaCreacion", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsDatosAdicionalesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsDatosAdicionalesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsDatosAdicionalesTbl implements Serializable {

    @Id
    @Column(name = "DATO_ADICIONAL_ID", nullable = false)
    private BigDecimal datoAdicionalId;

    @Column(name = "DATO_NOMBRE", nullable = false)
    private String datoNombre;

    @Column(name = "DESCRIPCION_DATO", nullable = false)
    private String descripcionDato;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datoAdicionalId")
    private Collection<TmsActDatosAdicionalesTbl> tmsActDatosAdicionalesTblCollection;
    
    /** Creates a new instance of TmsDatosAdicionalesTbl */
    public TmsDatosAdicionalesTbl() {
    }

    /**
     * Creates a new instance of TmsDatosAdicionalesTbl with the specified values.
     * @param datoAdicionalId the datoAdicionalId of the TmsDatosAdicionalesTbl
     */
    public TmsDatosAdicionalesTbl(BigDecimal datoAdicionalId) {
        this.datoAdicionalId = datoAdicionalId;
    }

    /**
     * Creates a new instance of TmsDatosAdicionalesTbl with the specified values.
     * @param datoAdicionalId the datoAdicionalId of the TmsDatosAdicionalesTbl
     * @param datoNombre the datoNombre of the TmsDatosAdicionalesTbl
     * @param descripcionDato the descripcionDato of the TmsDatosAdicionalesTbl
     * @param habilitado the habilitado of the TmsDatosAdicionalesTbl
     * @param creadoPor the creadoPor of the TmsDatosAdicionalesTbl
     * @param fechaCreacion the fechaCreacion of the TmsDatosAdicionalesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsDatosAdicionalesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsDatosAdicionalesTbl
     */
    public TmsDatosAdicionalesTbl(BigDecimal datoAdicionalId, String datoNombre, String descripcionDato, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.datoAdicionalId = datoAdicionalId;
        this.datoNombre = datoNombre;
        this.descripcionDato = descripcionDato;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the datoAdicionalId of this TmsDatosAdicionalesTbl.
     * @return the datoAdicionalId
     */
    public BigDecimal getDatoAdicionalId() {
        return this.datoAdicionalId;
    }

    /**
     * Sets the datoAdicionalId of this TmsDatosAdicionalesTbl to the specified value.
     * @param datoAdicionalId the new datoAdicionalId
     */
    public void setDatoAdicionalId(BigDecimal datoAdicionalId) {
        this.datoAdicionalId = datoAdicionalId;
    }

    /**
     * Gets the datoNombre of this TmsDatosAdicionalesTbl.
     * @return the datoNombre
     */
    public String getDatoNombre() {
        return this.datoNombre;
    }

    /**
     * Sets the datoNombre of this TmsDatosAdicionalesTbl to the specified value.
     * @param datoNombre the new datoNombre
     */
    public void setDatoNombre(String datoNombre) {
        this.datoNombre = datoNombre;
    }

    /**
     * Gets the descripcionDato of this TmsDatosAdicionalesTbl.
     * @return the descripcionDato
     */
    public String getDescripcionDato() {
        return this.descripcionDato;
    }

    /**
     * Sets the descripcionDato of this TmsDatosAdicionalesTbl to the specified value.
     * @param descripcionDato the new descripcionDato
     */
    public void setDescripcionDato(String descripcionDato) {
        this.descripcionDato = descripcionDato;
    }

    /**
     * Gets the habilitado of this TmsDatosAdicionalesTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsDatosAdicionalesTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsDatosAdicionalesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsDatosAdicionalesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsDatosAdicionalesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsDatosAdicionalesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsDatosAdicionalesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsDatosAdicionalesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsDatosAdicionalesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsDatosAdicionalesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsActDatosAdicionalesTblCollection of this TmsDatosAdicionalesTbl.
     * @return the tmsActDatosAdicionalesTblCollection
     */
    public Collection<TmsActDatosAdicionalesTbl> getTmsActDatosAdicionalesTblCollection() {
        return this.tmsActDatosAdicionalesTblCollection;
    }

    /**
     * Sets the tmsActDatosAdicionalesTblCollection of this TmsDatosAdicionalesTbl to the specified value.
     * @param tmsActDatosAdicionalesTblCollection the new tmsActDatosAdicionalesTblCollection
     */
    public void setTmsActDatosAdicionalesTblCollection(Collection<TmsActDatosAdicionalesTbl> tmsActDatosAdicionalesTblCollection) {
        this.tmsActDatosAdicionalesTblCollection = tmsActDatosAdicionalesTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.datoAdicionalId != null ? this.datoAdicionalId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsDatosAdicionalesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsDatosAdicionalesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsDatosAdicionalesTbl)) {
            return false;
        }
        TmsDatosAdicionalesTbl other = (TmsDatosAdicionalesTbl)object;
        if (this.datoAdicionalId != other.datoAdicionalId && (this.datoAdicionalId == null || !this.datoAdicionalId.equals(other.datoAdicionalId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsactividadesadicionales.entidad.TmsDatosAdicionalesTbl[datoAdicionalId=" + datoAdicionalId + "]";
    }
    
}
