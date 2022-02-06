/*
 * TmsRecaudacionGastosTbl.java
 *
 * Created on 13 de septiembre de 2007, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
 * Entity class TmsRecaudacionGastosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RECAUDACION_GASTOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByRecaudacionGastoId", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.recaudacionGastoId = :recaudacionGastoId"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByRecaudacionGastoNombre", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.recaudacionGastoNombre = :recaudacionGastoNombre"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByDescripcion", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByRecaudacionGastoAplica", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.recaudacionGastoAplica = :recaudacionGastoAplica"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByRecaudacionGastoAfectaCaja", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.recaudacionGastoAfectaCaja = :recaudacionGastoAfectaCaja"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByRecaudacionGastoComprobable", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.recaudacionGastoComprobable = :recaudacionGastoComprobable"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByHabilitado", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional1", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional2", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional3", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional4", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional5", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional6", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional7", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional8", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional9", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByAdicional10", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByCreadoPor", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByFechaCreacion", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRecaudacionGastosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRecaudacionGastosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRecaudacionGastosTbl implements Serializable {

    @Id
    @Column(name = "RECAUDACION_GASTO_ID", nullable = false)
    private BigDecimal recaudacionGastoId;

    @Column(name = "RECAUDACION_GASTO_NOMBRE")
    private String recaudacionGastoNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "RECAUDACION_GASTO_CARGA")
    private String recaudacionGastoCarga;

    @Column(name = "RECAUDACION_GASTO_APLICA")
    private String recaudacionGastoAplica;

    @Column(name = "RECAUDACION_GASTO_AFECTA_CAJA")
    private String recaudacionGastoAfectaCaja;

    @Column(name = "RECAUDACION_GASTO_COMPROBABLE", nullable = false)
    private String recaudacionGastoComprobable;

    @Column(name = "HABILITADO", nullable = false)
    private String habilitado;

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

    @OneToMany(mappedBy = "recaudacionGastoId")
    private Collection<TmsRecaudacionLineasTbl> tmsRecaudacionLineasTblCollection;
    
    /** Creates a new instance of TmsRecaudacionGastosTbl */
    public TmsRecaudacionGastosTbl() {
    }

    /**
     * Creates a new instance of TmsRecaudacionGastosTbl with the specified values.
     * @param recaudacionGastoId the recaudacionGastoId of the TmsRecaudacionGastosTbl
     */
    public TmsRecaudacionGastosTbl(BigDecimal recaudacionGastoId) {
        this.recaudacionGastoId = recaudacionGastoId;
    }

    /**
     * Creates a new instance of TmsRecaudacionGastosTbl with the specified values.
     * @param recaudacionGastoId the recaudacionGastoId of the TmsRecaudacionGastosTbl
     * @param recaudacionGastoComprobable the recaudacionGastoComprobable of the TmsRecaudacionGastosTbl
     * @param habilitado the habilitado of the TmsRecaudacionGastosTbl
     * @param creadoPor the creadoPor of the TmsRecaudacionGastosTbl
     * @param fechaCreacion the fechaCreacion of the TmsRecaudacionGastosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRecaudacionGastosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRecaudacionGastosTbl
     */
    public TmsRecaudacionGastosTbl(BigDecimal recaudacionGastoId, String recaudacionGastoComprobable, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.recaudacionGastoId = recaudacionGastoId;
        this.recaudacionGastoComprobable = recaudacionGastoComprobable;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recaudacionGastoId of this TmsRecaudacionGastosTbl.
     * @return the recaudacionGastoId
     */
    public BigDecimal getRecaudacionGastoId() {
        return this.recaudacionGastoId;
    }

    /**
     * Sets the recaudacionGastoId of this TmsRecaudacionGastosTbl to the specified value.
     * @param recaudacionGastoId the new recaudacionGastoId
     */
    public void setRecaudacionGastoId(BigDecimal recaudacionGastoId) {
        this.recaudacionGastoId = recaudacionGastoId;
    }

    /**
     * Gets the recaudacionGastoNombre of this TmsRecaudacionGastosTbl.
     * @return the recaudacionGastoNombre
     */
    public String getRecaudacionGastoNombre() {
        return this.recaudacionGastoNombre;
    }

    /**
     * Sets the recaudacionGastoNombre of this TmsRecaudacionGastosTbl to the specified value.
     * @param recaudacionGastoNombre the new recaudacionGastoNombre
     */
    public void setRecaudacionGastoNombre(String recaudacionGastoNombre) {
        this.recaudacionGastoNombre = recaudacionGastoNombre;
    }

    /**
     * Gets the descripcion of this TmsRecaudacionGastosTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsRecaudacionGastosTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the recaudacionGastoAplica of this TmsRecaudacionGastosTbl.
     * @return the recaudacionGastoAplica
     */
    public String getRecaudacionGastoAplica() {
        return this.recaudacionGastoAplica;
    }

    /**
     * Sets the recaudacionGastoAplica of this TmsRecaudacionGastosTbl to the specified value.
     * @param recaudacionGastoAplica the new recaudacionGastoAplica
     */
    public void setRecaudacionGastoAplica(String recaudacionGastoAplica) {
        this.recaudacionGastoAplica = recaudacionGastoAplica;
    }

    /**
     * Gets the recaudacionGastoAfectaCaja of this TmsRecaudacionGastosTbl.
     * @return the recaudacionGastoAfectaCaja
     */
    public String getRecaudacionGastoAfectaCaja() {
        return this.recaudacionGastoAfectaCaja;
    }

    /**
     * Sets the recaudacionGastoAfectaCaja of this TmsRecaudacionGastosTbl to the specified value.
     * @param recaudacionGastoAfectaCaja the new recaudacionGastoAfectaCaja
     */
    public void setRecaudacionGastoAfectaCaja(String recaudacionGastoAfectaCaja) {
        this.recaudacionGastoAfectaCaja = recaudacionGastoAfectaCaja;
    }

    /**
     * Gets the recaudacionGastoComprobable of this TmsRecaudacionGastosTbl.
     * @return the recaudacionGastoComprobable
     */
    public String getRecaudacionGastoComprobable() {
        return this.recaudacionGastoComprobable;
    }

    /**
     * Sets the recaudacionGastoComprobable of this TmsRecaudacionGastosTbl to the specified value.
     * @param recaudacionGastoComprobable the new recaudacionGastoComprobable
     */
    public void setRecaudacionGastoComprobable(String recaudacionGastoComprobable) {
        this.recaudacionGastoComprobable = recaudacionGastoComprobable;
    }

    /**
     * Gets the habilitado of this TmsRecaudacionGastosTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsRecaudacionGastosTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the adicional1 of this TmsRecaudacionGastosTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsRecaudacionGastosTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsRecaudacionGastosTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsRecaudacionGastosTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsRecaudacionGastosTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsRecaudacionGastosTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsRecaudacionGastosTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsRecaudacionGastosTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsRecaudacionGastosTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsRecaudacionGastosTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsRecaudacionGastosTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the creadoPor of this TmsRecaudacionGastosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRecaudacionGastosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRecaudacionGastosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRecaudacionGastosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRecaudacionGastosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRecaudacionGastosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRecaudacionGastosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRecaudacionGastosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsRecaudacionLineasTblCollection of this TmsRecaudacionGastosTbl.
     * @return the tmsRecaudacionLineasTblCollection
     */
    public Collection<TmsRecaudacionLineasTbl> getTmsRecaudacionLineasTblCollection() {
        return this.tmsRecaudacionLineasTblCollection;
    }

    /**
     * Sets the tmsRecaudacionLineasTblCollection of this TmsRecaudacionGastosTbl to the specified value.
     * @param tmsRecaudacionLineasTblCollection the new tmsRecaudacionLineasTblCollection
     */
    public void setTmsRecaudacionLineasTblCollection(Collection<TmsRecaudacionLineasTbl> tmsRecaudacionLineasTblCollection) {
        this.tmsRecaudacionLineasTblCollection = tmsRecaudacionLineasTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.recaudacionGastoId != null ? this.recaudacionGastoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRecaudacionGastosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRecaudacionGastosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRecaudacionGastosTbl)) {
            return false;
        }
        TmsRecaudacionGastosTbl other = (TmsRecaudacionGastosTbl)object;
        if (this.recaudacionGastoId != other.recaudacionGastoId && (this.recaudacionGastoId == null || !this.recaudacionGastoId.equals(other.recaudacionGastoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsRecaudacionGastosTbl[recaudacionGastoId=" + recaudacionGastoId + "]";
    }

    public String getRecaudacionGastoCarga() {
        return recaudacionGastoCarga;
    }

    public void setRecaudacionGastoCarga(String recaudacionGastoCarga) {
        this.recaudacionGastoCarga = recaudacionGastoCarga;
    }
    
}
