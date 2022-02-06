/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_USUARIOS_TBL", catalog = "")
@NamedStoredProcedureQuery(name = "findUsuario",
  procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_FIND_USUARIO_PRC",
  resultClass = TmsUsuario.class,
  parameters = {
    @StoredProcedureParameter(queryParameter = "usuario", name = "usuario"),
    @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
  })
public class TmsUsuario implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "USUARIO_ID")
  private BigDecimal usuarioId;
  @Column(name = "USUARIO_NUMERO")
  private String usuarioNumero;
  @Basic(optional = false)
  @Column(name = "USUARIO_NOMBRE")
  private String usuarioNombre;
  @Column(name = "DESCRIPCION")
  private String descripcion;
  @Column(name = "UBICACION_ID")
  private BigInteger ubicacionId;
  @Basic(optional = false)
  @Column(name = "FECHA_INICIAL")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaInicial;
  @Column(name = "FECHA_FINAL")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaFinal;
  @Basic(optional = false)
  @Column(name = "CONTRASENA_ENCRIPTADA")
  private String contrasenaEncriptada;
  @Column(name = "CONTRASENA_FECHA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date contrasenaFecha;
  @Column(name = "CONTRASENA_ACCESOS_FALTAN")
  private BigInteger contrasenaAccesosFaltan;
  @Column(name = "CONTRASENA_LIMITE_DIAS")
  private BigInteger contrasenaLimiteDias;
  @Column(name = "CONTRASENA_LIMITE_ACCESOS")
  private BigInteger contrasenaLimiteAccesos;
  @Column(name = "CORREO_ELECTRONICO")
  private String correoElectronico;
  @Column(name = "ESTADO")
  private String estado;
  @Column(name = "CONTADOR_INTENTOS")
  private BigInteger contadorIntentos;
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tmsUsuarios")
  private Collection<TmsTarjetasViaje> tmsTarjetasViajeCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tmsUsuarios1")
  private Collection<TmsTarjetasViaje> tmsTarjetasViajeCollection1;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tmsUsuarios")
  private Collection<TmsUsuario> tmsUsuariosCollection;
  @JoinColumn(name = "ULTIMA_ACTUALIZACION_POR", referencedColumnName = "USUARIO_ID")
  @ManyToOne(optional = false)
  private TmsUsuario tmsUsuarios;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tmsUsuarios1")
  private Collection<TmsUsuario> tmsUsuariosCollection1;
  @JoinColumn(name = "CREADO_POR", referencedColumnName = "USUARIO_ID")
  @ManyToOne(optional = false)
  private TmsUsuario tmsUsuarios1;
  

  public TmsUsuario() {
  }

  public TmsUsuario(BigDecimal usuarioId) {
    this.usuarioId = usuarioId;
  }

  public TmsUsuario(BigDecimal usuarioId, String usuarioNombre, Date fechaInicial, String contrasenaEncriptada, Date fechaCreacion, Date ultimaFechaActualizacion) {
    this.usuarioId = usuarioId;
    this.usuarioNombre = usuarioNombre;
    this.fechaInicial = fechaInicial;
    this.contrasenaEncriptada = contrasenaEncriptada;
    this.fechaCreacion = fechaCreacion;
    this.ultimaFechaActualizacion = ultimaFechaActualizacion;
  }

  public BigDecimal getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(BigDecimal usuarioId) {
    this.usuarioId = usuarioId;
  }

  public String getUsuarioNumero() {
    return usuarioNumero;
  }

  public void setUsuarioNumero(String usuarioNumero) {
    this.usuarioNumero = usuarioNumero;
  }

  public String getUsuarioNombre() {
    return usuarioNombre;
  }

  public void setUsuarioNombre(String usuarioNombre) {
    this.usuarioNombre = usuarioNombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public BigInteger getUbicacionId() {
    return ubicacionId;
  }

  public void setUbicacionId(BigInteger ubicacionId) {
    this.ubicacionId = ubicacionId;
  }

  public Date getFechaInicial() {
    return fechaInicial;
  }

  public void setFechaInicial(Date fechaInicial) {
    this.fechaInicial = fechaInicial;
  }

  public Date getFechaFinal() {
    return fechaFinal;
  }

  public void setFechaFinal(Date fechaFinal) {
    this.fechaFinal = fechaFinal;
  }

  public String getContrasenaEncriptada() {
    return contrasenaEncriptada;
  }

  public void setContrasenaEncriptada(String contrasenaEncriptada) {
    this.contrasenaEncriptada = contrasenaEncriptada;
  }

  public Date getContrasenaFecha() {
    return contrasenaFecha;
  }

  public void setContrasenaFecha(Date contrasenaFecha) {
    this.contrasenaFecha = contrasenaFecha;
  }

  public BigInteger getContrasenaAccesosFaltan() {
    return contrasenaAccesosFaltan;
  }

  public void setContrasenaAccesosFaltan(BigInteger contrasenaAccesosFaltan) {
    this.contrasenaAccesosFaltan = contrasenaAccesosFaltan;
  }

  public BigInteger getContrasenaLimiteDias() {
    return contrasenaLimiteDias;
  }

  public void setContrasenaLimiteDias(BigInteger contrasenaLimiteDias) {
    this.contrasenaLimiteDias = contrasenaLimiteDias;
  }

  public BigInteger getContrasenaLimiteAccesos() {
    return contrasenaLimiteAccesos;
  }

  public void setContrasenaLimiteAccesos(BigInteger contrasenaLimiteAccesos) {
    this.contrasenaLimiteAccesos = contrasenaLimiteAccesos;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public BigInteger getContadorIntentos() {
    return contadorIntentos;
  }

  public void setContadorIntentos(BigInteger contadorIntentos) {
    this.contadorIntentos = contadorIntentos;
  }

  public String getAdicional1() {
    return adicional1;
  }

  public void setAdicional1(String adicional1) {
    this.adicional1 = adicional1;
  }

  public String getAdicional2() {
    return adicional2;
  }

  public void setAdicional2(String adicional2) {
    this.adicional2 = adicional2;
  }

  public String getAdicional3() {
    return adicional3;
  }

  public void setAdicional3(String adicional3) {
    this.adicional3 = adicional3;
  }

  public String getAdicional4() {
    return adicional4;
  }

  public void setAdicional4(String adicional4) {
    this.adicional4 = adicional4;
  }

  public String getAdicional5() {
    return adicional5;
  }

  public void setAdicional5(String adicional5) {
    this.adicional5 = adicional5;
  }

  public String getAdicional6() {
    return adicional6;
  }

  public void setAdicional6(String adicional6) {
    this.adicional6 = adicional6;
  }

  public String getAdicional7() {
    return adicional7;
  }

  public void setAdicional7(String adicional7) {
    this.adicional7 = adicional7;
  }

  public String getAdicional8() {
    return adicional8;
  }

  public void setAdicional8(String adicional8) {
    this.adicional8 = adicional8;
  }

  public String getAdicional9() {
    return adicional9;
  }

  public void setAdicional9(String adicional9) {
    this.adicional9 = adicional9;
  }

  public String getAdicional10() {
    return adicional10;
  }

  public void setAdicional10(String adicional10) {
    this.adicional10 = adicional10;
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

  public Collection<TmsTarjetasViaje> getTmsTarjetasViajeCollection() {
    return tmsTarjetasViajeCollection;
  }

  public void setTmsTarjetasViajeCollection(Collection<TmsTarjetasViaje> tmsTarjetasViajeCollection) {
    this.tmsTarjetasViajeCollection = tmsTarjetasViajeCollection;
  }

  public Collection<TmsTarjetasViaje> getTmsTarjetasViajeCollection1() {
    return tmsTarjetasViajeCollection1;
  }

  public void setTmsTarjetasViajeCollection1(Collection<TmsTarjetasViaje> tmsTarjetasViajeCollection1) {
    this.tmsTarjetasViajeCollection1 = tmsTarjetasViajeCollection1;
  }

  public Collection<TmsUsuario> getTmsUsuariosCollection() {
    return tmsUsuariosCollection;
  }

  public void setTmsUsuariosCollection(Collection<TmsUsuario> tmsUsuariosCollection) {
    this.tmsUsuariosCollection = tmsUsuariosCollection;
  }

  public TmsUsuario getTmsUsuarios() {
    return tmsUsuarios;
  }

  public void setTmsUsuarios(TmsUsuario tmsUsuarios) {
    this.tmsUsuarios = tmsUsuarios;
  }

  public Collection<TmsUsuario> getTmsUsuariosCollection1() {
    return tmsUsuariosCollection1;
  }

  public void setTmsUsuariosCollection1(Collection<TmsUsuario> tmsUsuariosCollection1) {
    this.tmsUsuariosCollection1 = tmsUsuariosCollection1;
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
    hash += (usuarioId != null ? usuarioId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsUsuario)) {
      return false;
    }
    TmsUsuario other = (TmsUsuario) object;
    if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.estrellaroja.confirmacionviajes.entidades.TmsUsuarios[usuarioId=" + usuarioId + "]";
  }

}
