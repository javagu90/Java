/*
 * ImporteSuficienteResponse.java
 *
 * Created on 20 de enero de 2011, 05:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="importeSuficienteResponse", propOrder={"_return"})
public class ImporteSuficienteResponse
{

  @XmlElement(name="return")
  protected Boolean _return;

  public Boolean isReturn()
  {
    return this._return;
  }

  public void setReturn(Boolean value)
  {
    this._return = value;
  }
}
