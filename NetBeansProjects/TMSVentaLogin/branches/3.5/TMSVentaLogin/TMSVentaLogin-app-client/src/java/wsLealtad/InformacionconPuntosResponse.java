/*
 * InformacionconPuntosResponse.java
 *
 * Created on 20 de enero de 2011, 05:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="informacionconPuntosResponse", propOrder={"informacionCliente", "status"})
public class InformacionconPuntosResponse
{
  protected InformacionCliente informacionCliente;
  protected StatusType status;

  public InformacionCliente getInformacionCliente()
  {
    return this.informacionCliente;
  }

  public void setInformacionCliente(InformacionCliente value)
  {
    this.informacionCliente = value;
  }

  public StatusType getStatus()
  {
    return this.status;
  }

  public void setStatus(StatusType value)
  {
    this.status = value;
  }
}