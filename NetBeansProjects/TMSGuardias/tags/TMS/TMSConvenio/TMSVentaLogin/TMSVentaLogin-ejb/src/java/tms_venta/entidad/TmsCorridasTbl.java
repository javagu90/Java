/*
 * TmsCorridasTbl.java
 *
 * Created on 14 de noviembre de 2008, 11:20 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsCorridasTbl
 * 
 * @author asolis
 */
@Entity
@Table(name = "TMS_CORRIDAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsCorridasTbl.findByCorridaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.corridaId = :corridaId"),
        @NamedQuery(name = "TmsCorridasTbl.findByClaveCorrida", query = "SELECT t FROM TmsCorridasTbl t WHERE t.claveCorrida = :claveCorrida"),
        @NamedQuery(name = "TmsCorridasTbl.findByServicioId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.servicioId = :servicioId"),
        @NamedQuery(name = "TmsCorridasTbl.findByEmpresaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.empresaId = :empresaId"),
        @NamedQuery(name = "TmsCorridasTbl.findByRutaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.rutaId = :rutaId"),
        @NamedQuery(name = "TmsCorridasTbl.findByFechaHoraCorrida", query = "SELECT t FROM TmsCorridasTbl t WHERE t.fechaHoraCorrida = :fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasTbl.findByOrigenId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.origenId = :origenId"),
        @NamedQuery(name = "TmsCorridasTbl.findByDestinoId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.destinoId = :destinoId"),
        @NamedQuery(name = "TmsCorridasTbl.findByAutobusId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.autobusId = :autobusId"),
        @NamedQuery(name = "TmsCorridasTbl.findByAutobusOriginalId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.autobusOriginalId = :autobusOriginalId"),
        @NamedQuery(name = "TmsCorridasTbl.findByOperadorId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.operadorId = :operadorId"),
        @NamedQuery(name = "TmsCorridasTbl.findByOperadorOriginalId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.operadorOriginalId = :operadorOriginalId"),
        @NamedQuery(name = "TmsCorridasTbl.findByTipoCorrida", query = "SELECT t FROM TmsCorridasTbl t WHERE t.tipoCorrida = :tipoCorrida"),
        @NamedQuery(name = "TmsCorridasTbl.findByEstadoCorridaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.estadoCorridaId = :estadoCorridaId"),
        @NamedQuery(name = "TmsCorridasTbl.findByPlantillaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.plantillaId = :plantillaId"),
        @NamedQuery(name = "TmsCorridasTbl.findByCorridaRelacionadaId", query = "SELECT t FROM TmsCorridasTbl t WHERE t.corridaRelacionadaId = :corridaRelacionadaId"),
        @NamedQuery(name = "TmsCorridasTbl.findBySueldoOperador", query = "SELECT t FROM TmsCorridasTbl t WHERE t.sueldoOperador = :sueldoOperador"),
        @NamedQuery(name = "TmsCorridasTbl.findByNumeroContrato", query = "SELECT t FROM TmsCorridasTbl t WHERE t.numeroContrato = :numeroContrato"),
        @NamedQuery(name = "TmsCorridasTbl.findByNumeroOrden", query = "SELECT t FROM TmsCorridasTbl t WHERE t.numeroOrden = :numeroOrden"),
        @NamedQuery(name = "TmsCorridasTbl.findByMontoAnticipos", query = "SELECT t FROM TmsCorridasTbl t WHERE t.montoAnticipos = :montoAnticipos"),
        @NamedQuery(name = "TmsCorridasTbl.findByCreadoPor", query = "SELECT t FROM TmsCorridasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsCorridasTbl.findByFechaCreacion", query = "SELECT t FROM TmsCorridasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsCorridasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsCorridasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsCorridasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsCorridasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional1", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional2", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional3", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional4", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional5", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional6", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional7", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional8", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional9", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsCorridasTbl.findByAdicional10", query = "SELECT t FROM TmsCorridasTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsCorridasTbl.findByReplicacionEstado", query = "SELECT t FROM TmsCorridasTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsCorridasTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsCorridasTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsCorridasTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsCorridasTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsCorridasTbl implements Serializable {

    @Id
    @Column(name = "CORRIDA_ID", nullable = false)
    private BigDecimal corridaId;

    @Column(name = "CLAVE_CORRIDA", nullable = false)
    private String claveCorrida;

    @Column(name = "SERVICIO_ID", nullable = false)
    private long servicioId;

    @Column(name = "EMPRESA_ID", nullable = false)
    private BigInteger empresaId;

    @Column(name = "RUTA_ID", nullable = false)
    private BigInteger rutaId;

    @Column(name = "FECHA_HORA_CORRIDA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaHoraCorrida;

    @Column(name = "ORIGEN_ID", nullable = false)
    private BigInteger origenId;

    @Column(name = "DESTINO_ID", nullable = false)
    private BigInteger destinoId;

    @Column(name = "AUTOBUS_ID")
    private BigInteger autobusId;

    @Column(name = "AUTOBUS_ORIGINAL_ID")
    private BigInteger autobusOriginalId;

    @Column(name = "OPERADOR_ID")
    private BigInteger operadorId;

    @Column(name = "OPERADOR_ORIGINAL_ID")
    private BigInteger operadorOriginalId;

    @Column(name = "TIPO_CORRIDA", nullable = false)
    private String tipoCorrida;

    @Column(name = "ESTADO_CORRIDA_ID", nullable = false)
    private BigInteger estadoCorridaId;

    @Column(name = "PLANTILLA_ID", nullable = false)
    private BigInteger plantillaId;

    @Column(name = "CORRIDA_RELACIONADA_ID")
    private BigInteger corridaRelacionadaId;

    @Column(name = "SUELDO_OPERADOR")
    private BigDecimal sueldoOperador;

    @Column(name = "NUMERO_CONTRATO")
    private String numeroContrato;

    @Column(name = "NUMERO_ORDEN")
    private String numeroOrden;

    @Column(name = "MONTO_ANTICIPOS")
    private BigDecimal montoAnticipos;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

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

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;
    
    /** Creates a new instance of TmsCorridasTbl */
    public TmsCorridasTbl() {
    }

    /**
     * Creates a new instance of TmsCorridasTbl with the specified values.
     * @param corridaId the corridaId of the TmsCorridasTbl
     */
    public TmsCorridasTbl(BigDecimal corridaId) {
        this.corridaId = corridaId;
    }

    /**
     * Creates a new instance of TmsCorridasTbl with the specified values.
     * @param corridaId the corridaId of the TmsCorridasTbl
     * @param claveCorrida the claveCorrida of the TmsCorridasTbl
     * @param servicioId the servicioId of the TmsCorridasTbl
     * @param empresaId the empresaId of the TmsCorridasTbl
     * @param rutaId the rutaId of the TmsCorridasTbl
     * @param fechaHoraCorrida the fechaHoraCorrida of the TmsCorridasTbl
     * @param origenId the origenId of the TmsCorridasTbl
     * @param destinoId the destinoId of the TmsCorridasTbl
     * @param tipoCorrida the tipoCorrida of the TmsCorridasTbl
     * @param estadoCorridaId the estadoCorridaId of the TmsCorridasTbl
     * @param plantillaId the plantillaId of the TmsCorridasTbl
     * @param creadoPor the creadoPor of the TmsCorridasTbl
     * @param fechaCreacion the fechaCreacion of the TmsCorridasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsCorridasTbl
     */
    public TmsCorridasTbl(BigDecimal corridaId, String claveCorrida, long servicioId, BigInteger empresaId, BigInteger rutaId, Date fechaHoraCorrida, BigInteger origenId, BigInteger destinoId, String tipoCorrida, BigInteger estadoCorridaId, BigInteger plantillaId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor) {
        this.corridaId = corridaId;
        this.claveCorrida = claveCorrida;
        this.servicioId = servicioId;
        this.empresaId = empresaId;
        this.rutaId = rutaId;
        this.fechaHoraCorrida = fechaHoraCorrida;
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.tipoCorrida = tipoCorrida;
        this.estadoCorridaId = estadoCorridaId;
        this.plantillaId = plantillaId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the corridaId of this TmsCorridasTbl.
     * @return the corridaId
     */
    public BigDecimal getCorridaId() {
        return this.corridaId;
    }

    /**
     * Sets the corridaId of this TmsCorridasTbl to the specified value.
     * @param corridaId the new corridaId
     */
    public void setCorridaId(BigDecimal corridaId) {
        this.corridaId = corridaId;
    }

    /**
     * Gets the claveCorrida of this TmsCorridasTbl.
     * @return the claveCorrida
     */
    public String getClaveCorrida() {
        return this.claveCorrida;
    }

    /**
     * Sets the claveCorrida of this TmsCorridasTbl to the specified value.
     * @param claveCorrida the new claveCorrida
     */
    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    /**
     * Gets the servicioId of this TmsCorridasTbl.
     * @return the servicioId
     */
    public long getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsCorridasTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(long servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Gets the empresaId of this TmsCorridasTbl.
     * @return the empresaId
     */
    public BigInteger getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsCorridasTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the rutaId of this TmsCorridasTbl.
     * @return the rutaId
     */
    public BigInteger getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsCorridasTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(BigInteger rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Gets the fechaHoraCorrida of this TmsCorridasTbl.
     * @return the fechaHoraCorrida
     */
    public Date getFechaHoraCorrida() {
        return this.fechaHoraCorrida;
    }

    /**
     * Sets the fechaHoraCorrida of this TmsCorridasTbl to the specified value.
     * @param fechaHoraCorrida the new fechaHoraCorrida
     */
    public void setFechaHoraCorrida(Date fechaHoraCorrida) {
        this.fechaHoraCorrida = fechaHoraCorrida;
    }

    /**
     * Gets the origenId of this TmsCorridasTbl.
     * @return the origenId
     */
    public BigInteger getOrigenId() {
        return this.origenId;
    }

    /**
     * Sets the origenId of this TmsCorridasTbl to the specified value.
     * @param origenId the new origenId
     */
    public void setOrigenId(BigInteger origenId) {
        this.origenId = origenId;
    }

    /**
     * Gets the destinoId of this TmsCorridasTbl.
     * @return the destinoId
     */
    public BigInteger getDestinoId() {
        return this.destinoId;
    }

    /**
     * Sets the destinoId of this TmsCorridasTbl to the specified value.
     * @param destinoId the new destinoId
     */
    public void setDestinoId(BigInteger destinoId) {
        this.destinoId = destinoId;
    }

    /**
     * Gets the autobusId of this TmsCorridasTbl.
     * @return the autobusId
     */
    public BigInteger getAutobusId() {
        return this.autobusId;
    }

    /**
     * Sets the autobusId of this TmsCorridasTbl to the specified value.
     * @param autobusId the new autobusId
     */
    public void setAutobusId(BigInteger autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Gets the autobusOriginalId of this TmsCorridasTbl.
     * @return the autobusOriginalId
     */
    public BigInteger getAutobusOriginalId() {
        return this.autobusOriginalId;
    }

    /**
     * Sets the autobusOriginalId of this TmsCorridasTbl to the specified value.
     * @param autobusOriginalId the new autobusOriginalId
     */
    public void setAutobusOriginalId(BigInteger autobusOriginalId) {
        this.autobusOriginalId = autobusOriginalId;
    }

    /**
     * Gets the operadorId of this TmsCorridasTbl.
     * @return the operadorId
     */
    public BigInteger getOperadorId() {
        return this.operadorId;
    }

    /**
     * Sets the operadorId of this TmsCorridasTbl to the specified value.
     * @param operadorId the new operadorId
     */
    public void setOperadorId(BigInteger operadorId) {
        this.operadorId = operadorId;
    }

    /**
     * Gets the operadorOriginalId of this TmsCorridasTbl.
     * @return the operadorOriginalId
     */
    public BigInteger getOperadorOriginalId() {
        return this.operadorOriginalId;
    }

    /**
     * Sets the operadorOriginalId of this TmsCorridasTbl to the specified value.
     * @param operadorOriginalId the new operadorOriginalId
     */
    public void setOperadorOriginalId(BigInteger operadorOriginalId) {
        this.operadorOriginalId = operadorOriginalId;
    }

    /**
     * Gets the tipoCorrida of this TmsCorridasTbl.
     * @return the tipoCorrida
     */
    public String getTipoCorrida() {
        return this.tipoCorrida;
    }

    /**
     * Sets the tipoCorrida of this TmsCorridasTbl to the specified value.
     * @param tipoCorrida the new tipoCorrida
     */
    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    /**
     * Gets the estadoCorridaId of this TmsCorridasTbl.
     * @return the estadoCorridaId
     */
    public BigInteger getEstadoCorridaId() {
        return this.estadoCorridaId;
    }

    /**
     * Sets the estadoCorridaId of this TmsCorridasTbl to the specified value.
     * @param estadoCorridaId the new estadoCorridaId
     */
    public void setEstadoCorridaId(BigInteger estadoCorridaId) {
        this.estadoCorridaId = estadoCorridaId;
    }

    /**
     * Gets the plantillaId of this TmsCorridasTbl.
     * @return the plantillaId
     */
    public BigInteger getPlantillaId() {
        return this.plantillaId;
    }

    /**
     * Sets the plantillaId of this TmsCorridasTbl to the specified value.
     * @param plantillaId the new plantillaId
     */
    public void setPlantillaId(BigInteger plantillaId) {
        this.plantillaId = plantillaId;
    }

    /**
     * Gets the corridaRelacionadaId of this TmsCorridasTbl.
     * @return the corridaRelacionadaId
     */
    public BigInteger getCorridaRelacionadaId() {
        return this.corridaRelacionadaId;
    }

    /**
     * Sets the corridaRelacionadaId of this TmsCorridasTbl to the specified value.
     * @param corridaRelacionadaId the new corridaRelacionadaId
     */
    public void setCorridaRelacionadaId(BigInteger corridaRelacionadaId) {
        this.corridaRelacionadaId = corridaRelacionadaId;
    }

    /**
     * Gets the sueldoOperador of this TmsCorridasTbl.
     * @return the sueldoOperador
     */
    public BigDecimal getSueldoOperador() {
        return this.sueldoOperador;
    }

    /**
     * Sets the sueldoOperador of this TmsCorridasTbl to the specified value.
     * @param sueldoOperador the new sueldoOperador
     */
    public void setSueldoOperador(BigDecimal sueldoOperador) {
        this.sueldoOperador = sueldoOperador;
    }

    /**
     * Gets the numeroContrato of this TmsCorridasTbl.
     * @return the numeroContrato
     */
    public String getNumeroContrato() {
        return this.numeroContrato;
    }

    /**
     * Sets the numeroContrato of this TmsCorridasTbl to the specified value.
     * @param numeroContrato the new numeroContrato
     */
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    /**
     * Gets the numeroOrden of this TmsCorridasTbl.
     * @return the numeroOrden
     */
    public String getNumeroOrden() {
        return this.numeroOrden;
    }

    /**
     * Sets the numeroOrden of this TmsCorridasTbl to the specified value.
     * @param numeroOrden the new numeroOrden
     */
    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * Gets the montoAnticipos of this TmsCorridasTbl.
     * @return the montoAnticipos
     */
    public BigDecimal getMontoAnticipos() {
        return this.montoAnticipos;
    }

    /**
     * Sets the montoAnticipos of this TmsCorridasTbl to the specified value.
     * @param montoAnticipos the new montoAnticipos
     */
    public void setMontoAnticipos(BigDecimal montoAnticipos) {
        this.montoAnticipos = montoAnticipos;
    }

    /**
     * Gets the creadoPor of this TmsCorridasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsCorridasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsCorridasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsCorridasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsCorridasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsCorridasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsCorridasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsCorridasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the adicional1 of this TmsCorridasTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsCorridasTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsCorridasTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsCorridasTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsCorridasTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsCorridasTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsCorridasTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsCorridasTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsCorridasTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsCorridasTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsCorridasTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsCorridasTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsCorridasTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsCorridasTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsCorridasTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsCorridasTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsCorridasTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsCorridasTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsCorridasTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsCorridasTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the replicacionEstado of this TmsCorridasTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsCorridasTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsCorridasTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsCorridasTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsCorridasTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsCorridasTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.corridaId != null ? this.corridaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsCorridasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsCorridasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsCorridasTbl)) {
            return false;
        }
        TmsCorridasTbl other = (TmsCorridasTbl)object;
        if (this.corridaId != other.corridaId && (this.corridaId == null || !this.corridaId.equals(other.corridaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsCorridasTbl[corridaId=" + corridaId + "]";
    }
    
}
