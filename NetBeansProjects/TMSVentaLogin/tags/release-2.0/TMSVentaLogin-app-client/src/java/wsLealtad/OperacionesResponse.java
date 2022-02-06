/*
 * OperacionesResponse.java
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
@XmlType(name="operacionesResponse", propOrder={"puntos", "status"})
public class OperacionesResponse
{
  protected Puntos puntos;
  protected StatusType status;

  public Puntos getPuntos()
  {
    return this.puntos;
  }

  public void setPuntos(Puntos value)
  {
    this.puntos = value;
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