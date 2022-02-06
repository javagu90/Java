/*
 * TmsTarjetasViajeTbl.java
 *
 * Created on 8 de diciembre de 2007, 08:00 PM
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity class TmsTarjetasViajeTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_TARJETAS_VIAJE_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByTarjetaViajeId", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.tarjetaViajeId = :tarjetaViajeId"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByFolioTarjeta", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.folioTarjeta = :folioTarjeta"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByCorridaId", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.corridaId = :corridaId"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByEstadoTarjetaId", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.estadoTarjetaId = :estadoTarjetaId"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNumeroImpresion", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.numeroImpresion = :numeroImpresion"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoAdultosAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noAdultosAbordados = :noAdultosAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoAdultosAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoAdultosAbordados = :montoAdultosAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoEstudiantesAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noEstudiantesAbordados = :noEstudiantesAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoEstudiantesAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoEstudiantesAbordados = :montoEstudiantesAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoSenectudAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noSenectudAbordados = :noSenectudAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoSenectudAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoSenectudAbordados = :montoSenectudAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoMenoresAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noMenoresAbordados = :noMenoresAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoMenoresAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoMenoresAbordados = :montoMenoresAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoProfesorAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noProfesorAbordados = :noProfesorAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoProfesorAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoProfesorAbordados = :montoProfesorAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByNoEspecialAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.noEspecialAbordados = :noEspecialAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMontoEspecialAbordados", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.montoEspecialAbordados = :montoEspecialAbordados"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByMotivoReimpresion", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.motivoReimpresion = :motivoReimpresion"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByOperador", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.operador = :operador"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAutobus", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.autobus = :autobus"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional1", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional2", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional3", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional4", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional5", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional6", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional7", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional8", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional9", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByAdicional10", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByCreadoPor", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByFechaCreacion", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTarjetasViajeTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTarjetasViajeTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsTarjetasViajeTbl implements Serializable {
    @SequenceGenerator(name="TMS_TV_SEQ",sequenceName="TMS_TARJETAS_VIAJE_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_TV_SEQ")
    @Id
    @Column(name = "TARJETA_VIAJE_ID", nullable = false)
    private BigDecimal tarjetaViajeId;

    @Column(name = "FOLIO_TARJETA", nullable = false)
    private String folioTarjeta;

    @Column(name = "CORRIDA_ID", nullable = false)
    private BigInteger corridaId;

    @Column(name = "ESTADO_TARJETA_ID", nullable = false)
    private BigInteger estadoTarjetaId;

    @Column(name = "NUMERO_IMPRESION")
    private BigInteger numeroImpresion;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;
    
    @Column(name = "NO_ADULTOS_ABORDADOS", nullable = false)
    private BigInteger noAdultosAbordados;

    @Column(name = "MONTO_ADULTOS_ABORDADOS", nullable = false)
    private BigDecimal montoAdultosAbordados;

    @Column(name = "NO_ESTUDIANTES_ABORDADOS", nullable = false)
    private BigInteger noEstudiantesAbordados;

    @Column(name = "MONTO_ESTUDIANTES_ABORDADOS", nullable = false)
    private BigDecimal montoEstudiantesAbordados;

    @Column(name = "NO_SENECTUD_ABORDADOS", nullable = false)
    private BigInteger noSenectudAbordados;

    @Column(name = "MONTO_SENECTUD_ABORDADOS", nullable = false)
    private BigDecimal montoSenectudAbordados;

    @Column(name = "NO_MENORES_ABORDADOS", nullable = false)
    private BigInteger noMenoresAbordados;

    @Column(name = "MONTO_MENORES_ABORDADOS", nullable = false)
    private BigDecimal montoMenoresAbordados;

    @Column(name = "NO_PROFESOR_ABORDADOS", nullable = false)
    private BigInteger noProfesorAbordados;

    @Column(name = "MONTO_PROFESOR_ABORDADOS", nullable = false)
    private BigDecimal montoProfesorAbordados;

    @Column(name = "NO_ESPECIAL_ABORDADOS", nullable = false)
    private BigInteger noEspecialAbordados;

    @Column(name = "MONTO_ESPECIAL_ABORDADOS", nullable = false)
    private BigDecimal montoEspecialAbordados;

    @Column(name = "MOTIVO_REIMPRESION")
    private String motivoReimpresion;

    @Column(name = "OPERADOR")
    private String operador;

    @Column(name = "AUTOBUS")
    private String autobus;

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

    @Column(name = "ADICIONAL6")
    private String adicional6;

    @Column(name = "ADICIONAL7")
    private String adicional7;

    @Column(name = "ADICIONAL8")
    private String adicional8;

    @Column(name = "ADICIONAL9")
    private String adicional9;

    @Column(name = "ADICIONAL10")
    private String adicional10;

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

    @Transient
    private String recaudacionAutomatica;
    

    /** Creates a new instance of TmsTarjetasViajeTbl */
    public TmsTarjetasViajeTbl() {
    }

    /**
     * Creates a new instance of TmsTarjetasViajeTbl with the specified values.
     * @param tarjetaViajeId the tarjetaViajeId of the TmsTarjetasViajeTbl
     */
    public TmsTarjetasViajeTbl(BigDecimal tarjetaViajeId) {
        this.tarjetaViajeId = tarjetaViajeId;
    }

    /**
     * Creates a new instance of TmsTarjetasViajeTbl with the specified values.
     * @param tarjetaViajeId the tarjetaViajeId of the TmsTarjetasViajeTbl
     * @param folioTarjeta the folioTarjeta of the TmsTarjetasViajeTbl
     * @param corridaId the corridaId of the TmsTarjetasViajeTbl
     * @param estadoTarjetaId the estadoTarjetaId of the TmsTarjetasViajeTbl
     * @param noAdultosAbordados the noAdultosAbordados of the TmsTarjetasViajeTbl
     * @param montoAdultosAbordados the montoAdultosAbordados of the TmsTarjetasViajeTbl
     * @param noEstudiantesAbordados the noEstudiantesAbordados of the TmsTarjetasViajeTbl
     * @param montoEstudiantesAbordados the montoEstudiantesAbordados of the TmsTarjetasViajeTbl
     * @param noSenectudAbordados the noSenectudAbordados of the TmsTarjetasViajeTbl
     * @param montoSenectudAbordados the montoSenectudAbordados of the TmsTarjetasViajeTbl
     * @param noMenoresAbordados the noMenoresAbordados of the TmsTarjetasViajeTbl
     * @param montoMenoresAbordados the montoMenoresAbordados of the TmsTarjetasViajeTbl
     * @param noProfesorAbordados the noProfesorAbordados of the TmsTarjetasViajeTbl
     * @param montoProfesorAbordados the montoProfesorAbordados of the TmsTarjetasViajeTbl
     * @param noEspecialAbordados the noEspecialAbordados of the TmsTarjetasViajeTbl
     * @param montoEspecialAbordados the montoEspecialAbordados of the TmsTarjetasViajeTbl
     * @param creadoPor the creadoPor of the TmsTarjetasViajeTbl
     * @param fechaCreacion the fechaCreacion of the TmsTarjetasViajeTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsTarjetasViajeTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsTarjetasViajeTbl
     */
    public TmsTarjetasViajeTbl(BigDecimal tarjetaViajeId, String folioTarjeta, BigInteger corridaId, BigInteger estadoTarjetaId, BigInteger noAdultosAbordados, BigDecimal montoAdultosAbordados, BigInteger noEstudiantesAbordados, BigDecimal montoEstudiantesAbordados, BigInteger noSenectudAbordados, BigDecimal montoSenectudAbordados, BigInteger noMenoresAbordados, BigDecimal montoMenoresAbordados, BigInteger noProfesorAbordados, BigDecimal montoProfesorAbordados, BigInteger noEspecialAbordados, BigDecimal montoEspecialAbordados, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.tarjetaViajeId = tarjetaViajeId;
        this.folioTarjeta = folioTarjeta;
        this.corridaId = corridaId;
        this.estadoTarjetaId = estadoTarjetaId;
        this.noAdultosAbordados = noAdultosAbordados;
        this.montoAdultosAbordados = montoAdultosAbordados;
        this.noEstudiantesAbordados = noEstudiantesAbordados;
        this.montoEstudiantesAbordados = montoEstudiantesAbordados;
        this.noSenectudAbordados = noSenectudAbordados;
        this.montoSenectudAbordados = montoSenectudAbordados;
        this.noMenoresAbordados = noMenoresAbordados;
        this.montoMenoresAbordados = montoMenoresAbordados;
        this.noProfesorAbordados = noProfesorAbordados;
        this.montoProfesorAbordados = montoProfesorAbordados;
        this.noEspecialAbordados = noEspecialAbordados;
        this.montoEspecialAbordados = montoEspecialAbordados;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tarjetaViajeId of this TmsTarjetasViajeTbl.
     * @return the tarjetaViajeId
     */
    public BigDecimal getTarjetaViajeId() {
        return this.tarjetaViajeId;
    }

    /**
     * Sets the tarjetaViajeId of this TmsTarjetasViajeTbl to the specified value.
     * @param tarjetaViajeId the new tarjetaViajeId
     */
    public void setTarjetaViajeId(BigDecimal tarjetaViajeId) {
        this.tarjetaViajeId = tarjetaViajeId;
    }

    /**
     * Gets the folioTarjeta of this TmsTarjetasViajeTbl.
     * @return the folioTarjeta
     */
    public String getFolioTarjeta() {
        return this.folioTarjeta;
    }

    /**
     * Sets the folioTarjeta of this TmsTarjetasViajeTbl to the specified value.
     * @param folioTarjeta the new folioTarjeta
     */
    public void setFolioTarjeta(String folioTarjeta) {
        this.folioTarjeta = folioTarjeta;
    }

    /**
     * Gets the corridaId of this TmsTarjetasViajeTbl.
     * @return the corridaId
     */
    public BigInteger getCorridaId() {
        return this.corridaId;
    }

    /**
     * Sets the corridaId of this TmsTarjetasViajeTbl to the specified value.
     * @param corridaId the new corridaId
     */
    public void setCorridaId(BigInteger corridaId) {
        this.corridaId = corridaId;
    }

    /**
     * Gets the estadoTarjetaId of this TmsTarjetasViajeTbl.
     * @return the estadoTarjetaId
     */
    public BigInteger getEstadoTarjetaId() {
        return this.estadoTarjetaId;
    }

    /**
     * Sets the estadoTarjetaId of this TmsTarjetasViajeTbl to the specified value.
     * @param estadoTarjetaId the new estadoTarjetaId
     */
    public void setEstadoTarjetaId(BigInteger estadoTarjetaId) {
        this.estadoTarjetaId = estadoTarjetaId;
    }

    /**
     * Gets the numeroImpresion of this TmsTarjetasViajeTbl.
     * @return the numeroImpresion
     */
    public BigInteger getNumeroImpresion() {
        return this.numeroImpresion;
    }

    /**
     * Sets the numeroImpresion of this TmsTarjetasViajeTbl to the specified value.
     * @param numeroImpresion the new numeroImpresion
     */
    public void setNumeroImpresion(BigInteger numeroImpresion) {
        this.numeroImpresion = numeroImpresion;
    }

    /**
     * Gets the noAdultosAbordados of this TmsTarjetasViajeTbl.
     * @return the noAdultosAbordados
     */
    public BigInteger getNoAdultosAbordados() {
        return this.noAdultosAbordados;
    }

    /**
     * Sets the noAdultosAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noAdultosAbordados the new noAdultosAbordados
     */
    public void setNoAdultosAbordados(BigInteger noAdultosAbordados) {
        this.noAdultosAbordados = noAdultosAbordados;
    }

    /**
     * Gets the montoAdultosAbordados of this TmsTarjetasViajeTbl.
     * @return the montoAdultosAbordados
     */
    public BigDecimal getMontoAdultosAbordados() {
        return this.montoAdultosAbordados;
    }

    /**
     * Sets the montoAdultosAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoAdultosAbordados the new montoAdultosAbordados
     */
    public void setMontoAdultosAbordados(BigDecimal montoAdultosAbordados) {
        this.montoAdultosAbordados = montoAdultosAbordados;
    }

    /**
     * Gets the noEstudiantesAbordados of this TmsTarjetasViajeTbl.
     * @return the noEstudiantesAbordados
     */
    public BigInteger getNoEstudiantesAbordados() {
        return this.noEstudiantesAbordados;
    }

    /**
     * Sets the noEstudiantesAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noEstudiantesAbordados the new noEstudiantesAbordados
     */
    public void setNoEstudiantesAbordados(BigInteger noEstudiantesAbordados) {
        this.noEstudiantesAbordados = noEstudiantesAbordados;
    }

    /**
     * Gets the montoEstudiantesAbordados of this TmsTarjetasViajeTbl.
     * @return the montoEstudiantesAbordados
     */
    public BigDecimal getMontoEstudiantesAbordados() {
        return this.montoEstudiantesAbordados;
    }

    /**
     * Sets the montoEstudiantesAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoEstudiantesAbordados the new montoEstudiantesAbordados
     */
    public void setMontoEstudiantesAbordados(BigDecimal montoEstudiantesAbordados) {
        this.montoEstudiantesAbordados = montoEstudiantesAbordados;
    }

    /**
     * Gets the noSenectudAbordados of this TmsTarjetasViajeTbl.
     * @return the noSenectudAbordados
     */
    public BigInteger getNoSenectudAbordados() {
        return this.noSenectudAbordados;
    }

    /**
     * Sets the noSenectudAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noSenectudAbordados the new noSenectudAbordados
     */
    public void setNoSenectudAbordados(BigInteger noSenectudAbordados) {
        this.noSenectudAbordados = noSenectudAbordados;
    }

    /**
     * Gets the montoSenectudAbordados of this TmsTarjetasViajeTbl.
     * @return the montoSenectudAbordados
     */
    public BigDecimal getMontoSenectudAbordados() {
        return this.montoSenectudAbordados;
    }

    /**
     * Sets the montoSenectudAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoSenectudAbordados the new montoSenectudAbordados
     */
    public void setMontoSenectudAbordados(BigDecimal montoSenectudAbordados) {
        this.montoSenectudAbordados = montoSenectudAbordados;
    }

    /**
     * Gets the noMenoresAbordados of this TmsTarjetasViajeTbl.
     * @return the noMenoresAbordados
     */
    public BigInteger getNoMenoresAbordados() {
        return this.noMenoresAbordados;
    }

    /**
     * Sets the noMenoresAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noMenoresAbordados the new noMenoresAbordados
     */
    public void setNoMenoresAbordados(BigInteger noMenoresAbordados) {
        this.noMenoresAbordados = noMenoresAbordados;
    }

    /**
     * Gets the montoMenoresAbordados of this TmsTarjetasViajeTbl.
     * @return the montoMenoresAbordados
     */
    public BigDecimal getMontoMenoresAbordados() {
        return this.montoMenoresAbordados;
    }

    /**
     * Sets the montoMenoresAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoMenoresAbordados the new montoMenoresAbordados
     */
    public void setMontoMenoresAbordados(BigDecimal montoMenoresAbordados) {
        this.montoMenoresAbordados = montoMenoresAbordados;
    }

    /**
     * Gets the noProfesorAbordados of this TmsTarjetasViajeTbl.
     * @return the noProfesorAbordados
     */
    public BigInteger getNoProfesorAbordados() {
        return this.noProfesorAbordados;
    }

    /**
     * Sets the noProfesorAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noProfesorAbordados the new noProfesorAbordados
     */
    public void setNoProfesorAbordados(BigInteger noProfesorAbordados) {
        this.noProfesorAbordados = noProfesorAbordados;
    }

    /**
     * Gets the montoProfesorAbordados of this TmsTarjetasViajeTbl.
     * @return the montoProfesorAbordados
     */
    public BigDecimal getMontoProfesorAbordados() {
        return this.montoProfesorAbordados;
    }

    /**
     * Sets the montoProfesorAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoProfesorAbordados the new montoProfesorAbordados
     */
    public void setMontoProfesorAbordados(BigDecimal montoProfesorAbordados) {
        this.montoProfesorAbordados = montoProfesorAbordados;
    }

    /**
     * Gets the noEspecialAbordados of this TmsTarjetasViajeTbl.
     * @return the noEspecialAbordados
     */
    public BigInteger getNoEspecialAbordados() {
        return this.noEspecialAbordados;
    }

    /**
     * Sets the noEspecialAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param noEspecialAbordados the new noEspecialAbordados
     */
    public void setNoEspecialAbordados(BigInteger noEspecialAbordados) {
        this.noEspecialAbordados = noEspecialAbordados;
    }

    /**
     * Gets the montoEspecialAbordados of this TmsTarjetasViajeTbl.
     * @return the montoEspecialAbordados
     */
    public BigDecimal getMontoEspecialAbordados() {
        return this.montoEspecialAbordados;
    }

    /**
     * Sets the montoEspecialAbordados of this TmsTarjetasViajeTbl to the specified value.
     * @param montoEspecialAbordados the new montoEspecialAbordados
     */
    public void setMontoEspecialAbordados(BigDecimal montoEspecialAbordados) {
        this.montoEspecialAbordados = montoEspecialAbordados;
    }

    /**
     * Gets the motivoReimpresion of this TmsTarjetasViajeTbl.
     * @return the motivoReimpresion
     */
    public String getMotivoReimpresion() {
        return this.motivoReimpresion;
    }

    /**
     * Sets the motivoReimpresion of this TmsTarjetasViajeTbl to the specified value.
     * @param motivoReimpresion the new motivoReimpresion
     */
    public void setMotivoReimpresion(String motivoReimpresion) {
        this.motivoReimpresion = motivoReimpresion;
    }

    /**
     * Gets the operador of this TmsTarjetasViajeTbl.
     * @return the operador
     */
    public String getOperador() {
        return this.operador;
    }

    /**
     * Sets the operador of this TmsTarjetasViajeTbl to the specified value.
     * @param operador the new operador
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    /**
     * Gets the autobus of this TmsTarjetasViajeTbl.
     * @return the autobus
     */
    public String getAutobus() {
        return this.autobus;
    }

    /**
     * Sets the autobus of this TmsTarjetasViajeTbl to the specified value.
     * @param autobus the new autobus
     */
    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    /**
     * Gets the adicional1 of this TmsTarjetasViajeTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsTarjetasViajeTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsTarjetasViajeTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsTarjetasViajeTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsTarjetasViajeTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsTarjetasViajeTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsTarjetasViajeTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsTarjetasViajeTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsTarjetasViajeTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsTarjetasViajeTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsTarjetasViajeTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the creadoPor of this TmsTarjetasViajeTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTarjetasViajeTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTarjetasViajeTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTarjetasViajeTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTarjetasViajeTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTarjetasViajeTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTarjetasViajeTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTarjetasViajeTbl to the specified value.
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
        hash += (this.tarjetaViajeId != null ? this.tarjetaViajeId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTarjetasViajeTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTarjetasViajeTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTarjetasViajeTbl)) {
            return false;
        }
        TmsTarjetasViajeTbl other = (TmsTarjetasViajeTbl)object;
        if (this.tarjetaViajeId != other.tarjetaViajeId && (this.tarjetaViajeId == null || !this.tarjetaViajeId.equals(other.tarjetaViajeId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuertas.entidad.TmsTarjetasViajeTbl[tarjetaViajeId=" + tarjetaViajeId + "]";
    }

    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * @return the recaudacionAutomatica
     */
    public String getRecaudacionAutomatica() {
        return recaudacionAutomatica;
    }

    /**
     * @param recaudacionAutomatica the recaudacionAutomatica to set
     */
    public void setRecaudacionAutomatica(String recaudacionAutomatica) {
        this.recaudacionAutomatica = recaudacionAutomatica;
    }
    
}
