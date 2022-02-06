/*
 * StatusType.java
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
@XmlType(name="statusType", propOrder={"code", "message", "success"})
public class StatusType
{
  protected int code;
  protected String message;
  protected boolean success;

  public int getCode()
  {
    return this.code;
  }

  public void setCode(int value)
  {
    this.code = value;
  }

  public String getMessage()
  {
    return this.message;
  }

  public void setMessage(String value)
  {
    this.message = value;
  }

  public boolean isSuccess()
  {
    return this.success;
  }

  public void setSuccess(boolean value)
  {
    this.success = value;
  }
}
