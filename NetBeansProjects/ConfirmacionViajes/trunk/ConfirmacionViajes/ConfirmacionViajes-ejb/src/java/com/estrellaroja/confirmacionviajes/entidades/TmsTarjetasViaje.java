/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author EKS Victor
 */
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(name = "findByFolio",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TARJETAS_VIAJE_POR_FOLIO",
  resultClass = TmsTarjetasViaje.class,
  parameters = {
    @StoredProcedureParameter(queryParameter = "folio", name = "folio"),
    @StoredProcedureParameter(queryParameter = "origen", name = "origen"),
    @StoredProcedureParameter(queryParameter = "dblink", name = "dblink"),
    @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
  }),
  @NamedStoredProcedureQuery(name = "findByOperador",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TARJETAS_VIAJE_POR_OPERADOR",
  resultClass = TmsTarjetasViaje.class,
  parameters = {
    @StoredProcedureParameter(queryParameter = "clave", name = "clave"),
    @StoredProcedureParameter(queryParameter = "origen", name = "origen"),
    @StoredProcedureParameter(queryParameter = "dblink", name = "dblink"),
    @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
  }),
  @NamedStoredProcedureQuery(name = "confirmarTarjeta",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.CONFIRMAR_TARJETA",
  resultClass=void.class,returnsResultSet = false,
  parameters = {
    @StoredProcedureParameter(queryParameter = "tarjeta_id", name = "tarjeta_id"),
    @StoredProcedureParameter(queryParameter = "usuario_id", name = "usuario_id"),
    @StoredProcedureParameter(queryParameter = "origen", name = "origen"),
    @StoredProcedureParameter(queryParameter = "clave_operador", name = "clave_operador"),
    @StoredProcedureParameter(queryParameter = "clave_autobus", name = "clave_autobus"),
    @StoredProcedureParameter(queryParameter = "boletos_venta_manual", name = "boletos_venta_manual"),
    @StoredProcedureParameter(queryParameter = "importe_venta_manual", name = "importe_venta_manual"),
    @StoredProcedureParameter(queryParameter = "return_code", name = "return_code", direction = Direction.OUT),
    @StoredProcedureParameter(queryParameter = "error_message", name = "error_message", direction = Direction.OUT)
  }),
  @NamedStoredProcedureQuery(name = "getValues",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_GET_VALORES_SP",
//  resultClass=void.class,returnsResultSet = false,
  parameters = {
    @StoredProcedureParameter(queryParameter = "tarjeta_id", name = "tarjeta_id"),
    @StoredProcedureParameter(queryParameter = "OPERADOR", name = "operador"),
    @StoredProcedureParameter(queryParameter = "SUELDO", name = "sueldo", direction = Direction.OUT),
    @StoredProcedureParameter(queryParameter = "RETENCION", name = "retencion", direction=Direction.OUT),
    @StoredProcedureParameter(queryParameter = "PAGOOPERADOR", name = "pagooperador", direction = Direction.OUT),
    @StoredProcedureParameter(queryParameter = "saldo_operador", name = "saldo_operador", direction= Direction.OUT)
  })
})

@Entity
@Table(name = "TMS_TARJETAS_VIAJE_TBL", catalog = "")

