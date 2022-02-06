/*
 * TmsTerminalParametrosTbl.java
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
 * Entity class TmsTerminalParametrosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TERMINAL_PARAMETROS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByTerminalParametroId", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.terminalParametroId = :terminalParametroId"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByParametroValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.parametroValor = :parametroValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByTurno", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.turno = :turno"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByLunesValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.lunesValor = :lunesValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByMartesValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.martesValor = :martesValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByMiercolesValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.miercolesValor = :miercolesValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByJuevesValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.juevesValor = :juevesValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByViernesValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.viernesValor = :viernesValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findBySabadoValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.sabadoValor = :sabadoValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByDomingoValor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.domingoValor = :domingoValor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByCreadoPor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByFechaCreacion", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTerminalParametrosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTerminalParametrosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsTerminalParametrosTbl implements Serializable {

    @Id
    @Column(name = "TERMINAL_PARAMETRO_ID", nullable = false)
    private BigDecimal terminalParametroId;

    @Column(name = "TERMINAL_ID", nullable = false)
    private BigDecimal terminalId;

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
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @JoinColumn(name = "PARAMETRO_CONFIG_ID", referencedColumnName = "PARAMETRO_CONFIG_ID")
    @ManyToOne
    private TmsParametrosConfigTbl parametroConfigId;
    
    /** Creates a new instance of TmsTerminalParametrosTbl */
    public TmsTerminalParametrosTbl() {
    }

    /**
     * Creates a new instance of TmsTerminalParametrosTbl with the specified values.
     * @param terminalParametroId the terminalParametroId of the TmsTerminalParametrosTbl
     */
    public TmsTerminalParametrosTbl(BigDecimal terminalParametroId) {
        this.terminalParametroId = terminalParametroId;
    }

    /**
     * Creates a new instance of TmsTerminalParametrosTbl with the specified values.
     * @param terminalParametroId the terminalParametroId of the TmsTerminalParametrosTbl
     * @param creadoPor the creadoPor of the TmsTerminalParametrosTbl
     * @param fechaCreacion the fechaCreacion of the TmsTerminalParametrosTbl
     */
    public TmsTerminalParametrosTbl(BigDecimal terminalParametroId, BigInteger creadoPor, Date fechaCreacion) {
        this.terminalParametroId = terminalParametroId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the terminalParametroId of this TmsTerminalParametrosTbl.
     * @return the terminalParametroId
     */
    public BigDecimal getTerminalParametroId() {
        return this.terminalParametroId;
    }

    /**
     * Sets the terminalParametroId of this TmsTerminalParametrosTbl to the specified value.
     * @param terminalParametroId the new terminalParametroId
     */
    public void setTerminalParametroId(BigDecimal terminalParametroId) {
        this.terminalParametroId = terminalParametroId;
    }

    /**
     * Gets the parametroValor of this TmsTerminalParametrosTbl.
     * @return the parametroValor
     */
    public String getParametroValor() {
        return this.parametroValor;
    }

    /**
     * Sets the parametroValor of this TmsTerminalParametrosTbl to the specified value.
     * @param parametroValor the new parametroValor
     */
    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    /**
     * Gets the turno of this TmsTerminalParametrosTbl.
     * @return the turno
     */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Sets the turno of this TmsTerminalParametrosTbl to the specified value.
     * @param turno the new turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Gets the lunesValor of this TmsTerminalParametrosTbl.
     * @return the lunesValor
     */
    public String getLunesValor() {
        return this.lunesValor;
    }

    /**
     * Sets the lunesValor of this TmsTerminalParametrosTbl to the specified value.
     * @param lunesValor the new lunesValor
     */
    public void setLunesValor(String lunesValor) {
        this.lunesValor = lunesValor;
    }

    /**
     * Gets the martesValor of this TmsTerminalParametrosTbl.
     * @return the martesValor
     */
    public String getMartesValor() {
        return this.martesValor;
    }

    /**
     * Sets the martesValor of this TmsTerminalParametrosTbl to the specified value.
     * @param martesValor the new martesValor
     */
    public void setMartesValor(String martesValor) {
        this.martesValor = martesValor;
    }

    /**
     * Gets the miercolesValor of this TmsTerminalParametrosTbl.
     * @return the miercolesValor
     */
    public String getMiercolesValor() {
        return this.miercolesValor;
    }

    /**
     * Sets the miercolesValor of this TmsTerminalParametrosTbl to the specified value.
     * @param miercolesValor the new miercolesValor
     */
    public void setMiercolesValor(String miercolesValor) {
        this.miercolesValor = miercolesValor;
    }

    /**
     * Gets the juevesValor of this TmsTerminalParametrosTbl.
     * @return the juevesValor
     */
    public String getJuevesValor() {
        return this.juevesValor;
    }

    /**
     * Sets the juevesValor of this TmsTerminalParametrosTbl to the specified value.
     * @param juevesValor the new juevesValor
     */
    public void setJuevesValor(String juevesValor) {
        this.juevesValor = juevesValor;
    }

    /**
     * Gets the viernesValor of this TmsTerminalParametrosTbl.
     * @return the viernesValor
     */
    public String getViernesValor() {
        return this.viernesValor;
    }

    /**
     * Sets the viernesValor of this TmsTerminalParametrosTbl to the specified value.
     * @param viernesValor the new viernesValor
     */
    public void setViernesValor(String viernesValor) {
        this.viernesValor = viernesValor;
    }

    /**
     * Gets the sabadoValor of this TmsTerminalParametrosTbl.
     * @return the sabadoValor
     */
    public String getSabadoValor() {
        return this.sabadoValor;
    }

    /**
     * Sets the sabadoValor of this TmsTerminalParametrosTbl to the specified value.
     * @param sabadoValor the new sabadoValor
     */
    public void setSabadoValor(String sabadoValor) {
        this.sabadoValor = sabadoValor;
    }

    /**
     * Gets the domingoValor of this TmsTerminalParametrosTbl.
     * @return the domingoValor
     */
    public String getDomingoValor() {
        return this.domingoValor;
    }

    /**
     * Sets the domingoValor of this TmsTerminalParametrosTbl to the specified value.
     * @param domingoValor the new domingoValor
     */
    public void setDomingoValor(String domingoValor) {
        this.domingoValor = domingoValor;
    }

    /**
     * Gets the creadoPor of this TmsTerminalParametrosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTerminalParametrosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTerminalParametrosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTerminalParametrosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTerminalParametrosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTerminalParametrosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTerminalParametrosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTerminalParametrosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the parametroConfigId of this TmsTerminalParametrosTbl.
     * @return the parametroConfigId
     */
    public TmsParametrosConfigTbl getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsTerminalParametrosTbl to the specified value.
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
        hash += (this.terminalParametroId != null ? this.terminalParametroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTerminalParametrosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTerminalParametrosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTerminalParametrosTbl)) {
            return false;
        }
        TmsTerminalParametrosTbl other = (TmsTerminalParametrosTbl)object;
        if (this.terminalParametroId != other.terminalParametroId && (this.terminalParametroId == null || !this.terminalParametroId.equals(other.terminalParametroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.solicitud.TmsTerminalParametrosTbl[terminalParametroId=" + terminalParametroId + "]";
    }

    public BigDecimal getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(BigDecimal terminalId) {
        this.terminalId = terminalId;
    }
    
}
