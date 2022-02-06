/*
 * TmsSesionesGlobalTbl.java
 *
 * Created on 8 de agosto de 2007, 07:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmstrafico.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsSesionesGlobalTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_SESIONES_GLOBAL_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByNumeroSesion", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.numeroSesion = :numeroSesion"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByFechaInicio", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.fechaInicio = :fechaInicio"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByFechaFin", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.fechaFin = :fechaFin"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByEstadoSesion", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.estadoSesion = :estadoSesion"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByNombreEquipo", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.nombreEquipo = :nombreEquipo"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByDireccionIp", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.direccionIp = :direccionIp"),
        @NamedQuery(name = "TmsSesionesGlobalTbl.findByEdoSesionAndUsuario", query = "SELECT t FROM TmsSesionesGlobalTbl t WHERE t.estadoSesion = :estadoSesion and t.usuarioId = :usuarioid")
    })
public class TmsSesionesGlobalTbl implements Serializable {
    @SequenceGenerator(name="TMS_SESG_SEQ",sequenceName="TMS_SESIONES_GLOBAL_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_SESG_SEQ")
    @Id
    @Column(name = "NUMERO_SESION", nullable = false)
    private long numeroSesion;

    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @Column(name = "ESTADO_SESION", nullable = false)
    private String estadoSesion;

    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "DIRECCION_IP")
    private String direccionIp;

    //@JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    //@ManyToOne
    //private TmsUsuariosTbl usuarioId;
    @Column(name = "USUARIO_ID")
    private long usuarioId; 
    
    /** Creates a new instance of TmsSesionesGlobalTbl */
    public TmsSesionesGlobalTbl() {
    }

    /**
     * Creates a new instance of TmsSesionesGlobalTbl with the specified values.
     * @param numeroSesion the numeroSesion of the TmsSesionesGlobalTbl
     */
    public TmsSesionesGlobalTbl(long numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    /**
     * Creates a new instance of TmsSesionesGlobalTbl with the specified values.
     * @param numeroSesion the numeroSesion of the TmsSesionesGlobalTbl
     * @param fechaInicio the fechaInicio of the TmsSesionesGlobalTbl
     * @param estadoSesion the estadoSesion of the TmsSesionesGlobalTbl
     */
    public TmsSesionesGlobalTbl(long numeroSesion, Date fechaInicio, String estadoSesion) {
        this.numeroSesion = numeroSesion;
        this.fechaInicio = fechaInicio;
        this.estadoSesion = estadoSesion;
    }

    /**
     * Gets the numeroSesion of this TmsSesionesGlobalTbl.
     * @return the numeroSesion
     */
    public long getNumeroSesion() {
        return this.numeroSesion;
    }

    /**
     * Sets the numeroSesion of this TmsSesionesGlobalTbl to the specified value.
     * @param numeroSesion the new numeroSesion
     */
    public void setNumeroSesion(long numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    /**
     * Gets the fechaInicio of this TmsSesionesGlobalTbl.
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    /**
     * Sets the fechaInicio of this TmsSesionesGlobalTbl to the specified value.
     * @param fechaInicio the new fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets the fechaFin of this TmsSesionesGlobalTbl.
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return this.fechaFin;
    }

    /**
     * Sets the fechaFin of this TmsSesionesGlobalTbl to the specified value.
     * @param fechaFin the new fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Gets the estadoSesion of this TmsSesionesGlobalTbl.
     * @return the estadoSesion
     */
    public String getEstadoSesion() {
        return this.estadoSesion;
    }

    /**
     * Sets the estadoSesion of this TmsSesionesGlobalTbl to the specified value.
     * @param estadoSesion the new estadoSesion
     */
    public void setEstadoSesion(String estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    /**
     * Gets the nombreEquipo of this TmsSesionesGlobalTbl.
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return this.nombreEquipo;
    }

    /**
     * Sets the nombreEquipo of this TmsSesionesGlobalTbl to the specified value.
     * @param nombreEquipo the new nombreEquipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Gets the direccionIp of this TmsSesionesGlobalTbl.
     * @return the direccionIp
     */
    public String getDireccionIp() {
        return this.direccionIp;
    }

    /**
     * Sets the direccionIp of this TmsSesionesGlobalTbl to the specified value.
     * @param direccionIp the new direccionIp
     */
    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    /**
     * Gets the usuarioId of this TmsSesionesGlobalTbl.
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuarioId of this TmsSesionesGlobalTbl to the specified value.
     * @param usuarioId the new usuarioId
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsventalogin.entidad.TmsSesionesGlobalTbl[numeroSesion=" + numeroSesion + "]";
    }
    
}
