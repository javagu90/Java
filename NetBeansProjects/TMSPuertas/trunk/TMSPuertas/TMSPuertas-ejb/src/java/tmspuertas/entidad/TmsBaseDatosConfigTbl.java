/*
 * TmsBaseDatosConfigTbl.java
 *
 * Created on 10 de diciembre de 2007, 09:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

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
 * Entity class TmsBaseDatosConfigTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_BASE_DATOS_CONFIG_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByBdconfigId", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.bdconfigId = :bdconfigId"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByNombreEsquema", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.nombreEsquema = :nombreEsquema"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByNombreTerminal", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.nombreTerminal = :nombreTerminal"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByNombreDblink", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.nombreDblink = :nombreDblink"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByEsquemaPropio", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.esquemaPropio = :esquemaPropio and t.nombreTerminal <> 'CENTRAL'"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByHabilitado", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByCreadoPor", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByFechaCreacion", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByReplicacionEstado", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsBaseDatosConfigTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsBaseDatosConfigTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsBaseDatosConfigTbl implements Serializable {

    @Id
    @Column(name = "BDCONFIG_ID", nullable = false)
    private BigDecimal bdconfigId;

    @Column(name = "NOMBRE_ESQUEMA")
    private String nombreEsquema;

    @Column(name = "NOMBRE_TERMINAL", nullable = false)
    private String nombreTerminal;

    @Column(name = "NOMBRE_DBLINK")
    private String nombreDblink;

    @Column(name = "ESQUEMA_PROPIO")
    private String esquemaPropio;

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

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @JoinColumn(name = "TERMINAL_ID", referencedColumnName = "ESTADO_ID")
    @ManyToOne
    private TmsEstadosTbl terminalId;
    
    /** Creates a new instance of TmsBaseDatosConfigTbl */
    public TmsBaseDatosConfigTbl() {
    }

    /**
     * Creates a new instance of TmsBaseDatosConfigTbl with the specified values.
     * @param bdconfigId the bdconfigId of the TmsBaseDatosConfigTbl
     */
    public TmsBaseDatosConfigTbl(BigDecimal bdconfigId) {
        this.bdconfigId = bdconfigId;
    }

    /**
     * Creates a new instance of TmsBaseDatosConfigTbl with the specified values.
     * @param bdconfigId the bdconfigId of the TmsBaseDatosConfigTbl
     * @param nombreTerminal the nombreTerminal of the TmsBaseDatosConfigTbl
     * @param habilitado the habilitado of the TmsBaseDatosConfigTbl
     * @param creadoPor the creadoPor of the TmsBaseDatosConfigTbl
     * @param fechaCreacion the fechaCreacion of the TmsBaseDatosConfigTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsBaseDatosConfigTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsBaseDatosConfigTbl
     */
    public TmsBaseDatosConfigTbl(BigDecimal bdconfigId, String nombreTerminal, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.bdconfigId = bdconfigId;
        this.nombreTerminal = nombreTerminal;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the bdconfigId of this TmsBaseDatosConfigTbl.
     * @return the bdconfigId
     */
    public BigDecimal getBdconfigId() {
        return this.bdconfigId;
    }

    /**
     * Sets the bdconfigId of this TmsBaseDatosConfigTbl to the specified value.
     * @param bdconfigId the new bdconfigId
     */
    public void setBdconfigId(BigDecimal bdconfigId) {
        this.bdconfigId = bdconfigId;
    }

    /**
     * Gets the nombreEsquema of this TmsBaseDatosConfigTbl.
     * @return the nombreEsquema
     */
    public String getNombreEsquema() {
        return this.nombreEsquema;
    }

    /**
     * Sets the nombreEsquema of this TmsBaseDatosConfigTbl to the specified value.
     * @param nombreEsquema the new nombreEsquema
     */
    public void setNombreEsquema(String nombreEsquema) {
        this.nombreEsquema = nombreEsquema;
    }

    /**
     * Gets the nombreTerminal of this TmsBaseDatosConfigTbl.
     * @return the nombreTerminal
     */
    public String getNombreTerminal() {
        return this.nombreTerminal;
    }

    /**
     * Sets the nombreTerminal of this TmsBaseDatosConfigTbl to the specified value.
     * @param nombreTerminal the new nombreTerminal
     */
    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    /**
     * Gets the nombreDblink of this TmsBaseDatosConfigTbl.
     * @return the nombreDblink
     */
    public String getNombreDblink() {
        return this.nombreDblink;
    }

    /**
     * Sets the nombreDblink of this TmsBaseDatosConfigTbl to the specified value.
     * @param nombreDblink the new nombreDblink
     */
    public void setNombreDblink(String nombreDblink) {
        this.nombreDblink = nombreDblink;
    }

    /**
     * Gets the esquemaPropio of this TmsBaseDatosConfigTbl.
     * @return the esquemaPropio
     */
    public String getEsquemaPropio() {
        return this.esquemaPropio;
    }

    /**
     * Sets the esquemaPropio of this TmsBaseDatosConfigTbl to the specified value.
     * @param esquemaPropio the new esquemaPropio
     */
    public void setEsquemaPropio(String esquemaPropio) {
        this.esquemaPropio = esquemaPropio;
    }

    /**
     * Gets the habilitado of this TmsBaseDatosConfigTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsBaseDatosConfigTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsBaseDatosConfigTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsBaseDatosConfigTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsBaseDatosConfigTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsBaseDatosConfigTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsBaseDatosConfigTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsBaseDatosConfigTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsBaseDatosConfigTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsBaseDatosConfigTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsBaseDatosConfigTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsBaseDatosConfigTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsBaseDatosConfigTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsBaseDatosConfigTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsBaseDatosConfigTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsBaseDatosConfigTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the terminalId of this TmsBaseDatosConfigTbl.
     * @return the terminalId
     */
    public TmsEstadosTbl getTerminalId() {
        return this.terminalId;
    }

    /**
     * Sets the terminalId of this TmsBaseDatosConfigTbl to the specified value.
     * @param terminalId the new terminalId
     */
    public void setTerminalId(TmsEstadosTbl terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.bdconfigId != null ? this.bdconfigId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsBaseDatosConfigTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsBaseDatosConfigTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsBaseDatosConfigTbl)) {
            return false;
        }
        TmsBaseDatosConfigTbl other = (TmsBaseDatosConfigTbl)object;
        if (this.bdconfigId != other.bdconfigId && (this.bdconfigId == null || !this.bdconfigId.equals(other.bdconfigId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuertas.solicitud.TmsBaseDatosConfigTbl[bdconfigId=" + bdconfigId + "]";
    }
    
}
