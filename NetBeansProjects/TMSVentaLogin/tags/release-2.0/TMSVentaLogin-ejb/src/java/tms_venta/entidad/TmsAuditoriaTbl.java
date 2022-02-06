/*
 * TmsAuditoriaTbl.java
 *
 * Created on 1 de octubre de 2007, 10:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Entity class TmsAuditoriaTbl
 * 
 * @author ocruz
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

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_AUDITORIA_GENERADOR")
    @SequenceGenerator(name = "TMS_AUDITORIA_GENERADOR", 
                       sequenceName = "TMS_AUDITORIA_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name = "AUDITORIA_ID", nullable = false)
    private Long auditoriaId;

    @Column(name = "USUARIO_ID", nullable = false)
    private Long usuarioId;

    @Column(name = "FECHA")
    private Timestamp fecha;

    @Column(name = "DESCRIPCION_PROCESO")
    private String descripcionProceso;

    @Column(name = "ESTADO_PROCESO")
    private String estadoProceso;

    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "FUNCION_NUMERO", nullable = false)
    private Long funcionNumero;
    
    /** Creates a new instance of TmsAuditoriaTbl */
    public TmsAuditoriaTbl() {
    }

    /**
     * Creates a new instance of TmsAuditoriaTbl with the specified values.
     * @param auditoriaId the auditoriaId of the TmsAuditoriaTbl
     */
    public TmsAuditoriaTbl(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    /**
     * Creates a new instance of TmsAuditoriaTbl with the specified values.
     * @param auditoriaId the auditoriaId of the TmsAuditoriaTbl
     * @param usuarioId the usuarioId of the TmsAuditoriaTbl
     */
    public TmsAuditoriaTbl(Long auditoriaId, Long usuarioId) {
        this.auditoriaId = auditoriaId;
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the auditoriaId of this TmsAuditoriaTbl.
     * @return the auditoriaId
     */
    public Long getAuditoriaId() {
        return this.auditoriaId;
    }

    /**
     * Sets the auditoriaId of this TmsAuditoriaTbl to the specified value.
     * @param auditoriaId the new auditoriaId
     */
    public void setAuditoriaId(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    /**
     * Gets the usuarioId of this TmsAuditoriaTbl.
     * @return the usuarioId
     */
    public Long getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuarioId of this TmsAuditoriaTbl to the specified value.
     * @param usuarioId the new usuarioId
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the fecha of this TmsAuditoriaTbl.
     * @return the fecha
     */
    public Timestamp getFecha() {
        return this.fecha;
    }

    /**
     * Sets the fecha of this TmsAuditoriaTbl to the specified value.
     * @param fecha the new fecha
     */
    public void setFecha(Timestamp fecha) {
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
     * Gets the funcionId of this TmsAuditoriaTbl.
     * @return the funcionId
     */
    public Long getFuncionNumero() {
        return this.funcionNumero;
    }

    /**
     * Sets the funcionId of this TmsAuditoriaTbl to the specified value.
     * @param funcionId the new funcionId
     */
    public void setFuncionNumero(Long funcionNumero) {
        this.funcionNumero = funcionNumero;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.auditoriaId != null ? this.auditoriaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsAuditoriaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsAuditoriaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsAuditoriaTbl)) {
            return false;
        }
        TmsAuditoriaTbl other = (TmsAuditoriaTbl)object;
        if (this.auditoriaId != other.auditoriaId && (this.auditoriaId == null || !this.auditoriaId.equals(other.auditoriaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tms_venta.entidad.TmsAuditoriaTbl[auditoriaId=" + auditoriaId + "]";
    }
    
}
