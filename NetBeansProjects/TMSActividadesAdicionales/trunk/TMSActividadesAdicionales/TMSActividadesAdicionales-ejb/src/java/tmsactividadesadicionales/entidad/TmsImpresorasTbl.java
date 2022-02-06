/*
 * TmsImpresorasTbl.java
 *
 * Created on 3 de septiembre de 2007, 09:18 PM
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
 * Entity class TmsImpresorasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_IMPRESORAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsImpresorasTbl.findByImpresoraId", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.impresoraId = :impresoraId"),
        @NamedQuery(name = "TmsImpresorasTbl.findByImpresoraNombre", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.impresoraNombre = :impresoraNombre"),
        @NamedQuery(name = "TmsImpresorasTbl.findByDescripcion", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsImpresorasTbl.findByImpresoraMarca", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.impresoraMarca = :impresoraMarca"),
        @NamedQuery(name = "TmsImpresorasTbl.findByImpresoraModelo", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.impresoraModelo = :impresoraModelo"),
        @NamedQuery(name = "TmsImpresorasTbl.findByImpresoraTipo", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.impresoraTipo = :impresoraTipo"),
        @NamedQuery(name = "TmsImpresorasTbl.findByAdicional1", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsImpresorasTbl.findByAdicional2", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsImpresorasTbl.findByAdicional3", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsImpresorasTbl.findByAdicional4", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsImpresorasTbl.findByAdicional5", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsImpresorasTbl.findByCreadoPor", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsImpresorasTbl.findByFechaCreacion", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsImpresorasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsImpresorasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsImpresorasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsImpresorasTbl implements Serializable {

    @Id
    @Column(name = "IMPRESORA_ID", nullable = false)
    private BigDecimal impresoraId;

    @Column(name = "IMPRESORA_NOMBRE", nullable = false)
    private String impresoraNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "IMPRESORA_MARCA")
    private String impresoraMarca;

    @Column(name = "IMPRESORA_MODELO")
    private String impresoraModelo;

    @Column(name = "IMPRESORA_TIPO")
    private String impresoraTipo;

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
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "impresoraId")
    private Collection<TmsCajasImpresorasTbl> tmsCajasImpresorasTblCollection;
    
    /** Creates a new instance of TmsImpresorasTbl */
    public TmsImpresorasTbl() {
    }

    /**
     * Creates a new instance of TmsImpresorasTbl with the specified values.
     * @param impresoraId the impresoraId of the TmsImpresorasTbl
     */
    public TmsImpresorasTbl(BigDecimal impresoraId) {
        this.impresoraId = impresoraId;
    }

    /**
     * Creates a new instance of TmsImpresorasTbl with the specified values.
     * @param impresoraId the impresoraId of the TmsImpresorasTbl
     * @param impresoraNombre the impresoraNombre of the TmsImpresorasTbl
     * @param creadoPor the creadoPor of the TmsImpresorasTbl
     * @param fechaCreacion the fechaCreacion of the TmsImpresorasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsImpresorasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsImpresorasTbl
     */
    public TmsImpresorasTbl(BigDecimal impresoraId, String impresoraNombre, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.impresoraId = impresoraId;
        this.impresoraNombre = impresoraNombre;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the impresoraId of this TmsImpresorasTbl.
     * @return the impresoraId
     */
    public BigDecimal getImpresoraId() {
        return this.impresoraId;
    }

    /**
     * Sets the impresoraId of this TmsImpresorasTbl to the specified value.
     * @param impresoraId the new impresoraId
     */
    public void setImpresoraId(BigDecimal impresoraId) {
        this.impresoraId = impresoraId;
    }

    /**
     * Gets the impresoraNombre of this TmsImpresorasTbl.
     * @return the impresoraNombre
     */
    public String getImpresoraNombre() {
        return this.impresoraNombre;
    }

    /**
     * Sets the impresoraNombre of this TmsImpresorasTbl to the specified value.
     * @param impresoraNombre the new impresoraNombre
     */
    public void setImpresoraNombre(String impresoraNombre) {
        this.impresoraNombre = impresoraNombre;
    }

    /**
     * Gets the descripcion of this TmsImpresorasTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsImpresorasTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the impresoraMarca of this TmsImpresorasTbl.
     * @return the impresoraMarca
     */
    public String getImpresoraMarca() {
        return this.impresoraMarca;
    }

    /**
     * Sets the impresoraMarca of this TmsImpresorasTbl to the specified value.
     * @param impresoraMarca the new impresoraMarca
     */
    public void setImpresoraMarca(String impresoraMarca) {
        this.impresoraMarca = impresoraMarca;
    }

    /**
     * Gets the impresoraModelo of this TmsImpresorasTbl.
     * @return the impresoraModelo
     */
    public String getImpresoraModelo() {
        return this.impresoraModelo;
    }

    /**
     * Sets the impresoraModelo of this TmsImpresorasTbl to the specified value.
     * @param impresoraModelo the new impresoraModelo
     */
    public void setImpresoraModelo(String impresoraModelo) {
        this.impresoraModelo = impresoraModelo;
    }

    /**
     * Gets the impresoraTipo of this TmsImpresorasTbl.
     * @return the impresoraTipo
     */
    public String getImpresoraTipo() {
        return this.impresoraTipo;
    }

    /**
     * Sets the impresoraTipo of this TmsImpresorasTbl to the specified value.
     * @param impresoraTipo the new impresoraTipo
     */
    public void setImpresoraTipo(String impresoraTipo) {
        this.impresoraTipo = impresoraTipo;
    }

    /**
     * Gets the adicional1 of this TmsImpresorasTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsImpresorasTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsImpresorasTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsImpresorasTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsImpresorasTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsImpresorasTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsImpresorasTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsImpresorasTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsImpresorasTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsImpresorasTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsImpresorasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsImpresorasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsImpresorasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsImpresorasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsImpresorasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsImpresorasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsImpresorasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsImpresorasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsCajasImpresorasTblCollection of this TmsImpresorasTbl.
     * @return the tmsCajasImpresorasTblCollection
     */
    public Collection<TmsCajasImpresorasTbl> getTmsCajasImpresorasTblCollection() {
        return this.tmsCajasImpresorasTblCollection;
    }

    /**
     * Sets the tmsCajasImpresorasTblCollection of this TmsImpresorasTbl to the specified value.
     * @param tmsCajasImpresorasTblCollection the new tmsCajasImpresorasTblCollection
     */
    public void setTmsCajasImpresorasTblCollection(Collection<TmsCajasImpresorasTbl> tmsCajasImpresorasTblCollection) {
        this.tmsCajasImpresorasTblCollection = tmsCajasImpresorasTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.impresoraId != null ? this.impresoraId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsImpresorasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsImpresorasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsImpresorasTbl)) {
            return false;
        }
        TmsImpresorasTbl other = (TmsImpresorasTbl)object;
        if (this.impresoraId != other.impresoraId && (this.impresoraId == null || !this.impresoraId.equals(other.impresoraId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.entidad.TmsImpresorasTbl[impresoraId=" + impresoraId + "]";
    }
    
}
