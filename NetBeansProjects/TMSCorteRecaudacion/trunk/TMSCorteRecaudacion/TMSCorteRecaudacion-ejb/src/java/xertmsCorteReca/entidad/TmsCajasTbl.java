/*
 * TmsCajasTbl.java
 *
 * Created on 5 de octubre de 2007, 02:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

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
 * Entity class TmsCajasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_CAJAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsCajasTbl.findByCajaId", query = "SELECT t FROM TmsCajasTbl t WHERE t.cajaId = :cajaId"),
        @NamedQuery(name = "TmsCajasTbl.findByCajaNombre", query = "SELECT t FROM TmsCajasTbl t WHERE t.cajaNombre = :cajaNombre"),
        @NamedQuery(name = "TmsCajasTbl.findByCajaNumero", query = "SELECT t FROM TmsCajasTbl t WHERE t.cajaNumero = :cajaNumero"),
        @NamedQuery(name = "TmsCajasTbl.findByNombreEquipo", query = "SELECT t FROM TmsCajasTbl t WHERE t.nombreEquipo = :nombreEquipo"),
        @NamedQuery(name = "TmsCajasTbl.findByDescripcion", query = "SELECT t FROM TmsCajasTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsCajasTbl.findByCajaIp", query = "SELECT t FROM TmsCajasTbl t WHERE t.cajaIp = :cajaIp"),
        @NamedQuery(name = "TmsCajasTbl.findByUbicacionId", query = "SELECT t FROM TmsCajasTbl t WHERE t.ubicacionId = :ubicacionId"),
        @NamedQuery(name = "TmsCajasTbl.findByDireccionMac", query = "SELECT t FROM TmsCajasTbl t WHERE t.direccionMac = :direccionMac"),
        @NamedQuery(name = "TmsCajasTbl.findByPrefijoSerie", query = "SELECT t FROM TmsCajasTbl t WHERE t.prefijoSerie = :prefijoSerie"),
        @NamedQuery(name = "TmsCajasTbl.findByAdicional1", query = "SELECT t FROM TmsCajasTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsCajasTbl.findByAdicional2", query = "SELECT t FROM TmsCajasTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsCajasTbl.findByAdicional3", query = "SELECT t FROM TmsCajasTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsCajasTbl.findByAdicional4", query = "SELECT t FROM TmsCajasTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsCajasTbl.findByAdicional5", query = "SELECT t FROM TmsCajasTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsCajasTbl.findByCreadoPor", query = "SELECT t FROM TmsCajasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsCajasTbl.findByFechaCreacion", query = "SELECT t FROM TmsCajasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsCajasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsCajasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsCajasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsCajasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsCajasTbl implements Serializable {

    @Id
    @Column(name = "CAJA_ID", nullable = false)
    private BigDecimal cajaId;

    @Column(name = "CAJA_NOMBRE", nullable = false)
    private String cajaNombre;

    @Column(name = "CAJA_NUMERO")
    private String cajaNumero;

    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CAJA_IP")
    private String cajaIp;

    @Column(name = "UBICACION_ID")
    private BigInteger ubicacionId;

    @Column(name = "DIRECCION_MAC")
    private String direccionMac;

    @Column(name = "PREFIJO_SERIE", nullable = false)
    private String prefijoSerie;

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

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(mappedBy = "cajaId")
    private Collection<TmsSesionActividadesTbl> tmsSesionActividadesTblCollection;
    
    /** Creates a new instance of TmsCajasTbl */
    public TmsCajasTbl() {
    }

    /**
     * Creates a new instance of TmsCajasTbl with the specified values.
     * @param cajaId the cajaId of the TmsCajasTbl
     */
    public TmsCajasTbl(BigDecimal cajaId) {
        this.cajaId = cajaId;
    }

    /**
     * Creates a new instance of TmsCajasTbl with the specified values.
     * @param cajaId the cajaId of the TmsCajasTbl
     * @param cajaNombre the cajaNombre of the TmsCajasTbl
     * @param prefijoSerie the prefijoSerie of the TmsCajasTbl
     * @param creadoPor the creadoPor of the TmsCajasTbl
     * @param fechaCreacion the fechaCreacion of the TmsCajasTbl
     */
    public TmsCajasTbl(BigDecimal cajaId, String cajaNombre, String prefijoSerie, BigInteger creadoPor, Date fechaCreacion) {
        this.cajaId = cajaId;
        this.cajaNombre = cajaNombre;
        this.prefijoSerie = prefijoSerie;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the cajaId of this TmsCajasTbl.
     * @return the cajaId
     */
    public BigDecimal getCajaId() {
        return this.cajaId;
    }

    /**
     * Sets the cajaId of this TmsCajasTbl to the specified value.
     * @param cajaId the new cajaId
     */
    public void setCajaId(BigDecimal cajaId) {
        this.cajaId = cajaId;
    }

    /**
     * Gets the cajaNombre of this TmsCajasTbl.
     * @return the cajaNombre
     */
    public String getCajaNombre() {
        return this.cajaNombre;
    }

    /**
     * Sets the cajaNombre of this TmsCajasTbl to the specified value.
     * @param cajaNombre the new cajaNombre
     */
    public void setCajaNombre(String cajaNombre) {
        this.cajaNombre = cajaNombre;
    }

    /**
     * Gets the cajaNumero of this TmsCajasTbl.
     * @return the cajaNumero
     */
    public String getCajaNumero() {
        return this.cajaNumero;
    }

    /**
     * Sets the cajaNumero of this TmsCajasTbl to the specified value.
     * @param cajaNumero the new cajaNumero
     */
    public void setCajaNumero(String cajaNumero) {
        this.cajaNumero = cajaNumero;
    }

    /**
     * Gets the nombreEquipo of this TmsCajasTbl.
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return this.nombreEquipo;
    }

    /**
     * Sets the nombreEquipo of this TmsCajasTbl to the specified value.
     * @param nombreEquipo the new nombreEquipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Gets the descripcion of this TmsCajasTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsCajasTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the cajaIp of this TmsCajasTbl.
     * @return the cajaIp
     */
    public String getCajaIp() {
        return this.cajaIp;
    }

    /**
     * Sets the cajaIp of this TmsCajasTbl to the specified value.
     * @param cajaIp the new cajaIp
     */
    public void setCajaIp(String cajaIp) {
        this.cajaIp = cajaIp;
    }

    /**
     * Gets the ubicacionId of this TmsCajasTbl.
     * @return the ubicacionId
     */
    public BigInteger getUbicacionId() {
        return this.ubicacionId;
    }

    /**
     * Sets the ubicacionId of this TmsCajasTbl to the specified value.
     * @param ubicacionId the new ubicacionId
     */
    public void setUbicacionId(BigInteger ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    /**
     * Gets the direccionMac of this TmsCajasTbl.
     * @return the direccionMac
     */
    public String getDireccionMac() {
        return this.direccionMac;
    }

    /**
     * Sets the direccionMac of this TmsCajasTbl to the specified value.
     * @param direccionMac the new direccionMac
     */
    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }

    /**
     * Gets the prefijoSerie of this TmsCajasTbl.
     * @return the prefijoSerie
     */
    public String getPrefijoSerie() {
        return this.prefijoSerie;
    }

    /**
     * Sets the prefijoSerie of this TmsCajasTbl to the specified value.
     * @param prefijoSerie the new prefijoSerie
     */
    public void setPrefijoSerie(String prefijoSerie) {
        this.prefijoSerie = prefijoSerie;
    }

    /**
     * Gets the adicional1 of this TmsCajasTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsCajasTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsCajasTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsCajasTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsCajasTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsCajasTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsCajasTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsCajasTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsCajasTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsCajasTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsCajasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsCajasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsCajasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsCajasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsCajasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsCajasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsCajasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsCajasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsSesionActividadesTblCollection of this TmsCajasTbl.
     * @return the tmsSesionActividadesTblCollection
     */
    public Collection<TmsSesionActividadesTbl> getTmsSesionActividadesTblCollection() {
        return this.tmsSesionActividadesTblCollection;
    }

    /**
     * Sets the tmsSesionActividadesTblCollection of this TmsCajasTbl to the specified value.
     * @param tmsSesionActividadesTblCollection the new tmsSesionActividadesTblCollection
     */
    public void setTmsSesionActividadesTblCollection(Collection<TmsSesionActividadesTbl> tmsSesionActividadesTblCollection) {
        this.tmsSesionActividadesTblCollection = tmsSesionActividadesTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.cajaId != null ? this.cajaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsCajasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsCajasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsCajasTbl)) {
            return false;
        }
        TmsCajasTbl other = (TmsCajasTbl)object;
        if (this.cajaId != other.cajaId && (this.cajaId == null || !this.cajaId.equals(other.cajaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertmsCorteReca.entidad.TmsCajasTbl[cajaId=" + cajaId + "]";
    }
    
}
