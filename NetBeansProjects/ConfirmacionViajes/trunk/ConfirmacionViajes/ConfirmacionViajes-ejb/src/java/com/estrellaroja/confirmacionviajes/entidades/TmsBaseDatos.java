/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_BASE_DATOS_CONFIG_TBL")
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(name = "findByPrefijo",
    procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_FIND_TERMINAL_PRC",
    resultClass = TmsBaseDatos.class,
    parameters = {
      @StoredProcedureParameter(queryParameter = "prefijo", name = "prefijo"),
      @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
    }),
  @NamedStoredProcedureQuery(name = "findByTerminal",
    procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_FIND_BY_TERMINAL_PRC",
    resultClass = TmsBaseDatos.class,
    parameters = {
      @StoredProcedureParameter(queryParameter = "origen", name = "origen"),
      @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
    }),
  @NamedStoredProcedureQuery(name = "findAll",
    procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_ALL_TERMINALS_PRC",
    resultClass = TmsBaseDatos.class,
    parameters = {
      @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
    })
})
public class TmsBaseDatos implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "BDCONFIG_ID")
  private BigDecimal bdconfigId;
  @Column(name = "NOMBRE_ESQUEMA")
  private String nombreEsquema;
  @Basic(optional = false)
  @Column(name = "TERMINAL_ID")
  private BigInteger terminalId;
  @Basic(optional = false)
  @Column(name = "NOMBRE_TERMINAL")
  private String nombreTerminal;
  @Column(name = "NOMBRE_DBLINK")
  private String nombreDblink;
  @Column(name = "ESQUEMA_PROPIO")
  private String esquemaPropio;
  @Basic(optional = false)
  @Column(name = "HABILITADO")
  private String habilitado;
  @Basic(optional = false)
  @Column(name = "FECHA_CREACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;
  @Basic(optional = false)
  @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ultimaFechaActualizacion;
  @Column(name = "REPLICACION_ESTADO")
  private String replicacionEstado;
  @Column(name = "REPLICACION_INTENTOS")
  private BigInteger replicacionIntentos;
  @Column(name = "REPLICACION_ORIGEN")
  private String replicacionOrigen;
  @Column(name = "FOLIADO_VALIDACION")
  private String foliadoValidacion;

  public TmsBaseDatos() {
  }

  public TmsBaseDatos(BigDecimal bdconfigId) {
    this.bdconfigId = bdconfigId;
  }

  public TmsBaseDatos(BigDecimal bdconfigId, String nombreTerminal, String habilitado, Date fechaCreacion, Date ultimaFechaActualizacion) {
    this.bdconfigId = bdconfigId;
    this.nombreTerminal = nombreTerminal;
    this.habilitado = habilitado;
    this.fechaCreacion = fechaCreacion;
    this.ultimaFechaActualizacion = ultimaFechaActualizacion;
  }

  public BigDecimal getBdconfigId() {
    return bdconfigId;
  }

  public void setBdconfigId(BigDecimal bdconfigId) {
    this.bdconfigId = bdconfigId;
  }

  public String getNombreEsquema() {
    return nombreEsquema;
  }

  public void setNombreEsquema(String nombreEsquema) {
    this.nombreEsquema = nombreEsquema;
  }

  public BigInteger getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(BigInteger terminalId) {
    this.terminalId = terminalId;
  }

  public String getNombreTerminal() {
    return nombreTerminal;
  }

  public void setNombreTerminal(String nombreTerminal) {
    this.nombreTerminal = nombreTerminal;
  }

  public String getNombreDblink() {
    return nombreDblink;
  }

  public void setNombreDblink(String nombreDblink) {
    this.nombreDblink = nombreDblink;
  }

  public String getEsquemaPropio() {
    return esquemaPropio;
  }

  public void setEsquemaPropio(String esquemaPropio) {
    this.esquemaPropio = esquemaPropio;
  }

  public String getHabilitado() {
    return habilitado;
  }

  public void setHabilitado(String habilitado) {
    this.habilitado = habilitado;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Date getUltimaFechaActualizacion() {
    return ultimaFechaActualizacion;
  }

  public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
    this.ultimaFechaActualizacion = ultimaFechaActualizacion;
  }

  public String getReplicacionEstado() {
    return replicacionEstado;
  }

  public void setReplicacionEstado(String replicacionEstado) {
    this.replicacionEstado = replicacionEstado;
  }

  public BigInteger getReplicacionIntentos() {
    return replicacionIntentos;
  }

  public void setReplicacionIntentos(BigInteger replicacionIntentos) {
    this.replicacionIntentos = replicacionIntentos;
  }

  public String getReplicacionOrigen() {
    return replicacionOrigen;
  }

  public void setReplicacionOrigen(String replicacionOrigen) {
    this.replicacionOrigen = replicacionOrigen;
  }

  public String getFoliadoValidacion() {
    return foliadoValidacion;
  }

  public void setFoliadoValidacion(String foliadoValidacion) {
    this.foliadoValidacion = foliadoValidacion;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (bdconfigId != null ? bdconfigId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsBaseDatos)) {
      return false;
    }
    TmsBaseDatos other = (TmsBaseDatos) object;
    if ((this.bdconfigId == null && other.bdconfigId != null) || (this.bdconfigId != null && !this.bdconfigId.equals(other.bdconfigId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return this.nombreTerminal;
  }

}
