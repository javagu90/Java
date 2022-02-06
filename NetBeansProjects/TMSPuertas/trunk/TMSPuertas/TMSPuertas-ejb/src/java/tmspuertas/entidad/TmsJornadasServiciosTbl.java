/*
 * TmsJornadasServiciosTbl.java
 *
 * Created on 9 de octubre de 2007, 10:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsJornadasServiciosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_JORNADAS_SERVICIOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByJornadaServicioId", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.jornadaServicioId = :jornadaServicioId"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByNombreJornada", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.nombreJornada = :nombreJornada"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByDescripcion", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByDiasTrabajo", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.diasTrabajo = :diasTrabajo"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByDiasDescanso", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.diasDescanso = :diasDescanso"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByHoraInicial", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.horaInicial = :horaInicial"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByHoraFinal", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.horaFinal = :horaFinal"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByHabilitado", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByCreadoPor", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByFechaCreacion", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsJornadasServiciosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsJornadasServiciosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsJornadasServiciosTbl implements Serializable {

    @Id
    @Column(name = "JORNADA_SERVICIO_ID", nullable = false)
    private BigDecimal jornadaServicioId;

    @Column(name = "NOMBRE_JORNADA", nullable = false)
    private String nombreJornada;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "DIAS_TRABAJO")
    private BigInteger diasTrabajo;

    @Column(name = "DIAS_DESCANSO")
    private BigInteger diasDescanso;

    @Column(name = "HORA_INICIAL")
    @Temporal(TemporalType.DATE)
    private Date horaInicial;

    @Column(name = "HORA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date horaFinal;

    @Column(name = "HABILITADO")
    private String habilitado;

    @Column(name = "CREADO_POR")
    private Integer creadoPor;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaServicioId")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection;

    @JoinColumn(name = "TIPO_JORNADA_ID", referencedColumnName = "TIPO_JORNADA_ID")
    @ManyToOne
    private TmsTiposJornadaTbl tipoJornadaId;
    
    /** Creates a new instance of TmsJornadasServiciosTbl */
    public TmsJornadasServiciosTbl() {
    }

    /**
     * Creates a new instance of TmsJornadasServiciosTbl with the specified values.
     * @param jornadaServicioId the jornadaServicioId of the TmsJornadasServiciosTbl
     */
    public TmsJornadasServiciosTbl(BigDecimal jornadaServicioId) {
        this.jornadaServicioId = jornadaServicioId;
    }

    /**
     * Creates a new instance of TmsJornadasServiciosTbl with the specified values.
     * @param jornadaServicioId the jornadaServicioId of the TmsJornadasServiciosTbl
     * @param nombreJornada the nombreJornada of the TmsJornadasServiciosTbl
     */
    public TmsJornadasServiciosTbl(BigDecimal jornadaServicioId, String nombreJornada) {
        this.jornadaServicioId = jornadaServicioId;
        this.nombreJornada = nombreJornada;
    }

    /**
     * Gets the jornadaServicioId of this TmsJornadasServiciosTbl.
     * @return the jornadaServicioId
     */
    public BigDecimal getJornadaServicioId() {
        return this.jornadaServicioId;
    }

    /**
     * Sets the jornadaServicioId of this TmsJornadasServiciosTbl to the specified value.
     * @param jornadaServicioId the new jornadaServicioId
     */
    public void setJornadaServicioId(BigDecimal jornadaServicioId) {
        this.jornadaServicioId = jornadaServicioId;
    }

    /**
     * Gets the nombreJornada of this TmsJornadasServiciosTbl.
     * @return the nombreJornada
     */
    public String getNombreJornada() {
        return this.nombreJornada;
    }

    /**
     * Sets the nombreJornada of this TmsJornadasServiciosTbl to the specified value.
     * @param nombreJornada the new nombreJornada
     */
    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    /**
     * Gets the descripcion of this TmsJornadasServiciosTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsJornadasServiciosTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the diasTrabajo of this TmsJornadasServiciosTbl.
     * @return the diasTrabajo
     */
    public BigInteger getDiasTrabajo() {
        return this.diasTrabajo;
    }

    /**
     * Sets the diasTrabajo of this TmsJornadasServiciosTbl to the specified value.
     * @param diasTrabajo the new diasTrabajo
     */
    public void setDiasTrabajo(BigInteger diasTrabajo) {
        this.diasTrabajo = diasTrabajo;
    }

    /**
     * Gets the diasDescanso of this TmsJornadasServiciosTbl.
     * @return the diasDescanso
     */
    public BigInteger getDiasDescanso() {
        return this.diasDescanso;
    }

    /**
     * Sets the diasDescanso of this TmsJornadasServiciosTbl to the specified value.
     * @param diasDescanso the new diasDescanso
     */
    public void setDiasDescanso(BigInteger diasDescanso) {
        this.diasDescanso = diasDescanso;
    }

    /**
     * Gets the horaInicial of this TmsJornadasServiciosTbl.
     * @return the horaInicial
     */
    public Date getHoraInicial() {
        return this.horaInicial;
    }

    /**
     * Sets the horaInicial of this TmsJornadasServiciosTbl to the specified value.
     * @param horaInicial the new horaInicial
     */
    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * Gets the horaFinal of this TmsJornadasServiciosTbl.
     * @return the horaFinal
     */
    public Date getHoraFinal() {
        return this.horaFinal;
    }

    /**
     * Sets the horaFinal of this TmsJornadasServiciosTbl to the specified value.
     * @param horaFinal the new horaFinal
     */
    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Gets the habilitado of this TmsJornadasServiciosTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsJornadasServiciosTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsJornadasServiciosTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsJornadasServiciosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsJornadasServiciosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsJornadasServiciosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsJornadasServiciosTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsJornadasServiciosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsJornadasServiciosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsJornadasServiciosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsOperadoresTblCollection of this TmsJornadasServiciosTbl.
     * @return the tmsOperadoresTblCollection
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection() {
        return this.tmsOperadoresTblCollection;
    }

    /**
     * Sets the tmsOperadoresTblCollection of this TmsJornadasServiciosTbl to the specified value.
     * @param tmsOperadoresTblCollection the new tmsOperadoresTblCollection
     */
    public void setTmsOperadoresTblCollection(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection) {
        this.tmsOperadoresTblCollection = tmsOperadoresTblCollection;
    }

    /**
     * Gets the tipoJornadaId of this TmsJornadasServiciosTbl.
     * @return the tipoJornadaId
     */
    public TmsTiposJornadaTbl getTipoJornadaId() {
        return this.tipoJornadaId;
    }

    /**
     * Sets the tipoJornadaId of this TmsJornadasServiciosTbl to the specified value.
     * @param tipoJornadaId the new tipoJornadaId
     */
    public void setTipoJornadaId(TmsTiposJornadaTbl tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.jornadaServicioId != null ? this.jornadaServicioId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsJornadasServiciosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsJornadasServiciosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsJornadasServiciosTbl)) {
            return false;
        }
        TmsJornadasServiciosTbl other = (TmsJornadasServiciosTbl)object;
        if (this.jornadaServicioId != other.jornadaServicioId && (this.jornadaServicioId == null || !this.jornadaServicioId.equals(other.jornadaServicioId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsJornadasServiciosTbl[jornadaServicioId=" + jornadaServicioId + "]";
    }
    
}
