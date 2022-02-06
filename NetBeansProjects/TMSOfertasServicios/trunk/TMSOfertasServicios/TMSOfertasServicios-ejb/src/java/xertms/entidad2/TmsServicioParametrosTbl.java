/*
 * TmsServicioParametrosTbl.java
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
 * Entity class TmsServicioParametrosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_SERVICIO_PARAMETROS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsServicioParametrosTbl.findByServicioParametroId", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.servicioParametroId = :servicioParametroId"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByParametroValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.parametroValor = :parametroValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByTurno", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.turno = :turno"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByLunesValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.lunesValor = :lunesValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByMartesValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.martesValor = :martesValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByMiercolesValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.miercolesValor = :miercolesValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByJuevesValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.juevesValor = :juevesValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByViernesValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.viernesValor = :viernesValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findBySabadoValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.sabadoValor = :sabadoValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByDomingoValor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.domingoValor = :domingoValor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByCreadoPor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByFechaCreacion", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsServicioParametrosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsServicioParametrosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsServicioParametrosTbl implements Serializable {

    @Id
    @Column(name = "SERVICIO_PARAMETRO_ID", nullable = false)
    private BigDecimal servicioParametroId;

    @Column(name = "SERVICIO_ID", nullable = false)
    private BigDecimal servicioId;

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
    
    /** Creates a new instance of TmsServicioParametrosTbl */
    public TmsServicioParametrosTbl() {
    }

    /**
     * Creates a new instance of TmsServicioParametrosTbl with the specified values.
     * @param servicioParametroId the servicioParametroId of the TmsServicioParametrosTbl
     */
    public TmsServicioParametrosTbl(BigDecimal servicioParametroId) {
        this.servicioParametroId = servicioParametroId;
    }

    /**
     * Creates a new instance of TmsServicioParametrosTbl with the specified values.
     * @param servicioParametroId the servicioParametroId of the TmsServicioParametrosTbl
     * @param creadoPor the creadoPor of the TmsServicioParametrosTbl
     * @param fechaCreacion the fechaCreacion of the TmsServicioParametrosTbl
     */
    public TmsServicioParametrosTbl(BigDecimal servicioParametroId, BigInteger creadoPor, Date fechaCreacion) {
        this.servicioParametroId = servicioParametroId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the servicioParametroId of this TmsServicioParametrosTbl.
     * @return the servicioParametroId
     */
    public BigDecimal getServicioParametroId() {
        return this.servicioParametroId;
    }

    /**
     * Sets the servicioParametroId of this TmsServicioParametrosTbl to the specified value.
     * @param servicioParametroId the new servicioParametroId
     */
    public void setServicioParametroId(BigDecimal servicioParametroId) {
        this.servicioParametroId = servicioParametroId;
    }

    /**
     * Gets the parametroValor of this TmsServicioParametrosTbl.
     * @return the parametroValor
     */
    public String getParametroValor() {
        return this.parametroValor;
    }

    /**
     * Sets the parametroValor of this TmsServicioParametrosTbl to the specified value.
     * @param parametroValor the new parametroValor
     */
    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    /**
     * Gets the turno of this TmsServicioParametrosTbl.
     * @return the turno
     */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Sets the turno of this TmsServicioParametrosTbl to the specified value.
     * @param turno the new turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Gets the lunesValor of this TmsServicioParametrosTbl.
     * @return the lunesValor
     */
    public String getLunesValor() {
        return this.lunesValor;
    }

    /**
     * Sets the lunesValor of this TmsServicioParametrosTbl to the specified value.
     * @param lunesValor the new lunesValor
     */
    public void setLunesValor(String lunesValor) {
        this.lunesValor = lunesValor;
    }

    /**
     * Gets the martesValor of this TmsServicioParametrosTbl.
     * @return the martesValor
     */
    public String getMartesValor() {
        return this.martesValor;
    }

    /**
     * Sets the martesValor of this TmsServicioParametrosTbl to the specified value.
     * @param martesValor the new martesValor
     */
    public void setMartesValor(String martesValor) {
        this.martesValor = martesValor;
    }

    /**
     * Gets the miercolesValor of this TmsServicioParametrosTbl.
     * @return the miercolesValor
     */
    public String getMiercolesValor() {
        return this.miercolesValor;
    }

    /**
     * Sets the miercolesValor of this TmsServicioParametrosTbl to the specified value.
     * @param miercolesValor the new miercolesValor
     */
    public void setMiercolesValor(String miercolesValor) {
        this.miercolesValor = miercolesValor;
    }

    /**
     * Gets the juevesValor of this TmsServicioParametrosTbl.
     * @return the juevesValor
     */
    public String getJuevesValor() {
        return this.juevesValor;
    }

    /**
     * Sets the juevesValor of this TmsServicioParametrosTbl to the specified value.
     * @param juevesValor the new juevesValor
     */
    public void setJuevesValor(String juevesValor) {
        this.juevesValor = juevesValor;
    }

    /**
     * Gets the viernesValor of this TmsServicioParametrosTbl.
     * @return the viernesValor
     */
    public String getViernesValor() {
        return this.viernesValor;
    }

    /**
     * Sets the viernesValor of this TmsServicioParametrosTbl to the specified value.
     * @param viernesValor the new viernesValor
     */
    public void setViernesValor(String viernesValor) {
        this.viernesValor = viernesValor;
    }

    /**
     * Gets the sabadoValor of this TmsServicioParametrosTbl.
     * @return the sabadoValor
     */
    public String getSabadoValor() {
        return this.sabadoValor;
    }

    /**
     * Sets the sabadoValor of this TmsServicioParametrosTbl to the specified value.
     * @param sabadoValor the new sabadoValor
     */
    public void setSabadoValor(String sabadoValor) {
        this.sabadoValor = sabadoValor;
    }

    /**
     * Gets the domingoValor of this TmsServicioParametrosTbl.
     * @return the domingoValor
     */
    public String getDomingoValor() {
        return this.domingoValor;
    }

    /**
     * Sets the domingoValor of this TmsServicioParametrosTbl to the specified value.
     * @param domingoValor the new domingoValor
     */
    public void setDomingoValor(String domingoValor) {
        this.domingoValor = domingoValor;
    }

    /**
     * Gets the creadoPor of this TmsServicioParametrosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsServicioParametrosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsServicioParametrosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsServicioParametrosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsServicioParametrosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsServicioParametrosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsServicioParametrosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsServicioParametrosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the parametroConfigId of this TmsServicioParametrosTbl.
     * @return the parametroConfigId
     */
    public TmsParametrosConfigTbl getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsServicioParametrosTbl to the specified value.
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
        hash += (this.servicioParametroId != null ? this.servicioParametroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsServicioParametrosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsServicioParametrosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsServicioParametrosTbl)) {
            return false;
        }
        TmsServicioParametrosTbl other = (TmsServicioParametrosTbl)object;
        if (this.servicioParametroId != other.servicioParametroId && (this.servicioParametroId == null || !this.servicioParametroId.equals(other.servicioParametroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.solicitud.TmsServicioParametrosTbl[servicioParametroId=" + servicioParametroId + "]";
    }

    public BigDecimal getServicioId() {
        return servicioId;
    }

    public void setServicioId(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }
    
}
