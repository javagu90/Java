/*
 * TmsActAdicionalesTbl.java
 *
 * Created on 9 de diciembre de 2007, 03:14 AM
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsActAdicionalesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ACT_ADICIONALES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsActAdicionalesTbl.findByTipoActividadAdicionalId", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.tipoActividadAdicionalId = :tipoActividadAdicionalId"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByActividadClave", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.actividadClave = :actividadClave"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByActividadEditable", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.actividadEditable = :actividadEditable"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByActividadAfavor", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.actividadAfavor = :actividadAfavor"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByCodigoParametro", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.codigoParametro = :codigoParametro"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByAplicaRecaudacion", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.aplicaRecaudacion = :aplicaRecaudacion"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByConfigurable", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.configurable = :configurable"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByIncidenciaValor1", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.incidenciaValor1 = :incidenciaValor1"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByIncidenciaValor2", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.incidenciaValor2 = :incidenciaValor2"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByIncidenciaDuracion", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.incidenciaDuracion = :incidenciaDuracion"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByHabilitado", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByCreadoPor", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByFechaCreacion", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByReplicacionEstado", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsActAdicionalesTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsActAdicionalesTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsActAdicionalesTbl implements Serializable {

    @Id
    @Column(name = "TIPO_ACTIVIDAD_ADICIONAL_ID", nullable = false)
    private BigDecimal tipoActividadAdicionalId;

    @Column(name = "ACTIVIDAD_CLAVE", nullable = false)
    private String actividadClave;

    @Column(name = "ACTIVIDAD_EDITABLE", nullable = false)
    private String actividadEditable;

    @Column(name = "ACTIVIDAD_AFAVOR", nullable = false)
    private String actividadAfavor;

    @Column(name = "CODIGO_PARAMETRO")
    private String codigoParametro;

    @Column(name = "APLICA_RECAUDACION", nullable = false)
    private String aplicaRecaudacion;

    @Column(name = "CONFIGURABLE", nullable = false)
    private String configurable;

    @Column(name = "INCIDENCIA_VALOR1")
    private String incidenciaValor1;

    @Column(name = "INCIDENCIA_VALOR2")
    private String incidenciaValor2;

    @Column(name = "INCIDENCIA_DURACION")
    private String incidenciaDuracion;

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

    @JoinColumn(name = "ACCION_ID", referencedColumnName = "ACCION_ID")
    @ManyToOne
    private TmsAccionesTbl accionId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoActividadAdicionalId")
    private Collection<TmsActDatosAdicionalesTbl> tmsActDatosAdicionalesTblCollection;
    
    /** Creates a new instance of TmsActAdicionalesTbl */
    public TmsActAdicionalesTbl() {
    }

    /**
     * Creates a new instance of TmsActAdicionalesTbl with the specified values.
     * @param tipoActividadAdicionalId the tipoActividadAdicionalId of the TmsActAdicionalesTbl
     */
    public TmsActAdicionalesTbl(BigDecimal tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
    }

    /**
     * Creates a new instance of TmsActAdicionalesTbl with the specified values.
     * @param tipoActividadAdicionalId the tipoActividadAdicionalId of the TmsActAdicionalesTbl
     * @param actividadClave the actividadClave of the TmsActAdicionalesTbl
     * @param actividadEditable the actividadEditable of the TmsActAdicionalesTbl
     * @param actividadAfavor the actividadAfavor of the TmsActAdicionalesTbl
     * @param aplicaRecaudacion the aplicaRecaudacion of the TmsActAdicionalesTbl
     * @param configurable the configurable of the TmsActAdicionalesTbl
     * @param habilitado the habilitado of the TmsActAdicionalesTbl
     * @param creadoPor the creadoPor of the TmsActAdicionalesTbl
     * @param fechaCreacion the fechaCreacion of the TmsActAdicionalesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsActAdicionalesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsActAdicionalesTbl
     */
    public TmsActAdicionalesTbl(BigDecimal tipoActividadAdicionalId, String actividadClave, String actividadEditable, String actividadAfavor, String aplicaRecaudacion, String configurable, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
        this.actividadClave = actividadClave;
        this.actividadEditable = actividadEditable;
        this.actividadAfavor = actividadAfavor;
        this.aplicaRecaudacion = aplicaRecaudacion;
        this.configurable = configurable;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tipoActividadAdicionalId of this TmsActAdicionalesTbl.
     * @return the tipoActividadAdicionalId
     */
    public BigDecimal getTipoActividadAdicionalId() {
        return this.tipoActividadAdicionalId;
    }

    /**
     * Sets the tipoActividadAdicionalId of this TmsActAdicionalesTbl to the specified value.
     * @param tipoActividadAdicionalId the new tipoActividadAdicionalId
     */
    public void setTipoActividadAdicionalId(BigDecimal tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
    }

    /**
     * Gets the actividadClave of this TmsActAdicionalesTbl.
     * @return the actividadClave
     */
    public String getActividadClave() {
        return this.actividadClave;
    }

    /**
     * Sets the actividadClave of this TmsActAdicionalesTbl to the specified value.
     * @param actividadClave the new actividadClave
     */
    public void setActividadClave(String actividadClave) {
        this.actividadClave = actividadClave;
    }

    /**
     * Gets the actividadEditable of this TmsActAdicionalesTbl.
     * @return the actividadEditable
     */
    public String getActividadEditable() {
        return this.actividadEditable;
    }

    /**
     * Sets the actividadEditable of this TmsActAdicionalesTbl to the specified value.
     * @param actividadEditable the new actividadEditable
     */
    public void setActividadEditable(String actividadEditable) {
        this.actividadEditable = actividadEditable;
    }

    /**
     * Gets the actividadAfavor of this TmsActAdicionalesTbl.
     * @return the actividadAfavor
     */
    public String getActividadAfavor() {
        return this.actividadAfavor;
    }

    /**
     * Sets the actividadAfavor of this TmsActAdicionalesTbl to the specified value.
     * @param actividadAfavor the new actividadAfavor
     */
    public void setActividadAfavor(String actividadAfavor) {
        this.actividadAfavor = actividadAfavor;
    }

    /**
     * Gets the codigoParametro of this TmsActAdicionalesTbl.
     * @return the codigoParametro
     */
    public String getCodigoParametro() {
        return this.codigoParametro;
    }

    /**
     * Sets the codigoParametro of this TmsActAdicionalesTbl to the specified value.
     * @param codigoParametro the new codigoParametro
     */
    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    /**
     * Gets the aplicaRecaudacion of this TmsActAdicionalesTbl.
     * @return the aplicaRecaudacion
     */
    public String getAplicaRecaudacion() {
        return this.aplicaRecaudacion;
    }

    /**
     * Sets the aplicaRecaudacion of this TmsActAdicionalesTbl to the specified value.
     * @param aplicaRecaudacion the new aplicaRecaudacion
     */
    public void setAplicaRecaudacion(String aplicaRecaudacion) {
        this.aplicaRecaudacion = aplicaRecaudacion;
    }

    /**
     * Gets the configurable of this TmsActAdicionalesTbl.
     * @return the configurable
     */
    public String getConfigurable() {
        return this.configurable;
    }

    /**
     * Sets the configurable of this TmsActAdicionalesTbl to the specified value.
     * @param configurable the new configurable
     */
    public void setConfigurable(String configurable) {
        this.configurable = configurable;
    }

    /**
     * Gets the incidenciaValor1 of this TmsActAdicionalesTbl.
     * @return the incidenciaValor1
     */
    public String getIncidenciaValor1() {
        return this.incidenciaValor1;
    }

    /**
     * Sets the incidenciaValor1 of this TmsActAdicionalesTbl to the specified value.
     * @param incidenciaValor1 the new incidenciaValor1
     */
    public void setIncidenciaValor1(String incidenciaValor1) {
        this.incidenciaValor1 = incidenciaValor1;
    }

    /**
     * Gets the incidenciaValor2 of this TmsActAdicionalesTbl.
     * @return the incidenciaValor2
     */
    public String getIncidenciaValor2() {
        return this.incidenciaValor2;
    }

    /**
     * Sets the incidenciaValor2 of this TmsActAdicionalesTbl to the specified value.
     * @param incidenciaValor2 the new incidenciaValor2
     */
    public void setIncidenciaValor2(String incidenciaValor2) {
        this.incidenciaValor2 = incidenciaValor2;
    }

    /**
     * Gets the incidenciaDuracion of this TmsActAdicionalesTbl.
     * @return the incidenciaDuracion
     */
    public String getIncidenciaDuracion() {
        return this.incidenciaDuracion;
    }

    /**
     * Sets the incidenciaDuracion of this TmsActAdicionalesTbl to the specified value.
     * @param incidenciaDuracion the new incidenciaDuracion
     */
    public void setIncidenciaDuracion(String incidenciaDuracion) {
        this.incidenciaDuracion = incidenciaDuracion;
    }

    /**
     * Gets the habilitado of this TmsActAdicionalesTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsActAdicionalesTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsActAdicionalesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsActAdicionalesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsActAdicionalesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsActAdicionalesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsActAdicionalesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsActAdicionalesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsActAdicionalesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsActAdicionalesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsActAdicionalesTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsActAdicionalesTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsActAdicionalesTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsActAdicionalesTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsActAdicionalesTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsActAdicionalesTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the accionId of this TmsActAdicionalesTbl.
     * @return the accionId
     */
    public TmsAccionesTbl getAccionId() {
        return this.accionId;
    }

    /**
     * Sets the accionId of this TmsActAdicionalesTbl to the specified value.
     * @param accionId the new accionId
     */
    public void setAccionId(TmsAccionesTbl accionId) {
        this.accionId = accionId;
    }

    /**
     * Gets the tmsActDatosAdicionalesTblCollection of this TmsActAdicionalesTbl.
     * @return the tmsActDatosAdicionalesTblCollection
     */
    public Collection<TmsActDatosAdicionalesTbl> getTmsActDatosAdicionalesTblCollection() {
        return this.tmsActDatosAdicionalesTblCollection;
    }

    /**
     * Sets the tmsActDatosAdicionalesTblCollection of this TmsActAdicionalesTbl to the specified value.
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
        hash += (this.tipoActividadAdicionalId != null ? this.tipoActividadAdicionalId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsActAdicionalesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsActAdicionalesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsActAdicionalesTbl)) {
            return false;
        }
        TmsActAdicionalesTbl other = (TmsActAdicionalesTbl)object;
        if (this.tipoActividadAdicionalId != other.tipoActividadAdicionalId && (this.tipoActividadAdicionalId == null || !this.tipoActividadAdicionalId.equals(other.tipoActividadAdicionalId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsactividadesadicionales.entidad.TmsActAdicionalesTbl[tipoActividadAdicionalId=" + tipoActividadAdicionalId + "]";
    }
    
}