public class TmsTarjetasViaje implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "TARJETA_VIAJE_ID")
  private BigInteger tarjetaViajeId;
  @Basic(optional = false)
  @Column(name = "FOLIO_TARJETA")
  private String folioTarjeta;
  @Column(name = "CLAVE_CORRIDA")
  private String claveCorrida;
  @Column(name = "FECHA_HORA_CORRIDA")
  private Timestamp fechaHoraCorrida;
  @Column(name = "ESTADO_TARJETA")
  private String estadoTarjeta;
  @Column(name = "CLAVE_OPERADOR")
  private String claveOperador;
  @Column(name = "OPERADOR_NOMBRE_COMPLETO")
  private String nombreOperador;
  @Column(name = "AUTOBUS")
  private String autobus;
  @Column(name = "SERVICIO")
  private String servicio;
  @Column(name = "EMPRESA_ID")
  private BigInteger empresa;
  @Column(name = "EMPRESA_NOMBRE")
  private String empresaNombre;
  @Column(name = "ORIGEN")
  private String origen;
  @Column(name = "DESTINO")
  private String destino;
  @Column(name = "BOLETOS_VENTA_MANUAL")
  private String boletosVentaManual;
  @Column(name = "IMPORTE_VENTA_MANUAL")
  private String importeVentaManual;
  @Column(name = "RECAUDACION_AUTOMATICA")
  private String recaudacionAutomatica;
 
  
  @ManyToOne(optional = false)
  private TmsUsuario tmsUsuarios;
  @JoinColumn(name = "CREADO_POR", referencedColumnName = "USUARIO_ID")
  @ManyToOne(optional = false)
  private TmsUsuario tmsUsuarios1;

  public TmsTarjetasViaje() {
  }

  public TmsTarjetasViaje(BigInteger tarjetaViajeId) {
    this.tarjetaViajeId = tarjetaViajeId;
  }

  public TmsTarjetasViaje(BigInteger tarjetaViajeId, String folioTarjeta, String claveCorrida, Timestamp fechaHoraCorrida, String estadoTarjeta, String claveOperador, String nombreOperador, String autobus, String servicio, BigInteger empresa, String empresaNombre, String origen, String destino, String boletosVentaManual, String importeVentaManual, String recaudacionAutomatica, TmsUsuario tmsUsuarios, TmsUsuario tmsUsuarios1) {
    this.tarjetaViajeId = tarjetaViajeId;
    this.folioTarjeta = folioTarjeta;
    this.claveCorrida = claveCorrida;
    this.fechaHoraCorrida = fechaHoraCorrida;
    this.estadoTarjeta = estadoTarjeta;
    this.claveOperador = claveOperador;
    this.nombreOperador = nombreOperador;
    this.autobus = autobus;
    this.servicio = servicio;
    this.empresa = empresa;
    this.empresaNombre = empresaNombre;
    this.origen = origen;
    this.destino = destino;
    this.boletosVentaManual = boletosVentaManual;
    this.importeVentaManual = importeVentaManual;
    this.recaudacionAutomatica = recaudacionAutomatica;
    this.tmsUsuarios = tmsUsuarios;
    this.tmsUsuarios1 = tmsUsuarios1;
  }

  public TmsTarjetasViaje(BigInteger tarjetaViajeId, String folioTarjeta, String claveCorrida, Timestamp fechaHoraCorrida, String estadoTarjeta, String claveOperador, String nombreOperador, String autobus, String servicio, BigInteger empresa, String empresaNombre, String origen, String destino, String boletosVentaManual, String importeVentaManual, String recaudacionAutomatica) {
    this.tarjetaViajeId = tarjetaViajeId;
    this.folioTarjeta = folioTarjeta;
    this.claveCorrida = claveCorrida;
    this.fechaHoraCorrida = fechaHoraCorrida;
    this.estadoTarjeta = estadoTarjeta;
    this.claveOperador = claveOperador;
    this.nombreOperador = nombreOperador;
    this.autobus = autobus;
    this.servicio = servicio;
    this.empresa = empresa;
    this.empresaNombre = empresaNombre;
    this.origen = origen;
    this.destino = destino;
    this.boletosVentaManual = boletosVentaManual;
    this.importeVentaManual = importeVentaManual;
    this.recaudacionAutomatica = recaudacionAutomatica;
  }

  public BigInteger getTarjetaViajeId() {
    return tarjetaViajeId;
  }

  public void setTarjetaViajeId(BigInteger tarjetaViajeId) {
    this.tarjetaViajeId = tarjetaViajeId;
  }

  public String getFolioTarjeta() {
    return folioTarjeta;
  }

  public void setFolioTarjeta(String folioTarjeta) {
    this.folioTarjeta = folioTarjeta;
  }

  public String getClaveCorrida() {
    return claveCorrida;
  }

  public void setClaveCorrida(String claveCorrida) {
    this.claveCorrida = claveCorrida;
  }

  public Timestamp getFechaHoraCorrida() {
    return fechaHoraCorrida;
  }

  public void setFechaHoraCorrida(Timestamp fechaHoraCorrida) {
    this.fechaHoraCorrida = fechaHoraCorrida;
  }

  public String getClaveOperador() {
    return claveOperador;
  }

  public void setClaveOperador(String claveOperador) {
    this.claveOperador = claveOperador;
  }

  public String getNombreOperador() {
    return nombreOperador;
  }

  public void setNombreOperador(String nombreOperador) {
    this.nombreOperador = nombreOperador;
  }

  public String getAutobus() {
    return autobus;
  }

  public void setAutobus(String autobus) {
    this.autobus = autobus;
  }

  public String getServicio() {
    return servicio;
  }

  public void setServicio(String servicio) {
    this.servicio = servicio;
  }

  public BigInteger getEmpresa() {
    return empresa;
  }

  public void setEmpresa(BigInteger empresa) {
    this.empresa = empresa;
  }

  public void setEmpresaNombre(String empresaNombre) {
    this.empresaNombre = empresaNombre;
  }

  public String getEmpresaNombre() {
    return empresaNombre;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public String getBoletosVentaManual() {
    return boletosVentaManual;
  }

  public void setBoletosVentaManual(String boletosVentaManual) {
    this.boletosVentaManual = boletosVentaManual;
  }

  public String getImporteVentaManual() {
    return importeVentaManual;
  }

  public void setImporteVentaManual(String importeVentaManual) {
    this.importeVentaManual = importeVentaManual;
  }

  public void setRecaudacionAutomatica(String recaudacionAutomatica) {
    this.recaudacionAutomatica = recaudacionAutomatica;
  }

  public String getRecaudacionAutomatica() {
    return recaudacionAutomatica;
  }

  public String getEstadoTarjeta() {
    return estadoTarjeta;
  }

  public void setEstadoTarjeta(String estadoTarjeta) {
    this.estadoTarjeta = estadoTarjeta;
  }

  public TmsUsuario getTmsUsuarios() {
    return tmsUsuarios;
  }

  public void setTmsUsuarios(TmsUsuario tmsUsuarios) {
    this.tmsUsuarios = tmsUsuarios;
  }

  public TmsUsuario getTmsUsuarios1() {
    return tmsUsuarios1;
  }

  public void setTmsUsuarios1(TmsUsuario tmsUsuarios1) {
    this.tmsUsuarios1 = tmsUsuarios1;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (tarjetaViajeId != null ? tarjetaViajeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsTarjetasViaje)) {
      return false;
    }
    TmsTarjetasViaje other = (TmsTarjetasViaje) object;
    if ((this.tarjetaViajeId == null && other.tarjetaViajeId != null) || (this.tarjetaViajeId != null && !this.tarjetaViajeId.equals(other.tarjetaViajeId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje[tarjetaViajeId=" + tarjetaViajeId + "]";
  }

}
