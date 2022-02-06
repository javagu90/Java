/*
 * TmsTerminalesTbl.java
 *
 * Created on 15 de agosto de 2007, 05:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsTerminalesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TERMINALES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTerminalesTbl.findByTerminalId", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.terminalId = :terminalId"),
        @NamedQuery(name = "TmsTerminalesTbl.findByTerminalNombre", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.terminalNombre = :terminalNombre"),
        @NamedQuery(name = "TmsTerminalesTbl.findByTerminalNumero", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.terminalNumero = :terminalNumero"),
        @NamedQuery(name = "TmsTerminalesTbl.findByDescripcion", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsTerminalesTbl.findByTerminalIp", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.terminalIp = :terminalIp"),
        @NamedQuery(name = "TmsTerminalesTbl.findByTerminalGrupo", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.terminalGrupo = :terminalGrupo"),
        @NamedQuery(name = "TmsTerminalesTbl.findByPuedeVender", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.puedeVender = :puedeVender"),
        @NamedQuery(name = "TmsTerminalesTbl.findByPuedeReservar", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.puedeReservar = :puedeReservar"),
        @NamedQuery(name = "TmsTerminalesTbl.findByPuedeRecaudar", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.puedeRecaudar = :puedeRecaudar"),
        @NamedQuery(name = "TmsTerminalesTbl.findByPuedeImprimir", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.puedeImprimir = :puedeImprimir"),
        @NamedQuery(name = "TmsTerminalesTbl.findByEmpDifMismoFolio", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.empDifMismoFolio = :empDifMismoFolio"),
        @NamedQuery(name = "TmsTerminalesTbl.findByPrefijoSerie", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.prefijoSerie = :prefijoSerie"),
        @NamedQuery(name = "TmsTerminalesTbl.findByAdicional1", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsTerminalesTbl.findByAdicional2", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsTerminalesTbl.findByAdicional3", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsTerminalesTbl.findByAdicional4", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsTerminalesTbl.findByAdicional5", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsTerminalesTbl.findByCreadoPor", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTerminalesTbl.findByFechaCreacion", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTerminalesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTerminalesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTerminalesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsTerminalesTbl implements Serializable {

    @Id
    @Column(name = "TERMINAL_ID", nullable = false)
    private BigDecimal terminalId;

    @Column(name = "TERMINAL_NOMBRE", nullable = false)
    private String terminalNombre;

    @Column(name = "TERMINAL_NUMERO")
    private String terminalNumero;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "TERMINAL_IP")
    private String terminalIp;

    @Column(name = "TERMINAL_GRUPO")
    private String terminalGrupo;

    @Column(name = "PUEDE_VENDER")
    private String puedeVender;

    @Column(name = "PUEDE_RESERVAR")
    private String puedeReservar;

    @Column(name = "PUEDE_RECAUDAR")
    private String puedeRecaudar;

    @Column(name = "PUEDE_IMPRIMIR")
    private String puedeImprimir;

    @Column(name = "EMP_DIF_MISMO_FOLIO")
    private String empDifMismoFolio;

    @Column(name = "PREFIJO_SERIE", nullable = false)
    private String prefijoSerie;

    @Column(name = "ADICIONAL1")
    private String adicional1;

    @Column(name = "ADICIONAL2")
    private String adicional2;

    @Column(name = "ADICIONAL3")
    private String adicional3;

    @Column(name = "ADICIONAL4")
    private String adicional4;

    @Column(name = "ADICIONAL5")
    private String adicional5;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    
    /** Creates a new instance of TmsTerminalesTbl */
    public TmsTerminalesTbl() {
    }

    /**
     * Creates a new instance of TmsTerminalesTbl with the specified values.
     * @param terminalId the terminalId of the TmsTerminalesTbl
     */
    public TmsTerminalesTbl(BigDecimal terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * Creates a new instance of TmsTerminalesTbl with the specified values.
     * @param terminalId the terminalId of the TmsTerminalesTbl
     * @param terminalNombre the terminalNombre of the TmsTerminalesTbl
     * @param prefijoSerie the prefijoSerie of the TmsTerminalesTbl
     * @param creadoPor the creadoPor of the TmsTerminalesTbl
     * @param fechaCreacion the fechaCreacion of the TmsTerminalesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsTerminalesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsTerminalesTbl
     */
    public TmsTerminalesTbl(BigDecimal terminalId, String terminalNombre, String prefijoSerie, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.terminalId = terminalId;
        this.terminalNombre = terminalNombre;
        this.prefijoSerie = prefijoSerie;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the terminalId of this TmsTerminalesTbl.
     * @return the terminalId
     */
    public BigDecimal getTerminalId() {
        return this.terminalId;
    }

    /**
     * Sets the terminalId of this TmsTerminalesTbl to the specified value.
     * @param terminalId the new terminalId
     */
    public void setTerminalId(BigDecimal terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * Gets the terminalNombre of this TmsTerminalesTbl.
     * @return the terminalNombre
     */
    public String getTerminalNombre() {
        return this.terminalNombre;
    }

    /**
     * Sets the terminalNombre of this TmsTerminalesTbl to the specified value.
     * @param terminalNombre the new terminalNombre
     */
    public void setTerminalNombre(String terminalNombre) {
        this.terminalNombre = terminalNombre;
    }

    /**
     * Gets the terminalNumero of this TmsTerminalesTbl.
     * @return the terminalNumero
     */
    public String getTerminalNumero() {
        return this.terminalNumero;
    }

    /**
     * Sets the terminalNumero of this TmsTerminalesTbl to the specified value.
     * @param terminalNumero the new terminalNumero
     */
    public void setTerminalNumero(String terminalNumero) {
        this.terminalNumero = terminalNumero;
    }

    /**
     * Gets the descripcion of this TmsTerminalesTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsTerminalesTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the terminalIp of this TmsTerminalesTbl.
     * @return the terminalIp
     */
    public String getTerminalIp() {
        return this.terminalIp;
    }

    /**
     * Sets the terminalIp of this TmsTerminalesTbl to the specified value.
     * @param terminalIp the new terminalIp
     */
    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    /**
     * Gets the terminalGrupo of this TmsTerminalesTbl.
     * @return the terminalGrupo
     */
    public String getTerminalGrupo() {
        return this.terminalGrupo;
    }

    /**
     * Sets the terminalGrupo of this TmsTerminalesTbl to the specified value.
     * @param terminalGrupo the new terminalGrupo
     */
    public void setTerminalGrupo(String terminalGrupo) {
        this.terminalGrupo = terminalGrupo;
    }

    /**
     * Gets the puedeVender of this TmsTerminalesTbl.
     * @return the puedeVender
     */
    public String getPuedeVender() {
        return this.puedeVender;
    }

    /**
     * Sets the puedeVender of this TmsTerminalesTbl to the specified value.
     * @param puedeVender the new puedeVender
     */
    public void setPuedeVender(String puedeVender) {
        this.puedeVender = puedeVender;
    }

    /**
     * Gets the puedeReservar of this TmsTerminalesTbl.
     * @return the puedeReservar
     */
    public String getPuedeReservar() {
        return this.puedeReservar;
    }

    /**
     * Sets the puedeReservar of this TmsTerminalesTbl to the specified value.
     * @param puedeReservar the new puedeReservar
     */
    public void setPuedeReservar(String puedeReservar) {
        this.puedeReservar = puedeReservar;
    }

    /**
     * Gets the puedeRecaudar of this TmsTerminalesTbl.
     * @return the puedeRecaudar
     */
    public String getPuedeRecaudar() {
        return this.puedeRecaudar;
    }

    /**
     * Sets the puedeRecaudar of this TmsTerminalesTbl to the specified value.
     * @param puedeRecaudar the new puedeRecaudar
     */
    public void setPuedeRecaudar(String puedeRecaudar) {
        this.puedeRecaudar = puedeRecaudar;
    }

    /**
     * Gets the puedeImprimir of this TmsTerminalesTbl.
     * @return the puedeImprimir
     */
    public String getPuedeImprimir() {
        return this.puedeImprimir;
    }

    /**
     * Sets the puedeImprimir of this TmsTerminalesTbl to the specified value.
     * @param puedeImprimir the new puedeImprimir
     */
    public void setPuedeImprimir(String puedeImprimir) {
        this.puedeImprimir = puedeImprimir;
    }

    /**
     * Gets the empDifMismoFolio of this TmsTerminalesTbl.
     * @return the empDifMismoFolio
     */
    public String getEmpDifMismoFolio() {
        return this.empDifMismoFolio;
    }

    /**
     * Sets the empDifMismoFolio of this TmsTerminalesTbl to the specified value.
     * @param empDifMismoFolio the new empDifMismoFolio
     */
    public void setEmpDifMismoFolio(String empDifMismoFolio) {
        this.empDifMismoFolio = empDifMismoFolio;
    }

    /**
     * Gets the prefijoSerie of this TmsTerminalesTbl.
     * @return the prefijoSerie
     */
    public String getPrefijoSerie() {
        return this.prefijoSerie;
    }

    /**
     * Sets the prefijoSerie of this TmsTerminalesTbl to the specified value.
     * @param prefijoSerie the new prefijoSerie
     */
    public void setPrefijoSerie(String prefijoSerie) {
        this.prefijoSerie = prefijoSerie;
    }

    /**
     * Gets the adicional1 of this TmsTerminalesTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsTerminalesTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsTerminalesTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsTerminalesTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsTerminalesTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsTerminalesTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsTerminalesTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsTerminalesTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsTerminalesTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsTerminalesTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsTerminalesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTerminalesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTerminalesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTerminalesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTerminalesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTerminalesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTerminalesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTerminalesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }


    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.terminalId != null ? this.terminalId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTerminalesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTerminalesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTerminalesTbl)) {
            return false;
        }
        TmsTerminalesTbl other = (TmsTerminalesTbl)object;
        if (this.terminalId != other.terminalId && (this.terminalId == null || !this.terminalId.equals(other.terminalId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "xertms.entidad.TmsTerminalesTbl[terminalId=" + terminalId + "]";
    }
    
}
