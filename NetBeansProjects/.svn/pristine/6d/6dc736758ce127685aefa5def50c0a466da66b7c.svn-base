/*
 * TmsGlobalParametrosTbl.java
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
 * Entity class TmsGlobalParametrosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_GLOBAL_PARAMETROS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByGlobalParametroId", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.globalParametroId = :globalParametroId"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByParametroValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.parametroValor = :parametroValor"),
        //@NamedQuery(name = "TmsGlobalParametrosTbl.findByParametroConfigId", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.parametroConfigId = :parametroConfigId"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByTurno", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.turno = :turno"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByLunesValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.lunesValor = :lunesValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByMartesValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.martesValor = :martesValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByMiercolesValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.miercolesValor = :miercolesValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByJuevesValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.juevesValor = :juevesValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByViernesValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.viernesValor = :viernesValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findBySabadoValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.sabadoValor = :sabadoValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByDomingoValor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.domingoValor = :domingoValor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByCreadoPor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByFechaCreacion", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsGlobalParametrosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsGlobalParametrosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsGlobalParametrosTbl implements Serializable {

    @Id
    @Column(name = "GLOBAL_PARAMETRO_ID", nullable = false)
    private BigDecimal globalParametroId;

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

//    @Column(name = "PARAMETRO_CONFIG_ID", nullable = false)
  //  private long parametroConfigId;

    
    @JoinColumn(name = "PARAMETRO_CONFIG_ID", referencedColumnName = "PARAMETRO_CONFIG_ID")
    @ManyToOne
    private TmsParametrosConfigTbl parametroConfigId;
    
    /** Creates a new instance of TmsGlobalParametrosTbl */
    public TmsGlobalParametrosTbl() {
    }

    /**
     * Creates a new instance of TmsGlobalParametrosTbl with the specified values.
     * @param globalParametroId the globalParametroId of the TmsGlobalParametrosTbl
     */
    public TmsGlobalParametrosTbl(BigDecimal globalParametroId) {
        this.globalParametroId = globalParametroId;
    }

    /**
     * Creates a new instance of TmsGlobalParametrosTbl with the specified values.
     * @param globalParametroId the globalParametroId of the TmsGlobalParametrosTbl
     * @param creadoPor the creadoPor of the TmsGlobalParametrosTbl
     * @param fechaCreacion the fechaCreacion of the TmsGlobalParametrosTbl
     */
    public TmsGlobalParametrosTbl(BigDecimal globalParametroId, BigInteger creadoPor, Date fechaCreacion) {
        this.globalParametroId = globalParametroId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the globalParametroId of this TmsGlobalParametrosTbl.
     * @return the globalParametroId
     */
    public BigDecimal getGlobalParametroId() {
        return this.globalParametroId;
    }

    /**
     * Sets the globalParametroId of this TmsGlobalParametrosTbl to the specified value.
     * @param globalParametroId the new globalParametroId
     */
    public void setGlobalParametroId(BigDecimal globalParametroId) {
        this.globalParametroId = globalParametroId;
    }

    /**
     * Gets the parametroValor of this TmsGlobalParametrosTbl.
     * @return the parametroValor
     */
    public String getParametroValor() {
        return this.parametroValor;
    }

    /**
     * Sets the parametroValor of this TmsGlobalParametrosTbl to the specified value.
     * @param parametroValor the new parametroValor
     */
    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    /**
     * Gets the turno of this TmsGlobalParametrosTbl.
     * @return the turno
     */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Sets the turno of this TmsGlobalParametrosTbl to the specified value.
     * @param turno the new turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Gets the lunesValor of this TmsGlobalParametrosTbl.
     * @return the lunesValor
     */
    public String getLunesValor() {
        return this.lunesValor;
    }

    /**
     * Sets the lunesValor of this TmsGlobalParametrosTbl to the specified value.
     * @param lunesValor the new lunesValor
     */
    public void setLunesValor(String lunesValor) {
        this.lunesValor = lunesValor;
    }

    /**
     * Gets the martesValor of this TmsGlobalParametrosTbl.
     * @return the martesValor
     */
    public String getMartesValor() {
        return this.martesValor;
    }

    /**
     * Sets the martesValor of this TmsGlobalParametrosTbl to the specified value.
     * @param martesValor the new martesValor
     */
    public void setMartesValor(String martesValor) {
        this.martesValor = martesValor;
    }

    /**
     * Gets the miercolesValor of this TmsGlobalParametrosTbl.
     * @return the miercolesValor
     */
    public String getMiercolesValor() {
        return this.miercolesValor;
    }

    /**
     * Sets the miercolesValor of this TmsGlobalParametrosTbl to the specified value.
     * @param miercolesValor the new miercolesValor
     */
    public void setMiercolesValor(String miercolesValor) {
        this.miercolesValor = miercolesValor;
    }

    /**
     * Gets the juevesValor of this TmsGlobalParametrosTbl.
     * @return the juevesValor
     */
    public String getJuevesValor() {
        return this.juevesValor;
    }

    /**
     * Sets the juevesValor of this TmsGlobalParametrosTbl to the specified value.
     * @param juevesValor the new juevesValor
     */
    public void setJuevesValor(String juevesValor) {
        this.juevesValor = juevesValor;
    }

    /**
     * Gets the viernesValor of this TmsGlobalParametrosTbl.
     * @return the viernesValor
     */
    public String getViernesValor() {
        return this.viernesValor;
    }

    /**
     * Sets the viernesValor of this TmsGlobalParametrosTbl to the specified value.
     * @param viernesValor the new viernesValor
     */
    public void setViernesValor(String viernesValor) {
        this.viernesValor = viernesValor;
    }

    /**
     * Gets the sabadoValor of this TmsGlobalParametrosTbl.
     * @return the sabadoValor
     */
    public String getSabadoValor() {
        return this.sabadoValor;
    }

    /**
     * Sets the sabadoValor of this TmsGlobalParametrosTbl to the specified value.
     * @param sabadoValor the new sabadoValor
     */
    public void setSabadoValor(String sabadoValor) {
        this.sabadoValor = sabadoValor;
    }

    /**
     * Gets the domingoValor of this TmsGlobalParametrosTbl.
     * @return the domingoValor
     */
    public String getDomingoValor() {
        return this.domingoValor;
    }

    /**
     * Sets the domingoValor of this TmsGlobalParametrosTbl to the specified value.
     * @param domingoValor the new domingoValor
     */
    public void setDomingoValor(String domingoValor) {
        this.domingoValor = domingoValor;
    }

    /**
     * Gets the creadoPor of this TmsGlobalParametrosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsGlobalParametrosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsGlobalParametrosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsGlobalParametrosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsGlobalParametrosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsGlobalParametrosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsGlobalParametrosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsGlobalParametrosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
    /**
     * Gets the parametroConfigId of this TmsGlobalParametrosTbl.
     * @return the parametroConfigId
     */
    public TmsParametrosConfigTbl getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsGlobalParametrosTbl to the specified value.
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
        hash += (this.globalParametroId != null ? this.globalParametroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsGlobalParametrosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsGlobalParametrosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsGlobalParametrosTbl)) {
            return false;
        }
        TmsGlobalParametrosTbl other = (TmsGlobalParametrosTbl)object;
        if (this.globalParametroId != other.globalParametroId && (this.globalParametroId == null || !this.globalParametroId.equals(other.globalParametroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.solicitud.TmsGlobalParametrosTbl[globalParametroId=" + globalParametroId + "]";
    }
/*
    public long getParametroConfigId() {
        return parametroConfigId;
    }

    public void setParametroConfigId(long parametroConfigId) {
        this.parametroConfigId = parametroConfigId;
    }
    */
}
