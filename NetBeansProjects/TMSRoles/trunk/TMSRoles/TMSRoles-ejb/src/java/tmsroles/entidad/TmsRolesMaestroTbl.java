/*
 * TmsRolesMaestroTbl.java
 *
 * Created on 24 de diciembre de 2007, 12:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsRolesMaestroTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ROLES_MAESTRO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRolesMaestroTbl.findByRolMaestroId", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.rolMaestroId = :rolMaestroId"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByRolMaestroNombre", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.rolMaestroNombre = :rolMaestroNombre"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByFechaInicial", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.fechaInicial = :fechaInicial"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByFechaFinal", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.fechaFinal = :fechaFinal"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByOfertaServicioNombre", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.ofertaServicioNombre = :ofertaServicioNombre"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByHabilitado", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByCreadoPor", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByFechaCreacion", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByReplicacionEstado", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsRolesMaestroTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsRolesMaestroTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsRolesMaestroTbl implements Serializable {
    @SequenceGenerator(name="TMS_RM_SEQ",sequenceName="TMS_ROLES_MAESTRO_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_RM_SEQ")
    @Id
    @Column(name = "ROL_MAESTRO_ID", nullable = false)
    private BigDecimal rolMaestroId;

    @Column(name = "ROL_MAESTRO_NOMBRE", nullable = false)
    private String rolMaestroNombre;

    @Column(name = "FECHA_INICIAL", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Column(name = "OFERTA_SERVICIO_NOMBRE")
    private String ofertaServicioNombre;

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

    @JoinColumn(name = "FLOTILLA_ID", referencedColumnName = "FLOTILLA_ID")
    @ManyToOne
    private TmsFlotillasTbl flotillaId;

    @JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
    @ManyToOne
    private TmsServiciosTbl servicioId;
    
    /** Creates a new instance of TmsRolesMaestroTbl */
    public TmsRolesMaestroTbl() {
    }

    /**
     * Creates a new instance of TmsRolesMaestroTbl with the specified values.
     * @param rolMaestroId the rolMaestroId of the TmsRolesMaestroTbl
     */
    public TmsRolesMaestroTbl(BigDecimal rolMaestroId) {
        this.rolMaestroId = rolMaestroId;
    }

    /**
     * Creates a new instance of TmsRolesMaestroTbl with the specified values.
     * @param rolMaestroId the rolMaestroId of the TmsRolesMaestroTbl
     * @param rolMaestroNombre the rolMaestroNombre of the TmsRolesMaestroTbl
     * @param fechaInicial the fechaInicial of the TmsRolesMaestroTbl
     * @param habilitado the habilitado of the TmsRolesMaestroTbl
     * @param creadoPor the creadoPor of the TmsRolesMaestroTbl
     * @param fechaCreacion the fechaCreacion of the TmsRolesMaestroTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRolesMaestroTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRolesMaestroTbl
     */
    public TmsRolesMaestroTbl(BigDecimal rolMaestroId, String rolMaestroNombre, Date fechaInicial, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.rolMaestroId = rolMaestroId;
        this.rolMaestroNombre = rolMaestroNombre;
        this.fechaInicial = fechaInicial;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the rolMaestroId of this TmsRolesMaestroTbl.
     * @return the rolMaestroId
     */
    public BigDecimal getRolMaestroId() {
        return this.rolMaestroId;
    }

    /**
     * Sets the rolMaestroId of this TmsRolesMaestroTbl to the specified value.
     * @param rolMaestroId the new rolMaestroId
     */
    public void setRolMaestroId(BigDecimal rolMaestroId) {
        this.rolMaestroId = rolMaestroId;
    }

    /**
     * Gets the rolMaestroNombre of this TmsRolesMaestroTbl.
     * @return the rolMaestroNombre
     */
    public String getRolMaestroNombre() {
        return this.rolMaestroNombre;
    }

    /**
     * Sets the rolMaestroNombre of this TmsRolesMaestroTbl to the specified value.
     * @param rolMaestroNombre the new rolMaestroNombre
     */
    public void setRolMaestroNombre(String rolMaestroNombre) {
        this.rolMaestroNombre = rolMaestroNombre;
    }

    /**
     * Gets the fechaInicial of this TmsRolesMaestroTbl.
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    /**
     * Sets the fechaInicial of this TmsRolesMaestroTbl to the specified value.
     * @param fechaInicial the new fechaInicial
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * Gets the fechaFinal of this TmsRolesMaestroTbl.
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    /**
     * Sets the fechaFinal of this TmsRolesMaestroTbl to the specified value.
     * @param fechaFinal the new fechaFinal
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * Gets the ofertaServicioNombre of this TmsRolesMaestroTbl.
     * @return the ofertaServicioNombre
     */
    public String getOfertaServicioNombre() {
        return this.ofertaServicioNombre;
    }

    /**
     * Sets the ofertaServicioNombre of this TmsRolesMaestroTbl to the specified value.
     * @param ofertaServicioNombre the new ofertaServicioNombre
     */
    public void setOfertaServicioNombre(String ofertaServicioNombre) {
        this.ofertaServicioNombre = ofertaServicioNombre;
    }

    /**
     * Gets the habilitado of this TmsRolesMaestroTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsRolesMaestroTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsRolesMaestroTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRolesMaestroTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRolesMaestroTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRolesMaestroTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRolesMaestroTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRolesMaestroTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRolesMaestroTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRolesMaestroTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsRolesMaestroTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsRolesMaestroTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsRolesMaestroTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsRolesMaestroTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsRolesMaestroTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsRolesMaestroTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the flotillaId of this TmsRolesMaestroTbl.
     * @return the flotillaId
     */
    public TmsFlotillasTbl getFlotillaId() {
        return this.flotillaId;
    }

    /**
     * Sets the flotillaId of this TmsRolesMaestroTbl to the specified value.
     * @param flotillaId the new flotillaId
     */
    public void setFlotillaId(TmsFlotillasTbl flotillaId) {
        this.flotillaId = flotillaId;
    }

    /**
     * Gets the servicioId of this TmsRolesMaestroTbl.
     * @return the servicioId
     */
    public TmsServiciosTbl getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsRolesMaestroTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(TmsServiciosTbl servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rolMaestroId != null ? this.rolMaestroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRolesMaestroTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRolesMaestroTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRolesMaestroTbl)) {
            return false;
        }
        TmsRolesMaestroTbl other = (TmsRolesMaestroTbl)object;
        if (this.rolMaestroId != other.rolMaestroId && (this.rolMaestroId == null || !this.rolMaestroId.equals(other.rolMaestroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsRolesMaestroTbl[rolMaestroId=" + rolMaestroId + "]";
    }
    
}
