/*
 * TmsFormatosBoletoTbl.java
 *
 * Created on 3 de septiembre de 2007, 09:28 PM
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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsFormatosBoletoTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_FORMATOS_BOLETO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByFormatoBoletoId", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.formatoBoletoId = :formatoBoletoId"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByFormatoNombre", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.formatoNombre = :formatoNombre"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByDescripcion", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByValido", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.valido = :valido"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByAdicional1", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByAdicional2", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByAdicional3", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByAdicional4", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByAdicional5", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByCreadoPor", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByFechaCreacion", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsFormatosBoletoTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsFormatosBoletoTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsFormatosBoletoTbl implements Serializable {

    @Id
    @Column(name = "FORMATO_BOLETO_ID", nullable = false)
    private BigDecimal formatoBoletoId;

    @Column(name = "FORMATO_NOMBRE", nullable = false)
    private String formatoNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "VALIDO")
    private String valido;

    @Column(name = "ADICIONAL1")
    private String adicional1;

    @Column(name = "ADICIONAL2")
    private String adicional2;

    @Column(name = "ADICIONAL3")
    private String adicional3;

    @Column(name = "ADICIONAL4")
    private String adicional4;

    @Column(name = "ADICIONAL5")
    private String adicional5;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;
    
    /** Creates a new instance of TmsFormatosBoletoTbl */
    public TmsFormatosBoletoTbl() {
    }

    /**
     * Creates a new instance of TmsFormatosBoletoTbl with the specified values.
     * @param formatoBoletoId the formatoBoletoId of the TmsFormatosBoletoTbl
     */
    public TmsFormatosBoletoTbl(BigDecimal formatoBoletoId) {
        this.formatoBoletoId = formatoBoletoId;
    }

    /**
     * Creates a new instance of TmsFormatosBoletoTbl with the specified values.
     * @param formatoBoletoId the formatoBoletoId of the TmsFormatosBoletoTbl
     * @param formatoNombre the formatoNombre of the TmsFormatosBoletoTbl
     * @param creadoPor the creadoPor of the TmsFormatosBoletoTbl
     * @param fechaCreacion the fechaCreacion of the TmsFormatosBoletoTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsFormatosBoletoTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsFormatosBoletoTbl
     */
    public TmsFormatosBoletoTbl(BigDecimal formatoBoletoId, String formatoNombre, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.formatoBoletoId = formatoBoletoId;
        this.formatoNombre = formatoNombre;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the formatoBoletoId of this TmsFormatosBoletoTbl.
     * @return the formatoBoletoId
     */
    public BigDecimal getFormatoBoletoId() {
        return this.formatoBoletoId;
    }

    /**
     * Sets the formatoBoletoId of this TmsFormatosBoletoTbl to the specified value.
     * @param formatoBoletoId the new formatoBoletoId
     */
    public void setFormatoBoletoId(BigDecimal formatoBoletoId) {
        this.formatoBoletoId = formatoBoletoId;
    }

    /**
     * Gets the formatoNombre of this TmsFormatosBoletoTbl.
     * @return the formatoNombre
     */
    public String getFormatoNombre() {
        return this.formatoNombre;
    }

    /**
     * Sets the formatoNombre of this TmsFormatosBoletoTbl to the specified value.
     * @param formatoNombre the new formatoNombre
     */
    public void setFormatoNombre(String formatoNombre) {
        this.formatoNombre = formatoNombre;
    }

    /**
     * Gets the descripcion of this TmsFormatosBoletoTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsFormatosBoletoTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the valido of this TmsFormatosBoletoTbl.
     * @return the valido
     */
    public String getValido() {
        return this.valido;
    }

    /**
     * Sets the valido of this TmsFormatosBoletoTbl to the specified value.
     * @param valido the new valido
     */
    public void setValido(String valido) {
        this.valido = valido;
    }

    /**
     * Gets the adicional1 of this TmsFormatosBoletoTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsFormatosBoletoTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsFormatosBoletoTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsFormatosBoletoTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsFormatosBoletoTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsFormatosBoletoTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsFormatosBoletoTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsFormatosBoletoTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsFormatosBoletoTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsFormatosBoletoTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsFormatosBoletoTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsFormatosBoletoTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsFormatosBoletoTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsFormatosBoletoTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsFormatosBoletoTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsFormatosBoletoTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsFormatosBoletoTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsFormatosBoletoTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.formatoBoletoId != null ? this.formatoBoletoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsFormatosBoletoTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsFormatosBoletoTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsFormatosBoletoTbl)) {
            return false;
        }
        TmsFormatosBoletoTbl other = (TmsFormatosBoletoTbl)object;
        if (this.formatoBoletoId != other.formatoBoletoId && (this.formatoBoletoId == null || !this.formatoBoletoId.equals(other.formatoBoletoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.entidad.TmsFormatosBoletoTbl[formatoBoletoId=" + formatoBoletoId + "]";
    }
    
}
