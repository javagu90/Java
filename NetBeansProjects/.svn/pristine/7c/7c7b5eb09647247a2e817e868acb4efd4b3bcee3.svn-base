/*
 * TmsRutaParametrosTbl.java
 *
 * Created on 12 de septiembre de 2007, 02:24 PM
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
 * Entity class TmsRutaParametrosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RUTA_PARAMETROS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRutaParametrosTbl.findByRutaParametroId", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.rutaParametroId = :rutaParametroId"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByParametroValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.parametroValor = :parametroValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByTurno", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.turno = :turno"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByLunesValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.lunesValor = :lunesValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByMartesValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.martesValor = :martesValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByMiercolesValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.miercolesValor = :miercolesValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByJuevesValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.juevesValor = :juevesValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByViernesValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.viernesValor = :viernesValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findBySabadoValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.sabadoValor = :sabadoValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByDomingoValor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.domingoValor = :domingoValor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByCreadoPor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByFechaCreacion", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRutaParametrosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRutaParametrosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRutaParametrosTbl implements Serializable {

    @Id
    @Column(name = "RUTA_PARAMETRO_ID", nullable = false)
    private BigDecimal rutaParametroId;

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

    @JoinColumn(name = "RUTA_ID", referencedColumnName = "RUTA_ID")
    @ManyToOne
    private TmsRutasTbl rutaId;
    
    /** Creates a new instance of TmsRutaParametrosTbl */
    public TmsRutaParametrosTbl() {
    }

    /**
     * Creates a new instance of TmsRutaParametrosTbl with the specified values.
     * @param rutaParametroId the rutaParametroId of the TmsRutaParametrosTbl
     */
    public TmsRutaParametrosTbl(BigDecimal rutaParametroId) {
        this.rutaParametroId = rutaParametroId;
    }

    /**
     * Creates a new instance of TmsRutaParametrosTbl with the specified values.
     * @param rutaParametroId the rutaParametroId of the TmsRutaParametrosTbl
     * @param creadoPor the creadoPor of the TmsRutaParametrosTbl
     * @param fechaCreacion the fechaCreacion of the TmsRutaParametrosTbl
     */
    public TmsRutaParametrosTbl(BigDecimal rutaParametroId, BigInteger creadoPor, Date fechaCreacion) {
        this.rutaParametroId = rutaParametroId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the rutaParametroId of this TmsRutaParametrosTbl.
     * @return the rutaParametroId
     */
    public BigDecimal getRutaParametroId() {
        return this.rutaParametroId;
    }

    /**
     * Sets the rutaParametroId of this TmsRutaParametrosTbl to the specified value.
     * @param rutaParametroId the new rutaParametroId
     */
    public void setRutaParametroId(BigDecimal rutaParametroId) {
        this.rutaParametroId = rutaParametroId;
    }

    /**
     * Gets the parametroValor of this TmsRutaParametrosTbl.
     * @return the parametroValor
     */
    public String getParametroValor() {
        return this.parametroValor;
    }

    /**
     * Sets the parametroValor of this TmsRutaParametrosTbl to the specified value.
     * @param parametroValor the new parametroValor
     */
    public void setParametroValor(String parametroValor) {
        this.parametroValor = parametroValor;
    }

    /**
     * Gets the turno of this TmsRutaParametrosTbl.
     * @return the turno
     */
    public String getTurno() {
        return this.turno;
    }

    /**
     * Sets the turno of this TmsRutaParametrosTbl to the specified value.
     * @param turno the new turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Gets the lunesValor of this TmsRutaParametrosTbl.
     * @return the lunesValor
     */
    public String getLunesValor() {
        return this.lunesValor;
    }

    /**
     * Sets the lunesValor of this TmsRutaParametrosTbl to the specified value.
     * @param lunesValor the new lunesValor
     */
    public void setLunesValor(String lunesValor) {
        this.lunesValor = lunesValor;
    }

    /**
     * Gets the martesValor of this TmsRutaParametrosTbl.
     * @return the martesValor
     */
    public String getMartesValor() {
        return this.martesValor;
    }

    /**
     * Sets the martesValor of this TmsRutaParametrosTbl to the specified value.
     * @param martesValor the new martesValor
     */
    public void setMartesValor(String martesValor) {
        this.martesValor = martesValor;
    }

    /**
     * Gets the miercolesValor of this TmsRutaParametrosTbl.
     * @return the miercolesValor
     */
    public String getMiercolesValor() {
        return this.miercolesValor;
    }

    /**
     * Sets the miercolesValor of this TmsRutaParametrosTbl to the specified value.
     * @param miercolesValor the new miercolesValor
     */
    public void setMiercolesValor(String miercolesValor) {
        this.miercolesValor = miercolesValor;
    }

    /**
     * Gets the juevesValor of this TmsRutaParametrosTbl.
     * @return the juevesValor
     */
    public String getJuevesValor() {
        return this.juevesValor;
    }

    /**
     * Sets the juevesValor of this TmsRutaParametrosTbl to the specified value.
     * @param juevesValor the new juevesValor
     */
    public void setJuevesValor(String juevesValor) {
        this.juevesValor = juevesValor;
    }

    /**
     * Gets the viernesValor of this TmsRutaParametrosTbl.
     * @return the viernesValor
     */
    public String getViernesValor() {
        return this.viernesValor;
    }

    /**
     * Sets the viernesValor of this TmsRutaParametrosTbl to the specified value.
     * @param viernesValor the new viernesValor
     */
    public void setViernesValor(String viernesValor) {
        this.viernesValor = viernesValor;
    }

    /**
     * Gets the sabadoValor of this TmsRutaParametrosTbl.
     * @return the sabadoValor
     */
    public String getSabadoValor() {
        return this.sabadoValor;
    }

    /**
     * Sets the sabadoValor of this TmsRutaParametrosTbl to the specified value.
     * @param sabadoValor the new sabadoValor
     */
    public void setSabadoValor(String sabadoValor) {
        this.sabadoValor = sabadoValor;
    }

    /**
     * Gets the domingoValor of this TmsRutaParametrosTbl.
     * @return the domingoValor
     */
    public String getDomingoValor() {
        return this.domingoValor;
    }

    /**
     * Sets the domingoValor of this TmsRutaParametrosTbl to the specified value.
     * @param domingoValor the new domingoValor
     */
    public void setDomingoValor(String domingoValor) {
        this.domingoValor = domingoValor;
    }

    /**
     * Gets the creadoPor of this TmsRutaParametrosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRutaParametrosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRutaParametrosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRutaParametrosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRutaParametrosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRutaParametrosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRutaParametrosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRutaParametrosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the parametroConfigId of this TmsRutaParametrosTbl.
     * @return the parametroConfigId
     */
    public TmsParametrosConfigTbl getParametroConfigId() {
        return this.parametroConfigId;
    }

    /**
     * Sets the parametroConfigId of this TmsRutaParametrosTbl to the specified value.
     * @param parametroConfigId the new parametroConfigId
     */
    public void setParametroConfigId(TmsParametrosConfigTbl parametroConfigId) {
        this.parametroConfigId = parametroConfigId;
    }

    /**
     * Gets the rutaId of this TmsRutaParametrosTbl.
     * @return the rutaId
     */
    public TmsRutasTbl getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsRutaParametrosTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(TmsRutasTbl rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rutaParametroId != null ? this.rutaParametroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRutaParametrosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRutaParametrosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRutaParametrosTbl)) {
            return false;
        }
        TmsRutaParametrosTbl other = (TmsRutaParametrosTbl)object;
        if (this.rutaParametroId != other.rutaParametroId && (this.rutaParametroId == null || !this.rutaParametroId.equals(other.rutaParametroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsRutaParametrosTbl[rutaParametroId=" + rutaParametroId + "]";
    }
    
}
