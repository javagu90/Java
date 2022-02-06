/*
 * TmsServiciosTbl.java
 *
 * Created on 2 de octubre de 2007, 07:50 PM
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
 * Entity class TmsServiciosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_SERVICIOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsServiciosTbl.findByServicioId", query = "SELECT t FROM TmsServiciosTbl t WHERE t.servicioId = :servicioId"),
        @NamedQuery(name = "TmsServiciosTbl.findByServicioClave", query = "SELECT t FROM TmsServiciosTbl t WHERE t.servicioClave = :servicioClave"),
        @NamedQuery(name = "TmsServiciosTbl.findByServicioNumero", query = "SELECT t FROM TmsServiciosTbl t WHERE t.servicioNumero = :servicioNumero"),
        @NamedQuery(name = "TmsServiciosTbl.findByServicioNombre", query = "SELECT t FROM TmsServiciosTbl t WHERE t.servicioNombre = :servicioNombre"),
        @NamedQuery(name = "TmsServiciosTbl.findByDescripcion", query = "SELECT t FROM TmsServiciosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional1", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional2", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional3", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional4", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional5", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional6", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional7", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional8", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional9", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsServiciosTbl.findByAdicional10", query = "SELECT t FROM TmsServiciosTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsServiciosTbl.findByHabilitado", query = "SELECT t FROM TmsServiciosTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsServiciosTbl.findByCreadoPor", query = "SELECT t FROM TmsServiciosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsServiciosTbl.findByFechaCreacion", query = "SELECT t FROM TmsServiciosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsServiciosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsServiciosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsServiciosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsServiciosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsServiciosTbl implements Serializable {

    @Id
    @Column(name = "SERVICIO_ID", nullable = false)
    private BigDecimal servicioId;

    @Column(name = "SERVICIO_CLAVE", nullable = false)
    private String servicioClave;

    @Column(name = "SERVICIO_NUMERO")
    private String servicioNumero;

    @Column(name = "SERVICIO_NOMBRE", nullable = false)
    private String servicioNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

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

    @Column(name = "ADICIONAL6")
    private String adicional6;

    @Column(name = "ADICIONAL7")
    private String adicional7;

    @Column(name = "ADICIONAL8")
    private String adicional8;

    @Column(name = "ADICIONAL9")
    private String adicional9;

    @Column(name = "ADICIONAL10")
    private String adicional10;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioId")
    private Collection<TmsPagosActAdicionalesTbl> tmsPagosActAdicionalesTblCollection;
    
    /** Creates a new instance of TmsServiciosTbl */
    public TmsServiciosTbl() {
    }

    /**
     * Creates a new instance of TmsServiciosTbl with the specified values.
     * @param servicioId the servicioId of the TmsServiciosTbl
     */
    public TmsServiciosTbl(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Creates a new instance of TmsServiciosTbl with the specified values.
     * @param servicioId the servicioId of the TmsServiciosTbl
     * @param servicioClave the servicioClave of the TmsServiciosTbl
     * @param servicioNombre the servicioNombre of the TmsServiciosTbl
     * @param habilitado the habilitado of the TmsServiciosTbl
     * @param creadoPor the creadoPor of the TmsServiciosTbl
     * @param fechaCreacion the fechaCreacion of the TmsServiciosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsServiciosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsServiciosTbl
     */
    public TmsServiciosTbl(BigDecimal servicioId, String servicioClave, String servicioNombre, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.servicioId = servicioId;
        this.servicioClave = servicioClave;
        this.servicioNombre = servicioNombre;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the servicioId of this TmsServiciosTbl.
     * @return the servicioId
     */
    public BigDecimal getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsServiciosTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Gets the servicioClave of this TmsServiciosTbl.
     * @return the servicioClave
     */
    public String getServicioClave() {
        return this.servicioClave;
    }

    /**
     * Sets the servicioClave of this TmsServiciosTbl to the specified value.
     * @param servicioClave the new servicioClave
     */
    public void setServicioClave(String servicioClave) {
        this.servicioClave = servicioClave;
    }

    /**
     * Gets the servicioNumero of this TmsServiciosTbl.
     * @return the servicioNumero
     */
    public String getServicioNumero() {
        return this.servicioNumero;
    }

    /**
     * Sets the servicioNumero of this TmsServiciosTbl to the specified value.
     * @param servicioNumero the new servicioNumero
     */
    public void setServicioNumero(String servicioNumero) {
        this.servicioNumero = servicioNumero;
    }

    /**
     * Gets the servicioNombre of this TmsServiciosTbl.
     * @return the servicioNombre
     */
    public String getServicioNombre() {
        return this.servicioNombre;
    }

    /**
     * Sets the servicioNombre of this TmsServiciosTbl to the specified value.
     * @param servicioNombre the new servicioNombre
     */
    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    /**
     * Gets the descripcion of this TmsServiciosTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsServiciosTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the adicional1 of this TmsServiciosTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsServiciosTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsServiciosTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsServiciosTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsServiciosTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsServiciosTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsServiciosTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsServiciosTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsServiciosTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsServiciosTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsServiciosTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsServiciosTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsServiciosTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsServiciosTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsServiciosTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsServiciosTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsServiciosTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsServiciosTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsServiciosTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsServiciosTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the habilitado of this TmsServiciosTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsServiciosTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsServiciosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsServiciosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsServiciosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsServiciosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsServiciosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsServiciosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsServiciosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsServiciosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsPagosActAdicionalesTblCollection of this TmsServiciosTbl.
     * @return the tmsPagosActAdicionalesTblCollection
     */
    public Collection<TmsPagosActAdicionalesTbl> getTmsPagosActAdicionalesTblCollection() {
        return this.tmsPagosActAdicionalesTblCollection;
    }

    /**
     * Sets the tmsPagosActAdicionalesTblCollection of this TmsServiciosTbl to the specified value.
     * @param tmsPagosActAdicionalesTblCollection the new tmsPagosActAdicionalesTblCollection
     */
    public void setTmsPagosActAdicionalesTblCollection(Collection<TmsPagosActAdicionalesTbl> tmsPagosActAdicionalesTblCollection) {
        this.tmsPagosActAdicionalesTblCollection = tmsPagosActAdicionalesTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.servicioId != null ? this.servicioId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsServiciosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsServiciosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsServiciosTbl)) {
            return false;
        }
        TmsServiciosTbl other = (TmsServiciosTbl)object;
        if (this.servicioId != other.servicioId && (this.servicioId == null || !this.servicioId.equals(other.servicioId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsactividadesadicionales.entidad.TmsServiciosTbl[servicioId=" + servicioId + "]";
    }
    
}
