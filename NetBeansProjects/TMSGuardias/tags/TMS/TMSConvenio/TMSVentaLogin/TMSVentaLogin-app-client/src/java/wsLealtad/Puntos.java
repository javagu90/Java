/*
 * Puntos.java
 *
 * Created on 20 de enero de 2011, 05:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="puntos", propOrder={"puntosOtorgados", "saldoMoneda", "saldoPuntos"})
public class Puntos
{
  protected String puntosOtorgados;
  protected String saldoMoneda;
  protected String saldoPuntos;

  public String getPuntosOtorgados()
  {
    return this.puntosOtorgados;
  }

  public void setPuntosOtorgados(String value)
  {
    this.puntosOtorgados = value;
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
}