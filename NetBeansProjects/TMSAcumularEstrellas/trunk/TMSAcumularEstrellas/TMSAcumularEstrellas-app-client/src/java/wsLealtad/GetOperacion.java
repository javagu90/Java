/*
 * GetOperacion.java
 *
 * Created on 20 de enero de 2011, 05:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="getOperacion", propOrder={"numeroOperacion"})
public class GetOperacion
{
  protected String numeroOperacion;

  public String getNumeroOperacion()
  {
    return this.numeroOperacion;
  }

  public void setNumeroOperacion(String value)
  {
    this.numeroOperacion = value;
  }
}
