/*
 * TmsAuditoriaTbl.java
 *
 * Created on 9 de agosto de 2007, 09:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsgloballogin.entidad;

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
 * Entity class TmsAuditoriaTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_AUDITORIA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsAuditoriaTbl.findByAuditoriaId", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.auditoriaId = :auditoriaId"),
        @NamedQuery(name = "TmsAuditoriaTbl.findByUsuarioId", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.usuarioId = :usuarioId"),
        @NamedQuery(name = "TmsAuditoriaTbl.findByFecha", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.fecha = :fecha"),
        @NamedQuery(name = "TmsAuditoriaTbl.findByDescripcionProceso", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.descripcionProceso = :descripcionProceso"),
        @NamedQuery(name = "TmsAuditoriaTbl.findByEstadoProceso", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.estadoProceso = :estadoProceso"),
        @NamedQuery(name = "TmsAuditoriaTbl.findByNombreEquipo", query = "SELECT t FROM TmsAuditoriaTbl t WHERE t.nombreEquipo = :nombreEquipo")
    })
public class TmsAuditoriaTbl implements Serializable {

    @SequenceGenerator(name="TMS_AUDI_SEQ",sequenceName="TMS_AUDITORIA_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_AUDI_SEQ")
    @Id
    @Column(name = "AUDITORIA_ID", nullable = false)
    private long auditoriaId;

    @Column(name = "USUARIO_ID", nullable = false)
    private long usuarioId;

    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "DESCRIPCION_PROCESO")
    private String descripcionProceso;

    @Column(name = "ESTADO_PROCESO")
    private String estadoProceso;

    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;
    
    @Column(name = "FUNCION_NUMERO")
    private String funcionNumero;
    /*
    @JoinColumn(name = "FUNCION_ID", referencedColumnName = "FUNCION_ID")
    @ManyToOne
    private TmsFuncionesTbl funcionId;
    */
    /** Creates a new instance of TmsAuditoriaTbl */
    public TmsAuditoriaTbl() {
    }

    /**
     * Creates a new instance of TmsAuditoriaTbl with the specified values.
     * @param auditoriaId the auditoriaId of the TmsAuditoriaTbl
     */
    public TmsAuditoriaTbl(long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    /**
     * Creates a new instance of TmsAuditoriaTbl with the specified values.
     * @param auditoriaId the auditoriaId of the TmsAuditoriaTbl
     * @param usuarioId the usuarioId of the TmsAuditoriaTbl
     */
    public TmsAuditoriaTbl(long auditoriaId, long usuarioId) {
        this.auditoriaId = auditoriaId;
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the auditoriaId of this TmsAuditoriaTbl.
     * @return the auditoriaId
     */
    public long getAuditoriaId() {
        return this.auditoriaId;
    }

    /**
     * Sets the auditoriaId of this TmsAuditoriaTbl to the specified value.
     * @param auditoriaId the new auditoriaId
     */
    public void setAuditoriaId(long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    /**
     * Gets the usuarioId of this TmsAuditoriaTbl.
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuarioId of this TmsAuditoriaTbl to the specified value.
     * @param usuarioId the new usuarioId
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the fecha of this TmsAuditoriaTbl.
     * @return the fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * Sets the fecha of this TmsAuditoriaTbl to the specified value.
     * @param fecha the new fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the descripcionProceso of this TmsAuditoriaTbl.
     * @return the descripcionProceso
     */
    public String getDescripcionProceso() {
        return this.descripcionProceso;
    }

    /**
     * Sets the descripcionProceso of this TmsAuditoriaTbl to the specified value.
     * @param descripcionProceso the new descripcionProceso
     */
    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }

    /**
     * Gets the estadoProceso of this TmsAuditoriaTbl.
     * @return the estadoProceso
     */
    public String getEstadoProceso() {
        return this.estadoProceso;
    }

    /**
     * Sets the estadoProceso of this TmsAuditoriaTbl to the specified value.
     * @param estadoProceso the new estadoProceso
     */
    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    /**
     * Gets the nombreEquipo of this TmsAuditoriaTbl.
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return this.nombreEquipo;
    }

    /**
     * Sets the nombreEquipo of this TmsAuditoriaTbl to the specified value.
     * @param nombreEquipo the new nombreEquipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }


    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsventalogin.entidad.TmsAuditoriaTbl[auditoriaId=" + auditoriaId + "]";
    }

    public String getFuncionNumero() {
        return funcionNumero;
    }

    public void setFuncionNumero(String funcionNumero) {
        this.funcionNumero = funcionNumero;
    }

    
}
