/*
 * TmsRolesBaseTbl.java
 *
 * Created on 20 de noviembre de 2007, 10:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsRolesBaseTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ROLES_BASE_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRolesBaseTbl.findByRolBaseId", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.rolBaseId = :rolBaseId"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByRolBaseCategoria", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.rolBaseCategoria = :rolBaseCategoria"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByCantidadAutobuses", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.cantidadAutobuses = :cantidadAutobuses"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaLunes", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaLunes = :aplicaLunes"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaMartes", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaMartes = :aplicaMartes"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaMiercoles", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaMiercoles = :aplicaMiercoles"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaJueves", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaJueves = :aplicaJueves"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaViernes", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaViernes = :aplicaViernes"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaSabado", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaSabado = :aplicaSabado"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByAplicaDomingo", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.aplicaDomingo = :aplicaDomingo"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByCreadoPor", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByFechaCreacion", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByReplicacionEstado", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsRolesBaseTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsRolesBaseTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsRolesBaseTbl implements Serializable {

    @SequenceGenerator(name="TMS_RBE_SEQ",sequenceName="TMS_ROLES_BASE_ENC_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_RBE_SEQ")
    @Id
    @Column(name = "ROL_BASE_ID", nullable = false)
    private BigDecimal rolBaseId;

    @Column(name = "ROL_BASE_CATEGORIA", nullable = false)
    private String rolBaseCategoria;

    @Column(name = "CANTIDAD_AUTOBUSES")
    private BigInteger cantidadAutobuses;

    @Column(name = "APLICA_LUNES", nullable = false)
    private String aplicaLunes;

    @Column(name = "APLICA_MARTES", nullable = false)
    private String aplicaMartes;

    @Column(name = "APLICA_MIERCOLES", nullable = false)
    private String aplicaMiercoles;

    @Column(name = "APLICA_JUEVES", nullable = false)
    private String aplicaJueves;

    @Column(name = "APLICA_VIERNES", nullable = false)
    private String aplicaViernes;

    @Column(name = "APLICA_SABADO", nullable = false)
    private String aplicaSabado;

    @Column(name = "APLICA_DOMINGO", nullable = false)
    private String aplicaDomingo;

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

    @JoinColumn(name = "ROL_MAESTRO_ID", referencedColumnName = "ROL_MAESTRO_ID")
    @ManyToOne
    private TmsRolesMaestroTbl rolMaestroId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolBaseId")
    private Collection<TmsRolesBaseLineasTbl> tmsRolesBaseLineasTblCollection;
    
    /** Creates a new instance of TmsRolesBaseTbl */
    public TmsRolesBaseTbl() {
    }

    /**
     * Creates a new instance of TmsRolesBaseTbl with the specified values.
     * @param rolBaseId the rolBaseId of the TmsRolesBaseTbl
     */
    public TmsRolesBaseTbl(BigDecimal rolBaseId) {
        this.rolBaseId = rolBaseId;
    }

    /**
     * Creates a new instance of TmsRolesBaseTbl with the specified values.
     * @param rolBaseId the rolBaseId of the TmsRolesBaseTbl
     * @param rolBaseCategoria the rolBaseCategoria of the TmsRolesBaseTbl
     * @param aplicaLunes the aplicaLunes of the TmsRolesBaseTbl
     * @param aplicaMartes the aplicaMartes of the TmsRolesBaseTbl
     * @param aplicaMiercoles the aplicaMiercoles of the TmsRolesBaseTbl
     * @param aplicaJueves the aplicaJueves of the TmsRolesBaseTbl
     * @param aplicaViernes the aplicaViernes of the TmsRolesBaseTbl
     * @param aplicaSabado the aplicaSabado of the TmsRolesBaseTbl
     * @param aplicaDomingo the aplicaDomingo of the TmsRolesBaseTbl
     * @param creadoPor the creadoPor of the TmsRolesBaseTbl
     * @param fechaCreacion the fechaCreacion of the TmsRolesBaseTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRolesBaseTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRolesBaseTbl
     */
    public TmsRolesBaseTbl(BigDecimal rolBaseId, String rolBaseCategoria, String aplicaLunes, String aplicaMartes, String aplicaMiercoles, String aplicaJueves, String aplicaViernes, String aplicaSabado, String aplicaDomingo, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.rolBaseId = rolBaseId;
        this.rolBaseCategoria = rolBaseCategoria;
        this.aplicaLunes = aplicaLunes;
        this.aplicaMartes = aplicaMartes;
        this.aplicaMiercoles = aplicaMiercoles;
        this.aplicaJueves = aplicaJueves;
        this.aplicaViernes = aplicaViernes;
        this.aplicaSabado = aplicaSabado;
        this.aplicaDomingo = aplicaDomingo;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the rolBaseId of this TmsRolesBaseTbl.
     * @return the rolBaseId
     */
    public BigDecimal getRolBaseId() {
        return this.rolBaseId;
    }

    /**
     * Sets the rolBaseId of this TmsRolesBaseTbl to the specified value.
     * @param rolBaseId the new rolBaseId
     */
    public void setRolBaseId(BigDecimal rolBaseId) {
        this.rolBaseId = rolBaseId;
    }

    /**
     * Gets the rolBaseCategoria of this TmsRolesBaseTbl.
     * @return the rolBaseCategoria
     */
    public String getRolBaseCategoria() {
        return this.rolBaseCategoria;
    }

    /**
     * Sets the rolBaseCategoria of this TmsRolesBaseTbl to the specified value.
     * @param rolBaseCategoria the new rolBaseCategoria
     */
    public void setRolBaseCategoria(String rolBaseCategoria) {
        this.rolBaseCategoria = rolBaseCategoria;
    }

    /**
     * Gets the cantidadAutobuses of this TmsRolesBaseTbl.
     * @return the cantidadAutobuses
     */
    public BigInteger getCantidadAutobuses() {
        return this.cantidadAutobuses;
    }

    /**
     * Sets the cantidadAutobuses of this TmsRolesBaseTbl to the specified value.
     * @param cantidadAutobuses the new cantidadAutobuses
     */
    public void setCantidadAutobuses(BigInteger cantidadAutobuses) {
        this.cantidadAutobuses = cantidadAutobuses;
    }

    /**
     * Gets the aplicaLunes of this TmsRolesBaseTbl.
     * @return the aplicaLunes
     */
    public String getAplicaLunes() {
        return this.aplicaLunes;
    }

    /**
     * Sets the aplicaLunes of this TmsRolesBaseTbl to the specified value.
     * @param aplicaLunes the new aplicaLunes
     */
    public void setAplicaLunes(String aplicaLunes) {
        this.aplicaLunes = aplicaLunes;
    }

    /**
     * Gets the aplicaMartes of this TmsRolesBaseTbl.
     * @return the aplicaMartes
     */
    public String getAplicaMartes() {
        return this.aplicaMartes;
    }

    /**
     * Sets the aplicaMartes of this TmsRolesBaseTbl to the specified value.
     * @param aplicaMartes the new aplicaMartes
     */
    public void setAplicaMartes(String aplicaMartes) {
        this.aplicaMartes = aplicaMartes;
    }

    /**
     * Gets the aplicaMiercoles of this TmsRolesBaseTbl.
     * @return the aplicaMiercoles
     */
    public String getAplicaMiercoles() {
        return this.aplicaMiercoles;
    }

    /**
     * Sets the aplicaMiercoles of this TmsRolesBaseTbl to the specified value.
     * @param aplicaMiercoles the new aplicaMiercoles
     */
    public void setAplicaMiercoles(String aplicaMiercoles) {
        this.aplicaMiercoles = aplicaMiercoles;
    }

    /**
     * Gets the aplicaJueves of this TmsRolesBaseTbl.
     * @return the aplicaJueves
     */
    public String getAplicaJueves() {
        return this.aplicaJueves;
    }

    /**
     * Sets the aplicaJueves of this TmsRolesBaseTbl to the specified value.
     * @param aplicaJueves the new aplicaJueves
     */
    public void setAplicaJueves(String aplicaJueves) {
        this.aplicaJueves = aplicaJueves;
    }

    /**
     * Gets the aplicaViernes of this TmsRolesBaseTbl.
     * @return the aplicaViernes
     */
    public String getAplicaViernes() {
        return this.aplicaViernes;
    }

    /**
     * Sets the aplicaViernes of this TmsRolesBaseTbl to the specified value.
     * @param aplicaViernes the new aplicaViernes
     */
    public void setAplicaViernes(String aplicaViernes) {
        this.aplicaViernes = aplicaViernes;
    }

    /**
     * Gets the aplicaSabado of this TmsRolesBaseTbl.
     * @return the aplicaSabado
     */
    public String getAplicaSabado() {
        return this.aplicaSabado;
    }

    /**
     * Sets the aplicaSabado of this TmsRolesBaseTbl to the specified value.
     * @param aplicaSabado the new aplicaSabado
     */
    public void setAplicaSabado(String aplicaSabado) {
        this.aplicaSabado = aplicaSabado;
    }

    /**
     * Gets the aplicaDomingo of this TmsRolesBaseTbl.
     * @return the aplicaDomingo
     */
    public String getAplicaDomingo() {
        return this.aplicaDomingo;
    }

    /**
     * Sets the aplicaDomingo of this TmsRolesBaseTbl to the specified value.
     * @param aplicaDomingo the new aplicaDomingo
     */
    public void setAplicaDomingo(String aplicaDomingo) {
        this.aplicaDomingo = aplicaDomingo;
    }

    /**
     * Gets the creadoPor of this TmsRolesBaseTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRolesBaseTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRolesBaseTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRolesBaseTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRolesBaseTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRolesBaseTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRolesBaseTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRolesBaseTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsRolesBaseTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsRolesBaseTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsRolesBaseTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsRolesBaseTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsRolesBaseTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsRolesBaseTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the rolMaestroId of this TmsRolesBaseTbl.
     * @return the rolMaestroId
     */
    public TmsRolesMaestroTbl getRolMaestroId() {
        return this.rolMaestroId;
    }

    /**
     * Sets the rolMaestroId of this TmsRolesBaseTbl to the specified value.
     * @param rolMaestroId the new rolMaestroId
     */
    public void setRolMaestroId(TmsRolesMaestroTbl rolMaestroId) {
        this.rolMaestroId = rolMaestroId;
    }

    /**
     * Gets the tmsRolesBaseLineasTblCollection of this TmsRolesBaseTbl.
     * @return the tmsRolesBaseLineasTblCollection
     */
    public Collection<TmsRolesBaseLineasTbl> getTmsRolesBaseLineasTblCollection() {
        return this.tmsRolesBaseLineasTblCollection;
    }

    /**
     * Sets the tmsRolesBaseLineasTblCollection of this TmsRolesBaseTbl to the specified value.
     * @param tmsRolesBaseLineasTblCollection the new tmsRolesBaseLineasTblCollection
     */
    public void setTmsRolesBaseLineasTblCollection(Collection<TmsRolesBaseLineasTbl> tmsRolesBaseLineasTblCollection) {
        this.tmsRolesBaseLineasTblCollection = tmsRolesBaseLineasTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rolBaseId != null ? this.rolBaseId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRolesBaseTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRolesBaseTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRolesBaseTbl)) {
            return false;
        }
        TmsRolesBaseTbl other = (TmsRolesBaseTbl)object;
        if (this.rolBaseId != other.rolBaseId && (this.rolBaseId == null || !this.rolBaseId.equals(other.rolBaseId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsRolesBaseTbl[rolBaseId=" + rolBaseId + "]";
    }
    
}
