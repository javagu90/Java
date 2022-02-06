/*
 * TmsEmpresaParametrosTbl.java
 *
 * Created on 29 de agosto de 2007, 06:14 PM
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsEmpresaParametrosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_EMPRESA_PARAMETROS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByEmpresaParametroId", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.empresaParametroId = :empresaParametroId"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByParametroValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.parametroValor = :parametroValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByTurno", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.turno = :turno"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByLunesValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.lunesValor = :lunesValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByMartesValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.martesValor = :martesValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByMiercolesValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.miercolesValor = :miercolesValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByJuevesValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.juevesValor = :juevesValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByViernesValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.viernesValor = :viernesValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findBySabadoValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.sabadoValor = :sabadoValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByDomingoValor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.domingoValor = :domingoValor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByCreadoPor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByFechaCreacion", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsEmpresaParametrosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsEmpresaParametrosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsEmpresaParametrosTbl implements Serializable {

    @Id
    @Column(name = "EMPRESA_PARAMETRO_ID", nullable = false)
    private BigDecimal empresaParametroId;

    @Column(name = "EMPRESA_ID", nullable = false)
    private BigDecimal empresaId;

    @Column(name = "PARAMETRO_VALOR")
    private String parametroValor;

    @Column(name = "TURNO")
    private String turno;

    @Column(name = "LUNES_VALOR")
    private String lunesValor;

    @Column(name = "MARTES_VALOR")
    private String martesValor;

    @Column(name = "MIERCOLES_VALOR")
    private String miercolesValor;

    @Column(name = "JUEVES_VALOR")
    private String juevesValor;

    @Column(name = "VIERNES_VALOR")
    private String viernesValor;

    @Column(name = "SABADO_VALOR")
    private String sabadoValor;

    @Column(name = "DOMINGO_VALOR")
    private String domingoValor;

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

    @JoinColumn(name = "PARAMETRO_CONFIG_ID", referencedColumnName = "PARAMETRO_CONFIG_ID")
    @ManyToOne
    private TmsParametrosConfigTbl parametroConfigId;
    
    /** Creates a new instance of TmsEmpresaParametrosTbl */
    public TmsEmpresaParametrosTbl() {
    }

    /**
     * Creates a new instance of TmsEmpresaParametrosTbl with the specified values.
     * @param empresaParametroId the empresaParametroId of the TmsEmpresaParametrosTbl
     */
    public TmsEmpresaParametrosTbl(BigDecimal empresaParametroId) {
        this.empresaParametroId = empresaParametroId;
    }

    /**
     * Creates a new instance of TmsEmpresaParametrosTbl with the specified values.
     * @param empresaParametroId the empresaParametroId of the TmsEmpresaParametrosTbl
     * @param creadoPor the creadoPor of the TmsEmpresaParametrosTbl
     * @param fechaCreacion the fechaCreacion of the TmsEmpresaParametrosTbl
     */
    public TmsEmpresaParametrosTbl(BigDecimal empresaParametroId, BigInteger creadoPor, Date fechaCreacion) {
        this.empresaParametroId = empresaParametroId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the empresaParametroId of this TmsEmpresaParametrosTbl.
     * @return the empresaParametroId
     */
    public BigDecimal getEmpresaParametroId() {
        return this.empresaParametroId;
    }

    /**
     * Sets the empresaParametroId of this TmsEmpresaParametrosTbl to the specified value.
     * @param empresaParametroId the new empresaParametroId
     */
    public void setEmpresaParametroId(BigDecimal empresaParametroId) {
        this.empresaParametroId = empresaParametroId;
    }

    /**
     * Gets the parametroValor of this TmsEmpresaParametrosTbl.
     * @return the parametroValor
     */
    public String getParametroValor() {
        return this.parametroValor;
    }

    /**
     * Sets the parametroValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param parametroValor the new parametroValor
     */
    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    /**
     * Gets the turno of this TmsEmpresaParametrosTbl.
     * @return the turno
     */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Sets the turno of this TmsEmpresaParametrosTbl to the specified value.
     * @param turno the new turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Gets the lunesValor of this TmsEmpresaParametrosTbl.
     * @return the lunesValor
     */
    public String getLunesValor() {
        return this.lunesValor;
    }

    /**
     * Sets the lunesValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param lunesValor the new lunesValor
     */
    public void setLunesValor(String lunesValor) {
        this.lunesValor = lunesValor;
    }

    /**
     * Gets the martesValor of this TmsEmpresaParametrosTbl.
     * @return the martesValor
     */
    public String getMartesValor() {
        return this.martesValor;
    }

    /**
     * Sets the martesValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param martesValor the new martesValor
     */
    public void setMartesValor(String martesValor) {
        this.martesValor = martesValor;
    }

    /**
     * Gets the miercolesValor of this TmsEmpresaParametrosTbl.
     * @return the miercolesValor
     */
    public String getMiercolesValor() {
        return this.miercolesValor;
    }

    /**
     * Sets the miercolesValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param miercolesValor the new miercolesValor
     */
    public void setMiercolesValor(String miercolesValor) {
        this.miercolesValor = miercolesValor;
    }

    /**
     * Gets the juevesValor of this TmsEmpresaParametrosTbl.
     * @return the juevesValor
     */
    public String getJuevesValor() {
        return this.juevesValor;
    }

    /**
     * Sets the juevesValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param juevesValor the new juevesValor
     */
    public void setJuevesValor(String juevesValor) {
        this.juevesValor = juevesValor;
    }

    /**
     * Gets the viernesValor of this TmsEmpresaParametrosTbl.
     * @return the viernesValor
     */
    public String getViernesValor() {
        return this.viernesValor;
    }

    /**
     * Sets the viernesValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param viernesValor the new viernesValor
     */
    public void setViernesValor(String viernesValor) {
        this.viernesValor = viernesValor;
    }

    /**
     * Gets the sabadoValor of this TmsEmpresaParametrosTbl.
     * @return the sabadoValor
     */
    public String getSabadoValor() {
        return this.sabadoValor;
    }

    /**
     * Sets the sabadoValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param sabadoValor the new sabadoValor
     */
    public void setSabadoValor(String sabadoValor) {
        this.sabadoValor = sabadoValor;
    }

    /**
     * Gets the domingoValor of this TmsEmpresaParametrosTbl.
     * @return the domingoValor
     */
    public String getDomingoValor() {
        return this.domingoValor;
    }

    /**
     * Sets the domingoValor of this TmsEmpresaParametrosTbl to the specified value.
     * @param domingoValor the new domingoValor
     */
    public void setDomingoValor(String domingoValor) {
        this.domingoValor = domingoValor;
    }

    /**
     * Gets the creadoPor of this TmsEmpresaParametrosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsEmpresaParametrosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsEmpresaParametrosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsEmpresaParametrosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsEmpresaParametrosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsEmpresaParametrosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsEmpresaParametrosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsEmpresaParametrosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the parametroConfigId of this TmsEmpresaParametrosTbl.
     * @return the parametroConfigId
     */
    public TmsParametrosConfigTbl getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsEmpresaParametrosTbl to the specified value.
     * @param parametroConfigId the new parametroConfigId
     */
    public void setParametroConfigId(TmsParametrosConfigTbl parametroConfigId) {
        this.parametroConfigId = parametroConfigId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.empresaParametroId != null ? this.empresaParametroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsEmpresaParametrosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsEmpresaParametrosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsEmpresaParametrosTbl)) {
            return false;
        }
        TmsEmpresaParametrosTbl other = (TmsEmpresaParametrosTbl)object;
        if (this.empresaParametroId != other.empresaParametroId && (this.empresaParametroId == null || !this.empresaParametroId.equals(other.empresaParametroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.solicitud.TmsEmpresaParametrosTbl[empresaParametroId=" + empresaParametroId + "]";
    }

    public BigDecimal getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(BigDecimal empresaId) {
        this.empresaId = empresaId;
    }
    
}
