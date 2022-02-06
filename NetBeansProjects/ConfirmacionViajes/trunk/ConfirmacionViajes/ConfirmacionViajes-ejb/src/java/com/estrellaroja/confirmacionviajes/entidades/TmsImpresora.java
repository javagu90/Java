/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author EKS Victor
 */
@Entity
@Table(name = "TMS_IMPRESORAS_TBL")
@NamedStoredProcedureQuery(name = "Impresora.findByPrefijo",
    procedureName = "TMS_CONFIRMACION_VIAJE_PKG.TMS_BUSCAR_IMPRESORA_SP",
    resultClass = TmsImpresora.class,
    parameters = {
      @StoredProcedureParameter(queryParameter = "NOMBRE_CAJA", name = "nombre_caja"),
      @StoredProcedureParameter(queryParameter = "RESULT_CURSOR", name = "result", direction = Direction.OUT_CURSOR)
    })
public class TmsImpresora implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "IMPRESORA_ID")
  private BigDecimal impresoraId;
  @Basic(optional = false)
  @Column(name = "IMPRESORA_NOMBRE")
  private String impresoraNombre;
  @Column(name = "PUERTO")
  private String puerto;
  @Column(name = "FORMATO")
  private String formato;
  @Column(name = "FORMATO_NOMBRE")
  private String formatoNombre;


  public TmsImpresora() {
  }

  public TmsImpresora(BigDecimal impresoraId) {
    this.impresoraId = impresoraId;
  }

  public TmsImpresora(BigDecimal impresoraId, String impresoraNombre, String puerto, String formato) {
    this.impresoraId = impresoraId;
    this.impresoraNombre = impresoraNombre;
    this.puerto = puerto;
    this.formato = formato;
  }

  public BigDecimal getImpresoraId() {
    return impresoraId;
  }

  public void setImpresoraId(BigDecimal impresoraId) {
    this.impresoraId = impresoraId;
  }

  public String getImpresoraNombre() {
    return impresoraNombre;
  }

  public void setImpresoraNombre(String impresoraNombre) {
    this.impresoraNombre = impresoraNombre;
  }

  public void setPuerto(String puerto) {
    this.puerto = puerto;
  }

  public String getPuerto() {
    return puerto;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public String getFormato() {
    return formato;
  }


  @Override
  public int hashCode() {
    int hash = 0;
    hash += (impresoraId != null ? impresoraId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TmsImpresora)) {
      return false;
    }
    TmsImpresora other = (TmsImpresora) object;
    if ((this.impresoraId == null && other.impresoraId != null) || (this.impresoraId != null && !this.impresoraId.equals(other.impresoraId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.estrellaroja.confirmacionviajes.entidades.TmsImpresora[impresoraId=" + impresoraId + "]";
  }

    /**
     * @return the formatoNombre
     */
    public String getFormatoNombre() {
        return formatoNombre;
    }

    /**
     * @param formatoNombre the formatoNombre to set
     */
    public void setFormatoNombre(String formatoNombre) {
        this.formatoNombre = formatoNombre;
    }

}
