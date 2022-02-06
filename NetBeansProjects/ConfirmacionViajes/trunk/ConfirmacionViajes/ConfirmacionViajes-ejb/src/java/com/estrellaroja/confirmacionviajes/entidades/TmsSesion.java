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
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_SESIONES_GLOBAL_TBL")
@NamedStoredProcedureQuery(name = "findSession",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_FIND_SESION",
  resultClass = TmsSesion.class,
  parameters = {
    @StoredProcedureParameter(queryParameter = "sessionnumber", name = "sessionnumber"),
    @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
  })
public class TmsSesion implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "NUMERO_SESION")
  private BigDecimal numeroSesion;
  @Basic(optional = false)
  @Column(name = "FECHA_INICIO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaInicio;
  @Column(name = "FECHA_FIN")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaFin;
  @Basic(optional = false)
  @Column(name = "ESTADO_SESION")
  private String estadoSesion;
  @Column(name = "NOMBRE_EQUIPO")
  private String nombreEquipo;
  @Column(name = "DIRECCION_IP")
  private String direccionIp;
  @Column(name = "REPLICACION_ESTADO")
  private String replicacionEstado;
  @Column(name = "REPLICACION_INTENTOS")
  private BigInteger replicacionIntentos;
  @Column(name = "REPLICACION_ORIGEN")
  private String replicacionOrigen;

  public TmsSesion() {
  }

  public TmsSesion(BigDecimal numeroSesion) {
    this.numeroSesion = numeroSesion;
  }

  public TmsSesion(BigDecimal numeroSesion, Date fechaInicio, String estadoSesion) {
    this.numeroSesion = numeroSesion;
    this.fechaInicio = fechaInicio;
    this.estadoSesion = estadoSesion;
  }

  public BigDecimal getNumeroSesion() {
    return numeroSesion;
  }

  public void setNumeroSesion(BigDecimal numeroSesion) {
    this.numeroSesion = numeroSesion;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(Date fechaFin) {
    this.fechaFin = fechaFin;
  }

  public String getEstadoSesion() {
    return estadoSesion;
  }

  public void setEstadoSesion(String estadoSesion) {
    this.estadoSesion = estadoSesion;
  }

  public String getNombreEquipo() {
    return nombreEquipo;
  }

  public void setNombreEquipo(String nombreEquipo) {
    this.nombreEquipo = nombreEquipo;
  }

  public String getDireccionIp() {
    return direccionIp;
  }

  public void setDireccionIp(String direccionIp) {
    this.direccionIp = direccionIp;
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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (numeroSesion != null ? numeroSesion.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsSesion)) {
      return false;
    }
    TmsSesion other = (TmsSesion) object;
    if ((this.numeroSesion == null && other.numeroSesion != null) || (this.numeroSesion != null && !this.numeroSesion.equals(other.numeroSesion))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.estrellaroja.confirmacionviajes.entidades.TmsSesionesGlobalTbl[numeroSesion=" + numeroSesion + "]";
  }

}
