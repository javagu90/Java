/*
 * TmsParametrosConfigTbl.java
 *
 * Created on 29 de agosto de 2007, 06:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad2;

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
 * Entity class TmsParametrosConfigTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_PARAMETROS_CONFIG_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsParametrosConfigTbl.findByParametroConfigId", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.parametroConfigId = :parametroConfigId"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByParametroCodigo", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.parametroCodigo = :parametroCodigo"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByParametroNombre", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.parametroNombre = :parametroNombre"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByParametroDescripcion", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.parametroDescripcion = :parametroDescripcion"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByParametroTipo", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.parametroTipo = :parametroTipo"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByAplicaTurnoDias", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.aplicaTurnoDias = :aplicaTurnoDias"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByHabilitado", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByCreadoPor", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByFechaCreacion", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsParametrosConfigTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsParametrosConfigTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsParametrosConfigTbl implements Serializable {

    @Id
    @Column(name = "PARAMETRO_CONFIG_ID", nullable = false)
    private BigDecimal parametroConfigId;

    @Column(name = "PARAMETRO_CODIGO")
    private String parametroCodigo;

    @Column(name = "PARAMETRO_NOMBRE")
    private String parametroNombre;

    @Column(name = "PARAMETRO_DESCRIPCION")
    private String parametroDescripcion;

    @Column(name = "PARAMETRO_TIPO")
    private String parametroTipo;

    @Column(name = "APLICA_TURNO_DIAS")
    private String aplicaTurnoDias;

    @Column(name = "HABILITADO")
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroConfigId")
    private Collection<TmsServicioParametrosTbl> tmsServicioParametrosTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroConfigId")
    private Collection<TmsGlobalParametrosTbl> tmsGlobalParametrosTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroConfigId")
    private Collection<TmsCajaParametrosTbl> tmsCajaParametrosTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroConfigId")
    private Collection<TmsEmpresaParametrosTbl> tmsEmpresaParametrosTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroConfigId")
    private Collection<TmsTerminalParametrosTbl> tmsTerminalParametrosTblCollection;
    
    /** Creates a new instance of TmsParametrosConfigTbl */
    public TmsParametrosConfigTbl() {
    }

    /**
     * Creates a new instance of TmsParametrosConfigTbl with the specified values.
     * @param parametroConfigId the parametroConfigId of the TmsParametrosConfigTbl
     */
    public TmsParametrosConfigTbl(BigDecimal parametroConfigId) {
        this.parametroConfigId = parametroConfigId;
    }

    /**
     * Creates a new instance of TmsParametrosConfigTbl with the specified values.
     * @param parametroConfigId the parametroConfigId of the TmsParametrosConfigTbl
     * @param creadoPor the creadoPor of the TmsParametrosConfigTbl
     * @param fechaCreacion the fechaCreacion of the TmsParametrosConfigTbl
     */
    public TmsParametrosConfigTbl(BigDecimal parametroConfigId, BigInteger creadoPor, Date fechaCreacion) {
        this.parametroConfigId = parametroConfigId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the parametroConfigId of this TmsParametrosConfigTbl.
     * @return the parametroConfigId
     */
    public BigDecimal getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsParametrosConfigTbl to the specified value.
     * @param parametroConfigId the new parametroConfigId
     */
    public void setParametroConfigId(BigDecimal parametroConfigId) {
        this.parametroConfigId = parametroConfigId;
    }

    /**
     * Gets the parametroCodigo of this TmsParametrosConfigTbl.
     * @return the parametroCodigo
     */
    public String getParametroCodigo() {
        return this.parametroCodigo;
    }

    /**
     * Sets the parametroCodigo of this TmsParametrosConfigTbl to the specified value.
     * @param parametroCodigo the new parametroCodigo
     */
    public void setParametroCodigo(String parametroCodigo) {
        this.parametroCodigo = parametroCodigo;
    }

    /**
     * Gets the parametroNombre of this TmsParametrosConfigTbl.
     * @return the parametroNombre
     */
    public String getParametroNombre() {
        return this.parametroNombre;
    }

    /**
     * Sets the parametroNombre of this TmsParametrosConfigTbl to the specified value.
     * @param parametroNombre the new parametroNombre
     */
    public void setParametroNombre(String parametroNombre) {
        this.parametroNombre = parametroNombre;
    }

    /**
     * Gets the parametroDescripcion of this TmsParametrosConfigTbl.
     * @return the parametroDescripcion
     */
    public String getParametroDescripcion() {
        return this.parametroDescripcion;
    }

    /**
     * Sets the parametroDescripcion of this TmsParametrosConfigTbl to the specified value.
     * @param parametroDescripcion the new parametroDescripcion
     */
    public void setParametroDescripcion(String parametroDescripcion) {
        this.parametroDescripcion = parametroDescripcion;
    }

    /**
     * Gets the parametroTipo of this TmsParametrosConfigTbl.
     * @return the parametroTipo
     */
    public String getParametroTipo() {
        return this.parametroTipo;
    }

    /**
     * Sets the parametroTipo of this TmsParametrosConfigTbl to the specified value.
     * @param parametroTipo the new parametroTipo
     */
    public void setParametroTipo(String parametroTipo) {
        this.parametroTipo = parametroTipo;
    }

    /**
     * Gets the aplicaTurnoDias of this TmsParametrosConfigTbl.
     * @return the aplicaTurnoDias
     */
    public String getAplicaTurnoDias() {
        return this.aplicaTurnoDias;
    }

    /**
     * Sets the aplicaTurnoDias of this TmsParametrosConfigTbl to the specified value.
     * @param aplicaTurnoDias the new aplicaTurnoDias
     */
    public void setAplicaTurnoDias(String aplicaTurnoDias) {
        this.aplicaTurnoDias = aplicaTurnoDias;
    }

    /**
     * Gets the habilitado of this TmsParametrosConfigTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsParametrosConfigTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsParametrosConfigTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsParametrosConfigTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsParametrosConfigTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsParametrosConfigTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsParametrosConfigTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsParametrosConfigTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsParametrosConfigTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsParametrosConfigTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsServicioParametrosTblCollection of this TmsParametrosConfigTbl.
     * @return the tmsServicioParametrosTblCollection
     */
    public Collection<TmsServicioParametrosTbl> getTmsServicioParametrosTblCollection() {
        return this.tmsServicioParametrosTblCollection;
    }

    /**
     * Sets the tmsServicioParametrosTblCollection of this TmsParametrosConfigTbl to the specified value.
     * @param tmsServicioParametrosTblCollection the new tmsServicioParametrosTblCollection
     */
    public void setTmsServicioParametrosTblCollection(Collection<TmsServicioParametrosTbl> tmsServicioParametrosTblCollection) {
        this.tmsServicioParametrosTblCollection = tmsServicioParametrosTblCollection;
    }

    /**
     * Gets the tmsGlobalParametrosTblCollection of this TmsParametrosConfigTbl.
     * @return the tmsGlobalParametrosTblCollection
     */
    public Collection<TmsGlobalParametrosTbl> getTmsGlobalParametrosTblCollection() {
        return this.tmsGlobalParametrosTblCollection;
    }

    /**
     * Sets the tmsGlobalParametrosTblCollection of this TmsParametrosConfigTbl to the specified value.
     * @param tmsGlobalParametrosTblCollection the new tmsGlobalParametrosTblCollection
     */
    public void setTmsGlobalParametrosTblCollection(Collection<TmsGlobalParametrosTbl> tmsGlobalParametrosTblCollection) {
        this.tmsGlobalParametrosTblCollection = tmsGlobalParametrosTblCollection;
    }

    /**
     * Gets the tmsCajaParametrosTblCollection of this TmsParametrosConfigTbl.
     * @return the tmsCajaParametrosTblCollection
     */
    public Collection<TmsCajaParametrosTbl> getTmsCajaParametrosTblCollection() {
        return this.tmsCajaParametrosTblCollection;
    }

    /**
     * Sets the tmsCajaParametrosTblCollection of this TmsParametrosConfigTbl to the specified value.
     * @param tmsCajaParametrosTblCollection the new tmsCajaParametrosTblCollection
     */
    public void setTmsCajaParametrosTblCollection(Collection<TmsCajaParametrosTbl> tmsCajaParametrosTblCollection) {
        this.tmsCajaParametrosTblCollection = tmsCajaParametrosTblCollection;
    }

    /**
     * Gets the tmsEmpresaParametrosTblCollection of this TmsParametrosConfigTbl.
     * @return the tmsEmpresaParametrosTblCollection
     */
    public Collection<TmsEmpresaParametrosTbl> getTmsEmpresaParametrosTblCollection() {
        return this.tmsEmpresaParametrosTblCollection;
    }

    /**
     * Sets the tmsEmpresaParametrosTblCollection of this TmsParametrosConfigTbl to the specified value.
     * @param tmsEmpresaParametrosTblCollection the new tmsEmpresaParametrosTblCollection
     */
    public void setTmsEmpresaParametrosTblCollection(Collection<TmsEmpresaParametrosTbl> tmsEmpresaParametrosTblCollection) {
        this.tmsEmpresaParametrosTblCollection = tmsEmpresaParametrosTblCollection;
    }

    /**
     * Gets the tmsTerminalParametrosTblCollection of this TmsParametrosConfigTbl.
     * @return the tmsTerminalParametrosTblCollection
     */
    public Collection<TmsTerminalParametrosTbl> getTmsTerminalParametrosTblCollection() {
        return this.tmsTerminalParametrosTblCollection;
    }

    /**
     * Sets the tmsTerminalParametrosTblCollection of this TmsParametrosConfigTbl to the specified value.
     * @param tmsTerminalParametrosTblCollection the new tmsTerminalParametrosTblCollection
     */
    public void setTmsTerminalParametrosTblCollection(Collection<TmsTerminalParametrosTbl> tmsTerminalParametrosTblCollection) {
        this.tmsTerminalParametrosTblCollection = tmsTerminalParametrosTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.parametroConfigId != null ? this.parametroConfigId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsParametrosConfigTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsParametrosConfigTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsParametrosConfigTbl)) {
            return false;
        }
        TmsParametrosConfigTbl other = (TmsParametrosConfigTbl)object;
        if (this.parametroConfigId != other.parametroConfigId && (this.parametroConfigId == null || !this.parametroConfigId.equals(other.parametroConfigId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.solicitud.TmsParametrosConfigTbl[parametroConfigId=" + parametroConfigId + "]";
    }
}