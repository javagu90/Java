/*
 * ImporteSuficiente.java
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
@XmlType(name="importeSuficiente", propOrder={"noTarjeta", "usuario", "usrkey", "importeACubrir"})
public class ImporteSuficiente
{
  protected String noTarjeta;
  protected long usuario;
  protected String usrkey;
  protected double importeACubrir;

  public String getNoTarjeta()
  {
    return this.noTarjeta;
  }

  public void setNoTarjeta(String value)
  {
    this.noTarjeta = value;
  }

  public long getUsuario()
  {
    return this.usuario;
  }

  public void setUsuario(long value)
  {
    this.usuario = value;
  }

  public String getUsrkey()
  {
    return this.usrkey;
  }

  public void setUsrkey(String value)
  {
    this.usrkey = value;
  }

  public double getImporteACubrir()
  {
    return this.importeACubrir;
  }

  public void setImporteACubrir(double value)
  {
    this.importeACubrir = value;
  }
}