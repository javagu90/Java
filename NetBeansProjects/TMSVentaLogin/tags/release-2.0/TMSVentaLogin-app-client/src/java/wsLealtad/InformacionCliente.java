/*
 * InformacionCliente.java
 *
 * Created on 20 de enero de 2011, 05:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="informacionCliente", propOrder={"fechaAfiliacion", "fechaNacimiento", "fechaVencimiento", "noTarjeta", "nombre", "rfc", "saldoMoneda", "saldoPuntos", "statusTarjeta", "tipoTarjeta"})
public class InformacionCliente
{
  protected String fechaAfiliacion;
  protected String fechaNacimiento;
  protected String fechaVencimiento;
  protected String noTarjeta;
  protected String nombre;
  protected String rfc;
  protected String saldoMoneda;
  protected String saldoPuntos;
  protected String statusTarjeta;
  protected String tipoTarjeta;

  public String getFechaAfiliacion()
  {
    return this.fechaAfiliacion;
  }

  public void setFechaAfiliacion(String value)
  {
    this.fechaAfiliacion = value;
  }

  public String getFechaNacimiento()
  {
    return this.fechaNacimiento;
  }

  public void setFechaNacimiento(String value)
  {
    this.fechaNacimiento = value;
  }

  public String getFechaVencimiento()
  {
    return this.fechaVencimiento;
  }

  public void setFechaVencimiento(String value)
  {
    this.fechaVencimiento = value;
  }

  public String getNoTarjeta()
  {
    return this.noTarjeta;
  }

  public void setNoTarjeta(String value)
  {
    this.noTarjeta = value;
  }

  public String getNombre()
  {
    return this.nombre;
  }

  public void setNombre(String value)
  {
    this.nombre = value;
  }

  public String getRfc()
  {
    return this.rfc;
  }

  public void setRfc(String value)
  {
    this.rfc = value;
  }

  public String getSaldoMoneda()
  {
    return this.saldoMoneda;
  }

  public void setSaldoMoneda(String value)
  {
    this.saldoMoneda = value;
  }

  public String getSaldoPuntos()
  {
    return this.saldoPuntos;
  }

  public void setSaldoPuntos(String value)
  {
    this.saldoPuntos = value;
  }

  public String getStatusTarjeta()
  {
    return this.statusTarjeta;
  }

  public void setStatusTarjeta(String value)
  {
    this.statusTarjeta = value;
  }

  public String getTipoTarjeta()
  {
    return this.tipoTarjeta;
  }

  public void setTipoTarjeta(String value)
  {
    this.tipoTarjeta = value;
  }
}