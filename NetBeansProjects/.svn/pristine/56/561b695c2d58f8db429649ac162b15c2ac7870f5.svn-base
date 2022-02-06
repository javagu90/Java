/*
 * TmsMonitorCorridaV.java
 *
 * Created on 16 de octubre de 2007, 09:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsMonitorCorridaV.findAll", 
    query = "select o from TmsMonitorCorridaV o")
@Table(name = "TMS_MONITOR_CORRIDA_V")
public class TmsMonitorCorridaV implements Serializable {
    private String autobus;
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    @Column(name="AUTOBUS_ORIGINAL")
    private String autobusOriginal;
    @Column(name="AUTOBUS_ORIGINAL_ID", nullable = false)
    private Long autobusOriginalId;
    @Column(name="CLAVE_CORRIDA", nullable = false)
    private String claveCorrida;
    @Column(name="CORRIDA_CANCELADA")
    private String corridaCancelada;
    @Column(name="CORRIDA_CANCELADA_ID")
    private Long corridaCanceladaId;
    @Id
    @Column(name="CORRIDA_ID", nullable = false)
    private Long corridaId;
    private String destino;
    @Column(name="DESTINO_ID", nullable = false)
    private Long destinoId;
    private String empresa;
    @Column(name="EMPRESA_ID", nullable = false)
    private Long empresaId;
    @Column(name="ESTADO_CORRIDA")
    private String estadoCorrida;
    @Column(name="ESTADO_TARJETA_VIAJE")
    private String estadoTarjetaViaje;
    private Timestamp fecha;
    @Column(name="FECHA_HORA_CORRIDA")
    private Timestamp fechaHoraCorrida;
    @Column(name="FLOTILLA_ID")
    private Long flotillaId;
    @Column(name="FOLIO_TARJETA_VIAJE")
    private String folioTarjetaViaje;
    private Timestamp hora;
    @Column(name="NOMBRE_FLOTILLA", nullable = false)
    private String nombreFlotilla;
    @Column(name="NOMBRE_OPERADOR_ORIGINAL")
    private String nombreOperadorOriginal;
    @Column(name="NOMBRE_OPERADOR_SUST")
    private String nombreOperadorSust;
    private String operador;
    @Column(name="OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Column(name="OPERADOR_ORIGINAL")
    private String operadorOriginal;
    @Column(name="OPERADOR_ORIGINAL_ID", nullable = false)
    private Long operadorOriginalId;
    private String origen;
    @Column(name="ORIGEN_ID", nullable = false)
    private Long origenId;
    private String servicio;
    @Column(name="SERVICIO_ID", nullable = false)
    private Long servicioId;
    @Column(name="TARJETA_VIAJE_ID")
    private Long tarjetaViajeId;
    @Column(name="TIPO_CORRIDA", nullable = false)
    private String tipoCorrida;
    @Column(name="RUTA_ID", nullable = false)
    private Long rutaId;

    public TmsMonitorCorridaV() {
    }

    public String getAutobus() {
        return autobus;
    }

    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public String getAutobusOriginal() {
        return autobusOriginal;
    }

    public void setAutobusOriginal(String autobusOriginal) {
        this.autobusOriginal = autobusOriginal;
    }

    public Long getAutobusOriginalId() {
        return autobusOriginalId;
    }

    public void setAutobusOriginalId(Long autobusOriginalId) {
        this.autobusOriginalId = autobusOriginalId;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public String getCorridaCancelada() {
        return corridaCancelada;
    }

    public void setCorridaCancelada(String corridaCancelada) {
        this.corridaCancelada = corridaCancelada;
    }

    public Long getCorridaCanceladaId() {
        return corridaCanceladaId;
    }

    public void setCorridaCanceladaId(Long corridaCanceladaId) {
        this.corridaCanceladaId = corridaCanceladaId;
    }

    public Long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(Long corridaId) {
        this.corridaId = corridaId;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEstadoCorrida() {
        return estadoCorrida;
    }

    public void setEstadoCorrida(String estadoCorrida) {
        this.estadoCorrida = estadoCorrida;
    }

    public String getEstadoTarjetaViaje() {
        return estadoTarjetaViaje;
    }

    public void setEstadoTarjetaViaje(String estadoTarjetaViaje) {
        this.estadoTarjetaViaje = estadoTarjetaViaje;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Timestamp getFechaHoraCorrida() {
        return fechaHoraCorrida;
    }

    public void setFechaHoraCorrida(Timestamp fechaHoraCorrida) {
        this.fechaHoraCorrida = fechaHoraCorrida;
    }

    public Long getFlotillaId() {
        return flotillaId;
    }

    public void setFlotillaId(Long flotillaId) {
        this.flotillaId = flotillaId;
    }

    public String getFolioTarjetaViaje() {
        return folioTarjetaViaje;
    }

    public void setFolioTarjetaViaje(String folioTarjetaViaje) {
        this.folioTarjetaViaje = folioTarjetaViaje;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getNombreFlotilla() {
        return nombreFlotilla;
    }

    public void setNombreFlotilla(String nombreFlotilla) {
        this.nombreFlotilla = nombreFlotilla;
    }

    public String getNombreOperadorOriginal() {
        return nombreOperadorOriginal;
    }

    public void setNombreOperadorOriginal(String nombreOperadorOriginal) {
        this.nombreOperadorOriginal = nombreOperadorOriginal;
    }

    public String getNombreOperadorSust() {
        return nombreOperadorSust;
    }

    public void setNombreOperadorSust(String nombreOperadorSust) {
        this.nombreOperadorSust = nombreOperadorSust;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public String getOperadorOriginal() {
        return operadorOriginal;
    }

    public void setOperadorOriginal(String operadorOriginal) {
        this.operadorOriginal = operadorOriginal;
    }

    public Long getOperadorOriginalId() {
        return operadorOriginalId;
    }

    public void setOperadorOriginalId(Long operadorOriginalId) {
        this.operadorOriginalId = operadorOriginalId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }
    
    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public Long getTarjetaViajeId() {
        return tarjetaViajeId;
    }

    public void setTarjetaViajeId(Long tarjetaViajeId) {
        this.tarjetaViajeId = tarjetaViajeId;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }
}