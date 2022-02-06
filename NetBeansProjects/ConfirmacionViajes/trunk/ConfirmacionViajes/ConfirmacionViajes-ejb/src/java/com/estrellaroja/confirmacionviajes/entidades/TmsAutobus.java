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

/**
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_AUTOBUSES_TBL")
@NamedQueries({
  
  @NamedQuery(name = "TmsAutobus.findByEmpresaId", query = "SELECT t FROM TmsAutobus t WHERE t.empresaId = :empresaId and ((t.fechaHasta is null) or t.fechaHasta > CURRENT_DATE) ORDER BY t.numeroEconomico")
  })
public class TmsAutobus implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "AUTOBUS_ID")
  private BigDecimal autobusId;
  @Basic(optional = false)
  @Column(name = "NUMERO_ECONOMICO")
  private String numeroEconomico;
  @Column(name = "PLANTILLA_ENC_ID")
  private BigInteger plantillaEncId;
  @Column(name = "FLOTILLA_ID")
  private BigInteger flotillaId;
  @Column(name = "OPERADOR_ID_PLANTA")
  private BigInteger operadorIdPlanta;
  @Column(name = "KMS_RECORRIDOS")
  private BigInteger kmsRecorridos;
  @Column(name = "FECHA_MANTENIMIENTO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaMantenimiento;
  @Column(name = "COMPONENTE_1_ID")
  private BigInteger componente1Id;
  @Column(name = "COMPONENTE_2_ID")
  private BigInteger componente2Id;
  @Column(name = "COMPONENTE_3_ID")
  private BigInteger componente3Id;
  @Column(name = "FECHA_DESDE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaDesde;
  @Column(name = "FECHA_HASTA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaHasta;
  @Column(name = "KMS_ASIGNACION")
  private BigInteger kmsAsignacion;
  @Column(name = "KMS_ULTIMA_LECTURA")
  private BigInteger kmsUltimaLectura;
  @Column(name = "FECHA_ULTIMA_LECTURA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaUltimaLectura;
  @Column(name = "KMS_ULTIMO_RESET")
  private BigInteger kmsUltimoReset;
  @Column(name = "FECHA_ULTIMO_MP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaUltimoMp;
  @Column(name = "EMPRESA_ID")
  private BigInteger empresaId;
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

  public TmsAutobus() {
  }

  public TmsAutobus(BigDecimal autobusId) {
    this.autobusId = autobusId;
  }

  public TmsAutobus(BigDecimal autobusId, String numeroEconomico, Date fechaCreacion, Date ultimaFechaActualizacion) {
    this.autobusId = autobusId;
    this.numeroEconomico = numeroEconomico;
    this.fechaCreacion = fechaCreacion;
    this.ultimaFechaActualizacion = ultimaFechaActualizacion;
  }
  public TmsAutobus(BigDecimal autobusId, String numeroEconomico) {
    this.autobusId = autobusId;
    this.numeroEconomico = numeroEconomico;
  }

  public BigDecimal getAutobusId() {
    return autobusId;
  }

  public void setAutobusId(BigDecimal autobusId) {
    this.autobusId = autobusId;
  }

  public String getNumeroEconomico() {
    return numeroEconomico;
  }

  public void setNumeroEconomico(String numeroEconomico) {
    this.numeroEconomico = numeroEconomico;
  }

  public BigInteger getPlantillaEncId() {
    return plantillaEncId;
  }

  public void setPlantillaEncId(BigInteger plantillaEncId) {
    this.plantillaEncId = plantillaEncId;
  }

  public BigInteger getFlotillaId() {
    return flotillaId;
  }

  public void setFlotillaId(BigInteger flotillaId) {
    this.flotillaId = flotillaId;
  }

  public BigInteger getOperadorIdPlanta() {
    return operadorIdPlanta;
  }

  public void setOperadorIdPlanta(BigInteger operadorIdPlanta) {
    this.operadorIdPlanta = operadorIdPlanta;
  }

  public BigInteger getKmsRecorridos() {
    return kmsRecorridos;
  }

  public void setKmsRecorridos(BigInteger kmsRecorridos) {
    this.kmsRecorridos = kmsRecorridos;
  }

  public Date getFechaMantenimiento() {
    return fechaMantenimiento;
  }

  public void setFechaMantenimiento(Date fechaMantenimiento) {
    this.fechaMantenimiento = fechaMantenimiento;
  }

  public BigInteger getComponente1Id() {
    return componente1Id;
  }

  public void setComponente1Id(BigInteger componente1Id) {
    this.componente1Id = componente1Id;
  }

  public BigInteger getComponente2Id() {
    return componente2Id;
  }

  public void setComponente2Id(BigInteger componente2Id) {
    this.componente2Id = componente2Id;
  }

  public BigInteger getComponente3Id() {
    return componente3Id;
  }

  public void setComponente3Id(BigInteger componente3Id) {
    this.componente3Id = componente3Id;
  }

  public Date getFechaDesde() {
    return fechaDesde;
  }

  public void setFechaDesde(Date fechaDesde) {
    this.fechaDesde = fechaDesde;
  }

  public Date getFechaHasta() {
    return fechaHasta;
  }

  public void setFechaHasta(Date fechaHasta) {
    this.fechaHasta = fechaHasta;
  }

  public BigInteger getKmsAsignacion() {
    return kmsAsignacion;
  }

  public void setKmsAsignacion(BigInteger kmsAsignacion) {
    this.kmsAsignacion = kmsAsignacion;
  }

  public BigInteger getKmsUltimaLectura() {
    return kmsUltimaLectura;
  }

  public void setKmsUltimaLectura(BigInteger kmsUltimaLectura) {
    this.kmsUltimaLectura = kmsUltimaLectura;
  }

  public Date getFechaUltimaLectura() {
    return fechaUltimaLectura;
  }

  public void setFechaUltimaLectura(Date fechaUltimaLectura) {
    this.fechaUltimaLectura = fechaUltimaLectura;
  }

  public BigInteger getKmsUltimoReset() {
    return kmsUltimoReset;
  }

  public void setKmsUltimoReset(BigInteger kmsUltimoReset) {
    this.kmsUltimoReset = kmsUltimoReset;
  }

  public Date getFechaUltimoMp() {
    return fechaUltimoMp;
  }

  public void setFechaUltimoMp(Date fechaUltimoMp) {
    this.fechaUltimoMp = fechaUltimoMp;
  }

  public BigInteger getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(BigInteger empresaId) {
    this.empresaId = empresaId;
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

  @Override
  public int hashCode() {
    return numeroEconomico.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if(object instanceof String){
      return numeroEconomico.equalsIgnoreCase(object.toString());
    }
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsAutobus)) {
      return false;
    }
    TmsAutobus other = (TmsAutobus) object;
    if (numeroEconomico.equalsIgnoreCase(other.numeroEconomico)) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return numeroEconomico;
  }

}
