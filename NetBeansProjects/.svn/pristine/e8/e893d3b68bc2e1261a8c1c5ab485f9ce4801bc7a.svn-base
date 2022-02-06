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
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_OPERADORES_TBL")
@NamedQueries({
  @NamedQuery(name = "TmsOperador.findByEmpresa",
  query = "SELECT t FROM TmsOperador t WHERE t.empresaId = :empresaId and t.habilitado = 'S' and ((t.fechaBaja is null) or t.fechaBaja > CURRENT_DATE) ORDER BY t.claveOperador")
})
public class TmsOperador implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "OPERADOR_ID")
  private BigDecimal operadorId;
  @Basic(optional = false)
  @Column(name = "CLAVE_OPERADOR")
  private String claveOperador;
  @Basic(optional = false)
  @Column(name = "DIA_ACTIVO")
  private BigInteger diaActivo;
  @Column(name = "ACCION4_ID")
  private BigInteger accion4Id;
  @Basic(optional = false)
  @Column(name = "HABILITADO")
  private String habilitado;
  @Column(name = "OPERADOR_NOMBRE")
  private String operadorNombre;
  @Column(name = "OPERADOR_NOMBRE_MEDIO")
  private String operadorNombreMedio;
  @Column(name = "OPERADOR_APELLIDO")
  private String operadorApellido;
  @Column(name = "OPERADOR_NOMBRE_COMPLETO")
  private String operadorNombreCompleto;
  @Column(name = "FECHA_ALTA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaAlta;
  @Column(name = "FECHA_BAJA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaBaja;
  @Column(name = "APLICA_RETENCION")
  private String aplicaRetencion;
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
  @Column(name = "EMPRESA_ID")
  private BigInteger empresaId;
  @Column(name = "TIPO_SERVICIO")
  private String tipoServicio;
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
  @OneToMany(mappedBy = "tmsOperador")
  private Collection<TmsOperador> tmsOperadorCollection;
  @JoinColumn(name = "OPERADOR_ID_FIJO", referencedColumnName = "OPERADOR_ID")
  @ManyToOne
  private TmsOperador tmsOperador;

  public TmsOperador() {
  }

  public TmsOperador(BigDecimal operadorId) {
    this.operadorId = operadorId;
  }

  public TmsOperador(BigDecimal operadorId, String claveOperador, BigInteger diaActivo, String habilitado, Date fechaCreacion, Date ultimaFechaActualizacion) {
    this.operadorId = operadorId;
    this.claveOperador = claveOperador;
    this.diaActivo = diaActivo;
    this.habilitado = habilitado;
    this.fechaCreacion = fechaCreacion;
    this.ultimaFechaActualizacion = ultimaFechaActualizacion;
  }

  public BigDecimal getOperadorId() {
    return operadorId;
  }

  public void setOperadorId(BigDecimal operadorId) {
    this.operadorId = operadorId;
  }

  public String getClaveOperador() {
    return claveOperador;
  }

  public void setClaveOperador(String claveOperador) {
    this.claveOperador = claveOperador;
  }

  public BigInteger getDiaActivo() {
    return diaActivo;
  }

  public void setDiaActivo(BigInteger diaActivo) {
    this.diaActivo = diaActivo;
  }

  public BigInteger getAccion4Id() {
    return accion4Id;
  }

  public void setAccion4Id(BigInteger accion4Id) {
    this.accion4Id = accion4Id;
  }

  public String getHabilitado() {
    return habilitado;
  }

  public void setHabilitado(String habilitado) {
    this.habilitado = habilitado;
  }

  public String getOperadorNombre() {
    return operadorNombre;
  }

  public void setOperadorNombre(String operadorNombre) {
    this.operadorNombre = operadorNombre;
  }

  public String getOperadorNombreMedio() {
    return operadorNombreMedio;
  }

  public void setOperadorNombreMedio(String operadorNombreMedio) {
    this.operadorNombreMedio = operadorNombreMedio;
  }

  public String getOperadorApellido() {
    return operadorApellido;
  }

  public void setOperadorApellido(String operadorApellido) {
    this.operadorApellido = operadorApellido;
  }

  public String getOperadorNombreCompleto() {
    return operadorNombreCompleto;
  }

  public void setOperadorNombreCompleto(String operadorNombreCompleto) {
    this.operadorNombreCompleto = operadorNombreCompleto;
  }

  public Date getFechaAlta() {
    return fechaAlta;
  }

  public void setFechaAlta(Date fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  public Date getFechaBaja() {
    return fechaBaja;
  }

  public void setFechaBaja(Date fechaBaja) {
    this.fechaBaja = fechaBaja;
  }

  public String getAplicaRetencion() {
    return aplicaRetencion;
  }

  public void setAplicaRetencion(String aplicaRetencion) {
    this.aplicaRetencion = aplicaRetencion;
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

  public BigInteger getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(BigInteger empresaId) {
    this.empresaId = empresaId;
  }

  public String getTipoServicio() {
    return tipoServicio;
  }

  public void setTipoServicio(String tipoServicio) {
    this.tipoServicio = tipoServicio;
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

  public Collection<TmsOperador> getTmsOperadorCollection() {
    return tmsOperadorCollection;
  }

  public void setTmsOperadorCollection(Collection<TmsOperador> tmsOperadorCollection) {
    this.tmsOperadorCollection = tmsOperadorCollection;
  }

  public TmsOperador getTmsOperador() {
    return tmsOperador;
  }

  public void setTmsOperador(TmsOperador tmsOperador) {
    this.tmsOperador = tmsOperador;
  }

  @Override
  public int hashCode() {
    return claveOperador.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if(object instanceof String){
      return claveOperador.equalsIgnoreCase(object.toString());
    }
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsOperador)) {
      return false;
    }
    TmsOperador other = (TmsOperador) object;
    return claveOperador.equalsIgnoreCase(other.claveOperador);
  }

  @Override
  public String toString() {
    return claveOperador+" "+operadorNombreCompleto;
  }

}
