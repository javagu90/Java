/*
 * TmsBoletosVentaTbl.java
 *
 * Created on 7 de enero de 2009, 01:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entidad;

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
 * Entity class TmsBoletosVentaTbl
 * 
 * @author asolis
 */
@Entity
@Table(name = "TMS_BOLETOS_VENTA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsBoletosVentaTbl.findByBoletoId", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.boletoId = :boletoId"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByEmpresa", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.empresa = :empresa"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByServicio", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.servicio = :servicio"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByCaja", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.caja = :caja"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByCorteId", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.corteId = :corteId"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByClaveCorrida", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.claveCorrida = :claveCorrida"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByClienteId", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.clienteId = :clienteId"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByNoAsiento", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.noAsiento = :noAsiento"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByNombrePasajero", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.nombrePasajero = :nombrePasajero"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByTipoPasajero", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.tipoPasajero = :tipoPasajero"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByTipoPago", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.tipoPago = :tipoPago"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByReferenciaPago", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.referenciaPago = :referenciaPago"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByImporteBoleto", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.importeBoleto = :importeBoleto"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByTipoOperacion", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.tipoOperacion = :tipoOperacion"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByReservacionId", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.reservacionId = :reservacionId"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByBoletoRelacionadoId", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.boletoRelacionadoId = :boletoRelacionadoId"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByDiasValidezBoletoAbierto", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.diasValidezBoletoAbierto = :diasValidezBoletoAbierto"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByFolioPreimpreso", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.folioPreimpreso = :folioPreimpreso"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByFolioBoleto", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.folioBoleto = :folioBoleto"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByCiudadVenta", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.ciudadVenta = :ciudadVenta"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByOrigen", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.origen = :origen"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByDestino", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.destino = :destino"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByTipoTransaccion", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.tipoTransaccion = :tipoTransaccion"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByClaveCajero", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.claveCajero = :claveCajero"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByFechaHoraVenta", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.fechaHoraVenta = :fechaHoraVenta"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAutorizadoPor", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByFechaHoraAutorizacion", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.fechaHoraAutorizacion = :fechaHoraAutorizacion"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByFechaCreacion", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional1", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional2", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional3", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional4", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional5", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional6", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional7", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional8", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional9", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByAdicional10", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByReplicacionEstado", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsBoletosVentaTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsBoletosVentaTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsBoletosVentaTbl implements Serializable {

    @Id
    @Column(name = "BOLETO_ID", nullable = false)
    private BigDecimal boletoId;

    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "SERVICIO")
    private String servicio;

    @Column(name = "CAJA")
    private String caja;

    @Column(name = "CORTE_ID")
    private BigInteger corteId;

    @Column(name = "CLAVE_CORRIDA")
    private String claveCorrida;

    @Column(name = "CLIENTE_ID")
    private BigInteger clienteId;

    @Column(name = "NO_ASIENTO")
    private String noAsiento;

    @Column(name = "NOMBRE_PASAJERO")
    private String nombrePasajero;

    @Column(name = "TIPO_PASAJERO")
    private String tipoPasajero;

    @Column(name = "TIPO_PAGO")
    private String tipoPago;

    @Column(name = "REFERENCIA_PAGO")
    private String referenciaPago;

    @Column(name = "IMPORTE_BOLETO")
    private BigDecimal importeBoleto;

    @Column(name = "TIPO_OPERACION")
    private String tipoOperacion;

    @Column(name = "RESERVACION_ID")
    private BigInteger reservacionId;

    @Column(name = "BOLETO_RELACIONADO_ID")
    private BigInteger boletoRelacionadoId;

    @Column(name = "DIAS_VALIDEZ_BOLETO_ABIERTO")
    private BigInteger diasValidezBoletoAbierto;

    @Column(name = "FOLIO_PREIMPRESO")
    private String folioPreimpreso;

    @Column(name = "FOLIO_BOLETO")
    private String folioBoleto;

    @Column(name = "CIUDAD_VENTA")
    private String ciudadVenta;

    @Column(name = "ORIGEN")
    private String origen;

    @Column(name = "DESTINO")
    private String destino;

    @Column(name = "TIPO_TRANSACCION")
    private String tipoTransaccion;

    @Column(name = "CLAVE_CAJERO")
    private String claveCajero;

    @Column(name = "FECHA_HORA_VENTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraVenta;

    @Column(name = "AUTORIZADO_POR")
    private BigInteger autorizadoPor;

    @Column(name = "FECHA_HORA_AUTORIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraAutorizacion;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

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
    
    /** Creates a new instance of TmsBoletosVentaTbl */
    public TmsBoletosVentaTbl() {
    }

    /**
     * Creates a new instance of TmsBoletosVentaTbl with the specified values.
     * @param boletoId the boletoId of the TmsBoletosVentaTbl
     */
    public TmsBoletosVentaTbl(BigDecimal boletoId) {
        this.boletoId = boletoId;
    }

    /**
     * Gets the boletoId of this TmsBoletosVentaTbl.
     * @return the boletoId
     */
    public BigDecimal getBoletoId() {
        return this.boletoId;
    }

    /**
     * Sets the boletoId of this TmsBoletosVentaTbl to the specified value.
     * @param boletoId the new boletoId
     */
    public void setBoletoId(BigDecimal boletoId) {
        this.boletoId = boletoId;
    }

    /**
     * Gets the empresa of this TmsBoletosVentaTbl.
     * @return the empresa
     */
    public String getEmpresa() {
        return this.empresa;
    }

    /**
     * Sets the empresa of this TmsBoletosVentaTbl to the specified value.
     * @param empresa the new empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Gets the servicio of this TmsBoletosVentaTbl.
     * @return the servicio
     */
    public String getServicio() {
        return this.servicio;
    }

    /**
     * Sets the servicio of this TmsBoletosVentaTbl to the specified value.
     * @param servicio the new servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Gets the caja of this TmsBoletosVentaTbl.
     * @return the caja
     */
    public String getCaja() {
        return this.caja;
    }

    /**
     * Sets the caja of this TmsBoletosVentaTbl to the specified value.
     * @param caja the new caja
     */
    public void setCaja(String caja) {
        this.caja = caja;
    }

    /**
     * Gets the corteId of this TmsBoletosVentaTbl.
     * @return the corteId
     */
    public BigInteger getCorteId() {
        return this.corteId;
    }

    /**
     * Sets the corteId of this TmsBoletosVentaTbl to the specified value.
     * @param corteId the new corteId
     */
    public void setCorteId(BigInteger corteId) {
        this.corteId = corteId;
    }

    /**
     * Gets the claveCorrida of this TmsBoletosVentaTbl.
     * @return the claveCorrida
     */
    public String getClaveCorrida() {
        return this.claveCorrida;
    }

    /**
     * Sets the claveCorrida of this TmsBoletosVentaTbl to the specified value.
     * @param claveCorrida the new claveCorrida
     */
    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    /**
     * Gets the clienteId of this TmsBoletosVentaTbl.
     * @return the clienteId
     */
    public BigInteger getClienteId() {
        return this.clienteId;
    }

    /**
     * Sets the clienteId of this TmsBoletosVentaTbl to the specified value.
     * @param clienteId the new clienteId
     */
    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Gets the noAsiento of this TmsBoletosVentaTbl.
     * @return the noAsiento
     */
    public String getNoAsiento() {
        return this.noAsiento;
    }

    /**
     * Sets the noAsiento of this TmsBoletosVentaTbl to the specified value.
     * @param noAsiento the new noAsiento
     */
    public void setNoAsiento(String noAsiento) {
        this.noAsiento = noAsiento;
    }

    /**
     * Gets the nombrePasajero of this TmsBoletosVentaTbl.
     * @return the nombrePasajero
     */
    public String getNombrePasajero() {
        return this.nombrePasajero;
    }

    /**
     * Sets the nombrePasajero of this TmsBoletosVentaTbl to the specified value.
     * @param nombrePasajero the new nombrePasajero
     */
    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    /**
     * Gets the tipoPasajero of this TmsBoletosVentaTbl.
     * @return the tipoPasajero
     */
    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    /**
     * Sets the tipoPasajero of this TmsBoletosVentaTbl to the specified value.
     * @param tipoPasajero the new tipoPasajero
     */
    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    /**
     * Gets the tipoPago of this TmsBoletosVentaTbl.
     * @return the tipoPago
     */
    public String getTipoPago() {
        return this.tipoPago;
    }

    /**
     * Sets the tipoPago of this TmsBoletosVentaTbl to the specified value.
     * @param tipoPago the new tipoPago
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * Gets the referenciaPago of this TmsBoletosVentaTbl.
     * @return the referenciaPago
     */
    public String getReferenciaPago() {
        return this.referenciaPago;
    }

    /**
     * Sets the referenciaPago of this TmsBoletosVentaTbl to the specified value.
     * @param referenciaPago the new referenciaPago
     */
    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    /**
     * Gets the importeBoleto of this TmsBoletosVentaTbl.
     * @return the importeBoleto
     */
    public BigDecimal getImporteBoleto() {
        return this.importeBoleto;
    }

    /**
     * Sets the importeBoleto of this TmsBoletosVentaTbl to the specified value.
     * @param importeBoleto the new importeBoleto
     */
    public void setImporteBoleto(BigDecimal importeBoleto) {
        this.importeBoleto = importeBoleto;
    }

    /**
     * Gets the tipoOperacion of this TmsBoletosVentaTbl.
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return this.tipoOperacion;
    }

    /**
     * Sets the tipoOperacion of this TmsBoletosVentaTbl to the specified value.
     * @param tipoOperacion the new tipoOperacion
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * Gets the reservacionId of this TmsBoletosVentaTbl.
     * @return the reservacionId
     */
    public BigInteger getReservacionId() {
        return this.reservacionId;
    }

    /**
     * Sets the reservacionId of this TmsBoletosVentaTbl to the specified value.
     * @param reservacionId the new reservacionId
     */
    public void setReservacionId(BigInteger reservacionId) {
        this.reservacionId = reservacionId;
    }

    /**
     * Gets the boletoRelacionadoId of this TmsBoletosVentaTbl.
     * @return the boletoRelacionadoId
     */
    public BigInteger getBoletoRelacionadoId() {
        return this.boletoRelacionadoId;
    }

    /**
     * Sets the boletoRelacionadoId of this TmsBoletosVentaTbl to the specified value.
     * @param boletoRelacionadoId the new boletoRelacionadoId
     */
    public void setBoletoRelacionadoId(BigInteger boletoRelacionadoId) {
        this.boletoRelacionadoId = boletoRelacionadoId;
    }

    /**
     * Gets the diasValidezBoletoAbierto of this TmsBoletosVentaTbl.
     * @return the diasValidezBoletoAbierto
     */
    public BigInteger getDiasValidezBoletoAbierto() {
        return this.diasValidezBoletoAbierto;
    }

    /**
     * Sets the diasValidezBoletoAbierto of this TmsBoletosVentaTbl to the specified value.
     * @param diasValidezBoletoAbierto the new diasValidezBoletoAbierto
     */
    public void setDiasValidezBoletoAbierto(BigInteger diasValidezBoletoAbierto) {
        this.diasValidezBoletoAbierto = diasValidezBoletoAbierto;
    }

    /**
     * Gets the folioPreimpreso of this TmsBoletosVentaTbl.
     * @return the folioPreimpreso
     */
    public String getFolioPreimpreso() {
        return this.folioPreimpreso;
    }

    /**
     * Sets the folioPreimpreso of this TmsBoletosVentaTbl to the specified value.
     * @param folioPreimpreso the new folioPreimpreso
     */
    public void setFolioPreimpreso(String folioPreimpreso) {
        this.folioPreimpreso = folioPreimpreso;
    }

    /**
     * Gets the folioBoleto of this TmsBoletosVentaTbl.
     * @return the folioBoleto
     */
    public String getFolioBoleto() {
        return this.folioBoleto;
    }

    /**
     * Sets the folioBoleto of this TmsBoletosVentaTbl to the specified value.
     * @param folioBoleto the new folioBoleto
     */
    public void setFolioBoleto(String folioBoleto) {
        this.folioBoleto = folioBoleto;
    }

    /**
     * Gets the ciudadVenta of this TmsBoletosVentaTbl.
     * @return the ciudadVenta
     */
    public String getCiudadVenta() {
        return this.ciudadVenta;
    }

    /**
     * Sets the ciudadVenta of this TmsBoletosVentaTbl to the specified value.
     * @param ciudadVenta the new ciudadVenta
     */
    public void setCiudadVenta(String ciudadVenta) {
        this.ciudadVenta = ciudadVenta;
    }

    /**
     * Gets the origen of this TmsBoletosVentaTbl.
     * @return the origen
     */
    public String getOrigen() {
        return this.origen;
    }

    /**
     * Sets the origen of this TmsBoletosVentaTbl to the specified value.
     * @param origen the new origen
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * Gets the destino of this TmsBoletosVentaTbl.
     * @return the destino
     */
    public String getDestino() {
        return this.destino;
    }

    /**
     * Sets the destino of this TmsBoletosVentaTbl to the specified value.
     * @param destino the new destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Gets the tipoTransaccion of this TmsBoletosVentaTbl.
     * @return the tipoTransaccion
     */
    public String getTipoTransaccion() {
        return this.tipoTransaccion;
    }

    /**
     * Sets the tipoTransaccion of this TmsBoletosVentaTbl to the specified value.
     * @param tipoTransaccion the new tipoTransaccion
     */
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * Gets the claveCajero of this TmsBoletosVentaTbl.
     * @return the claveCajero
     */
    public String getClaveCajero() {
        return this.claveCajero;
    }

    /**
     * Sets the claveCajero of this TmsBoletosVentaTbl to the specified value.
     * @param claveCajero the new claveCajero
     */
    public void setClaveCajero(String claveCajero) {
        this.claveCajero = claveCajero;
    }

    /**
     * Gets the fechaHoraVenta of this TmsBoletosVentaTbl.
     * @return the fechaHoraVenta
     */
    public Date getFechaHoraVenta() {
        return this.fechaHoraVenta;
    }

    /**
     * Sets the fechaHoraVenta of this TmsBoletosVentaTbl to the specified value.
     * @param fechaHoraVenta the new fechaHoraVenta
     */
    public void setFechaHoraVenta(Date fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    /**
     * Gets the autorizadoPor of this TmsBoletosVentaTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsBoletosVentaTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the fechaHoraAutorizacion of this TmsBoletosVentaTbl.
     * @return the fechaHoraAutorizacion
     */
    public Date getFechaHoraAutorizacion() {
        return this.fechaHoraAutorizacion;
    }

    /**
     * Sets the fechaHoraAutorizacion of this TmsBoletosVentaTbl to the specified value.
     * @param fechaHoraAutorizacion the new fechaHoraAutorizacion
     */
    public void setFechaHoraAutorizacion(Date fechaHoraAutorizacion) {
        this.fechaHoraAutorizacion = fechaHoraAutorizacion;
    }

    /**
     * Gets the fechaCreacion of this TmsBoletosVentaTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsBoletosVentaTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsBoletosVentaTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsBoletosVentaTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the adicional1 of this TmsBoletosVentaTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsBoletosVentaTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsBoletosVentaTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsBoletosVentaTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsBoletosVentaTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsBoletosVentaTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsBoletosVentaTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsBoletosVentaTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsBoletosVentaTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsBoletosVentaTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsBoletosVentaTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the replicacionEstado of this TmsBoletosVentaTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsBoletosVentaTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsBoletosVentaTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsBoletosVentaTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsBoletosVentaTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsBoletosVentaTbl to the specified value.
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
        hash += (this.boletoId != null ? this.boletoId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsBoletosVentaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsBoletosVentaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsBoletosVentaTbl)) {
            return false;
        }
        TmsBoletosVentaTbl other = (TmsBoletosVentaTbl)object;
        if (this.boletoId != other.boletoId && (this.boletoId == null || !this.boletoId.equals(other.boletoId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsBoletosVentaTbl[boletoId=" + boletoId + "]";
    }
    
}
