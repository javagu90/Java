/*
 * GetInformacionPuntos.java
 *
 * Created on 20 de enero de 2011, 05:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="getInformacionPuntos", propOrder={"noTarjeta"})
public class GetInformacionPuntos
{
  protected String noTarjeta;

  public String getNoTarjeta()
  {
    return this.noTarjeta;
  }

  public void setNoTarjeta(String value)
  {
    this.noTarjeta = value;
  }
}
